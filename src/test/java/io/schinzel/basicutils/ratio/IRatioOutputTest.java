package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

@Accessors(prefix = "m")
public class IRatioOutputTest implements IRatioOutput<IRatioOutputTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;


    @Override
    public IRatioOutputTest getThis() {
        return this;
    }


    private static IRatioOutputTest create(int num, int den) {
        return new IRatioOutputTest().setRatio(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Test
    public void getStringTest() {
        IRatioOutputTest r = IRatioOutputTest.create(3, 7);
        Assert.assertEquals("3/7", r.getString());
    }


    @Test
    public void getStrTest() {
        IRatioOutputTest r = IRatioOutputTest.create(3, 7);
        Assert.assertEquals("3/7", r.getStr().getString());
    }


    @Test
    public void getDoubleTest() {
        IRatioOutputTest r = IRatioOutputTest.create(1, 2);
        Assert.assertEquals(0.5D, r.getDouble(), 0);
        r = IRatioOutputTest.create(1, 3);
        Assert.assertEquals(0.33333D, r.getDouble(), 0.001);
    }

}