package io.schinzel.basicutils;

/**
 * The purpose of this class is to offer less verbose error throwing.
 *
 * @author schinzel
 */
public class Thrower {

    /**
     * Throws error if the argument value with the argument name is empty.
     *
     * @param argValue The value to check
     * @param argName The name of the value to check
     */
    public static void throwErrorIfEmpty(String argValue, String argName) {
        if (Checker.isEmpty(argValue)) {
            throw new RuntimeException("Argument '" + argName + "' cannot be empty");
        }
    }

    /**
     * Throw error if the argument object is null
     *
     * @param o The argument to check
     * @param errorMessage The error message to throw.
     */
    public static void throwErrorIfEmpty(Object o, String errorMessage) {
        if (o == null) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * Throw error if argument expression is false.
     *
     * @param expression
     * @param errorMessage The error message to throw
     */
    public static void throwErrorIfFalse(boolean expression, String errorMessage) {
        if (!expression) {
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     *
     * @param value The value to check
     * @param valueName The name of the value to check
     * @param min The minimum allowed value that the argument value can have
     * @param max The maximum allowed value that the argument value can have
     */
    public static void throwErrorIfOutsideRange(int value, String valueName, int min, int max) {
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
     * Throw error if argument expression is true.
     *
     * @param expression
     * @param errorMessage The error message to throw
     */
    public static void throwErrorIfTrue(boolean expression, String errorMessage) {
        throwErrorIfFalse(!expression, errorMessage);
    }

}
