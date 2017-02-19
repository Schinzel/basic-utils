package io.schinzel.basicutils.state;

import java.util.Locale;

/**
 * The purpose of this class is to format numbers to a format that is easily
 * readable for humans. Examples: 123456789123456789 -> 123 456 789 123 456 789
 * 123456789.12345678d -> 123 456 789.12
 *
 * @author schinzel
 */
class NumberFormatter {

    /**
     * Package private constructor as this class should not be instantiated.
     */
    NumberFormatter() {
    }


    /**
     *
     * @param value
     * @return The argument value with space as thousands separator.
     */
    public static String format(int value) {
        return NumberFormatter.format((long) value);
    }


    /**
     *
     * @param value
     * @return he argument value with space as thousands separator.
     */
    public static String format(long value) {
        return String.format(Locale.US, "%,d", value).replace(",", " ");
    }


    /**
     *
     * @param value
     * @param numOfDecimals
     * @return he argument value with space as thousands separator rounded to
     * the argument number of decimals.
     */
    public static String format(float value, int numOfDecimals) {
        return NumberFormatter.format((double) value, numOfDecimals);
    }


    /**
     *
     * @param value
     * @param numOfDecimals
     * @return he argument value with space as thousands separator rounded to
     * the argument number of decimals.
     */
    public static String format(double value, int numOfDecimals) {
        return String.format(Locale.US, "%,." + numOfDecimals + "f", value)
                .replace(",", " ");
    }

}