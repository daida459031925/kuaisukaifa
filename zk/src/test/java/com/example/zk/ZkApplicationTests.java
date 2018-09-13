package com.example.zk;

import com.example.zk.bean.ZookeeperImproveLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkApplicationTests implements Runnable{

	//自增长序列
	private static int i =0;

	private static final int NUM =117;//单台服务器zk能承受最多同一时间访问117个线程
	//8G  i5 双核4线程 电脑

	private static CountDownLatch cdl = new CountDownLatch(NUM);

	private Lock lock = new ZookeeperImproveLock();
	//按照年月日时分秒-自增长序列的规则生成订单号
	public String getOrderCode(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
		return sdf.format(now)+"*"+ ++i+"*";
	}

	@Test
	public void mains() {
		ZkApplicationTests ong = new ZkApplicationTests();
		for (int i = 0; i < 10; i++) {
			System.out.println(ong.getOrderCode());
		}
	}

	//创建订单接口
	@Test
	public void contextLoads() {
		for(int i=1;i<=NUM;i++){
			new Thread(new ZkApplicationTests()).start();
			//保证所有线程同一时间启动
			cdl.countDown();
		}
	}

	public void createOrder(){
		String orderCode =null;
		lock.lock();
		try {
			//获取订单号
			orderCode = this.getOrderCode();
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		System.out.println(Thread.currentThread().getName()+"------啊实打实----->"+orderCode);

	}

	@Override
	public void run() {
		createOrder();
		try {
			//等待其他线程初始化
			cdl.await();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
