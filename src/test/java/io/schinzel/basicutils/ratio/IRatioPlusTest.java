package io.schinzel.basicutils.ratio;

import lombok.experimental.Accessors;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;


@Accessors(prefix = "m")
public class IRatioPlusTest extends AbstractRatio<IRatioPlusTest> implements IRatioPlus<IRatioPlusTest> {

    public IRatioPlusTest() {
        this(1, 1);
    }


    IRatioPlusTest(int num, int den){
        super(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }

    @Override
    public IRatioPlusTest newInstance(BigInteger numerator, BigInteger denominator) {
        return new IRatioPlusTest(numerator.intValue(), denominator.intValue());
    }


    @Test
    public void testAdd() {
        IRatioPlusTest r1 = new IRatioPlusTest(1, 2);
        IRatioPlusTest r2 = new IRatioPlusTest(1, 4);
        r1 = r1.plus(r2);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
        //
        r1 = new IRatioPlusTest(10, 20);
        r2 = new IRatioPlusTest(4, 16);
        r1 = r1.plus(r2);
        Assert.assertEquals("3", r1.getNumerator().toString());
        Assert.assertEquals("4", r1.getDenominator().toString());
        //
        r1 = new IRatioPlusTest(10, 20);
        r1 = r1.plus(2);
        Assert.assertEquals("5", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        //
        r1 = new IRatioPlusTest(10, 20);
        r1 = r1.plus(4);
        Assert.assertEquals("9", r1.getNumerator().toString());
        Assert.assertEquals("2", r1.getDenominator().toString());
        //
        r1 = new IRatioPlusTest(1, 3);
        r1 = r1.plus(1, 3);
        Assert.assertEquals("2", r1.getNumerator().toString());
        Assert.assertEquals("3", r1.getDenominator().toString());
    }


}