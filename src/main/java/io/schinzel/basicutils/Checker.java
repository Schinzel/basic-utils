package io.schinzel.basicutils;

import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to check variable and validate arguments.
 *
 * @author Schinzel
 */
@SuppressWarnings("WeakerAccess")
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
     * @param <T>   The type of the list
     * @return True if argument is null or empty, else false.
     */
    public static <T> boolean isEmpty(List<T> value) {
        return (value == null || value.isEmpty());
    }


    /**
     * @param value The value to check
     * @param <T>   The type of the list
     * @return True if argument is not null or empty, else false.
     */
    public static <T> boolean isNotEmpty(List<T> value) {
        return !Checker.isEmpty(value);
    }


    /**
     * @param value The value to check
     * @param <K>   The type of the key of the map
     * @param <V>   The value of the key of the map
     * @return True if argument is null or empty, else false.
     */
    public static <K, V> boolean isEmpty(Map<K, V> value) {
        return (value == null || value.isEmpty());
    }


    /**
     * @param value The value to check
     * @param <K>   The type of the key of the map
     * @param <V>   The value of the key of the map
     * @return True if argument is not null nor empty, else false.
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> value) {
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
     * @param value The type of the value to check
     * @return True if argument is not empty nor length 0, else false.
     */
    public static <T> boolean isNotEmpty(T[] value) {
        return !Checker.isEmpty(value);
    }


    /**
     * Check if a varargs of objects is empty. Is a special case as
     * for example for string varargs (String)null as argument
     * is represented as a new String[]{null}
     *
     * @param value The value to check
     * @return True if argument is empty, else false.
     */
    public static boolean isEmptyVarArgs(String... value) {
        return (value == null || value.length == 0 || value[0] == null);
    }


    /**
     * Check if a varargs of objects is not empty.
     *
     * @param value The value to check
     * @return True if argument is not empty, else false.
     */
    public static boolean isNotEmptyVarArgs(String... value) {
        //return (value != null && value.length > 0 || value[0] != null);
        return !Checker.isEmptyVarArgs(value);
    }

}
