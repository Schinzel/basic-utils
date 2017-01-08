package io.schinzel.basicutils;

import java.util.Collections;
import java.util.Map;

/**
 * These empty objects exists more efficiently use of RAM.
 *
 * @author Schinzel
 */
public class EmptyObjects {
    /**
     * An empty byte array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    /**
     * An empty byte array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    /**
     * An empty string array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    /**
     * An empty object array.
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    /**
     * An empty string.
     */
    public static final String EMPTY_STRING = "";
    /**
     * An empty map.
     */
    public static final Map EMPTY_MAP = Collections.emptyMap();

    /**
     * Private constructor as this class should not be instantiated. 
     */
    private EmptyObjects(){}
   
}
