package io.schinzel.basicutils.crypto;


import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.substring.SubString;
import lombok.Getter;

/**
 * The purpose of this class is manage strings with a version prefix.
 * <p>
 * Format: v[verion number]_[the string]
 * Example: v12_anyStringHere
 * <p>
 * Created by schinzel on 2017-05-04.
 */
public class VersionPrefix {
    @Getter private final String theString;
    @Getter private final Integer version;


    public VersionPrefix(String vithVersionPrefix) {
        Thrower.throwIfFalse(vithVersionPrefix.matches("^v(\\d+)_.*$"))
                .message("Versioned string was of the wrong format. Was '" + vithVersionPrefix + "' and should be v[version number]_[actual string], e.g. v8_abcdefg.");
        theString = SubString.create(vithVersionPrefix)
                .startDelimiter("_")
                .getString();
        String versionAsString = SubString.create(vithVersionPrefix)
                .startDelimiter("v")
                .endDelimiter("_")
                .getString();
        version = Integer.valueOf(versionAsString);
    }


    public static String addVersionPrefix(Integer version, String string) {
        return "v" + version + "_" + string;
    }
}

