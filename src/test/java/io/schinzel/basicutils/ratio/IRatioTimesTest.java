package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;


@Accessors(prefix = "m")
public class IRatioTimesTest implements IRatioTimes<IRatioTimesTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;


    @Override
    public IRatioTimesTest getThis() {
        return this;
    }


    private static IRatioTimesTest create(int num, int den) {
        return new IRatioTimesTest().setRatio(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Test
    public void testTimes() {
        IRatioTimesTest r1 = IRatioTimesTest.create(4, 8);
        IRatioTimesTest r2 = IRatioTimesTest.create(1, 2);
        r1.times(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testTimesInt() {
        IRatioTimesTest r1 = IRatioTimesTest.create(7, 4);
        r1.times(2);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        r1 = IRatioTimesTest.create(7, 4);
        r1.times(3);
        Assert.assertEquals("21", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testTimesInt2() {
        IRatioTimesTest r1 = IRatioTimesTest.create(7, 4);
        r1.times(1, 3);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("12", r1.getDenominator().toString());
        r1 = IRatioTimesTest.create(7, 4);
        r1.times(1, 2);
        Assert.assertEquals("7", r1.getNumerator().toString());
        Assert.assertEquals("8", r1.getDenominator().toString());
    }

}