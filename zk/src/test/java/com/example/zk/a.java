package com.example.zk;

public class a {
    public static void main(String[] args) {

        System.out.println(ColourDistance(new RGB(100,6,255),new RGB(100,6,254)));
    }

    static double ColourDistance(RGB e1, RGB e2)
    {
        long rmean = ( (long)e1.getR() + (long)e2.getR() ) / 2;
        long r = (long)e1.getR() - (long)e2.getR();
        long g = (long)e1.getG() - (long)e2.getG();
        long b = (long)e1.getB() - (long)e2.getB();
        return Math.sqrt((((512+rmean)*r*r)>>8) + 4*g*g + (((767-rmean)*b*b)>>8));
    }
}

class RGB{
    private int r, g, b;

    public RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}