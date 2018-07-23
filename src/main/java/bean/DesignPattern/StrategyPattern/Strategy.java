package bean.DesignPattern.StrategyPattern;

/**\
 * 这个设计模式的主要作用就是 降低代码的复杂度。减少代码中if else的产生
 * 使用面向接口编程 来实现   子类的不同工作任务
 */
public interface Strategy {
    public int doOperation(int numA,int numB);
}
