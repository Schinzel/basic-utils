
package io.schinzel.basicutils.crypto.hash;

import com.google.common.collect.ImmutableMap;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.VersionPrefix;
import lombok.*;

/**
 * The purpose of this class is to hold a set of hashes functions.
 * <p>
 * Created by schinzel on 2017-04-30.
 */
@Builder
public class HashLibrary {
    @Singular @Getter(AccessLevel.PRIVATE)
    private ImmutableMap<Integer, IHash> hashes;


    /**
     * Hash the argument string with the hashes function that has the argument version.
     *
     * @param version
     * @param clearText
     * @return The argument string hashed with a version prefix.
     */
    public String hash(Integer version, String clearText) {
        Thrower.throwIfFalse(this.getHashes().containsKey(version))
                .message("Cannot hashes as there is no hashes with version " + version);
        IHash hash = this.getHashes().get(version);
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
        Thrower.throwIfFalse(this.getHashes().containsKey(version))
                .message("Cannot hashes as there is no hashes with version " + version);
        return this.getHashes()
                .get(version)
                .matches(clearText, versionPrefix.getString());
    }

}

