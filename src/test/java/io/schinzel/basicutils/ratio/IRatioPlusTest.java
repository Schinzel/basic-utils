package io.schinzel.basicutils.ratio;

import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;


@Accessors(prefix = "m")
public class IRatioPlusTest {


    private class RatioPlus extends AbstractRatio<RatioPlus> implements IRatioPlus<RatioPlus> {
        RatioPlus(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioPlus newInstance(BigInteger num, BigInteger den) {
            return new RatioPlus(num.intValue(), den.intValue());
        }

    }


    @Test
    public void testAdd() {
        RatioPlus r1 = new RatioPlus(1, 2);
        RatioPlus r2 = new RatioPlus(1, 4);
        r1 = r1.plus(r2);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
        //
        r1 = new RatioPlus(10, 20);
        r2 = new RatioPlus(4, 16);
        r1 = r1.plus(r2);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
        //
        r1 = new RatioPlus(10, 20);
        r1 = r1.plus(2);
        Assert.assertEquals("5", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        //
        r1 = new RatioPlus(10, 20);
        r1 = r1.plus(4);
        Assert.assertEquals("9", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        //
        r1 = new RatioPlus(1, 3);
        r1 = r1.plus(1, 3);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("3", r1.getDenominator().toString());
    }


}