package bean.Multithreading.CyclicBarrier;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileClock implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.printf("%s\n",new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
                /**
                 *当线程中抛出throw new InterruptedException();
                 * 这个异常时候千万不要被吃掉。一旦吃掉了那么
                 * 整个线程还是会继续运行的不会直接结束
                 *
                 * 这个程序的目的是将中断请求抓住
                 * 这时候中断时  线程的等待时间  也没了直接的是抛出异常
                 * 从而让出cpu
                 *
                 * yield（）也是让出时间片
                 *
                 */
//                throw new InterruptedException();
            }catch (InterruptedException e){
                System.out.printf("the FileClock has been interrupted");
            }
        }
    }

    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }
        /**
         * 强制中断
         * 哪个线程运行这个就是告诉线程
         * 这个线程出现异常了   请执行操作
         */
        thread.interrupt();
    }
}
