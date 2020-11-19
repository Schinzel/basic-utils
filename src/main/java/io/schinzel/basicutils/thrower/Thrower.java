package io.schinzel.basicutils.thrower;

import io.schinzel.basicutils.Checker;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to offer less verbose exception throwing in
 * general and variable checking in particular.
 *
 * @author schinzel
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
public class Thrower {

    Thrower() {
    }

    /**
     * @return An ThrowerInstance for chaining of Thrower methods.
     */
    public static ThrowerInstance createInstance() {
        return new ThrowerInstance();
    }


    /**
     * @param string       The string to validate
     * @param variableName The name of the argument to validate
     * @param regex        The regex to match
     * @return The argument string
     */
    public static String throwIfNotMatchesRegex(String string, String variableName, Pattern regex) {
        ThrowerMessage.create(!regex.matcher(string).matches())
                .message("Argument '" + variableName + "' does not match regex '" + regex.pattern() + "'");
        return string;
    }

    /**
     * Throws runtime exception if the argument value with the argument name is null.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value checked
     */
    public static Object throwIfVarNull(Object value, String variableName) {
        ThrowerMessage.create(value == null).message(getErrorMessage(variableName, "null"));
        return value;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is null or an empty string.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value checked
     */
    public static String throwIfVarEmpty(String value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message(getErrorMessage(variableName, "empty"));
        return value;
    }


    /**
     * Throws runtime exception if the argument list with the argument name is null ot an empty list.
     *
     * @param <T>          The type of the list
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value checked
     */
    public static <T> List<T> throwIfVarEmpty(List<T> value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message(getErrorMessage(variableName, "empty"));
        return value;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is null or an empty map.
     *
     * @param <K>          The type of the keys in the map
     * @param <V>          The type of the values in the map
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return The argument value checked
     */
    public static <K, V> Map<K, V> throwIfVarEmpty(Map<K, V> value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message(getErrorMessage(variableName, "empty"));
        return value;
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param min          The min value the argument value should not be less than.
     * @return The argument value checked
     */
    public static int throwIfVarTooSmall(int valueToCheck, String variableName, int min) {
        Thrower.throwIfTrue(valueToCheck < min)
                .message("The value %1$d in variable '%2$s' is too small. Min value is %3$d.", valueToCheck, variableName, min);
        return valueToCheck;
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param max          The max value the argument value should not be larger than.
     * @return The argument value checked
     */
    public static int throwIfVarTooLarge(int valueToCheck, String variableName, int max) {
        Thrower.throwIfTrue(valueToCheck > max)
                .message("The value %1$d in variable '%2$s' is too large. Max value is %3$d.", valueToCheck, variableName, max);
        return valueToCheck;
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
     * @return The argument value checked
     */
    public static int throwIfVarOutsideRange(int valueToCheck, String variableName, int min, int max) {
        Thrower.throwIfTrue((max < min), "Error using method. Max cannot be smaller than min.");
        Thrower.throwIfVarEmpty(variableName, "variable");
        Thrower.throwIfVarTooSmall(valueToCheck, variableName, min);
        Thrower.throwIfVarTooLarge(valueToCheck, variableName, max);
        return valueToCheck;
    }


    /**
     * Throw runtime exception if argument expression is false.
     *
     * @param expression The expression to check
     * @param message    The exception message
     */
    public static void throwIfFalse(boolean expression, String message) {
        Thrower.throwIfFalse(expression).message(message);
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
    public static void throwIfTrue(boolean expression, String message) {
        throwIfFalse(!expression, message);
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
     * Throw if argument object is null.
     *
     * @param object  The object to check for null
     * @param message The exception message
     */
    public static void throwIfNull(Object object, String message) {
        Thrower.throwIfNull(object).message(message);
    }


    /**
     * @param variableName Name of variable to include in returned message
     * @param state        The state of the argument variable. E.g. "null" or "empty"
     * @return Error message prefix
     */
    static String getErrorMessage(String variableName, String state) {
        return "Argument '" + variableName + "' cannot be " + state;
    }


}



