package io.schinzel.basicutils.ratio;

import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;


@Accessors(prefix = "m")
public class IRatioTimesTest {

    private class RatioTimes extends AbstractRatio<RatioTimes> implements IRatioTimes<RatioTimes> {
        RatioTimes(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioTimes newInstance(BigInteger num, BigInteger den) {
            return new RatioTimes(num.intValue(), den.intValue());
        }

    }


    @Test
    public void testTimes() {
        RatioTimes r1 = new RatioTimes(4, 8);
        RatioTimes r2 = new RatioTimes(1, 2);
        r1 = r1.times(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testTimesInt() {
        RatioTimes r1 = new RatioTimes(7, 4);
        r1 = r1 = r1.times(2);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        r1 = r1 = new RatioTimes(7, 4);
        r1 = r1 = r1.times(3);
        Assert.assertEquals("21", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testTimesInt2() {
        RatioTimes r1 = new RatioTimes(7, 4);
        r1 = r1.times(1, 3);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("12", r1.getDenominator().toString());
        r1 = new RatioTimes(7, 4);
        r1 = r1.times(1, 2);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("8", r1.getDenominator().toString());
    }


}