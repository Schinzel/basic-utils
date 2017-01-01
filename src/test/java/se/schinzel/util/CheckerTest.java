package se.schinzel.util;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class CheckerTest {

    @Test
    public void testIsEmptyList() {
        List list = null;
        assertTrue(Checker.isEmpty(list));
        ArrayList arrayList = null;
        assertTrue(Checker.isEmpty(arrayList));
        arrayList = new ArrayList();
        assertTrue(Checker.isEmpty(arrayList));
        arrayList.add("monkey");
        assertFalse(Checker.isEmpty(arrayList));
        arrayList.remove("monkey");
        assertTrue(Checker.isEmpty(arrayList));
        arrayList.add("monkey");
        assertFalse(Checker.isEmpty(arrayList));
        arrayList.clear();
        assertTrue(Checker.isEmpty(arrayList));
    }


    @Test
    public void testIsEmptyObject() {
        ArbitraryObjectForTestingIsEmpty o = null;
        assertTrue(Checker.isEmpty(o));
        o = new ArbitraryObjectForTestingIsEmpty();
        assertFalse(Checker.isEmpty(o));
    }


    @Test
    public void testIsEmptyString() {
        String str = null;
        assertTrue(Checker.isEmpty(str));
        str = "";
        assertTrue(Checker.isEmpty(str));
        str = "monkey";
        assertFalse(Checker.isEmpty(str));
    }


    @Test
    public void testIsEmptyStringArray() {
        String[] arr = null;
        assertTrue(Checker.isEmpty(arr));
        arr = new String[0];
        assertTrue(Checker.isEmpty(arr));
        arr = new String[1];
        assertFalse(Checker.isEmpty(arr));
        arr = new String[]{"monkey"};
        assertFalse(Checker.isEmpty(arr));
    }


    @Test
    public void testIsEmptyByteArray() {
        byte[] arr = null;
        assertTrue(Checker.isEmpty(arr));
        arr = new byte[0];
        assertTrue(Checker.isEmpty(arr));
        arr = new byte[1];
        assertFalse(Checker.isEmpty(arr));
        arr = new byte[]{1};
        assertFalse(Checker.isEmpty(arr));
    }


    @Test
    public void testIsEmptyDoubleArray() {
        double[] arr = null;
        assertTrue(Checker.isEmpty(arr));

        arr = new double[0];
        assertTrue(Checker.isEmpty(arr));

        arr = new double[1];
        assertFalse(Checker.isEmpty(arr));

        arr = new double[]{1d};
        assertFalse(Checker.isEmpty(arr));
    }


    @Test
    public void testObjectArray() {
        ArbitraryObjectForTestingIsEmpty[] arr = null;
        assertTrue(Checker.isEmpty(arr));

        arr = new ArbitraryObjectForTestingIsEmpty[0];
        assertTrue(Checker.isEmpty(arr));

        arr = new ArbitraryObjectForTestingIsEmpty[1];
        assertFalse(Checker.isEmpty(arr));

        arr = new ArbitraryObjectForTestingIsEmpty[]{new ArbitraryObjectForTestingIsEmpty()};
        assertFalse(Checker.isEmpty(arr));
    }

    static class ArbitraryObjectForTestingIsEmpty {
    }
}
