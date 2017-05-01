package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import io.schinzel.basicutils.crypto.encoding.IEncoding;
import lombok.Builder;
import lombok.experimental.Accessors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * The purpose of this class is to encrypt and decrypt strings using AES 128 or 256.
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
@Accessors(prefix = "m")
public class AES implements ICipher {
    private static final int INIT_VECTOR_SIZE = 16;
    /** The encryption key */
    private final String mKey;
    /** The encoding used to encode the encrypted strings. */
    private final IEncoding mEncoding;
    /** Used to create random init vectors. */
    private RandomUtil mRandom = RandomUtil.create();


    public AES(String key) {
        this(key, null);
    }


    /**
     * @param key      The key must be length 16 (128 bits) or 32 (256 bits).
     * @param encoding What encoding to use to encode the encrypted string.
     */
    @Builder
    public AES(String key, IEncoding encoding) {
        Thrower.throwIfVarEmpty(key, "key");
        //If key length is not 16 or 32
        if (key.length() != 16 && key.length() != 32) {
            throw new RuntimeException("Key length must be 16 or 32, was " + key.length());
        }
        mKey = key;
        mEncoding = (encoding != null) ? encoding : Encoding.BASE64;
    }


    /**
     * @param clearTextString A clear test string to encrypt.
     * @return The argument string encrypted.
     */
    @Override
    public String encrypt(String clearTextString) {
        //Generate a random init vector
        String initVector = mRandom.getString(INIT_VECTOR_SIZE);
        //Convert the clear text string to as utf8 bytes
        byte[] clearTextAsBytes = UTF8.getBytes(clearTextString);
        //Encrypt
        byte[] encryptedTextAsBytes = AES.crypt(clearTextAsBytes, Cipher.ENCRYPT_MODE, mKey, initVector);
        //Encode the bytes to string
        String encryptedTestAsString = mEncoding.encode(encryptedTextAsBytes);
        //Concat the init vector and the encrypted string and return
        return initVector + encryptedTestAsString;
    }


    /**
     * @param encryptedString An encrypted string to decrypt.
     * @return The argument string decrypted.
     */
    @Override
    public String decrypt(String encryptedString) {
        //Extract the init vector.
        String initVector = encryptedString.substring(0, INIT_VECTOR_SIZE);
        //Remove the init vector from the encrypted string
        encryptedString = encryptedString.substring(INIT_VECTOR_SIZE, encryptedString.length());
        //Decode the encrypted string
        byte[] encryptedStringDecoded = mEncoding.decode(encryptedString);
        //Decrypt
        byte[] decryptedText = AES.crypt(encryptedStringDecoded, Cipher.DECRYPT_MODE, mKey, initVector);
        //Convert the encrypted text to a string and return
        return UTF8.getString(decryptedText);
    }


    /**
     * Encrypt of decrypts the argument input.
     *
     * @param input            Bytes that should be encrypted or decrypted.
     * @param encryptOrDecrypt Either Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
     * @param key              The encryption key
     * @param initVector       The initialization vector
     * @return The input either encrypted or decrypted.
     */
    static byte[] crypt(byte[] input, int encryptOrDecrypt, String key, String initVector) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(UTF8.getBytes(initVector));
            SecretKeySpec secretKeySpec = new SecretKeySpec(UTF8.getBytes(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(encryptOrDecrypt, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Problems encrypting or decrypting string. " + e.getMessage());
        }
    }

}
