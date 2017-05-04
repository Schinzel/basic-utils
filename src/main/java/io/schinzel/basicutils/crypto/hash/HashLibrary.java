
package io.schinzel.basicutils.crypto.hash;

import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.VersionPrefix;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

public class HashLibrary {
    private static final HashLibrary SINGLETON = new HashLibrary();
    @Getter(AccessLevel.PRIVATE)
    private Map<Integer, IHash> hashVersions = new HashMap();


    public static HashLibrary getSingleton() {
        return SINGLETON;
    }


    public HashLibrary addHash(Integer version, IHash hash) {
        Thrower.throwIfTrue(this.getHashVersions().containsKey(version))
                .message("Cannot add hash as there already exists a hash with version " + version);
        this.getHashVersions().put(version, hash);
        return this;
    }


    public String hash(Integer version, String clearText) {
        Thrower.throwIfFalse(this.getHashVersions().containsKey(version))
                .message("Cannot hash as there is no hash with version " + version);
        IHash hash = this.getHashVersions().get(version);
        return VersionPrefix.addVersionPrefix(version, hash.hash(clearText));
    }


    public boolean matches(String clearTextString, String encryptedStringWithVersion) {
        val versionPrefix = new VersionPrefix(encryptedStringWithVersion);
        Integer version = versionPrefix.getVersion();
        Thrower.throwIfFalse(this.getHashVersions().containsKey(version))
                .message("Cannot hash as there is no hash with version " + version);
        return this.getHashVersions()
                .get(version)
                .matches(clearTextString, versionPrefix.getTheString());
    }

}

