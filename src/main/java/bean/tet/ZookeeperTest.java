package bean.tet;

import bean.PublicLog;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;


public class ZookeeperTest implements PublicLog{
    //private static Logger log = LoggerFactory.getLogger(ZookeeperTest.class);
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        ZooKeeper zooKeeper
                = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",30000,new TestWatcher());
        String node ="/node2";
        Stat stat = zooKeeper.exists(node,false);

        if(null == stat){
            //创建节点
            String createResult
                    =zooKeeper.create(node,"test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            LOGGER.debug(createResult);
        }

        byte[] bytes = zooKeeper.getData(node,false,stat);
        LOGGER.debug(new String(bytes));
        zooKeeper.close();
    }

}

class TestWatcher implements Watcher,PublicLog{
//    private static Logger log = LoggerFactory.getLogger(ZookeeperTest.class);
    @Override
    public void process(WatchedEvent watchedEvent) {
        LOGGER.debug("-------------------------------------");
        LOGGER.debug("path:"+watchedEvent.getPath());
        LOGGER.debug("type:"+watchedEvent.getType());
        LOGGER.debug("state:"+watchedEvent.getState());
        LOGGER.debug("-------------------------------------");
    }
}