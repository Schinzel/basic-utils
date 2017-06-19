
package io.schinzel.basicutils.crypto.hashlibrary;

import com.google.common.collect.ImmutableMap;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.VersionPrefix;
import io.schinzel.basicutils.crypto.hash.IHash;
import lombok.*;

/**
 * The purpose of this class is to hold a set of hash functions.
 * <p>
 * Created by schinzel on 2017-04-30.
 */
@Builder
public class HashLibrary {
    @Singular("addHash") @Getter(AccessLevel.PRIVATE)
    private ImmutableMap<Integer, IHash> hashes;


    /**
     * Hash the argument string with the hashes function that has the argument version.
     *
     * @param version   The version of the hash to use
     * @param clearText A clear text string to hash
     * @return The argument string hashed with a version prefix.
     */
    public String hash(Integer version, String clearText) {
        Thrower.throwIfFalse(this.getHashes().containsKey(version))
                .message("Cannot hashes as there is no hashes with version " + version);
        IHash hash = this.getHashes().get(version);
        return VersionPrefix.addVersionPrefix(version, hash.hash(clearText));
    }


    /**
     * @param clearText               A clear text string to compare to the argument hashed string.
     * @param hashedStringWithVersion The hashed string to compare with the argument clear text
     *                                string. Example: v1_$2a$10$7QFT2oVQguvBBHM1czu4G.1SeUphAgIygkGu5AtzxHK3Swfrt6rXS
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

