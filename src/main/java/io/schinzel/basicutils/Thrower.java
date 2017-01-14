package io.schinzel.basicutils;

/**
 * The purpose of this class is to offer less verbose exception throwing. 
 *
 * @author schinzel
 */
public class Thrower {

    /**
     * Private constructor as this class should not be instantiated.
     */
    private Thrower() {
    }


    /**
     * Throws runtime exception if the argument value with the argument name is
     * empty.
     *
     * @param argValue The value to check
     * @param argName The name of the value to check
     */
    public static void throwIfEmpty(String argValue, String argName) {
        if (Checker.isEmpty(argValue)) {
            throw new RuntimeException("Argument '" + argName + "' cannot be empty");
        }
    }


    /**
     * Throw runtime exception if the argument object is null
     *
     * @param o The argument to check
     * @param message The exception message
     */
    public static void throwIfEmpty(Object o, String message) {
        if (o == null) {
            throw new RuntimeException(message);
        }
    }


    /**
     * Throw runtime exception if argument expression is false.
     *
     * @param expression
     * @param message The exception message
     */
    public static void throwIfFalse(boolean expression, String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }


    /**
     * Throw runtime exception if argument value is less than argument min or 
     * larger than argument max. 
     * 
     * @param value The value to check
     * @param valueName The name of the value to check
     * @param min The minimum allowed value that the argument value can have
     * @param max The maximum allowed value that the argument value can have
     */
    public static void throwIfOutsideRange(int value, String valueName, int min, int max) {
        if (max < min) {
            throw new RuntimeException("Error using method. Max cannot be smaller than min");
        }
        if (value < min || value > max) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requested value ").append(value).append(" for '").append(valueName).append("' is not allowed. Min value is ").append(min).append(" and max value is ").append(max).append(".");
            throw new RuntimeException(sb.toString());
        }
    }


    /**
     * Throw if argument expression is true.
     *
     * @param expression
     * @param message The exception message
     */
    public static void throwIfTrue(boolean expression, String message) {
        throwIfFalse(!expression, message);
    }


}
