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
 * AES 128 and 256 bits encryption and decryption.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
@Accessors(prefix = "m")
public class AES implements ICipher {
    /** The encryption key */
    private final String mKey;
    private final IEncoding mEncoding;
    /** Used to create random init vectors. */
    RandomUtil mRandom = RandomUtil.create();


    /**
     * @param key      The key must be 16 (128 bits) or 32 (256 bits).
     * @param encoding What encoding to use
     */
    @Builder
    private AES(String key, IEncoding encoding) {
        Thrower.throwIfVarEmpty(key, "key");
        //If key length is not 16 or 32
        if (key.length() != 16 && key.length() != 32) {
            throw new RuntimeException("Key length must be 16 or 32, was " + key.length());
        }
        mKey = key;
        mEncoding = (encoding != null) ? encoding : IEncoding.getInstance(Encoding.BASE64);
    }


    /**
     * @param clearTextString A clear test string to encrypt.
     * @return The argument string encrypted.
     */
    @Override
    public String encrypt(String clearTextString) {
        //Generate a random init vector
        String initVector = mRandom.getString(mKey.length());
        //Get the clear text as utf8 bytes
        byte[] clearTextAsBytes = UTF8.getBytes(clearTextString);
        //Encrypt
        byte[] encryptedTextAsBytes = AES.crypt(clearTextAsBytes, Cipher.ENCRYPT_MODE, mKey, initVector);
        //Encode the bytes to string
        String encryptedTestAsString = mEncoding.encode(encryptedTextAsBytes);
        //Add the init vector to the encrypted string
        return initVector + encryptedTestAsString;
    }


    /**
     * @param encryptedString An encrypted string to decrypt.
     * @return The argument string decrypted.
     */
    @Override
    public String decrypt(String encryptedString) {
        //Extract the init vector.
        String initVector = encryptedString.substring(0, mKey.length() - 1);
        //Remove the init vector from the encrypted string
        encryptedString = encryptedString.substring(mKey.length() - 1, encryptedString.length());
        //Decode the encrypted string
        byte[] encryptedStringDecoded = mEncoding.decode(encryptedString);
        //Decrypt
        byte[] decryptedText = AES.crypt(encryptedStringDecoded, Cipher.DECRYPT_MODE, mKey, initVector);
        //Convert the encrypted text to a string and return
        return UTF8.getString(decryptedText);
    }


    /**
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
