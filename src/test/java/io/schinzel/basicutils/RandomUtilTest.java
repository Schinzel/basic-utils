package io.schinzel.basicutils;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author schinzel
 */
@RunWith(JUnitParamsRunner.class)
public class RandomUtilTest {


    @Test
    public void generateSeed_Generate1000Seeds_SeedShouldNeverBeRepeated() {
        int noOfSeeds = 1000;
        Set<Long> set = new HashSet<>(noOfSeeds);
        for (int i = 0; i < noOfSeeds; i++) {
            long seed = RandomUtil.generateSeed();
            assertThat(set.contains(seed)).isFalse();
            set.add(seed);
        }
    }


    @Test
    public void testGetInstance_sameSeed() {
        //Two instance with the same seed should generate
        //the same random data
        RandomUtil instance1 = RandomUtil.create(123);
        RandomUtil instance2 = RandomUtil.create(123);
        Assert.assertEquals(instance1.getInt(10, 100), instance2.getInt(10, 100));
        Assert.assertEquals(instance1.getString(10), instance2.getString(10));
    }


    @Test
    public void testGetInstance_differentSeeds() {
        //Two instance with the same seed should generate
        //the same random data
        RandomUtil instance1 = RandomUtil.create(123);
        RandomUtil instance2 = RandomUtil.create(456);
        Assert.assertNotEquals(instance1.getInt(10, 100), instance2.getInt(10, 100));
        Assert.assertNotEquals(instance1.getString(10), instance2.getString(10));
    }


    @Test
    public void getInt_TwoRandObjectWithSameSeedGenerate1000numbers_NumbersEqual() {
        int seed = RandomUtil.getRandomNumber(10000, 20000);
        RandomUtil rand1 = RandomUtil.create(seed);
        RandomUtil rand2 = RandomUtil.create(seed);
        for (int i = 0; i < 1000; i++) {
            assertThat(rand1.getInt(0, 100)).isEqualTo(rand2.getInt(0, 100));
        }
    }


    @Test
    public void test_getIntArray() {
        RandomUtil mRand = RandomUtil.create();
        int arraySum, arraySize;
        int[] result;
        //Basic test
        arraySum = 100;
        arraySize = 5;
        result = mRand.getIntArray(arraySize, arraySum);
        Assert.assertEquals(arraySum, sumArray(result));
        Assert.assertEquals(arraySize, result.length);
        //Test one elem
        arraySum = 100;
        arraySize = 1;
        result = mRand.getIntArray(arraySize, arraySum);
        Assert.assertEquals(arraySum, sumArray(result));
        Assert.assertEquals(arraySize, result.length);
        //Test when sum is only slightly larger than size
        arraySum = 6;
        arraySize = 5;
        result = mRand.getIntArray(arraySize, arraySum);
        Assert.assertEquals(arraySum, sumArray(result));
        Assert.assertEquals(arraySize, result.length);
    }


    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int element : arr) {
            sum += element;
        }
        return sum;
    }


    @Test
    @Parameters({"10, 10", "10, 0", "2, 10"})
    public void getIntArray_SizeIsSameSizeAsSum_Exception(int arraySum, int arraySize) {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                RandomUtil.create().getIntArray(arraySize, arraySum)
        );
    }


    @Test
    public void testGetRandomNumber() {
        int min, max, result;
        min = 1;
        max = 10;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            Assert.assertTrue((result >= min) && (result <= max));
        }
        min = 10000;
        max = 10010;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            Assert.assertTrue((result >= min) && (result <= max));
        }
        min = 1;
        max = 2;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            Assert.assertTrue((result >= min) && (result <= max));
        }
        min = Integer.MAX_VALUE - 1;
        max = Integer.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            Assert.assertTrue((result >= min) && (result <= max));
        }
    }


    /**
     * Checks that all numbers in the range are a possible output.
     */
    @Test
    public void testRandomNumberRange() {
        int min = 3;
        int max = 5;
        for (int i = 0; i < 1000; i++) {
            int number = RandomUtil.getRandomNumber(min, max);
            assertThat(number).isBetween(min, max);
        }
    }


    @Test(expected = RuntimeException.class)
    public void testGetRandomNumberException() {
        RandomUtil.getRandomNumber(100, 1);
        // Exception should be thrown before this line
        Assert.fail();
    }


    @Test
    public void testGetRandomString() {
        String randomString;
        //Test that length is correct
        randomString = RandomUtil.getRandomString(5);
        assertThat(randomString).hasSize(5);
        //Test short
        randomString = RandomUtil.getRandomString(1);
        assertThat(randomString).hasSize(1);
        //Test long
        randomString = RandomUtil.getRandomString(400);
        assertThat(randomString).hasSize(400);
        //Test that only alphnum chars lower case
        randomString = RandomUtil.getRandomString(100);
        Pattern pattern = Pattern.compile("^[a-z0-9]*");
        assertThat(randomString).matches(pattern);
    }


    /**
     * Two instances with the same seed should generated the same doubles
     */
    @Test
    public void testGetDouble_SameSeed() {
        RandomUtil instance1 = RandomUtil.create(1234);
        RandomUtil instance2 = RandomUtil.create(1234);
        Assert.assertEquals(
                instance1.getDouble(0, 10d),
                instance2.getDouble(0, 10d),
                0);
    }


    /**
     * Make sure that the random doubles are less than max and more than min.
     */
    @Test
    public void testGetDouble_MaxMin() {
        RandomUtil rand = RandomUtil.create(1234);
        for (int i = 0; i < 1000; i++) {
            double min = 0d;
            double max = 1000d;
            double number = rand.getDouble(min, max);
            assertThat(number).isGreaterThanOrEqualTo(min);
            assertThat(number).isLessThanOrEqualTo(max);
        }
    }


    @Test
    @Parameters({
            "1, 160",
            "2, 160",
            "3, 160",
            "4, 0160"
    })
    public void testGetPaddedInt(int padding, String expected) {
        String actual = RandomUtil.create(1234).getPaddedInt(100, 200, padding);
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    @Parameters({"0", "1000"})
    public void testGetPaddedInt_incorrectPaddingArgToSmall(int padding) {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                RandomUtil.create(1234).getPaddedInt(100, 200, padding)
        );
    }


    @Test
    public void testGetPaddedInt_incorrectPaddingArgToLarge() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                RandomUtil.create(1234).getPaddedInt(100, 200, 1000)
        );
    }


}
