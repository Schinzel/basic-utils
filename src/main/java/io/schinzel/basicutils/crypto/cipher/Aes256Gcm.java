package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.substring.SubString;
import io.schinzel.basicutils.crypto.SaltShaker;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import io.schinzel.basicutils.crypto.encoding.IEncoding;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

/**
 * The purpose of this class is to encrypt and decrypt strings using AES 256.
 * <p>
 * Each encrypted string is prefixed with a hex encoded random salt.
 * <p>
 * Note that for more than 128 bits encryption you need to download
 * "Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files"
 * Can be found here:
 * http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 * Put the extracted files in this dir:
 * ${java.home}/jre/lib/security/
 * <p>
 * Created by schinzel on 2017-04-29.
 */
@SuppressWarnings("WeakerAccess")
@Accessors(prefix = "m")
public class Aes256Gcm implements ICipher {
    /** The encryption key */
    @Getter(AccessLevel.PACKAGE) private final byte[] mKey;
    /** The encoding used to encode the encrypted strings. */
    @Getter(AccessLevel.PACKAGE) private final IEncoding mEncoding;
    /** Used to create random init vectors. */
    @Getter(AccessLevel.PRIVATE) private final SaltShaker mSaltShaker;


    /**
     * @param key The key must result in 32 bytes (32 * 8 = 256 bits)
     * @return A new instance
     */
    public static Aes256Gcm create(String key) {
        return new Aes256Gcm(key);
    }


    /**
     * @param key The key must result in 32 bytes (32 * 8 = 256 bits)
     */
    public Aes256Gcm(String key) {
        this(key, null);
    }


    /**
     * @param key      The key must result in 32 bytes (32 * 8 = 256 bits)
     * @param encoding The encoding to use to encode the encrypted strings.
     */
    public Aes256Gcm(String key, IEncoding encoding) {
        this(checkKeyAndGetBytes(key), encoding);
    }


    /**
     * @param key      The key must be of length 32 bytes (256 bits).
     * @param encoding The encoding to use to encode the encrypted strings.
     */
    @SneakyThrows
    public Aes256Gcm(byte[] key, IEncoding encoding) {
        //Throw if argument key empty
        Thrower.throwIfTrue(Checker.isEmpty(key)).message("The key cannot be empty");
        //Throw if key length is not 32
        Thrower.throwIfTrue(key.length != 32).message("Key length must be 32, was " + key.length);
        mKey = key;
        //If no encoding set, set encoding to be hex
        mEncoding = (encoding != null) ? encoding : Encoding.HEX;
        mSaltShaker = new SaltShaker(16);
    }


    /**
     * Contains constructor logic. Checks that argument key becomes the correct number of byte.
     *
     * @param key The key to check and cast to byte array
     * @return The argument key as byte array
     */
    static byte[] checkKeyAndGetBytes(String key) {
        Thrower.throwIfVarEmpty(key, "key");
        byte[] keyAsBytes = UTF8.getBytes(key);
        Thrower.throwIfTrue(keyAsBytes.length != 32)
                .message("Key must be 32 bytes. String key '" + key + "' has size " + key.length() + " and converted to " + keyAsBytes.length + " bytes. Note that non ascii chars become two bytes.");
        return keyAsBytes;
    }


    /**
     * @param clearText A clear text string to encrypt.
     * @return The argument text encrypted.
     */
    @Override
    public String encrypt(String clearText) {
        //Create holder for init vector
        byte[] abInitVector = this.getSaltShaker().getSalt();
        //Convert the clear text string to as utf8 bytes
        byte[] clearTextAsBytes = UTF8.getBytes(clearText);
        //Encrypt
        byte[] encryptedTextAsBytes = Aes256Gcm.crypt(clearTextAsBytes, Cipher.ENCRYPT_MODE, this.getKey(), abInitVector);
        //Encode the bytes to string
        String encryptedTextAsString = this.getEncoding().encode(encryptedTextAsBytes);
        //Concat the hec encoded init vector and the encrypted string and return
        return Encoding.HEX.encode(abInitVector) + "_" + encryptedTextAsString;
    }


    /**
     * @param encryptedText An encrypted text to decrypt
     * @return The argument text decrypted
     */
    @Override
    public String decrypt(String encryptedText) {
        //Extract the init vector.
        String sInitVector = SubString.create(encryptedText).endDelimiter("_").getString();
        //Get the init vector as byte array
        byte[] abInitVector = Encoding.HEX.decode(sInitVector);
        //Remove the init vector from the encrypted string
        encryptedText = SubString.create(encryptedText).startDelimiter("_").getString();
        //Decode the encrypted string
        byte[] abEncryptedTextDecoded = this.getEncoding().decode(encryptedText);
        //Decrypt
        byte[] decryptedText = Aes256Gcm.crypt(abEncryptedTextDecoded, Cipher.DECRYPT_MODE, this.getKey(), abInitVector);
        //Convert the encrypted byte array to a string and return
        return UTF8.getString(decryptedText);
    }


    /**
     * Encrypt of decrypts the argument input.
     *
     * @param input            Bytes that should be encrypted or decrypted
     * @param encryptOrDecrypt Either Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
     * @param key              The encryption key
     * @param initVector       The initialization vector
     * @return The input either encrypted or decrypted
     */
    static byte[] crypt(byte[] input, int encryptOrDecrypt, byte[] key, byte[] initVector) {
        try {
            AlgorithmParameterSpec ivParameterSpec = new GCMParameterSpec(128, initVector);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
            cipher.init(encryptOrDecrypt, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(input);
        } catch (NoSuchProviderException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Problems encrypting or decrypting string. " + e.getMessage());
        }
    }

}
