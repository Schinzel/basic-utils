package io.schinzel.basicutils.ratio;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class IRatioDividedByTest {

    private class RatioDividedBy extends AbstractRatio<RatioDividedBy> implements IRatioDividedBy<RatioDividedBy> {
        RatioDividedBy(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioDividedBy newInstance(BigInteger num, BigInteger den) {
            return new RatioDividedBy(num.intValue(), den.intValue());
        }
    }


    @Test
    public void dividedBy_Ratio() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        RatioDividedBy r2 = new RatioDividedBy(2, 1);
        String actual = r1.dividedBy(r2).getStr().toString();
        assertThat(actual).isEqualTo("1/6");
    }


    @Test
    public void dividedBy_Int() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        String actual = r1.dividedBy(2).getStr().toString();
        assertThat(actual).isEqualTo("1/6");
    }


    @Test
    public void dividedBy_Long() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        String actual = r1.dividedBy(2L).getStr().toString();
        assertThat(actual).isEqualTo("1/6");
    }


    @Test
    public void dividedBy_BigInteger() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        String actual = r1.dividedBy(BigInteger.valueOf(2)).getStr().toString();
        assertThat(actual).isEqualTo("1/6");
    }


    @Test
    public void dividedBy_Int0_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new RatioDividedBy(1, 3).dividedBy(0);
        }).withMessageContaining("Cannot do division by zero");
    }


    @Test
    public void dividedBy_Long0_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new RatioDividedBy(1, 3).dividedBy(0L);
        }).withMessageContaining("Cannot do division by zero");
    }


    @Test
    public void dividedBy_BigInteger0_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new RatioDividedBy(1, 3).dividedBy(BigInteger.ZERO);
        }).withMessageContaining("Cannot do division by zero");
    }


    @Test
    public void dividedBy_Ratio0_Exception() {
        RatioDividedBy r1 = new RatioDividedBy(0, 1);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new RatioDividedBy(1, 3).dividedBy(r1);
        }).withMessageContaining("Cannot do division by zero");
    }

}
