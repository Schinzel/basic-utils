package io.schinzel.basicutils.crypto.encoding;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


public class Base62Test {

    @Test
    public void encodeDecode_FunnyChars_DecodedStringShouldBeSameAsInput() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String input = funnyChars.getString();
            String encodedString = Base62.encode(UTF8.getBytes(input));
            byte[] decodedBytes = Base62.decode(encodedString);
            String decodedString = UTF8.getString(decodedBytes);
            assertThat(decodedString).isEqualTo(input);
        }
    }


    @Test
    public void encodeDecode_LongStringOneMillionChars_DecodedStringShouldBeSameAsInput() {
        RandomUtil random = RandomUtil.create(123);
        Str str = Str.create();
        for (int i = 0; i < 10_000; i++) {
            str.a(random.getString(100));
        }
        String input = str.asString();
        String encodedString = Base62.encode(UTF8.getBytes(input));
        byte[] decodedBytes = Base62.decode(encodedString);
        String decodedString = UTF8.getString(decodedBytes);
        assertThat(decodedString).isEqualTo(input);
    }


    @Test
    public void encodeDecode_ShortStringOneChars_DecodedStringShouldBeSameAsInput() {
        String input = "a";
        String encodedString = Base62.encode(UTF8.getBytes(input));
        byte[] decodedBytes = Base62.decode(encodedString);
        String decodedString = UTF8.getString(decodedBytes);
        assertThat(decodedString).isEqualTo(input);
    }


    @Test
    public void encodeDecode_EmptyString_DecodedStringShouldBeSameAsInput() {
        String input = "";
        String encodedString = Base62.encode(UTF8.getBytes(input));
        byte[] decodedBytes = Base62.decode(encodedString);
        String decodedString = UTF8.getString(decodedBytes);
        assertThat(decodedString).isEqualTo(input);
    }


    @Test
    public void encode_NullString_ThrowException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                Base62.encode(null)
        );
    }

    @Test
    public void decode_NullString_ThrowException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                Base62.decode(null)
        );
    }


    @Test
    public void encodeDecode_TheThousandRandomChars_DecodedStringShouldBeSameAsInput() {
        RandomUtil random = RandomUtil.create();
        for (int i = 0; i < 10_000; i++) {
            int length = random.getInt(1, 500);
            String input = random.getString(length);
            byte[] randomBytes = UTF8.getBytes(input);
            String encodedString = Base62.encode(randomBytes);
            byte[] decodedBytes = Base62.decode(encodedString);
            String decodedString = UTF8.getString(decodedBytes);
            assertThat(decodedString).isEqualTo(input);
        }
    }


    /**
     * Test took just less than 100 ms on a on a 2016 MacBook Pro 2,6 GHz Intel Core i7.
     * Set to less than 400 as when running all test with code coverage this method
     * takes approximately 250 ms.
     */
    @Test
    public void encodeDecodeTime_TenThousandRandomString_LessThan400ms() {
        RandomUtil random = RandomUtil.create(123);
        ArrayList<byte[]> inputs = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            int length = random.getInt(1, 500);
            String randomString = random.getString(length);
            byte[] randomBytes = UTF8.getBytes(randomString);
            inputs.add(randomBytes);
        }
        long startTime = System.currentTimeMillis();
        for (byte[] input : inputs) {
            String encodedString = Base62.encode(input);
            Base62.decode(encodedString);
        }
        long execTime = System.currentTimeMillis() - startTime;
        assertThat(execTime).isLessThan(400);
    }
}