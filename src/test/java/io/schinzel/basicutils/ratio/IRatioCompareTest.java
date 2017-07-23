package io.schinzel.basicutils.ratio;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class IRatioCompareTest {

    private class RatioCompareTest extends AbstractRatio<RatioCompareTest> implements IRatioCompare<RatioCompareTest> {
        RatioCompareTest(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioCompareTest newInstance(BigInteger numerator, BigInteger denominator) {
            throw new RuntimeException("Not implemented!");
        }

    }


    @Test
    public void testCompare() {
        RatioCompareTest r1 = new RatioCompareTest(1, 3);
        RatioCompareTest r2 = new RatioCompareTest(1, 3);
        int result = r1.compareTo(r2);
        Assert.assertEquals(0, result);
        //
        r1 = new RatioCompareTest(1, 3);
        r2 = new RatioCompareTest(2, 3);
        result = r1.compareTo(r2);
        assertThat(result).isLessThan(0);
        //
        result = r2.compareTo(r1);
        assertThat(result).isGreaterThan(0);
    }


    @Test
    public void testGreaterThan() {
        RatioCompareTest r1 = new RatioCompareTest(1, 4);
        RatioCompareTest r2 = new RatioCompareTest(2, 4);
        RatioCompareTest r3 = new RatioCompareTest(3, 4);
        RatioCompareTest r4 = new RatioCompareTest(4, 4);
        assertTrue(r4.greaterThan(r3));
        assertTrue(r3.greaterThan(r2));
        assertTrue(r2.greaterThan(r1));
    }


    @Test
    public void testGreaterThan2() {
        RatioCompareTest r1 = new RatioCompareTest(1, 4);
        RatioCompareTest r2 = new RatioCompareTest(1, 3);
        RatioCompareTest r3 = new RatioCompareTest(1, 2);
        assertTrue(r3.greaterThan(r2));
        assertTrue(r2.greaterThan(r1));
    }


    @Test
    public void testLessThan() {
        RatioCompareTest r1 = new RatioCompareTest(4, 4);
        RatioCompareTest r2 = new RatioCompareTest(3, 4);
        RatioCompareTest r3 = new RatioCompareTest(2, 4);
        RatioCompareTest r4 = new RatioCompareTest(1, 4);
        assertTrue(r4.lessThan(r3));
        assertTrue(r3.lessThan(r2));
        assertTrue(r2.lessThan(r1));
    }


    @Test
    public void testLessThan2() {
        RatioCompareTest r1 = new RatioCompareTest(1, 2);
        RatioCompareTest r2 = new RatioCompareTest(1, 3);
        RatioCompareTest r3 = new RatioCompareTest(1, 4);
        assertTrue(r3.lessThan(r2));
        assertTrue(r2.lessThan(r1));
    }


    @Test
    public void testSort() {
        RatioCompareTest r1 = new RatioCompareTest(3, 4);
        RatioCompareTest r2 = new RatioCompareTest(1, 4);
        RatioCompareTest r3 = new RatioCompareTest(2, 4);
        List<RatioCompareTest> list = Arrays.asList(r1, r2, r3);
        Collections.sort(list);
        assertTrue(list.get(0).lessThan(list.get(1)));
        assertTrue(list.get(1).lessThan(list.get(2)));
    }


    @Test
    public void testEquals() {
        RatioCompareTest r1 = new RatioCompareTest(2, 4);
        RatioCompareTest r2 = new RatioCompareTest(1, 2);
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));
        r1 = new RatioCompareTest(1, 3);
        r2 = new RatioCompareTest(1, 2);
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
    }

}
