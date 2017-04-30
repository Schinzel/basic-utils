package io.schinzel.basicutils.crypto.encoding;


import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * Encodes binary data and decodes encoded string.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
public enum Encoding implements IEncoding {
    BASE64 {
        @Override
        public String encode(byte[] b) {
            return Base64.encodeBase64String(b);
        }


        @Override
        public byte[] decode(String str) {
            return Base64.decodeBase64(str);
        }
    },
    /** Base16 a.k.a. Hex encoding*/
    BASE16 {
        @Override
        public String encode(byte[] b) {
            return Hex.encodeHexString(b);
        }


        @SneakyThrows
        @Override
        public byte[] decode(String str) {
            return Hex.decodeHex(str.toCharArray());
        }

    };
}
