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
    public void testDividedBy() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        RatioDividedBy r2 = new RatioDividedBy(5, 4);
        r1 = r1.dividedBy(r2);
        assertThat(r1.getNumerator()).isEqualTo(4);
        assertThat(r1.getDenominator()).isEqualTo(15);
    }


    @Test
    public void testDividedByInt() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        r1 = r1.dividedBy(2);
        assertThat(r1.getNumerator()).isEqualTo(1);
        assertThat(r1.getDenominator()).isEqualTo(6);
        r1 = new RatioDividedBy(2, 5);
        r1 = r1.dividedBy(3);
        assertThat(r1.getNumerator()).isEqualTo(2);
        assertThat(r1.getDenominator()).isEqualTo(15);
    }


    @Test
    public void testDividedByInt2() {
        RatioDividedBy r1 = new RatioDividedBy(1, 3);
        r1 = r1.dividedBy(1, 3);
        assertThat(r1.getNumerator()).isEqualTo(1);
        assertThat(r1.getDenominator()).isEqualTo(1);
    }


    @Test
    public void testDivisionByZero() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new RatioDividedBy(1, 3).dividedBy(0);
        }).withMessageContaining("Cannot do division by zero.");
    }

}
