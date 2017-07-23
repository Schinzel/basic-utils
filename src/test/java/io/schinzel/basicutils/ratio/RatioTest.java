package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class RatioTest {

    @Test
    public void create_IntArguments() {
        Ratio r1 = Ratio.create(16, 8);
        assertThat(r1.getNumerator()).isEqualTo(2);
        assertThat(r1.getDenominator()).isEqualTo(1);
    }


    @Test
    public void create_LongArguments() {
        Ratio r1 = Ratio.create(16L, 8L);
        assertThat(r1.getNumerator()).isEqualTo(2);
        assertThat(r1.getDenominator()).isEqualTo(1);
    }


    @Test
    public void create_BigIntegerArguments() {
        Ratio r1 = Ratio.create(BigInteger.valueOf(16), BigInteger.valueOf(8));
        assertThat(r1.getNumerator()).isEqualTo(2);
        assertThat(r1.getDenominator()).isEqualTo(1);
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


    /**
     * Do this a bunch of times
     * - generate a random ratio
     * - generate a random multiplier
     * - test: ratio * multiplier/multiplier = ratio
     */
    @Test
    public void timesDividedBy_RandomValues() {
        RandomBigIntUtil randomBigIntUtil = new RandomBigIntUtil(123);
        Random randMultiplier = new Random(123);
        for (int i = 0; i < 1000; i++) {
            BigInteger num = randomBigIntUtil.getNext();
            BigInteger den = randomBigIntUtil.getNext();
            if (den.equals(BigInteger.ZERO)) {
                continue;
            }
            Ratio r1 = Ratio.create(num, den);
            int multiplier = randMultiplier.nextInt();
            Ratio r2 = r1.times(multiplier).dividedBy(multiplier);
            assertThat(r1.toString()).isEqualTo(r2.toString());
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


    @Test
    public void hashCode_AandBEqualButNotSameObject_SameHashCode() {
        Ratio a = Ratio.create(5, 2);
        Ratio b = Ratio.create(10, 4);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }


    @Test
    public void hashCode_SameObject_SameHashCode() {
        Ratio a = Ratio.create(5, 2);
        assertThat(a.hashCode()).isEqualTo(a.hashCode());
    }

}
