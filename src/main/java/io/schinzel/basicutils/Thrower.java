package io.schinzel.basicutils;

import io.schinzel.basicutils.str.Str;

import java.util.Arrays;

/**
 * The purpose of this class is to offer less verbose exception throwing in
 * general and variable checking in particular.
 *
 * @author schinzel
 */
@SuppressWarnings("WeakerAccess")
public class Thrower {


    /**
     * Throws runtime exception if the argument value with the argument name is null.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     */
    public static void throwIfVarNull(Object value, String variableName) {
        ThrowerMessage.create(value == null).message("Argument '" + variableName + "' cannot be null");
    }


    /**
     * Throws runtime exception if the argument value with the argument name is
     * empty.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     */
    public static void throwIfVarEmpty(String value, String variableName) {
        ThrowerMessage.create(Checker.isEmpty(value)).message("Argument '" + variableName + "' cannot be empty");
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param min          The min value the argument value should not be less than.
     */
    public static void throwIfVarTooSmall(int valueToCheck, String variableName, int min) {
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
     *                     check. Used to create more useful exception message.
     * @param max          The max value the argument value should not be larger than.
     */
    public static void throwIfVarTooLarge(int valueToCheck, String variableName, int max) {
        if (valueToCheck > max) {
            String message = String.format("The value %1$d in variable '%2$s' "
                    + "is too large. Max value is %3$d.", valueToCheck, variableName, max);
            throw new RuntimeException(message);
        }
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
     */
    public static void throwIfVarOutsideRange(int valueToCheck, String variableName, int min, int max) {
        Thrower.throwIfTrue((max < min), "Error using method. Max cannot be smaller than min.");
        Thrower.throwIfVarEmpty(variableName, "variable");
        Thrower.throwIfVarTooSmall(valueToCheck, variableName, min);
        Thrower.throwIfVarTooLarge(valueToCheck, variableName, max);
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
     * Throw if argument expression is true.
     *
     * @param object  The object to check for null
     * @param message The exception message
     */
    public static void throwIfNull(Object object, String message) {
        Thrower.throwIfNull(object).message(message);
    }


    /**
     * @param expression If evaluates to true, then a RuntimeException is thrown.
     * @param message    The exception message.
     * @param keyValues  A series of key values. As such the number of elements need to be even.
     */
    public static void throwIfTrue(boolean expression, String message, String... keyValues) {
        //If key values are empty && the number of key values is not empty
        if (!Checker.isEmptyVarArgs(keyValues) && keyValues.length % 2 != 0) {
            throw new RuntimeException("The number of key values is not even: '" + Arrays.toString(keyValues) + "'");
        }
        //If argument expression is true
        if (expression) {
            //Compile more extensive exception message.
            Str str = Str.create().asp(message).a(Thrower.getArgs(keyValues));
            throw new RuntimeException(str.getString());
        }
    }


    /**
     * Sample return:
     * Arguments: {k1:'v1' k2:'v2'}
     *
     * @param keyValues A series of key values. As such the number of elements need to be even.
     * @return The argument vararg as string.
     */
    static Str getArgs(String... keyValues) {
        if (Checker.isEmptyVarArgs(keyValues)) {
            return Str.create();
        }
        Str args = Str.create();
        for (int i = 0; i < keyValues.length; i += 2) {
            if (!args.isEmpty()) {
                args.asp();
            }
            args.a(keyValues[i]).a(":").aq(keyValues[i + 1]);
        }
        return Str.create().a("Props:{").a(args).a("}");
    }
}
