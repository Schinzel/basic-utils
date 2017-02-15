package io.schinzel.basicutils;

/**
 * The purpose of this class is to offer less verbose exception throwing in
 * general and variable checking in particular.
 *
 * @author schinzel
 */
public class Thrower {

    /**
     * Package private constructor as this class should not be instantiated.
     */
    Thrower() {
    }


    /**
     * Throws runtime exception if the argument value with the argument name is
     * empty.
     *
     * @param value The value to check
     * @param variableName The name of the value to check
     */
    public static void throwIfEmpty(String value, String variableName) {
        if (Checker.isEmpty(value)) {
            throw new RuntimeException("Argument '" + variableName + "' cannot be empty");
        }
    }


    /**
     * Throw runtime exception if the argument object is null.
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
     * @param expression The expression to check
     * @param message The exception message
     */
    public static void throwIfFalse(boolean expression, String message) {
        if (!expression) {
            throw new RuntimeException(message);
        }
    }


    /**
     * Throws runtime exception if argument value is less than argument min or
     * larger than argument max.
     *
     * @param valueToCheck The value to check
     * @param variableName The name of the variable that holds the value to
     * check. Used to create more useful exception message.
     * @param min The minimum allowed value that the argument value can have
     * @param max The maximum allowed value that the argument value can have
     */
    public static void throwIfOutsideRange(int valueToCheck, String variableName, int min, int max) {
        Thrower.throwIfTrue((max < min), "Error using method. Max cannot be smaller than min.");
        Thrower.throwIfEmpty(variableName, "variable");
        Thrower.throwIfTooSmall(valueToCheck, variableName, min);
        Thrower.throwIfTooLarge(valueToCheck, variableName, max);
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     * check. Used to create more useful exception message.
     * @param min The min value the argument value should not be less than.
     */
    public static void throwIfTooSmall(int valueToCheck, String variableName, int min) {
        if (valueToCheck < min) {
            String message = String.format("The value %1$d in variable '%2$s' "
                    + "is too small. Min value is %3$d.", valueToCheck, variableName, min);
            throw new RuntimeException(message);
        }
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     * check. Used to create more useful exception message.
     * @param max The max value the argument value should not be larger than.
     */
    public static void throwIfTooLarge(int valueToCheck, String variableName, int max) {
        if (valueToCheck > max) {
            String message = String.format("The value %1$d in variable '%2$s' "
                    + "is too large. Max value is %3$d.", valueToCheck, variableName, max);
            throw new RuntimeException(message);
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
