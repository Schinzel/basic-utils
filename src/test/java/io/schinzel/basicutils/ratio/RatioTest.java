package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;
import java.util.Random;

public class RatioTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


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
        exception.expect(RuntimeException.class);
        exception.expectMessage("Denominator cannot be zero");
        Ratio.create(4, 0);
    }


    @Test
    public void testNegativeRatios() {
        Ratio ratio = Ratio.create(1, -2);
        double result = ratio.getDouble();
        double expected = -0.5;
        Assert.assertEquals(expected, result, 0);
        //
        ratio = Ratio.create(-1, 2);
        result = ratio.getDouble();
        expected = -0.5;
        Assert.assertEquals(expected, result, 0);
        //
        ratio = Ratio.create(-1, -2);
        result = ratio.getDouble();
        expected = 0.5;
        Assert.assertEquals(expected, result, 0);
    }


    @Test
    public void testGetThis() {
        Ratio ratio = Ratio.create(1, -2);
        Assert.assertEquals(ratio, ratio.getThis());
        Assert.assertEquals("Ratio", ratio.getThis().getClass().getSimpleName());
    }


    /**
     * Do this a bunch of times
     * - generate a random ratio
     * - generate a random multiplier
     * - test: ratio * multiplier/multiplier = ratio
     */
    @Test
    public void testRandomTimesDiv() {
        RandomBigIntUtil randomBigIntUtil = new RandomBigIntUtil(123);
        Random randMultiplier = new Random(123);
        for (int i = 0; i < 10000; i++) {
            BigInteger numerator = randomBigIntUtil.getNext();
            BigInteger denom = randomBigIntUtil.getNext();
            if (denom.equals(BigInteger.ZERO)) {
                continue;
            }
            Ratio r1 = Ratio.create(numerator, denom);
            Ratio r2 = Ratio.create(numerator, denom);
            int multiplier = randMultiplier.nextInt();
            r1.times(multiplier).dividedBy(multiplier);
            Assert.assertEquals(r1, r2);
        }
    }


    /**
     * Do this a bunch of times
     * - generate a random ratio
     * - generate a random multiplier
     * - test: ratio * plus/minus = ratio
     */
    @Test
    public void testRandomMinusPlus() {
        RandomBigIntUtil randomBigIntUtil = new RandomBigIntUtil(123);
        Random randMultiplier = new Random(123);
        for (int i = 0; i < 10000; i++) {
            BigInteger numerator = randomBigIntUtil.getNext();
            BigInteger denom = randomBigIntUtil.getNext();
            if (denom.equals(BigInteger.ZERO)) {
                continue;
            }
            Ratio r1 = Ratio.create(numerator, denom);
            Ratio r2 = Ratio.create(numerator, denom);
            int multiplier = randMultiplier.nextInt();
            r1.plus(multiplier).minus(multiplier);
            Assert.assertEquals(r1, r2);
        }
    }


    @Test
    public void testToString() {
        Ratio ratio = Ratio.create(123, 456);
        Assert.assertEquals("41/152", ratio.toString());
    }
}
