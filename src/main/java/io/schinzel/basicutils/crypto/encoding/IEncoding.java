package io.schinzel.basicutils.crypto.encoding;

/**
 * The purpose of this class is to encode and decode.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
public interface IEncoding {
    String encode(byte[] bytes);


    byte[] decode(String string);


    static IEncoding getInstance(Encoding encoding) {
        return encoding;
    }
}
