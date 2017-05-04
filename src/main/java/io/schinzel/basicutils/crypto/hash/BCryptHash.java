package io.schinzel.basicutils.crypto.hash;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Why bcrypt?
 * <p>
 * This article/page is a good overview of password hashing:
 * https://crackstation.net/hashing-security.htm
 * It recommends the following algorithms:
 * PBKDF2, bcrypt, and scrypt.
 * <p>
 * This stack exchange answer deems bcrypt to be better than PBKDF2.
 * "TL;DR: bcrypt is better than PBKDF2 because PBKDF2 can be better accelerated with GPUs.
 * As such, PBKDF2 is easier to brute force offline with consumer hardware."
 * https://security.stackexchange.com/questions/4781/do-any-security-experts-recommend-bcrypt-for-password-storage/6415#6415
 * <p>
 * This Reddit thread with a post from probably Moxie Marlinspike, further argues for bcrypt over
 * PBKDF2 as PBKDF2 "almost always used with an HMAC or a cryptographic hash" and
 * "Cryptographic hash functions, by contrast, are not well understood at all. They are "magic" in
 * many ways, and aren't modeled after anything. Many more "bad things" happen in this space than
 * in the block cipher space."
 * https://news.ycombinator.com/item?id=3724560
 * <p>
 * My personal opinion is that I prefer bcrypt over scrypt as bcrypt has been more years and has
 * a proven track record, been scrutinized more and is more battle tested.
 * <p>
 * Created by schinzel on 2017-05-02.
 */
public class BCryptHash implements IHash {
    @Override
    public String hash(String clearText) {
        return BCrypt.hashpw(clearText, BCrypt.gensalt());
    }


    @Override
    public boolean matches(String clearText, String hashedString) {
        return BCrypt.checkpw(clearText, hashedString);
    }
}
