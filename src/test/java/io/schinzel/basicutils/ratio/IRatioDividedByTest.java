package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;

public class IRatioDividedByTest extends AbstractIRatio<IRatioDividedByTest> implements IRatioDividedBy<IRatioDividedByTest> {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    public IRatioDividedByTest() {
        super(0, 0);
    }


    IRatioDividedByTest(int num, int den) {
        super(num, den);
    }


    @Override
    public IRatioDividedByTest newInstance(BigInteger numerator, BigInteger denominator) {
        return new IRatioDividedByTest(numerator.intValue(), denominator.intValue());
    }


    @Test
    public void testDividedBy() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        IRatioDividedByTest r2 = new IRatioDividedByTest(5, 4);
        r1.dividedBy(r2);
        Assert.assertEquals("4", r1.getNumerator().toString());
        Assert.assertEquals("15", r1.getDenominator().toString());
    }


    @Test
    public void testDividedByInt() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        r1.dividedBy(2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("6", r1.getDenominator().toString());
        r1 = new IRatioDividedByTest(2, 5);
        r1.dividedBy(3);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("15", r1.getDenominator().toString());
    }


    @Test
    public void testDividedByInt2() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        r1 = r1.dividedBy(1, 3);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
    }


    @Test
    public void testDivisionByZero() {
        IRatioDividedByTest r1 = new IRatioDividedByTest(1, 3);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot do division by zero.");
        r1.dividedBy(0);
    }

}
