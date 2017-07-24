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
    public void compareTo_2to4_Negative() {
        int actual = new RatioCompare(2, 1)
                .compareTo(new RatioCompare(4, 1));
        assertThat(actual).isNegative();
    }


    @Test
    public void compareTo_2to2_Zero() {
        int actual = new RatioCompare(2, 1)
                .compareTo(new RatioCompare(2, 1));
        assertThat(actual).isZero();
    }


    @Test
    public void compareTo_4to2_Positive() {
        int actual = new RatioCompare(4, 1)
                .compareTo(new RatioCompare(2, 1));
        assertThat(actual).isPositive();
    }


    @Test
    public void greaterThan_2GreaterThan4_False() {
        boolean greaterThan = new RatioCompare(2, 1)
                .greaterThan(new RatioCompare(4, 1));
        assertThat(greaterThan).isFalse();
    }


    @Test
    public void greaterThan_4GreaterThan2_True() {
        boolean greaterThan = new RatioCompare(4, 1)
                .greaterThan(new RatioCompare(2, 1));
        assertThat(greaterThan).isTrue();
    }


    @Test
    public void greaterThan_2GreaterThan2_False() {
        boolean greaterThan = new RatioCompare(2, 1)
                .greaterThan(new RatioCompare(2, 1));
        assertThat(greaterThan).isFalse();
    }


    @Test
    public void lessThan_2LessThan4_True() {
        boolean lessThan = new RatioCompare(2, 1)
                .lessThan(new RatioCompare(4, 1));
        assertThat(lessThan).isTrue();
    }


    @Test
    public void lessThan_4LessThan2_False() {
        boolean lessThan = new RatioCompare(4, 1)
                .lessThan(new RatioCompare(2, 1));
        assertThat(lessThan).isFalse();
    }


    @Test
    public void lessThan_2LessThan2_False() {
        boolean lessThan = new RatioCompare(2, 1)
                .lessThan(new RatioCompare(2, 1));
        assertThat(lessThan).isFalse();
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
