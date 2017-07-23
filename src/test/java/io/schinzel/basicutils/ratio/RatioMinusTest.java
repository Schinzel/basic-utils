package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class RatioMinusTest {

    private class RatioMinus extends AbstractRatio<RatioMinus> implements IRatioMinus<RatioMinus> {
        RatioMinus(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioMinus newInstance(BigInteger num, BigInteger den) {
            return new RatioMinus(num.intValue(), den.intValue());
        }

    }



    @Test
    public void testMinus() {
        RatioMinus r1 = new RatioMinus(1, 2);
        RatioMinus r2 = new RatioMinus(1, 4);
        r1 = r1.minus(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testMinusInt() {
        RatioMinus r1 = new RatioMinus(7, 4);
        r1 = r1.minus(1);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


}
