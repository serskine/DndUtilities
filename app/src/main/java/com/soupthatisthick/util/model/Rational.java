package com.soupthatisthick.util.model;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */


public class Rational {

    private long num, denom, gcm;

    public Rational(double d) {
        String s = String.valueOf(d);
        long digitsDec = s.length() - 1 - s.indexOf('.');

        long denom = 1;
        for(long i = 0; i < digitsDec; i++){
            d *= 10;
            denom *= 10;
        }
        long num = Math.round(d);

        this.num = num; this.denom = denom;
    }

    public Rational(long num, long denom) {
        this.num = num; this.denom = denom;
    }

    public long getNum() {
        return this.num;
    }

    public long getDenom() {
        return this.denom;
    }

    public long getGcm() {
        return gcm(num, denom);
    }

    public String toString() {
        long gcm = getGcm();
        if (gcm == denom)
        {
            return "" + (num / gcm);
        } else {
            return "" + (num / gcm) + "/" + (denom / gcm);
        }
//        return String.valueOf(num/gcm) + "/" + String.valueOf(denom/gcm);
    }


    /**
     * @return the greatest common denominator
     */
    public static long gcm(long a, long b) {
        return b == 0 ? a : gcm(b, a % b); // Not bad for one line of code :)
    }

    /**
     * This will return the fraction as a string
     * @param a
     * @param b
     * @return
     */
    public static String asFraction(long a, long b) {
        long gcm = gcm(a, b);
        return (a / gcm) + "/" + (b / gcm);
    }
}
