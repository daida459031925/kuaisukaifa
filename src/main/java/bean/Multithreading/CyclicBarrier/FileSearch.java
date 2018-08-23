package bean.Multithreading.CyclicBarrier;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable{

    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if(file.isDirectory()){
            try {
                /**
                 * 进行查找文件内容查看是否是相同的文件名
                 */
                directoryProcess(file);
            }catch (Exception e){
                System.out.printf("%s: the search has been interrupteg",
                        Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file)throws InterruptedException{
        File list[] =file.listFiles();
        if(list != null){
            for(int i=0; i<list.length;i++){
                System.out.printf("*************************");
                if(list[i].isDirectory()){
                    /**
                     * 判断是否是文件夹，如果是文件夹，那么就继续递归循环计算
                     */
                    directoryProcess(list[i]);
                }else{
                    /**
                     * 如果是文件那么就看是否是相同的文件名。就是是否是我们需要的文件名
                     */
                    fileProcess(list[i]);
                }
            }
        }

        if(Thread.interrupted()){
            /**
             * 在这个地方相当于  在main中我已经告诉了线程   我需要关闭了
             * 将状态设置了true   然后进入到这里
             * 然后抛出 这个异常    这个异常一旦被捕获  那么就会直接中断当前线程
             * 换句话说可以设置一个返回值设置后将其线程掐断    或者说等待多少秒时间到了
             * 数据还没有返还的话就直接掐断
             */

            throw new InterruptedException();
        }
    }


    private void fileProcess(File file)throws  InterruptedException{
        if(file.getName().equals(fileName)){
            /**
             * 如果是相同的文件名，那么就将文件名打印出来。
             */
            System.out.printf("%s : %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }


    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("C:\\","uninstall.txt");
        Thread thread = new Thread(searcher);
        thread.start();
        try {
            /**
             * TimeUnit这个类是时间工具类
             * 1）提供了可读性更好的线程暂停操作，通常用来替换Thread.sleep()；
             * 2）提供了便捷方法用于把时间转换成不同单位，如把秒转换成毫秒；
             */
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        /**
         * 等待十秒以后将当前线程关闭
         */
        thread.interrupt();
    }
}
