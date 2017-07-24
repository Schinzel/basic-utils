package io.schinzel.basicutils.ratio;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class AbstractRatioTest {


    private class AbstractRatioTestClass extends AbstractRatio<AbstractRatioTestClass> {
        AbstractRatioTestClass(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public AbstractRatioTestClass newInstance(BigInteger num, BigInteger den) {
            return new AbstractRatioTestClass(num.intValue(), den.intValue());
        }
    }


    @Test
    public void getNumerator_5over2_5() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(5, 2);
        assertThat(r1.getNumerator()).isEqualTo(5);
    }


    @Test
    public void getDenominator_5over2_2() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(5, 2);
        assertThat(r1.getDenominator()).isEqualTo(2);
    }


    @Test
    public void constructor_minus5overMinus2_5over2() {
        String actual = new AbstractRatioTestClass(-5, -2).getStr().toString();
        assertThat(actual).isEqualTo("5/2");
    }


    @Test
    public void constructor_minus5over2_minus5over2() {
        String actual = new AbstractRatioTestClass(-5, 2).getStr().toString();
        assertThat(actual).isEqualTo("-5/2");
    }


    @Test
    public void constructor_5overMinus2_minus5over2() {
        String actual = new AbstractRatioTestClass(5, -2).getStr().toString();
        assertThat(actual).isEqualTo("-5/2");
    }


    @Test
    public void constructor_0overMinus5_0over1() {
        String actual = new AbstractRatioTestClass(0, -5).getStr().toString();
        assertThat(actual).isEqualTo("0/1");
    }


    @Test
    public void constructor_GcdShouldBeUsed_MinPossibleValue() {
        String actual = new AbstractRatioTestClass(8000, 4000).getStr().toString();
        assertThat(actual).isEqualTo("2/1");
    }


    @Test
    public void constructor_ZeroDenominator_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new AbstractRatioTestClass(4, 0);
        }).withMessageContaining("Denominator cannot be zero");
    }
}