package bean.DesignPattern.TemplatePattrn;

public class game1 extends TemplatePattrn {
    @Override
    public void play() {
        System.out.println("game1play");
    }

    @Override
    public void endplay() {
        System.out.println("game1endplay");
    }

    @Override
    public void startPlay() {
        System.out.println("game1startPlay");
    }

    public static void main(String[] args) {
        TemplatePattrn templatePattrn = new game1();
        templatePattrn.start();
    }
}
