package bean.Multithreading.CyclicBarrier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 线程的中断机制
 * 这个类的主要作用就是实现线程的一个api    中断api以及查看是否中断的api
 */
public class PrimeGenerator extends Thread{
    private Logger log = LoggerFactory.getLogger(PrimeGenerator.class);
    @Override
    public void run() {
        long number = 1L;
        while (true){
            if(isPrime(number)){
                log.info("Number %d is Prime",number);
            }
            /**
             * isInterrupted() 这个方法是查看这个线程是否被中断 相当于线程是有状态
             * 的，状态被修改后应该怎么做
             *
             *
             */
            //interrupted()同样是检测是否属性但是这个可以设置属性值
            if(isInterrupted()){
                log.info("The Prime Generator has been Interrupted");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number){
        if(number <= 2){
            return true;
        }

        for (long i=2 ; i<number; i++){
            if((number % i) == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        }catch (Exception e){
            e.printStackTrace();
        }
        /**
         * 修改线程的状态为中断  让线程中的运行强制停止
         */
        task.interrupt();
    }
}
