package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import io.schinzel.basicutils.crypto.encoding.IEncoding;
import io.schinzel.basicutils.substring.SubString;
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
    private final String mInitVector;
    private final AESPadding mAesPadding;
    @Getter(AccessLevel.PACKAGE)
    private final IEncoding mEncoding;
    private final RandomUtil mRandom = RandomUtil.create();


    @Builder
    private AES(String key, String initVector, AESPadding padding, IEncoding encoding) {
        Thrower.throwIfVarEmpty(key, "key");
        //If key length is not 16 or 32
        if (key.length() == 16 || key.length() == 32) {
            throw new RuntimeException("Key length must be 16 or 32, was " + key.length());
        }
        mKey = key;
        mInitVector = initVector;
        mAesPadding = (padding != null) ? padding : AESPadding.NO_PADDING;
        mEncoding = (encoding != null) ? encoding : IEncoding.getInstance(Encoding.BASE64);
    }


    @Override
    public String encrypt(String clearTextString) {
        String initVector = (mInitVector != null)
                ? mInitVector
                : AES.extractInitVector(clearTextString);
        byte[] clearTextAsBytes = getBytes(clearTextString);
        byte[] encryptedTextAsBytes = this.crypt(clearTextAsBytes, Cipher.ENCRYPT_MODE, initVector);
        String encryptedString = this.getEncoding().encode(encryptedTextAsBytes);
        if (mInitVector == null) {
            encryptedString = initVector + "_" + encryptedString;
        }
        return encryptedString;
    }


    static String extractInitVector(String encryptedString) {
        if (!encryptedString.contains("_")) {
            throw new RuntimeException("No init vector found in encrypted string '" + encryptedString + "'");
        }
        return SubString.builder()
                .string(encryptedString)
                .endDelimiter("_")
                .build()
                .getString();
    }


    String getRandomInitVector() {
        return mRandom.getString(mKey.length());
    }


    @Override
    public String decrypt(String encryptedString) {
        String initVector = (mInitVector != null)
                ? mInitVector
                : AES.extractInitVector(encryptedString);
        byte[] encryptedStringDecoded = this.getEncoding().decode(encryptedString);
        byte[] decryptedTextAsString = this.crypt(encryptedStringDecoded, Cipher.DECRYPT_MODE, initVector);
        return new String(decryptedTextAsString, Charset.forName("UTF-8"));
    }


    byte[] crypt(byte[] input, int encryptOrDecrypt, String initVector) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(getBytes(initVector));
            SecretKeySpec secretKeySpec = new SecretKeySpec(getBytes(mKey), "AES");
            Cipher cipher = Cipher.getInstance(mAesPadding.getString());
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
