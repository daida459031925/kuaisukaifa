package bean.DesignPattern.TemplatePattrn;

/**
 * 模板模式的左右是   通用方法或者  比较复杂的方法进行抽象华
 */
public abstract class TemplatePattrn {
     abstract void play();
     abstract void endplay();
     abstract void startPlay();

    public void start(){
        play();
        endplay();
        startPlay();
    }
}
