package io.schinzel.basicutils.thrower;

import java.util.List;
import java.util.Map;

/**
 * The purpose of this class it to allow chaining Thrower methods for more readable code.
 *
 * @author Schinzel
 */
public class ThrowerInstance {

    ThrowerInstance() {
    }


    /**
     * Throws runtime exception if the argument value with the argument name is null.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     */
    public ThrowerInstance throwIfVarNull(Object value, String variableName) {
        Thrower.throwIfVarNull(value, variableName);
        return this;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is empty.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     */
    public ThrowerInstance throwIfVarEmpty(String value, String variableName) {
        Thrower.throwIfVarEmpty(value, variableName);
        return this;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is empty.
     *
     * @param <T>          The type of the list
     * @param value        The value to check
     * @param variableName The name of the value to check
     */
    public <T> ThrowerInstance throwIfVarEmpty(List<T> value, String variableName) {
        Thrower.throwIfVarEmpty(value, variableName);
        return this;
    }


    /**
     * Throws runtime exception if the argument value with the argument name is empty.
     *
     * @param <K>          The type of the keys in the map
     * @param <V>          The type of the values in the map
     * @param value        The value to check
     * @param variableName The name of the value to check
     */
    public <K, V> ThrowerInstance throwIfVarEmpty(Map<K, V> value, String variableName) {
        Thrower.throwIfVarEmpty(value, variableName);
        return this;
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param min          The min value the argument value should not be less than.
     */
    public ThrowerInstance throwIfVarTooSmall(int valueToCheck, String variableName, int min) {
        Thrower.throwIfVarTooSmall(valueToCheck, variableName, min);
        return this;
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param max          The max value the argument value should not be larger than.
     */
    public ThrowerInstance throwIfVarTooLarge(int valueToCheck, String variableName, int max) {
        Thrower.throwIfVarTooLarge(valueToCheck, variableName, max);
        return this;
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
    public ThrowerInstance throwIfVarOutsideRange(int valueToCheck, String variableName, int min, int max) {
        Thrower.throwIfVarOutsideRange(valueToCheck, variableName, min, max);
        return this;
    }


    /**
     * Throw runtime exception if argument expression is false.
     *
     * @param expression The expression to check
     * @param message    The exception message
     */
    public ThrowerInstance throwIfFalse(boolean expression, String message) {
        Thrower.throwIfFalse(expression, message);
        return this;
    }


    /**
     * Throw if argument expression is true.
     *
     * @param expression The boolean expression to evaluate.
     * @param message    The exception message
     */
    public ThrowerInstance throwIfTrue(boolean expression, String message) {
        Thrower.throwIfTrue(expression, message);
        return this;
    }


}


