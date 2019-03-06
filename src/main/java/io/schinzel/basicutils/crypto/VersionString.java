package io.schinzel.basicutils.crypto;


import io.schinzel.basicutils.thrower.Thrower;
import io.schinzel.basicutils.substring.SubString;

import java.util.regex.Pattern;

/**
 * The purpose of this class is manage strings with a version prefix.
 * <p>
 * Format: v[version number]_[non whitespace string]
 * Example: v12_anyStringHere
 * <p>
 * Created by schinzel on 2017-05-04.
 */
@SuppressWarnings("WeakerAccess")
public class VersionString {
    private static Pattern STRING_PATTERN = Pattern.compile("^v(\\d+)_.*$", Pattern.DOTALL);
    private static final String PREFIX_START = "v";
    private static final String PREFIX_END = "_";

    private VersionString() {
    }


    /**
     * Example:
     * Input: v12_myString
     * Output: myString
     *
     * @param stringWithVersionPrefix E.g. v12_myString
     * @return The string held by the argument string
     */
    public static String extractString(String stringWithVersionPrefix) {
        validateString(stringWithVersionPrefix);
        return SubString.create(stringWithVersionPrefix)
                .startDelimiter(PREFIX_END)
                .getString();
    }


    /**
     * Example:
     * Input: v12_myString
     * Output: 12
     *
     * @param stringWithVersionPrefix E.g. v12_myString
     * @return The version held by the argument string
     */
    public static Integer extractVersion(String stringWithVersionPrefix) {
        validateString(stringWithVersionPrefix);
        return SubString.create(stringWithVersionPrefix)
                .startDelimiter(PREFIX_START)
                .endDelimiter(PREFIX_END)
                .getStr()
                .asInt();
    }


    /**
     * Example return v12_myString
     *
     * @param version A positive number
     * @param string  Non whitespace string that is to receive a version prefix
     * @return The argument string prefixed by the argument version.
     */
    public static String addVersionPrefix(Integer version, String string) {
        Thrower.throwIfVarTooSmall(version, "version", 1);
        return PREFIX_START + version + PREFIX_END + string;
    }


    /**
     * Throw exception if argument string is not a correctly formatted version string
     * @param stringWithVersionPrefix
     */
    static void validateString(String stringWithVersionPrefix) {
        Thrower.throwIfFalse(STRING_PATTERN.matcher(stringWithVersionPrefix).matches())
                .message("Versioned string was of the wrong format. Was '" + stringWithVersionPrefix + "' and should be v[version number]_[actual string], e.g. v8_myString.");
    }
}

