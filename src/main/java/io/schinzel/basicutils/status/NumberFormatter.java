package io.schinzel.basicutils.status;

import java.util.Locale;

/**
 * The purpose of this class is to format numbers to a format that is easily
 * readable for humans.
 * Examples:
 * 123456789123456789 -> 123 456 789 123 456 789
 * 123456789.12345678d -> 123 456 789.12
 *
 * @author schinzel
 */
class NumberFormatter {

    static String format(int value) {
        return String.format(Locale.US, "%,d", value).replace(",", " ");
    }


    static String format(long value) {
        return String.format(Locale.US, "%,d", value).replace(",", " ");
    }


    static String format(float value) {
        return NumberFormatter.format(value, 2);
    }


    static String format(float value, int numOfDecimals) {
        return String.format(Locale.US, "%,." + numOfDecimals + "f", value)
                .replace(",", " ");
    }


    static String format(double value) {
        return NumberFormatter.format(value, 2);
    }


    static String format(double value, int numOfDecimals) {
        return String.format(Locale.US, "%,." + numOfDecimals + "f", value)
                .replace(",", " ");
    }


}
