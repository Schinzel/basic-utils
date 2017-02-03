package io.schinzel.basicutils;

import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to check variable and validate arguments.
 *
 * @author Schinzel
 */
public class Checker {

    /**
     * Package private constructor as this class should not be instantiated.
     */
    Checker() {
    }


    /**
     *
     * @param argValue
     * @return True if argument is null, else false.
     */
    public static boolean isEmpty(Object argValue) {
        return (argValue == null);
    }


    /**
     *
     * @param argValue
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(List argValue) {
        return (argValue == null || argValue.isEmpty());
    }


    /**
     *
     * @param argValue
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(Map argValue) {
        return (argValue == null || argValue.isEmpty());
    }


    /**
     *
     * @param argValue
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(String argValue) {
        return (argValue == null || argValue.length() == 0);
    }

    //*************************************************************************
    //* Array of Primitives
    //*************************************************************************

    /**
     *
     * @param argValue
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(byte[] argValue) {
        return (argValue == null || argValue.length == 0);
    }


    /**
     *
     * @param argValue
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(double[] argValue) {
        return (argValue == null || argValue.length == 0);
    }

    //*************************************************************************
    //* Array of Objects
    //*************************************************************************

    /**
     * Check if an array of objects is null or empty
     *
     * @param <T>
     * @param argValue
     * @return
     */
    public static <T> boolean isEmpty(T[] argValue) {
        return (argValue == null || argValue.length == 0);
    }

}
