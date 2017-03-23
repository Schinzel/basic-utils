package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import org.junit.Rule;
import org.junit.rules.ExpectedException;


@Accessors(prefix = "m")
public class IRatioTest implements IRatio<IRatioTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;
    @Rule
    public ExpectedException exception = ExpectedException.none();


    public IRatioTest getThis() {
        return this;
    }


    @Test
    public void testSetRatio() {
        IRatioTest r1 = new IRatioTest().setRatio(BigInteger.valueOf(4), BigInteger.valueOf(2));
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
        r1 = new IRatioTest().setRatio(BigInteger.valueOf(1), BigInteger.valueOf(3));
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("3", r1.getDenominator().toString());
    }

    @Test
    public void testSetRatio_ZeroDenominator() {
        Ratio ratio = Ratio.create(4, 1);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Denominator cannot be zero");
        ratio.setRatio(BigInteger.valueOf(4), BigInteger.valueOf(0));
    }

}
