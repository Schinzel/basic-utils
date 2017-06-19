package io.schinzel.basicutils.crypto.encoding;


import com.google.common.io.BaseEncoding;
import lombok.SneakyThrows;

/**
 * Encodes data and decodes encoded string.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
public enum Encoding implements IEncoding {
    BASE64 {
        @Override
        public String encode(byte[] b) {
            return BaseEncoding.base64().encode(b);
        }


        @Override
        public byte[] decode(String str) {
            return BaseEncoding.base64().decode(str);
        }
    },
    /** Hex encoding aka Base16 */
    HEX {
        @Override
        public String encode(byte[] b) {
            return BaseEncoding.base16().lowerCase().encode(b);
        }


        @SneakyThrows
        @Override
        public byte[] decode(String str) {
            return BaseEncoding.base16().lowerCase().decode(str);
        }

    }
}
