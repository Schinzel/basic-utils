package io.schinzel.basicutils.thrower;

import io.schinzel.basicutils.Checker;

import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to offer less verbose exception throwing in
 * general and variable checking in particular.
 *
 * @author schinzel
 */
@SuppressWarnings("WeakerAccess")
public class Thrower {
    private final static Thrower INSTANCE = new Thrower();


    /**
     * Throws runtime exception if the argument value with the argument name is null.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value
     */
    public static Thrower throwIfVarNull(Object value, String variableName) {
        ThrowerMessage.create(value == null).message("Argument '" + variableName + "' cannot be null");
        return INSTANCE;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is empty.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value
     */
    public static Thrower throwIfVarEmpty(String value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message("Argument '" + variableName + "' cannot be empty");
        return INSTANCE;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is empty.
     *
     * @param <T>          The type of the list
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value
     */
    public static <T> Thrower throwIfVarEmpty(List<T> value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message("Argument '" + variableName + "' cannot be empty");
        return INSTANCE;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is empty.
     *
     * @param <K>          The type of the keys in the map
     * @param <V>          The type of the values in the map
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value
     */
    public static <K, V> Thrower throwIfVarEmpty(Map<K, V> value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message("Argument '" + variableName + "' cannot be empty");
        return INSTANCE;
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param min          The min value the argument value should not be less than.
     * @return The argument value
     */
    public static Thrower throwIfVarTooSmall(int valueToCheck, String variableName, int min) {
        Thrower.throwIfTrue(valueToCheck < min)
                .message("The value %1$d in variable '%2$s' is too small. Min value is %3$d.", valueToCheck, variableName, min);
        return INSTANCE;
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param max          The max value the argument value should not be larger than.
     * @return The argument value
     */
    public static Thrower throwIfVarTooLarge(int valueToCheck, String variableName, int max) {
        Thrower.throwIfTrue(valueToCheck > max)
                .message("The value %1$d in variable '%2$s' is too large. Max value is %3$d.", valueToCheck, variableName, max);
        return INSTANCE;
    }


    /**
     * Throws runtime exception if argument value is less than argument min or
     * larger than argument max.
     *
     * @param valueToCheck The value to check
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param min          The minimum allowed value that the argument value can have
     * @param max          The maximum allowed value that the argument value can have
     * @return The argument value
     */
    public static Thrower throwIfVarOutsideRange(int valueToCheck, String variableName, int min, int max) {
        Thrower.throwIfTrue((max < min), "Error using method. Max cannot be smaller than min.");
        Thrower.throwIfVarEmpty(variableName, "variable");
        Thrower.throwIfVarTooSmall(valueToCheck, variableName, min);
        Thrower.throwIfVarTooLarge(valueToCheck, variableName, max);
        return INSTANCE;
    }


    /**
     * Throw runtime exception if argument expression is false.
     *
     * @param expression The expression to check
     * @param message    The exception message
     */
    public static Thrower throwIfFalse(boolean expression, String message) {
        Thrower.throwIfFalse(expression).message(message);
        return INSTANCE;
    }


    /**
     * Throw if argument expression is false.
     *
     * @param expression The expression to check
     * @return Thrower message for chaining the exception message.
     */
    public static ThrowerMessage throwIfFalse(boolean expression) {
        return ThrowerMessage.create(!expression);
    }


    /**
     * Throw if argument expression is true.
     *
     * @param expression The boolean expression to evaluate.
     * @param message    The exception message
     */
    public static Thrower throwIfTrue(boolean expression, String message) {
        throwIfFalse(!expression, message);
        return INSTANCE;
    }


    /**
     * Throw if argument expression is true.
     *
     * @param expression The expression to check
     * @return Thrower message for chaining the exception message.
     */
    public static ThrowerMessage throwIfTrue(boolean expression) {
        return ThrowerMessage.create(expression);
    }


    /**
     * Throw if argument expression is true.
     *
     * @param object The object to check for null
     * @return Thrower message for chaining the exception message.
     */
    public static ThrowerMessage throwIfNull(Object object) {
        return ThrowerMessage.create(object == null);
    }


    /**
     * Throw if argument expression is true.
     *
     * @param object  The object to check for null
     * @param message The exception message
     */
    public static Thrower throwIfNull(Object object, String message) {
        Thrower.throwIfNull(object).message(message);
        return INSTANCE;
    }


}



