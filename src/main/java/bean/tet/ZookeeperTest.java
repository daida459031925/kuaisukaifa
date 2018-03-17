package bean.tet;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ZookeeperTest {
    private static Logger log = LoggerFactory.getLogger(ZookeeperTest.class);
    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        ZooKeeper zooKeeper
                = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",30000,new TestWatcher());
        String node ="/node2";
        Stat stat = zooKeeper.exists(node,false);

        if(null == stat){
            //创建节点
            String createResult
                    =zooKeeper.create(node,"test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.debug(createResult);
        }

        byte[] bytes = zooKeeper.getData(node,false,stat);
        log.debug(new String(bytes));
        zooKeeper.close();
    }

}

class TestWatcher implements Watcher{
    private static Logger log = LoggerFactory.getLogger(ZookeeperTest.class);
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.debug("-------------------------------------");
        log.debug("path:"+watchedEvent.getPath());
        log.debug("type:"+watchedEvent.getType());
        log.debug("state:"+watchedEvent.getState());
        log.debug("-------------------------------------");
    }
}