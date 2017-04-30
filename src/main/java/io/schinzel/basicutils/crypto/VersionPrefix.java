package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.Thrower;

/**
 * The purpose of this class is to handle versioning of strings. The version is in a prefix.
 * This is useful for for example encryption, where you want to change keys or algorithm over
 * time for new data but still support decrypting the older encrypted data.
 * <p>
 * Created by schinzel on 2017-04-30.
 */
class VersionPrefix {
    private final String mPrefix;


    static VersionPrefix create(int version) {
        return new VersionPrefix(version);
    }


    VersionPrefix(int version) {
        Thrower.throwIfVarOutsideRange(version, "version", 1, 1000000);
        mPrefix = "v" + String.valueOf(version) + "_";
    }


    String addPrefix(String string) {
        return mPrefix + string;
    }


    String removePrefix(String string) {
        if (!this.hasThisPrefix(string)) {
            throw new RuntimeException("Cannot remove prefix '" + mPrefix + "' from string '" + string + "' as it has no such prefix.");
        }
        return string.substring(mPrefix.length(), string.length());
    }


    boolean hasThisPrefix(String string) {
        return string.startsWith(mPrefix);
    }
}
