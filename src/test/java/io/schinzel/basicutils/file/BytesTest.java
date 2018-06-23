package io.schinzel.basicutils.file;

import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.UTF8;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;

public class BytesTest {


    @Test
    public void empty_isEmpty() {
        assertThat(Bytes.EMPTY.getByteArray()).isEmpty();
    }


    @Test
    public void asString_ConstructorArgNull_EmptyString() {
        String actual = new Bytes(null).asString();
        assertThat(actual).isEmpty();
    }


    @Test
    public void asString_ConstructorEmptyArray_EmptyString() {
        String actual = new Bytes(EmptyObjects.EMPTY_BYTE_ARRAY).asString();
        assertThat(actual).isEmpty();
    }


    @Test
    public void asStr_ConstructorArgNull_EmptyString() {
        String actual = new Bytes(null).asStr().asString();
        assertThat(actual).isEmpty();
    }


    @Test
    public void asStr_ConstructorEmptyArray_EmptyString() {
        String actual = new Bytes(EmptyObjects.EMPTY_BYTE_ARRAY).asStr().asString();
        assertThat(actual).isEmpty();
    }


    @Test
    public void getByteArray_ConstructorArgNull_EmptyArray() {
        byte[] byteArray = new Bytes(EmptyObjects.EMPTY_BYTE_ARRAY).getByteArray();
        assertThat(byteArray).isEmpty();
    }


    @Test
    public void copyByteArray_ConstructorArgNull_EmptyArray() {
        byte[] byteArray = new Bytes(null).getByteArray();
        assertThat(byteArray).isEmpty();
    }


    @Test
    public void asString_ConstructorRandomArray_ConstructorArgumentArrayAsString() {
        String randomString = RandomUtil.getRandomString(20);
        String actual = new Bytes(UTF8.getBytes(randomString)).asString();
        assertThat(actual).isEqualTo(randomString);
    }


    @Test
    public void asStr_ConstructorRandomArray_ConstructorArgumentArrayAsString() {
        String randomString = RandomUtil.getRandomString(20);
        String actual = new Bytes(UTF8.getBytes(randomString)).asStr().toString();
        assertThat(actual).isEqualTo(randomString);
    }


    @Test
    public void getByteArray_ConstructorRandomArray_ConstructorArgumentArrayAsString() {
        byte[] inputArray = UTF8.getBytes(RandomUtil.getRandomString(20));
        byte[] actual = new Bytes(inputArray).getByteArray();
        assertArrayEquals(actual, inputArray);
    }


    @Test
    public void copyByteArray_ConstructorRandomArray_ConstructorArgumentArrayAsString() {
        byte[] inputArray = UTF8.getBytes(RandomUtil.getRandomString(20));
        byte[] actual = new Bytes(inputArray).copyByteArray();
        assertArrayEquals(actual, inputArray);
    }


    @Test
    public void copyByteArray_ConstructorRandomArray_ArrayCopyReturned() {
        byte[] inputArray = new byte[]{1, 2, 3};
        byte[] actual = new Bytes(inputArray).copyByteArray();
        //Changing the return array should not effect the input array
        actual[0] = 4;
        assertArrayEquals(inputArray, new byte[]{1, 2, 3});
    }

}