package com.example.zk.bean;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * java中提供的lock锁，实现此锁的目的是将以前或者老项目中
 * 使用此锁的进行兼容
 *
 * zk的内容类似与文件结构，他的内容与linux文件目录类似
 * 这样依赖就实现了当前目录下只能创建同一个名字目录的节点
 *
 * zk在服务不停访问的时候设置超时时间为1秒的时候，连接数115的时候就挂了
 * zookeeper默认对每个结点的最大数据量有一个上限是1M，如果你要设置的配置
 * 数据大于这个上限将无法写法，在网上查了一圈发现有一个解决方案如下，增加-Djute.maxbuffer=10240000参数
 * zkServer.sh      ===》   ZOO_USER_CFG="-Djute.maxbuffer=10240000"
 *
 * 单台zk服务器的并发数是100
 */

public class ZookeeperImproveLock implements Lock{

    /**这个字符串的定义是查找zk中固定位置为LOCK下是否被创建*/
    private static final String LOCK_PATH = "/LOCK";

    /**这个是zk项目路径*/
    private static final String ZOOKEEPER_IP_PORT = "localhost:2181";

    /**   ZOOKEEPER_IP_PORT：这个是提供的zk链接地址 以及端口号
     * _sessionTimeOut：当会话链接时间操过一秒的时候进行断开单位是毫秒
     * The connection timeout in milli seconds   connectionTimeout   链接操时时间
     * SerializableSerializer implements ZkSerializer目的是为了实现序列化源码中实现了序列化ObjectInputStream inputStream = new TcclAwareObjectIputStream(new ByteArrayInputStream(bytes));
     * 将字符串序列化成字节数组  此类提供序列化和反序列化*/
    private ZkClient client = new ZkClient(ZOOKEEPER_IP_PORT,1500,1500,new SerializableSerializer());

    private static Logger log = LoggerFactory.getLogger(ZookeeperImproveLock.class);

    /**CurnDutLink的同步控制使用AQS状态来表示计数.当计数器为0的时候会进行全部唤醒*/
    private CountDownLatch cdl;

    private String beforePath;//当前请求的节点
    private String currentPath;//当前请求的节点前一个节点

    //构造器 判断是否有这个LOCK目录，没有则需要创建
    public ZookeeperImproveLock(){
        //由于目录结构和系统目录结构相同那么。这个判断就和
        //文件目录判断类似this.client连接上后exists(LOCK_PATH)
        //查看是否存在 ！感叹号是进行取反
        if(!this.client.exists(LOCK_PATH)){
            this.client.createPersistent(LOCK_PATH);
        }
    }
    @Override
    public void lock() {
        /**进行上锁操作*/
        if(!tryLock()){
            waitForLock();
            lock();
        }else{
            log.info(Thread.currentThread().getName()+"获得分布式锁！");
        }
    }

    /**
     * 当前节点添加监听器
     * @return
     */
    private void waitForLock(){
        //所有没有加入的从002开始开始枷锁
        // 002的锁对象是001 001收到删除通知后实际上002的就是没有锁的对象
        // 003锁的是002这样一来就形成一个链表，但是这些数据存储在都是服务器的内存中。压力没有减轻。只是保证了
        // 服务的顺序性 线上环境使用这个的话需要配合  熔点断路使用降级策略以及rebbitmq等线性流程不然数据量太大会压垮zk/
        IZkDataListener listener = new IZkDataListener() {
            /**
             * 删除节点监听器
             * @throws Exception
             */
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(Thread.currentThread().getName()+":捕获到dataDelete事件！*******************************");
                if(cdl !=null){
                    //解放所有锁
                    cdl.countDown();
                }
            }
            /**
             * 发生改变的时候去监听
             * @param dataPath
             * @throws Exception
             */
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("-------NDSFISSFISDIFIS--------->"+dataPath);
            }
        };
        //给之前的节点添加数据删除的watcher
        //删除通知
        this.client.subscribeDataChanges(beforePath,listener);//订阅数据

        if(this.client.exists(beforePath)){
           //如果存在这个节点
            //就创建一个并让当前线程等待
           cdl = new CountDownLatch(1);
           try {
               cdl.await();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }
        this.client.unsubscribeDataChanges(beforePath,listener);//取消订阅
    }

    /**
     * @return 返还true是获得锁   返还f   就是继续等待
     */
    @Override
    public boolean tryLock() {
        //线程越多在这个地方一次性加入zk的数据量越大   这个时候并没有锁住任何东西只是开始往zk里加数据
        //并发的时候是在这里挂掉的并不是在处理数据的时候挂掉了
        //每个线程进入这里的时候相当于新创建一个这个锁。但是为什么会有的在没有创建currentPath时就有值？
        System.out.println(currentPath+"***************************************///////////////////////////////");
        /**核心业务*/
        //如果currentPath为空则为第一次尝试枷锁，第一次枷锁赋值currentPath
        if(currentPath == null || currentPath.length()<=0 ){//每次都会进入这个方法。。那么说明每次创建的
            //创建一个临时顺序节点
            currentPath = this.client.createEphemeralSequential(LOCK_PATH+"/","lock");
            //实际的原理不得而知。从日志打印出的结果分析得出100个线程cpu分配进入这个方法的时候
            //假设进入的1和2两个线程 时间片正好只给他们
            //这个时候就创建两个在zk服务器上的数据  0001和0002  这个地方不停访问服务器的时候就出现并发现象。（主要不是要知道如何解决这个并发，
            // 是要弄清楚这个实现的逻辑）
            System.out.println("-----------*------------->"+currentPath);
        }

        //获取所有临时节点并且排序，临时节点名称为自增长的字符串如：0004001
        // 在这个时候不管并发量多大这个zk服务器假设可以承受1W以上但是在这里的逻辑
        // 不管是多少第一个进来或者统一时间段内cpu都是在这里切开也就是说zk服务器在这里有1W条数据
        // 在计算过程中只会生成一条最小的假设是001/
        List<String> childrens = this.client.getChildren(LOCK_PATH);
        System.out.println(childrens.size()+"****************************");
        Collections.sort(childrens);

        /**
         * 这个时候就会将/LOCK/001和/LOCK+/+001进行对比  其他在这里没有进行排队也就是zk生成出来的数据001到10000
         * 这时候出现了  是否是最小的那一条记录
         */
        //假设当前访问001  走T   那么是正常逻辑
        if(currentPath.equals(LOCK_PATH+"/"+childrens.get(0))){
            //进行对比查找之前一个锁是否存在，既然存在存在那就证明已经创建
            //创建了就证明已经上锁了
            // 如果当前节点在所有节点中排名第一则获取锁成功
            return true;
        }else{
            //如果当前节点在所有节点中排名不是排名第一，
            // 则获取当前的节点名称，并赋值给beforePath
            //当前这个currentPath.substring(6)只支持当前程序可以做优化
            //这个位置只有并发产生这种处理方法
            System.out.println(currentPath.substring(6)+"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            //如果走的是002那么进入到这一边   这个时候就相当于在模拟队列
            //第一个001 走T   第二个002走F    走F 后那么  通过计算就要订阅一个消息  001在删除的时候我要去告诉002 你要开始工作了
            //这里的逻辑配合waitForLock
            int wz = Collections.binarySearch(childrens,currentPath.substring(6));
            beforePath = LOCK_PATH+"/"+childrens.get(wz-1);
        }
        return  false;

    }



    @Override
    public void unlock() {
        //删除请求的前一个节点
        //节点下面有内容时候是不能删除上级，可能是我操作失误
        client.delete(currentPath);
    }


    //====================用不到的实现方法===============
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
