package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class RatioTest {

    @Test
    public void create_Int16and0_2over1() {
        String actual = Ratio.create(16, 8).toString();
        assertThat(actual).isEqualTo("2/1");
    }


    @Test
    public void create_Long16and0_2over1() {
        String actual = Ratio.create(16L, 8L).toString();
        assertThat(actual).isEqualTo("2/1");
    }


    @Test
    public void create_BigInteger16and0_2over1() {
        String actual = Ratio.create(BigInteger.valueOf(16), BigInteger.valueOf(8)).toString();
        assertThat(actual).isEqualTo("2/1");
    }


    /**
     * Do this a bunch of times
     * - generate a random ratio
     * - generate a random multiplier
     * - test: ratio * multiplier/multiplier = ratio
     */
    @Test
    public void timesDividedBy_RandomValues() {
        RandomBigIntUtil randomBigIntUtil = new RandomBigIntUtil();
        Random randMultiplier = new Random();
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
    public void minusPlus_RandomValues() {
        RandomBigIntUtil randomBigIntUtil = new RandomBigIntUtil();
        Random randMultiplier = new Random();
        for (int i = 0; i < 10000; i++) {
            BigInteger numerator = randomBigIntUtil.getNext();
            BigInteger denom = randomBigIntUtil.getNext();
            if (denom.equals(BigInteger.ZERO)) {
                continue;
            }
            Ratio r1 = Ratio.create(numerator, denom);
            int number = randMultiplier.nextInt();
            Ratio r2 = r1.plus(number).minus(number);
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


    @Test
    public void equals_null_False() {
        assertThat(Ratio.create(4, 5).equals(null)).isFalse();
    }


    @Test
    public void equals_String_False() {
        assertThat(Ratio.create(4, 5).equals("monkey")).isFalse();
    }


    @Test
    public void equals_SameObject_True() {
        Ratio a = Ratio.create(5, 2);
        Ratio b = a;
        assertThat(a.equals(b)).isTrue();
        assertThat(b.equals(a)).isTrue();
    }


    @Test
    public void equals_DifferentObjectsSameValue_True() {
        Ratio a = Ratio.create(5, 2);
        Ratio b = Ratio.create(10, 4);
        assertThat(a.equals(b)).isTrue();
        assertThat(b.equals(a)).isTrue();
    }
}
