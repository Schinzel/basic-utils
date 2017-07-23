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
    public void getNumerator_NormalCase_NumShouldBeSetNum() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(5, 2);
        assertThat(r1.getNumerator()).isEqualTo(5);
    }


    @Test
    public void getDenominator_NormalCase_NumShouldBeSetNum() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(5, 2);
        assertThat(r1.getDenominator()).isEqualTo(2);
    }


    @Test
    public void getNumerator_NegativeNumAndDen_NumShouldBeSetNum() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(-5, -2);
        assertThat(r1.getNumerator()).isEqualTo(5);
    }


    @Test
    public void getDenominator_NegativeNumAndDen_NumShouldBeSetNum() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(-5, -2);
        assertThat(r1.getDenominator()).isEqualTo(2);
    }


    @Test
    public void getNumerator_ZeroNumAndNegativeDen_NumShouldBeSetNum() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(0, -5);
        assertThat(r1.getNumerator()).isEqualTo(0);
        assertThat(r1.getDenominator()).isEqualTo(1);
    }


    @Test
    public void getNumerator_GcdShouldBeUsed_ShouldBeMinPossibleValue() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(8000, 4000);
        assertThat(r1.getNumerator()).isEqualTo(2);
    }


    @Test
    public void getDenominator_GcdShouldBeUsed_ShouldBeMinPossibleValue() {
        AbstractRatioTestClass r1 = new AbstractRatioTestClass(8000, 4000);
        assertThat(r1.getDenominator()).isEqualTo(1);
    }


    @Test
    public void constructor_ZeroDenominator_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new AbstractRatioTestClass(4, 0);
        }).withMessageContaining("Denominator cannot be zero");
    }
}