package io.schinzel.basicutils.status;

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
     * Private constructor as this class should not be instantiated. 
     */
    private NumberFormatter(){}

    /**
     *
     * @param value
     * @return The argument value with space as thousands separator.
     */
    static String format(int value) {
        return String.format(Locale.US, "%,d", value).replace(",", " ");
    }


    /**
     *
     * @param value
     * @return he argument value with space as thousands separator.
     */
    static String format(long value) {
        return String.format(Locale.US, "%,d", value).replace(",", " ");
    }


    /**
     *
     * @param value
     * @param numOfDecimals
     * @return he argument value with space as thousands separator rounded to
     * the argument number of decimals.
     */
    static String format(float value, int numOfDecimals) {
        return String.format(Locale.US, "%,." + numOfDecimals + "f", value)
                .replace(",", " ");
    }


    /**
     *
     * @param value
     * @param numOfDecimals
     * @return he argument value with space as thousands separator rounded to
     * the argument number of decimals.
     */
    static String format(double value, int numOfDecimals) {
        return String.format(Locale.US, "%,." + numOfDecimals + "f", value)
                .replace(",", " ");
    }


}
