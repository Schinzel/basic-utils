package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import io.schinzel.basicutils.crypto.encoding.IEncoding;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * For AES encryption and decryption.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
@Accessors(prefix = "m")
public class AES implements ICipher {
    private final String mKey;
    @Getter(AccessLevel.PACKAGE)
    private final IEncoding mEncoding;
    RandomUtil mRandom = RandomUtil.create();


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


    @Override
    public String encrypt(String clearTextString) {
        String initVector = mRandom.getString(mKey.length());
        byte[] clearTextAsBytes = getBytes(clearTextString);
        byte[] encryptedTextAsBytes = AES.crypt(clearTextAsBytes, Cipher.ENCRYPT_MODE, initVector, mKey);
        String encryptedString = this.getEncoding().encode(encryptedTextAsBytes);
        encryptedString = initVector + encryptedString;
        return encryptedString;
    }


    @Override
    public String decrypt(String encryptedString) {
        String initVector = encryptedString.substring(0, mKey.length() - 1);
        encryptedString = encryptedString.substring(mKey.length() - 1, encryptedString.length());
        byte[] encryptedStringDecoded = this.getEncoding().decode(encryptedString);
        byte[] decryptedTextAsBytes = AES.crypt(encryptedStringDecoded, Cipher.DECRYPT_MODE, initVector, mKey);
        return new String(decryptedTextAsBytes, Charset.forName("UTF-8"));
    }


    static byte[] crypt(byte[] input, int encryptOrDecrypt, String initVector, String key) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(getBytes(initVector));
            SecretKeySpec secretKeySpec = new SecretKeySpec(getBytes(key), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(encryptOrDecrypt, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Problems encrypting or decrypting string. " + e.getMessage());
        }
    }


    private static byte[] getBytes(String str) {
        return str.getBytes(Charset.forName("UTF-8"));
    }
}
