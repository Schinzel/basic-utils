package io.schinzel.basicutils.ratio;

import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;


@Accessors(prefix = "m")
public class IRatioTimesTest extends AbstractIRatio<IRatioTimesTest> implements IRatioTimes<IRatioTimesTest> {

    public IRatioTimesTest() {
        super(0, 0);
    }

    IRatioTimesTest(int num, int den) {
        super(num, den);
    }

    @Override
    public IRatioTimesTest newInstance(BigInteger numerator, BigInteger denominator) {
        return new IRatioTimesTest(numerator.intValue(), denominator.intValue());
    }


    @Test
    public void testTimes() {
        IRatioTimesTest r1 = new IRatioTimesTest(4, 8);
        IRatioTimesTest r2 = new IRatioTimesTest(1, 2);
        r1.times(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testTimesInt() {
        IRatioTimesTest r1 = new IRatioTimesTest(7, 4);
        r1.times(2);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        r1 = new IRatioTimesTest(7, 4);
        r1.times(3);
        Assert.assertEquals("21", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testTimesInt2() {
        IRatioTimesTest r1 = new IRatioTimesTest(7, 4);
        r1.times(1, 3);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("12", r1.getDenominator().toString());
        r1 = new IRatioTimesTest(7, 4);
        r1.times(1, 2);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("8", r1.getDenominator().toString());
    }


}