package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.RandomUtil;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class IRatioCompareTest {

    private class RatioCompare extends AbstractRatio<RatioCompare> implements IRatioCompare<RatioCompare> {
        RatioCompare(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioCompare newInstance(BigInteger numerator, BigInteger denominator) {
            throw new RuntimeException("Not implemented!");
        }

    }


    @Test
    public void compareTo_LessThan_Negative() {
        int den1 = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        int den2 = RandomUtil.getRandomNumber(1, den1);
        RatioCompare r1 = new RatioCompare(1, den1);
        RatioCompare r2 = new RatioCompare(1, den2);
        int actual = r1.compareTo(r2);
        assertThat(actual).isNegative();
    }


    @Test
    public void compareTo_Equal_Zero() {
        int den = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den);
        RatioCompare r2 = new RatioCompare(1, den);
        int actual = r1.compareTo(r2);
        assertThat(actual).isZero();
    }


    @Test
    public void compareTo_LargerThan_Positive() {
        int den1 = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        int den2 = RandomUtil.getRandomNumber(den1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den1);
        RatioCompare r2 = new RatioCompare(1, den2);
        int actual = r1.compareTo(r2);
        assertThat(actual).isPositive();
    }


    @Test
    public void greaterThan_GreaterThan_True() {
        int den1 = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        int den2 = RandomUtil.getRandomNumber(den1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den1);
        RatioCompare r2 = new RatioCompare(1, den2);
        assertThat(r1.greaterThan(r2)).isTrue();
    }


    @Test
    public void greaterThan_Equal_False() {
        int den = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den);
        RatioCompare r2 = new RatioCompare(1, den);
        assertThat(r1.greaterThan(r2)).isFalse();
    }


    @Test
    public void greaterThan_LessThan_False() {
        int den1 = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        int den2 = RandomUtil.getRandomNumber(1, den1);
        RatioCompare r1 = new RatioCompare(1, den1);
        RatioCompare r2 = new RatioCompare(1, den2);
        assertThat(r1.greaterThan(r2)).isFalse();
    }


    @Test
    public void lessThan_GreaterThan_False() {
        int den1 = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        int den2 = RandomUtil.getRandomNumber(den1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den1);
        RatioCompare r2 = new RatioCompare(1, den2);
        assertThat(r1.lessThan(r2)).isFalse();
    }


    @Test
    public void lessThan_Equal_False() {
        int den = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den);
        RatioCompare r2 = new RatioCompare(1, den);
        assertThat(r1.lessThan(r2)).isFalse();
    }


    @Test
    public void lessThan_LessThan_True() {
        int den1 = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        int den2 = RandomUtil.getRandomNumber(1, den1);
        RatioCompare r1 = new RatioCompare(1, den1);
        RatioCompare r2 = new RatioCompare(1, den2);
        assertThat(r1.lessThan(r2)).isTrue();
    }


    @Test
    public void ComparableImplementation_Sort_AscendingOrder() {
        RatioCompare r1 = new RatioCompare(3, 4);
        RatioCompare r2 = new RatioCompare(1, 4);
        RatioCompare r3 = new RatioCompare(2, 4);
        List<RatioCompare> list = Arrays.asList(r1, r2, r3);
        Collections.sort(list);
        assertThat(list).containsExactly(r2, r3, r1);

    }


    @Test
    public void equals_AreEqual_True() {
        int den = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den);
        RatioCompare r2 = new RatioCompare(1, den);
        assertThat(r1.equals(r2)).isTrue();
        assertThat(r2.equals(r1)).isTrue();
    }


    @Test
    public void equals_NotEqual_True() {
        int den = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
        RatioCompare r1 = new RatioCompare(1, den);
        RatioCompare r2 = new RatioCompare(2, den);
        assertThat(r1.equals(r2)).isFalse();
        assertThat(r2.equals(r1)).isFalse();
    }


    @Test
    public void equals_Null_False() {
        assertThat(new RatioCompare(1, 1).equals(null)).isFalse();
    }

}
