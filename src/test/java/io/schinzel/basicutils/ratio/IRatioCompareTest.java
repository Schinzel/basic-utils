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

    private class RatioCompare extends AbstractRatio<RatioCompare> implements IRatioCompare<RatioCompare> {
        RatioCompare(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioCompare newInstance(BigInteger numerator, BigInteger denominator) {
            throw new RuntimeException("Not implemented!");
        }

    }


    @Test
    public void testCompare() {
        RatioCompare r1 = new RatioCompare(1, 3);
        RatioCompare r2 = new RatioCompare(1, 3);
        int result = r1.compareTo(r2);
        Assert.assertEquals(0, result);
        //
        r1 = new RatioCompare(1, 3);
        r2 = new RatioCompare(2, 3);
        result = r1.compareTo(r2);
        assertThat(result).isLessThan(0);
        //
        result = r2.compareTo(r1);
        assertThat(result).isGreaterThan(0);
    }


    @Test
    public void testGreaterThan() {
        RatioCompare r1 = new RatioCompare(1, 4);
        RatioCompare r2 = new RatioCompare(2, 4);
        RatioCompare r3 = new RatioCompare(3, 4);
        RatioCompare r4 = new RatioCompare(4, 4);
        assertTrue(r4.greaterThan(r3));
        assertTrue(r3.greaterThan(r2));
        assertTrue(r2.greaterThan(r1));
    }


    @Test
    public void testGreaterThan2() {
        RatioCompare r1 = new RatioCompare(1, 4);
        RatioCompare r2 = new RatioCompare(1, 3);
        RatioCompare r3 = new RatioCompare(1, 2);
        assertTrue(r3.greaterThan(r2));
        assertTrue(r2.greaterThan(r1));
    }


    @Test
    public void testLessThan() {
        RatioCompare r1 = new RatioCompare(4, 4);
        RatioCompare r2 = new RatioCompare(3, 4);
        RatioCompare r3 = new RatioCompare(2, 4);
        RatioCompare r4 = new RatioCompare(1, 4);
        assertTrue(r4.lessThan(r3));
        assertTrue(r3.lessThan(r2));
        assertTrue(r2.lessThan(r1));
    }


    @Test
    public void testLessThan2() {
        RatioCompare r1 = new RatioCompare(1, 2);
        RatioCompare r2 = new RatioCompare(1, 3);
        RatioCompare r3 = new RatioCompare(1, 4);
        assertTrue(r3.lessThan(r2));
        assertTrue(r2.lessThan(r1));
    }


    @Test
    public void testSort() {
        RatioCompare r1 = new RatioCompare(3, 4);
        RatioCompare r2 = new RatioCompare(1, 4);
        RatioCompare r3 = new RatioCompare(2, 4);
        List<RatioCompare> list = Arrays.asList(r1, r2, r3);
        Collections.sort(list);
        assertTrue(list.get(0).lessThan(list.get(1)));
        assertTrue(list.get(1).lessThan(list.get(2)));
    }


    @Test
    public void testEquals() {
        RatioCompare r1 = new RatioCompare(2, 4);
        RatioCompare r2 = new RatioCompare(1, 2);
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));
        r1 = new RatioCompare(1, 3);
        r2 = new RatioCompare(1, 2);
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
    }

}
