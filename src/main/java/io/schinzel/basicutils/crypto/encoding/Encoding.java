package io.schinzel.basicutils.crypto.encoding;


import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
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
    HEX {
        @Override
        public String encode(byte[] b) {
            return Hex.encodeHexString(b);
        }


        @Override
        @SneakyThrows
        public byte[] decode(String str) {
            return Hex.decodeHex(str.toCharArray());
        }

    };
}
