package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;

@Accessors(prefix = "m")
public class IRatioDividedByTest implements IRatioDividedBy<IRatioDividedByTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Override
    public IRatioDividedByTest getThis() {
        return this;
    }


    private static IRatioDividedByTest create(int num, int den) {
        return new IRatioDividedByTest().setRatio(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Test
    public void testDividedBy() {
        IRatioDividedByTest r1 = IRatioDividedByTest.create(1, 3);
        IRatioDividedByTest r2 = IRatioDividedByTest.create(5, 4);
        r1.dividedBy(r2);
        Assert.assertEquals("4", r1.getNumerator().toString());
        Assert.assertEquals("15", r1.getDenominator().toString());
    }


    @Test
    public void testDividedByInt() {
        IRatioDividedByTest r1 = IRatioDividedByTest.create(1, 3);
        r1.dividedBy(2);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("6", r1.getDenominator().toString());
        r1 = IRatioDividedByTest.create(2, 5);
        r1.dividedBy(3);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("15", r1.getDenominator().toString());
    }


    @Test
    public void testDividedByInt2() {
        IRatioDividedByTest r1 = IRatioDividedByTest.create(1, 3);
        r1.dividedBy(1, 3);
        Assert.assertEquals("1", r1.getNumerator().toString());
        Assert.assertEquals("1", r1.getDenominator().toString());
    }


    @Test
    public void testDivisionByZero() {
        IRatioDividedByTest r1 = IRatioDividedByTest.create(1, 3);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Cannot do division by zero.");
        r1.dividedBy(0);
    }

}
