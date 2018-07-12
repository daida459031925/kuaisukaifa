package bean.Multithreading.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier {
    public static void main(String[] args) {
        int count = 100;//准备要十个线程来测试
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(count);//当创建这个数据的时候必须传入需要几个
        ExecutorService executorService=Executors.newFixedThreadPool(count);//创建线程池

        for(int i=0;i<count;i++){
            int k = i+1;
            executorService.execute(new Tack(cyclicBarrier,k));
        }

        executorService.shutdown();//当所有线程加入线程池的时候关闭线程池

        while (!executorService.isTerminated()){
            //写死循环的目的是为了让主线程等待，等待线程池里的所有线程都运行完毕。
//            System.out.println(executorService.isTerminated());
        }
        System.out.println("end_All");
    }
}

/**
 * 实现Runnable  来完成线程的run方法
 */
class Tack implements Runnable{
    private java.util.concurrent.CyclicBarrier cyclicBarrier;//这个类就是实现等待线程方法的，当传入的值为10的时候10个线程都加入线程池中后 然后一起执行
    private int n=0;//记录一共有几个线程

    public Tack(java.util.concurrent.CyclicBarrier cyclicBarrier,int n){
        this.cyclicBarrier = cyclicBarrier;
        this.n=n;
    }

    @Override
    public void run() {
        try {
            System.out.println("第"+n+"个线程准备");
            cyclicBarrier.await();
            System.out.println("第"+n+"个县城开始跑");

            int max = 0;
            for(int i =0; i<100;i++){
                max +=i;
            }

            System.out.println("第"+n+"个县城开结束；计算结果是"+max);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}