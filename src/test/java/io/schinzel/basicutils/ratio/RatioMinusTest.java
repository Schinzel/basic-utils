package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class RatioMinusTest extends AbstractRatio<RatioMinusTest> implements IRatioMinus<RatioMinusTest> {

    public RatioMinusTest() {
        this(1, 1);
    }


    RatioMinusTest(int num, int den){
        super(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Override
    public RatioMinusTest newInstance(BigInteger numerator, BigInteger denominator) {
        return new RatioMinusTest(numerator.intValue(), denominator.intValue());
    }



    @Test
    public void testMinus() {
        RatioMinusTest r1 = new RatioMinusTest(1, 2);
        RatioMinusTest r2 = new RatioMinusTest(1, 4);
        r1 = r1.minus(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testMinusInt() {
        RatioMinusTest r1 = new RatioMinusTest(7, 4);
        r1 = r1.minus(1);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


}
