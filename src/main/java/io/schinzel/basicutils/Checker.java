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
     * @param value The value to check
     * @return True if argument is null, else false.
     */
    public static boolean isEmpty(Object value) {
        return (value == null);
    }


    /**
     * @param value The value to check
     * @return True if argument is not null, else false.
     */
    public static boolean isNotEmpty(Object value) {
        return (value != null);
    }


    /**
     * @param value The value to check
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(List value) {
        return (value == null || value.isEmpty());
    }


    /**
     * @param value The value to check
     * @return True if argument is not null or empty, else false.
     */
    public static boolean isNotEmpty(List value) {
        return !Checker.isEmpty(value);
    }


    /**
     * @param value The value to check
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(Map value) {
        return (value == null || value.isEmpty());
    }


    /**
     * @param value The value to check
     * @return True if argument is not null nor empty, else false.
     */
    public static boolean isNotEmpty(Map value) {
        return !Checker.isEmpty(value);
    }


    /**
     * @param value The value to check
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(String value) {
        return (value == null || value.length() == 0);
    }


    /**
     * @param value The value to check
     * @return True if argument is not null nor empty, else false.
     */
    public static boolean isNotEmpty(String value) {
        return !Checker.isEmpty(value);
    }


    /**
     * @param value The value to check
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(byte[] value) {
        return (value == null || value.length == 0);
    }


    /**
     * @param value The value to check
     * @return True if argument is not null nor empty, else false.
     */
    public static boolean isNotEmpty(byte[] value) {
        return !Checker.isEmpty(value);
    }


    /**
     * @param value The value to check
     * @return True if argument is null or empty, else false.
     */
    public static boolean isEmpty(double[] value) {
        return (value == null || value.length == 0);
    }


    /**
     * @param value The value to check
     * @return True if argument is not null nor empty, else false.
     */
    public static boolean isNotEmpty(double[] value) {
        return !Checker.isEmpty(value);
    }


    /**
     * Check if an array of objects is null or empty
     *
     * @param <T>   The type of the array
     * @param value The value to check
     * @return True if argument is empty or length 0, else false.
     */
    public static <T> boolean isEmpty(T[] value) {
        return (value == null || value.length == 0);
    }


    /**
     * Check if an array of objects is null or empty
     *
     * @param <T>   The type of the array
     * @param value The value to check
     * @return True if argument is not empty nor length 0, else false.
     */
    public static <T> boolean isNotEmpty(T[] value) {
        return !Checker.isEmpty(value);
    }
}
