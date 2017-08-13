package com.soupthatisthick.util.jsonstuff;

/**
 * Created by Owner on 8/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class TestObj {
    private boolean B;
    private short S;
    private int I;
    private long L;
    private float F;
    private double D;

    public TestObj(boolean B, short S, int I, long L, float F, double D) {
        super();
        this.B = B;
        this.S = S;
        this.I = I;
        this.L = L;
        this.F = F;
        this.S = S;
    }

    public TestObj() {
        this.B = true;
        this.S = (short) (Math.random()*Short.MAX_VALUE);
        this.I = (int) (Math.random()*Integer.MAX_VALUE);
        this.L = (long) (Math.random()*Long.MAX_VALUE);
        this.F = (float) (Math.random()*Float.MAX_VALUE);
        this.D = (double) (Math.random()*Double.MAX_VALUE);
    }

    public boolean isB() {
        return B;
    }

    public void setB(boolean b) {
        B = b;
    }

    public short getS() {
        return S;
    }

    public void setS(short s) {
        S = s;
    }

    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
    }

    public long getL() {
        return L;
    }

    public void setL(long l) {
        L = l;
    }

    public float getF() {
        return F;
    }

    public void setF(float f) {
        F = f;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }
}
