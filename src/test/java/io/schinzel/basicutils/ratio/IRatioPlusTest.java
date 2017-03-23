package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;


@Accessors(prefix = "m")
public class IRatioPlusTest implements IRatioPlus<IRatioPlusTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;


    @Override
    public IRatioPlusTest getThis() {
        return this;
    }


    private static IRatioPlusTest create(int num, int den) {
        return new IRatioPlusTest().setRatio(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Test
    public void testAdd() {
        IRatioPlusTest r1 = IRatioPlusTest.create(1, 2);
        IRatioPlusTest r2 = IRatioPlusTest.create(1, 4);
        r1.plus(r2);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
        //
        r1 = IRatioPlusTest.create(10, 20);
        r2 = IRatioPlusTest.create(4, 16);
        r1.plus(r2);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
        //
        r1 = IRatioPlusTest.create(10, 20);
        r1.plus(2);
        Assert.assertEquals("5", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        //
        r1 = IRatioPlusTest.create(10, 20);
        r1.plus(4);
        Assert.assertEquals("9", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        //
        r1 = IRatioPlusTest.create(1, 3);
        r1.plus(1, 3);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("3", r1.getDenominator().toString());
    }


}