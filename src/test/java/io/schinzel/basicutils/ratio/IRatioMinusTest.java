package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

@Accessors(prefix = "m")
public class IRatioMinusTest implements IRatioMinus<IRatioMinusTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;


    @Override
    public IRatioMinusTest getThis() {
        return this;
    }


    private static IRatioMinusTest create(int num, int den) {
        return new IRatioMinusTest().setRatio(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Test
    public void testMinus() {
        IRatioMinusTest r1 = IRatioMinusTest.create(1, 2);
        IRatioMinusTest r2 = IRatioMinusTest.create(1, 4);
        r1.minus(r2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }


    @Test
    public void testMinusInt() {
        IRatioMinusTest r1 = IRatioMinusTest.create(7, 4);
        r1.minus(1);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
    }
}
