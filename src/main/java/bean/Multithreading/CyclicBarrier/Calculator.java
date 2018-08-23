package bean.Multithreading.CyclicBarrier;


/**
 * 初步了解线程的继承以及实现等
 * 以及了解 printf 的基本构造使用
 *
 * System.exit(1);
 * System.exit()这个方法，接收一个参数status，0表示正常退出，非零参数表示非正常退出。不管status为何值都会退出程序。
 * 和return 相比，return是回到上一层，而System.exit(status)是回到最上层。
 */
public class Calculator implements Runnable{

    private int number;
    public Calculator(int number){
        this.number=number;
    }
    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        for (int i=1;i<=10;i++){
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }
}
