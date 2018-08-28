package bean.Multithreading.CyclicBarrier;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader17 implements Runnable{

    @Override
    public void run() {
        System.out.printf("Beginning data sources loading: %s\n",new Date());
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Data sources loading has finished: %s\n",new Date());
    }

    public static void main(String[] args) {
        DataSourcesLoader17 dataSourcesLoader17 = new DataSourcesLoader17();
        Thread thread = new Thread(dataSourcesLoader17,"DataSourceThread");
        NetworkConnectionsLoader17 networkConnectionsLoader17 = new NetworkConnectionsLoader17();
        Thread thread1 = new Thread(networkConnectionsLoader17,"NetworkConnectionLoader");
        thread.start();
        thread1.start();

        try {
            /**
             * 只有主函数 main   等待这两个线程执行结束的时候才可以打印输出mian方法里后面的内容
             */
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: configuration has been loaded: %s\n",new Date());
    }
}


class NetworkConnectionsLoader17 implements Runnable{

    @Override
    public void run() {
        System.out.printf("Beginning data sources loading: %s\n",new Date());
        try{
            TimeUnit.SECONDS.sleep(6);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Data sources loading has finished: %s\n",new Date());
    }
}