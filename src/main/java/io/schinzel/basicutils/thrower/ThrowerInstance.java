package io.schinzel.basicutils.thrower;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * The purpose of this class it to allow chaining Thrower methods for more readable code.
 *
 * @author Schinzel
 */
@SuppressWarnings("WeakerAccess")
public class ThrowerInstance {

    ThrowerInstance() {
    }

    /**
     * @param string       The string to validate
     * @param variableName The name of the argument to validate
     * @param regex        The regex to match
     * @return This for chaining
     */
    public ThrowerInstance throwIfNotMatchesRegex(String string, String variableName, Pattern regex) {
        Thrower.throwIfNotMatchesRegex(string, variableName, regex);
        return this;
    }

    /**
     * Throws runtime exception if the argument value with the argument name is null.
     *
     * @param value        The value to check
     * @param variableName The name of the value to check
     * @return This for chaining
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
     * @return This for chaining
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
     * @return This for chaining
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
     * @return This for chaining
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
     * @return This for chaining
     */
    public ThrowerInstance throwIfVarTooSmall(int valueToCheck, String variableName, int min) {
        return throwIfVarTooSmall(valueToCheck, variableName, (long)min);
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param min          The min value the argument value should not be less than.
     * @return This for chaining
     */
    public ThrowerInstance throwIfVarTooSmall(long valueToCheck, String variableName, long min) {
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
     * @return This for chaining
     */
    public ThrowerInstance throwIfVarTooLarge(int valueToCheck, String variableName, int max) {
        return throwIfVarTooLarge(valueToCheck, variableName, (long) max);
    }


    /**
     * Throws a runtime exception if argument value is less than argument min.
     *
     * @param valueToCheck The value to check.
     * @param variableName The name of the variable that holds the value to
     *                     check. Used to create more useful exception message.
     * @param max          The max value the argument value should not be larger than.
     * @return This for chaining
     */
    public ThrowerInstance throwIfVarTooLarge(long valueToCheck, String variableName, long max) {
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
     * @return This for chaining
     */
    public ThrowerInstance throwIfVarOutsideRange(int valueToCheck, String variableName, int min, int max) {
        return throwIfVarOutsideRange(valueToCheck, variableName, min, (long)max);
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
     * @return This for chaining
     */
    public ThrowerInstance throwIfVarOutsideRange(long valueToCheck, String variableName, long min, long max) {
        Thrower.throwIfVarOutsideRange(valueToCheck, variableName, min, max);
        return this;
    }

    /**
     * Throw runtime exception if argument expression is false.
     *
     * @param expression The expression to check
     * @param message    The exception message
     * @return This for chaining
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
     * @return This for chaining
     */
    public ThrowerInstance throwIfTrue(boolean expression, String message) {
        Thrower.throwIfTrue(expression, message);
        return this;
    }

}
