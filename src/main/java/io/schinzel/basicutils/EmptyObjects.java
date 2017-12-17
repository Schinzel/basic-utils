package io.schinzel.basicutils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * These empty objects exists to more efficiently use RAM.
 *
 * @author Schinzel
 */
public class EmptyObjects {
    /** An empty byte array. */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /** An empty int array. */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /** An empty long array. */
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    /** An empty double array. */
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    /** An empty float array. */
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    /** An empty boolean array. */
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    /** An empty string array. */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /** An empty object array. */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /** An empty string. */
    public static final String EMPTY_STRING = "";


    /**
     * Package private constructor as this class should not be instantiated.
     */
    EmptyObjects() {
    }


    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }


    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }


    public static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

}