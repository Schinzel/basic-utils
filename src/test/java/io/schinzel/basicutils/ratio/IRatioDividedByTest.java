package io.schinzel.basicutils.ratio;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class IRatioDividedByTest extends AbstractRatio<IRatioDividedByTest> implements IRatioDividedBy<IRatioDividedByTest> {

    public IRatioDividedByTest() {
        this(1, 1);
    }

    IRatioDividedByTest(int num, int den){
        super(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Override
    public IRatioDividedByTest newInstance(BigInteger numerator, BigInteger denominator) {
        return new IRatioDividedByTest(numerator.intValue(), denominator.intValue());
    }


    @Test
    public void testDividedBy() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        IRatioDividedByTest r2 = new IRatioDividedByTest(5, 4);
        r1 = r1.dividedBy(r2);
        assertThat(r1.getNumerator()).isEqualTo(4);
        assertThat(r1.getDenominator()).isEqualTo(15);
    }


    @Test
    public void testDividedByInt() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        r1 = r1.dividedBy(2);
        assertThat(r1.getNumerator()).isEqualTo(1);
        assertThat(r1.getDenominator()).isEqualTo(6);
        r1 = new IRatioDividedByTest(2, 5);
        r1 = r1.dividedBy(3);
        assertThat(r1.getNumerator()).isEqualTo(2);
        assertThat(r1.getDenominator()).isEqualTo(15);
    }


    @Test
    public void testDividedByInt2() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        r1 = r1.dividedBy(1, 3);
        assertThat(r1.getNumerator()).isEqualTo(1);
        assertThat(r1.getDenominator()).isEqualTo(1);
    }


    @Test
    public void testDivisionByZero() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            new IRatioDividedByTest(1, 3).dividedBy(0);
        }).withMessageContaining("Cannot do division by zero.");
    }

}
