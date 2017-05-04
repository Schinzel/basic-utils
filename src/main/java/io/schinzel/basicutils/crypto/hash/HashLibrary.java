
package io.schinzel.basicutils.crypto.hash;

import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.VersionPrefix;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to hold a set of hash functions.
 * <p>
 * Created by schinzel on 2017-04-30.
 */
public class HashLibrary {
    private static final HashLibrary SINGLETON = new HashLibrary();
    @Getter(AccessLevel.PRIVATE)
    private Map<Integer, IHash> hashVersions = new HashMap();


    /**
     * @return The singleton instance.
     */
    public static HashLibrary getSingleton() {
        return SINGLETON;
    }


    /**
     * Adds a hash function to this library.
     *
     * @param version The version of the hash to add.
     * @param hash    The hash function to add.
     * @return This for chaining.
     */
    public HashLibrary addHash(Integer version, IHash hash) {
        Thrower.throwIfTrue(this.getHashVersions().containsKey(version))
                .message("Cannot add hash as there already exists a hash with version " + version);
        this.getHashVersions().put(version, hash);
        return this;
    }


    /**
     * Hash the argument string with the hash function that has the argument version.
     *
     * @param version
     * @param clearText
     * @return The argument string hashed with a version prefix.
     */
    public String hash(Integer version, String clearText) {
        Thrower.throwIfFalse(this.getHashVersions().containsKey(version))
                .message("Cannot hash as there is no hash with version " + version);
        IHash hash = this.getHashVersions().get(version);
        return VersionPrefix.addVersionPrefix(version, hash.hash(clearText));
    }


    /**
     * @param clearText
     * @param hashedStringWithVersion
     * @return True if the argument clear text string is the same string as was input when
     * the argument hashed string was created.
     */
    public boolean matches(String clearText, String hashedStringWithVersion) {
        val versionPrefix = new VersionPrefix(hashedStringWithVersion);
        Integer version = versionPrefix.getVersion();
        Thrower.throwIfFalse(this.getHashVersions().containsKey(version))
                .message("Cannot hash as there is no hash with version " + version);
        return this.getHashVersions()
                .get(version)
                .matches(clearText, versionPrefix.getTheString());
    }

}

