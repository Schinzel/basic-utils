package io.schinzel.samples.crypto;

import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.Sandman;
import io.schinzel.basicutils.str.Str;
import io.schinzel.basicutils.crypto.hash.Bcrypt;
import io.schinzel.basicutils.crypto.hash.HmacSha512;
import io.schinzel.basicutils.crypto.hash.IHash;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to measure how long a hash takes.
 */

public class HashPerformanceSample {

    public static void main(String[] args) {
        IHash hash;
        hash = new HmacSha512("0123456789abcdef");
        hash = new Bcrypt(4);
        //Set up data to be hashed.
        int numberOfIterations = 100;
        List<String> clearTexts = new ArrayList<>();
        for (int i = 0; i < numberOfIterations; i++) {
            clearTexts.add(RandomUtil.getRandomString(200));
        }
        //Warm up
        for (int i = 0; i < 10; i++) {
            hash.hash(RandomUtil.getRandomString(10));
        }
        Sandman.snoozeMillis(100);
        //Start measurement
        long start = System.currentTimeMillis();
        for (String clearText : clearTexts) {
            hash.hash(clearText);
        }
        //Stop measurement
        long totTime = (System.currentTimeMillis() - start);
        double timePerLap = totTime / (numberOfIterations * 1d);
        Str.create()
                .a("Total run time: ").af(totTime).a(" ms").anl()
                .a("Time per hash: ").af(timePerLap, 2).a(" ms")
                .pln();
    }
}
