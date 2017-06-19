package io.schinzel.basicutils.crypto;


import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.substring.SubString;
import lombok.Getter;

import java.util.regex.Pattern;

/**
 * The purpose of this class is manage strings with a version prefix.
 * <p>
 * Format: v[version number]_[non whitespace string]
 * Example: v12_anyStringHere
 * <p>
 * Created by schinzel on 2017-05-04.
 */
public class VersionPrefix {
    @Getter private final String string;
    @Getter private final Integer version;
    private static Pattern STRING_PATTERN = Pattern.compile("^v(\\d+)_.*$", Pattern.DOTALL);
    private static final String PREFIX_START = "v";
    private static final String PREFIX_END = "_";


    /**
     * @param withVersionPrefix Example: v12_non_whitespace_string
     */
    public VersionPrefix(String withVersionPrefix) {
        Thrower.throwIfFalse(STRING_PATTERN.matcher(withVersionPrefix).matches())
                .message("Versioned string was of the wrong format. Was '" + withVersionPrefix + "' and should be v[version number]_[actual string], e.g. v8_abcdefg.");
        string = SubString.create(withVersionPrefix)
                .startDelimiter(PREFIX_END)
                .getString();
        String versionAsString = SubString.create(withVersionPrefix)
                .startDelimiter(PREFIX_START)
                .endDelimiter(PREFIX_END)
                .getString();
        version = Integer.valueOf(versionAsString);
    }


    /**
     * Example return v12_mystring
     *
     * @param version A positive number
     * @param string  Non whitespace string that is to receive a version prefix
     * @return The argument string prefixed by the argument version.
     */
    public static String addVersionPrefix(Integer version, String string) {
        Thrower.throwIfVarTooSmall(version, "version", 1);
        return PREFIX_START + version + PREFIX_END + string;
    }
}

