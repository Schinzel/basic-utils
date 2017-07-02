package io.schinzel.basicutils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.*;


/**
 * @author schinzel
 */
public class CheckerTest extends Checker {

    @Test
    public void testIsEmptyMap() {
        assertTrue(Checker.isEmpty(EmptyObjects.EMPTY_MAP));
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        assertFalse(Checker.isEmpty(map));
    }


    @Test
    public void testIsNotEmpty_NullMap() {
        assertTrue(Checker.isEmpty(EmptyObjects.EMPTY_MAP));
        Map<String, String> map = null;
        assertFalse(Checker.isNotEmpty(map));
    }


    @Test
    public void testIsNotEmpty_EmptyMap() {
        assertTrue(Checker.isEmpty(EmptyObjects.EMPTY_MAP));
        Map<String, String> map = new HashMap<>();
        assertFalse(Checker.isNotEmpty(map));
    }


    @Test
    public void testIsNotEmpty_NotEmptyMap() {
        assertTrue(Checker.isEmpty(EmptyObjects.EMPTY_MAP));
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        assertTrue(Checker.isNotEmpty(map));
    }


    @Test
    public void testIsEmptyList() {
        List list = null;
        assertTrue(Checker.isEmpty(list));
        List<String> arrayList = null;
        assertTrue(Checker.isEmpty(arrayList));
        arrayList = new ArrayList<>();
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
    public void testIsNotEmpty_NullList() {
        List list = null;
        assertFalse(Checker.isNotEmpty(list));
    }


    @Test
    public void testIsNotEmpty_EmptyList() {
        List list = new ArrayList();
        assertFalse(Checker.isNotEmpty(list));
    }


    @Test
    public void testIsNotEmpty_NotEmptyList() {
        List<String> list = new ArrayList<>();
        list.add("bear");
        assertTrue(Checker.isNotEmpty(list));
    }


    @Test
    public void testIsEmptyObject() {
        ArbitraryObjectForTestingIsEmpty o = null;
        assertTrue(Checker.isEmpty(o));
        o = new ArbitraryObjectForTestingIsEmpty();
        assertFalse(Checker.isEmpty(o));
    }


    @Test
    public void testIsNotEmpty_NullObject() {
        ArbitraryObjectForTestingIsEmpty o = null;
        assertFalse(Checker.isNotEmpty(o));
    }


    @Test
    public void testIsNotEmpty_NotEmptyObject() {
        ArbitraryObjectForTestingIsEmpty o = new ArbitraryObjectForTestingIsEmpty();
        assertTrue(Checker.isNotEmpty(o));
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
    public void testIsNotEmpty_NullString() {
        String str = null;
        assertFalse(Checker.isNotEmpty(str));
    }


    @Test
    public void testIsNotEmpty_EmptyString() {
        String str = "";
        assertFalse(Checker.isNotEmpty(str));
    }


    @Test
    public void testIsNotEmpty_NotEmptyString() {
        String str = "Bird";
        assertTrue(Checker.isNotEmpty(str));
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
    public void testIsNotEmpty_NullStringArray() {
        String[] arr = null;
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_EmptyStringArray() {
        String[] arr = new String[0];
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_NotEmptyStringArray() {
        String[] arr = new String[1];
        assertTrue(Checker.isNotEmpty(arr));
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
    public void testIsNotEmpty_NullByteArray() {
        byte[] arr = null;
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_EmptyByteArray() {
        byte[] arr = new byte[0];
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_NotEmptyByteArray() {
        byte[] arr = new byte[1];
        assertTrue(Checker.isNotEmpty(arr));
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
    public void testIsNotEmpty_NullDoubleArray() {
        double[] arr = null;
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_EmptyDoubleArray() {
        double[] arr = new double[0];
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_NotEmptyDoubleArray() {
        double[] arr = new double[1];
        assertTrue(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsEmptyObjectArray() {
        ArbitraryObjectForTestingIsEmpty[] arr = null;
        assertTrue(Checker.isEmpty(arr));
        arr = new ArbitraryObjectForTestingIsEmpty[0];
        assertTrue(Checker.isEmpty(arr));
        arr = new ArbitraryObjectForTestingIsEmpty[1];
        assertFalse(Checker.isEmpty(arr));
        arr = new ArbitraryObjectForTestingIsEmpty[]{new ArbitraryObjectForTestingIsEmpty()};
        assertFalse(Checker.isEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_NullObjectArray() {
        ArbitraryObjectForTestingIsEmpty[] arr = null;
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_EmptyObjectArray() {
        ArbitraryObjectForTestingIsEmpty[] arr = new ArbitraryObjectForTestingIsEmpty[0];
        assertFalse(Checker.isNotEmpty(arr));
    }


    @Test
    public void testIsNotEmpty_NotEmptyObjectArray() {
        ArbitraryObjectForTestingIsEmpty[] arr = new ArbitraryObjectForTestingIsEmpty[1];
        assertTrue(Checker.isNotEmpty(arr));
    }


    @Test
    public void isEmptyVarArg_NullString_True() {
        assertThat(Checker.isEmptyVarArgs((String) null)).isTrue();
    }


    @Test
    public void isEmptyVarArg_NullStringArray_True() {
        assertThat(Checker.isEmptyVarArgs((String[]) null)).isTrue();
    }


    @Test
    public void isEmptyVarArg_EmptyStringArray_True() {
        assertThat(Checker.isEmptyVarArgs(new String[0])).isTrue();
    }


    @Test
    public void isNotEmptyVarArg_NonEmptyString_True() {
        assertThat(Checker.isNotEmptyVarArgs("monkey")).isTrue();
    }


    @Test
    public void isNotEmptyVarArg_OneElemStringArray_True() {
        String[] arr = {"monkey"};
        assertThat(Checker.isNotEmptyVarArgs(arr)).isTrue();
    }


    static class ArbitraryObjectForTestingIsEmpty {
    }
}
