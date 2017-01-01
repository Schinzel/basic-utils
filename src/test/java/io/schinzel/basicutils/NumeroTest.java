package io.schinzel.basicutils;

import io.schinzel.basicutils.Numero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class NumeroTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testPlusFourDecmal() {
        Numero n1 = new Numero(0.0001d);
        Numero n2 = new Numero(0.0001d);
        //
        assertEquals(0.0001d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0002d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0003d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0004d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0005d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0006d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0007d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0008d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.0009d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.001d, n1.getDouble(4), 0.0001);
    }


    @Test
    public void testPlusOneDecimal() {
        Numero n1 = new Numero(0.1d);
        Numero n2 = new Numero(0.1d);
        //
        assertEquals(0.1d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.2d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.3d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.4d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.5d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.6d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.7d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.8d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(0.9d, n1.getDouble(4), 0.0001);
        //
        n1 = n1.plus(n2);
        assertEquals(1.0d, n1.getDouble(4), 0.0001);
    }


    @Test
    public void test_equals() {
        Numero n1 = new Numero(0.1d);
        Numero n2 = new Numero(0.1d);
        assertTrue(n1.equals(n2));
        //
        n1 = new Numero(0.1d);
        n2 = new Numero(0.2d);
        assertFalse(n1.equals(n2));
    }


    @Test
    public void test_compareTo() {
        Numero n1 = new Numero(0.1d);
        Numero n2 = new Numero(0.1d);
        assertEquals(0, n1.compareTo(n2));
        //
        n1 = new Numero(0.00000001d);
        n2 = new Numero(0.00000001d);
        assertEquals(0, n1.compareTo(n2));
        //
        n1 = new Numero(10000000000000.00000001d);
        n2 = new Numero(10000000000000.00000001d);
        assertEquals(0, n1.compareTo(n2));
        //
        n1 = new Numero(1000.00000001d);
        n2 = new Numero(1000.00000002d);
        assertTrue(n1.compareTo(n2) < 0);
        //
        n1 = new Numero(1000.00000002d);
        n2 = new Numero(1000.00000002d);
        assertTrue(n1.compareTo(n2) == 0);
        //
        n1 = new Numero(1000.00000002d);
        n2 = new Numero(1000.00000001d);
        assertTrue(n1.compareTo(n2) > 0);
    }


    @Test
    public void test_compareTo2() {
        Numero n1 = new Numero(0.3d);
        Numero n2 = new Numero(0.1d);
        Numero n3 = new Numero(0.2d);
        List<Numero> list = new ArrayList<>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        Collections.sort(list);
        assertEquals(n2, list.get(0));
        assertEquals(n3, list.get(1));
        assertEquals(n1, list.get(2));

    }


    @Test
    public void test_add() {
        Numero n1 = new Numero(0.2d);
        Numero n2 = new Numero(0.1d);
        Numero result = n1.plus(n2);
        ae(0.3, result.getDouble(1));
        //
        n1 = new Numero(0.0001d);
        n2 = new Numero(0.0002d);
        result = n1.plus(n2);
        ae(0.0003, result.getDouble(4));
        //
        n1 = new Numero(1000000000.0001d);
        n2 = new Numero(0.0002d);
        result = n1.plus(n2);
        ae(1000000000.0003, result.getDouble(4));
        //
        n1 = new Numero(0.0002d);
        n2 = new Numero(0.0001d);
        result = n1.plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2)
                .plus(n2);
        ae(0.0013, result.getDouble(4));

    }


    @Test
    public void test_multiply() {
        Numero n1 = new Numero(0.1d);
        Numero n2 = new Numero(0.2d);
        Numero result = n1.multiply(n2);
        ae(0.02, result.getDouble(2));
        //
        n1 = new Numero(1.0d);
        n2 = new Numero(0.1d);
        result = n1.multiply(n2)
                .multiply(n2)
                .multiply(n2)
                .multiply(n2);
        ae(0.0001, result.getDouble(4));
    }


    @Test
    public void test_getDouble() {
        double d;
        Numero n;
        //
        d = 123.45;
        n = new Numero(d);
        ae(d, n.getDouble());
        //
        d = 123.4567;
        n = new Numero(d);
        ae(123., n.getDouble(0));
        ae(123.5, n.getDouble(1));
        ae(123.46, n.getDouble());
        ae(123.46, n.getDouble(2));
        ae(123.457, n.getDouble(3));
        ae(123.4567, n.getDouble(4));
    }


    @Test
    public void test_getDouble_halfEvenRoudning() {
        double d;
        Numero n;
        //Should round down 
        d = 123.45;
        n = new Numero(d);
        ae(123.4, n.getDouble(1));
        //Should round up 
        d = 123.55;
        n = new Numero(d);
        ae(123.6, n.getDouble(1));
    }


    @Test
    public void test_getDoubleRangeHigh() {
        exception.expect(RuntimeException.class);
        new Numero(123.45).getDouble(5);
    }


    @Test
    public void test_getDoubleRangeLow() {
        exception.expect(RuntimeException.class);
        new Numero(123.45).getDouble(-1);
    }


    @Test
    public void test_multiplyByRatio() {
        Numero n1 = new Numero(0.2d);
        Numero numerator = new Numero(1.0d);
        Numero denominator = new Numero(4.0d);
        Numero result = n1.multiplyByRatio(numerator, denominator);
        ae(0.05, result.getDouble());
        //
        //Non terminating decimal
        n1 = new Numero(10d);
        numerator = new Numero(1.0d);
        denominator = new Numero(3.0d);
        result = n1.multiplyByRatio(numerator, denominator);
        ae(3.3333d, result.getDouble(4));
    }
    

    public void ae(double expected, double result) {
        if (Double.compare(expected, result) != 0) {
            throw new AssertionError("Expected " + expected + " but got " + result);
        }
    }

}
