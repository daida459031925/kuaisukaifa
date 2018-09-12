package bean.Multithreading.CyclicBarrier;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

class WriterTask18 implements Runnable{

    private Deque<Event18> deque;

    public WriterTask18(Deque<Event18> deque){
        this.deque=deque;
    }

    /**
     * 这段代码的意思就是  往这个队列中不停的添加
     * 每一个线程都会添加100次   但是没添加一次就会休息1秒
     */
    @Override
    public void run() {
        for(int i=1;i<100;i++){
            Event18 event = new Event18();
            event.setEvent(String.format("The thread %s has generated an event",Thread.currentThread().getId()));
            event.setData(new Date());
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.printf("%d\n",deque.size());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

class CleanerTask18 extends Thread{

    private Deque<Event18> deque;

    public CleanerTask18(Deque<Event18> deque){
        this.deque=deque;
        setDaemon(true);//设置当前想成为守护线程
    }

    @Override
    public void run() {
        super.run();
        while (true){
            Date date = new Date();
            clean(date);
        }
    }

    /**
     * 这段线程的意思是
     * 在不停的循环判断  查看是否根据时间来计算 如果大于10秒的就进行从队列中拿掉最后那个时间最长的
     *
     * @param date
     */
    private void clean(Date date){
        long difference;
        boolean delete;
        if(deque.size()==0){
            return;
        }
        delete=false;
        do {
            Event18 e = deque.getLast();
            difference =date.getTime() -e.getData().getTime();
            if(difference>10000){
                System.out.printf("Cleaner: %s\n",e.getEvent());
                deque.removeLast();
                delete=true;
            }
        }while (difference>10000);
        if(delete){
            System.out.printf("Cleaner: size of the queue: %d\n",deque.size());
        }
    }
}


public class Event18 {
    private Date data;
    private String event;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public static void main(String[] args) {
        Deque<Event18> deque = new ArrayDeque<>();
        WriterTask18 writerTask18 = new WriterTask18(deque);
        for (int i=0;i<3;i++){
            Thread thread = new Thread(writerTask18);
            thread.start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
            //守护线程  运行之前先让正常线程运行起来   这样可以保证cpu的时间先让
            //程序正常执行。。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CleanerTask18 cleanerTask18 = new CleanerTask18(deque);
        cleanerTask18.start();
    }
}
