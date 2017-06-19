package io.schinzel.basicutils.crypto.encoding;

/**
 * The purpose of this class is to encode and decode. Encoding is transformation of a set of bytes
 * to printable characters for the purpose of transmitting and storage of these bytes.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
public interface IEncoding {
    String encode(byte[] bytes);


    byte[] decode(String string);


}
