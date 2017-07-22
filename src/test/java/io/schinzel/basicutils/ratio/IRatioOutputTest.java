package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class IRatioOutputTest extends AbstractIRatio<IRatioOutputTest> implements IRatioOutput<IRatioOutputTest> {


    public IRatioOutputTest() {
        super(0, 0);
    }

    IRatioOutputTest(int num, int den) {
        super(num, den);
    }


    @Override
    public IRatioOutputTest newInstance(BigInteger numerator, BigInteger denominator) {
        throw new RuntimeException("Not implemented yet!");
    }


    @Test
    public void getStringTest() {
        IRatioOutputTest r = new IRatioOutputTest(3, 7);
        Assert.assertEquals("3/7", r.getString());
    }


    @Test
    public void getStrTest() {
        IRatioOutputTest r = new IRatioOutputTest(3, 7);
        Assert.assertEquals("3/7", r.getStr().getString());
    }


    @Test
    public void getDoubleTest() {
        IRatioOutputTest r = new IRatioOutputTest(1, 2);
        Assert.assertEquals(0.5D, r.getDouble(), 0);
        r = new IRatioOutputTest(1, 3);
        Assert.assertEquals(0.33333D, r.getDouble(), 0.001);
    }

}