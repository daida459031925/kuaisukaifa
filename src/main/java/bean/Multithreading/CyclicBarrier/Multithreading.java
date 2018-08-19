package bean.Multithreading.CyclicBarrier;

import java.util.concurrent.*;

public class Multithreading {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        //创建两个有返回值的任务
        Callable c1 = new MyCallable1("A");
        Callable c2 = new MyCallable2("B");
        //执行任务并获取Future对象
        Future f2 = pool.submit(c2);
        Future f1 = pool.submit(c1);
        //从Future对象上获取任务的返回值，并输出到控制台
        long l1 = System.currentTimeMillis();
        System.out.println(">>>"+f1.get().toString());
        System.out.println(">>>"+f2.get().toString());
        System.out.println(">>>"+(l1-System.currentTimeMillis()));
        //关闭线程池
        pool.shutdown();
    }
}

class MyCallable1 implements Callable {
    private String oid;

    MyCallable1(String oid) {
        this.oid = oid;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(1000);
        return oid+"任务返回的内容";
    }
}

class MyCallable2 implements Callable {
    private String oid;

    MyCallable2(String oid) {
        this.oid = oid;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(3000);
        return oid+"任务返回的内容";
    }
}