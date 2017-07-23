package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class IRatioCastTest {


    private class RatioOutput extends AbstractRatio<RatioOutput> implements IRatioCast<RatioOutput> {
        RatioOutput(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioOutput newInstance(BigInteger num, BigInteger den) {
            return new RatioOutput(num.intValue(), den.intValue());
        }
    }


    @Test
    public void getStringTest() {
        RatioOutput r = new RatioOutput(3, 7);
        Assert.assertEquals("3/7", r.getStr().getString());
    }


    @Test
    public void getStrTest() {
        RatioOutput r = new RatioOutput(3, 7);
        Assert.assertEquals("3/7", r.getStr().getString());
    }


    @Test
    public void getDoubleTest() {
        RatioOutput r = new RatioOutput(1, 2);
        Assert.assertEquals(0.5D, r.getDouble(), 0);
        r = new RatioOutput(1, 3);
        Assert.assertEquals(0.33333D, r.getDouble(), 0.001);
    }

}