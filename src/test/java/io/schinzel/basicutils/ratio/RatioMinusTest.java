package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class RatioMinusTest extends AbstractIRatio<RatioMinusTest> implements IRatioMinus<RatioMinusTest> {


    public RatioMinusTest() {
        super(0, 0);
    }


    RatioMinusTest(int num, int den) {
        super(num, den);
    }


    @Override
    public RatioMinusTest newInstance(BigInteger numerator, BigInteger denominator) {
        return new RatioMinusTest(numerator.intValue(), denominator.intValue());
    }


    @Test
    public void testMinus() {
        RatioMinusTest r1 = new RatioMinusTest(1, 2);
        RatioMinusTest r2 = new RatioMinusTest(1, 4);
        r1.minus(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testMinusInt() {
        RatioMinusTest r1 = new RatioMinusTest(7, 4);
        r1.minus(1);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


}
