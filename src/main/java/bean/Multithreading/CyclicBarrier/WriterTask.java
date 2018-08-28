package bean.Multithreading.CyclicBarrier;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

class Event {
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
}

public class WriterTask implements Runnable {

    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for(int i=1;i<100;i++){
            Event event = new Event();
            event.setData(new Date());
            event.setEvent(String.format("The thread %s has generated an event" +
                    "",Thread.currentThread().getId()));
            deque.addFirst(event);
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class CleanerTask extends  Thread{
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque){
        this.deque=deque;
        setDaemon(true);//初始化的时候将线程编程守护线程
    }

    @Override
    public void run() {
        super.run();
        while (true){
            Date date=new Date();
            clean(date);
        }
    }

    private void clean(Date date){
        long difference;
        boolean delete;
        if(deque.size() == 0){
            return;
        }
        delete=false;
        do {
            Event e = deque.getLast();
            difference = date.getTime() - e.getData().getTime();
            if(difference > 10000){
                System.out.printf("Cleaner: %s\n",e.getEvent());
                deque.removeLast();
                delete=true;
            }
        }while (difference > 10000);
        if(delete){
            System.out.printf("Cleaner: Size of the queue: %d\n",deque.size());
        }
    }
}