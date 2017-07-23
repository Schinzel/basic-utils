package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AbstractRatioTest {

    @Test
    public void testConstructor() {
        Ratio r1 = Ratio.create(4, 2);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
        //
        r1 = Ratio.create(8000, 4000);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
        //
        r1 = Ratio.create(16, 8);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
        r1 = Ratio.create(16L, 8L);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
        r1 = Ratio.create(BigInteger.valueOf(16), BigInteger.valueOf(8));
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
    }


    @Test
    public void testConstructor_ZeroDenominator() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            Ratio.create(4, 0);
        }).withMessageContaining("Denominator cannot be zero");
    }
}