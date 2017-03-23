package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@Accessors(prefix = "m")
public class IRatioCompareTest implements IRatioCompare<IRatioCompareTest> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;



    @Override
    public IRatioCompareTest getThis() {
        return this;
    }


    private static IRatioCompareTest create(int num, int den) {
        return new IRatioCompareTest().setRatio(BigInteger.valueOf(num), BigInteger.valueOf(den));
    }


    @Test
    public void testCompare() {
        IRatioCompareTest r1 = IRatioCompareTest.create(1, 3);
        IRatioCompareTest r2 = IRatioCompareTest.create(1, 3);
        int result = r1.compareTo(r2);
        Assert.assertEquals(0, result);
        //
        r1 = IRatioCompareTest.create(1, 3);
        r2 = IRatioCompareTest.create(2, 3);
        result = r1.compareTo(r2);
        Assert.assertThat(result, Matchers.lessThan(0));
        //
        result = r2.compareTo(r1);
        Assert.assertThat(result, Matchers.greaterThan(0));
    }


    @Test
    public void testGreaterThan() {
        IRatioCompareTest r1 = IRatioCompareTest.create(1, 4);
        IRatioCompareTest r2 = IRatioCompareTest.create(2, 4);
        IRatioCompareTest r3 = IRatioCompareTest.create(3, 4);
        IRatioCompareTest r4 = IRatioCompareTest.create(4, 4);
        assertTrue(r4.greaterThan(r3));
        assertTrue(r3.greaterThan(r2));
        assertTrue(r2.greaterThan(r1));
    }


    @Test
    public void testGreaterThan2() {
        IRatioCompareTest r1 = IRatioCompareTest.create(1, 4);
        IRatioCompareTest r2 = IRatioCompareTest.create(1, 3);
        IRatioCompareTest r3 = IRatioCompareTest.create(1, 2);
        assertTrue(r3.greaterThan(r2));
        assertTrue(r2.greaterThan(r1));
    }


    @Test
    public void testLessThan() {
        IRatioCompareTest r1 = IRatioCompareTest.create(4, 4);
        IRatioCompareTest r2 = IRatioCompareTest.create(3, 4);
        IRatioCompareTest r3 = IRatioCompareTest.create(2, 4);
        IRatioCompareTest r4 = IRatioCompareTest.create(1, 4);
        assertTrue(r4.lessThan(r3));
        assertTrue(r3.lessThan(r2));
        assertTrue(r2.lessThan(r1));
    }


    @Test
    public void testLessThan2() {
        IRatioCompareTest r1 = IRatioCompareTest.create(1, 2);
        IRatioCompareTest r2 = IRatioCompareTest.create(1, 3);
        IRatioCompareTest r3 = IRatioCompareTest.create(1, 4);
        assertTrue(r3.lessThan(r2));
        assertTrue(r2.lessThan(r1));
    }


    @Test
    public void testSort() {
        IRatioCompareTest r1 = IRatioCompareTest.create(3, 4);
        IRatioCompareTest r2 = IRatioCompareTest.create(1, 4);
        IRatioCompareTest r3 = IRatioCompareTest.create(2, 4);
        List<IRatioCompareTest> list = Arrays.asList(r1, r2, r3);
        Collections.sort(list);
        assertTrue(list.get(0).lessThan(list.get(1)));
        assertTrue(list.get(1).lessThan(list.get(2)));
    }


    @Test
    public void testEquals() {
        IRatioCompareTest r1 = IRatioCompareTest.create(2, 4);
        IRatioCompareTest r2 = IRatioCompareTest.create(1, 2);
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));
        r1 = IRatioCompareTest.create(1, 3);
        r2 = IRatioCompareTest.create(1, 2);
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
    }

}
