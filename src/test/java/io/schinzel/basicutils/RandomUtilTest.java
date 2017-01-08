package io.schinzel.basicutils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class RandomUtilTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetSeed() {
        int noOfSeeds = 1000;
        Set<Long> set = new HashSet<>(noOfSeeds);
        for (int i = 0; i < noOfSeeds; i++) {
            long seed = RandomUtil.generateSeed();
            Assert.assertFalse(set.contains(seed));
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
    public void test_getInt_OrderIsPredictable() {
        int seed = 456789;
        RandomUtil rand = RandomUtil.create(seed);
        int min = 0;
        int max = 100;
        int sequenceSize = 1000;
        int[] sequence = new int[sequenceSize];
        //Populate sequence
        for (int i = 0; i < sequenceSize; i++) {
            sequence[i] = rand.getInt(min, max);
        }
        for (int j = 0; j < sequence.length; j++) {
            rand = RandomUtil.create(seed);
            for (int i = 0; i < sequence.length; i++) {
                Assert.assertEquals(sequence[i], rand.getInt(min, max));
            }
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
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }


    @Test
    public void test_getIntArray_errors() {
        //fånga fallet att vi har mindre sum än count
        int arraySum, arraySize;
        arraySum = 10;
        arraySize = 10;
        exception.expect(RuntimeException.class);
        RandomUtil.create().getIntArray(arraySize, arraySum);
    }


    @Test
    public void test_getIntArray_errors_2() {
        //fånga fallet att vi har mindre sum än count
        int arraySum, arraySize;
        arraySum = 10;
        arraySize = 0;
        exception.expect(RuntimeException.class);
        RandomUtil.create().getIntArray(arraySize, arraySum);
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
            Assert.assertThat(number, Matchers.greaterThanOrEqualTo(min));
            Assert.assertThat(number, Matchers.lessThanOrEqualTo(max));
        }
    }


    @Test(expected = RuntimeException.class)
    public void testGetRandomNumberException() {
        RandomUtil.getRandomNumber(100, 1);
        // Exception should be thrown before this line
        Assert.assertTrue(false);
    }


    @Test
    public void testGetRandomString() {
        String randomString;
        //Test that length is correct
        randomString = RandomUtil.getRandomString(5);
        Assert.assertTrue(randomString.length() == 5);
        //Test short 
        randomString = RandomUtil.getRandomString(1);
        Assert.assertTrue(randomString.length() == 1);
        //Test long
        randomString = RandomUtil.getRandomString(400);
        Assert.assertTrue(randomString.length() == 400);
        //Test that only alphnum chars lower case
        randomString = RandomUtil.getRandomString(100);
        Pattern pattern = Pattern.compile("^[a-z0-9]*");
        Assert.assertTrue(pattern.matcher(randomString).matches());
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
            Assert.assertThat(number, Matchers.greaterThanOrEqualTo(min));
            Assert.assertThat(number, Matchers.lessThanOrEqualTo(max));
        }
    }


    @Test
    public void testGetPaddedInt() {
        Assert.assertEquals("160", RandomUtil.create(1234).getPaddedInt(100, 200, 1));
        Assert.assertEquals("160", RandomUtil.create(1234).getPaddedInt(100, 200, 2));
        Assert.assertEquals("160", RandomUtil.create(1234).getPaddedInt(100, 200, 3));
        Assert.assertEquals("0160", RandomUtil.create(1234).getPaddedInt(100, 200, 4));
    }


    /**
     * Test when padding is smaller than length of returned value.
     */
    @Test
    public void testGetPaddedInt_paddingShorterThanNumber() {
        Assert.assertEquals("160", RandomUtil.create(1234).getPaddedInt(100, 200, 1));
        Assert.assertEquals("160", RandomUtil.create(1234).getPaddedInt(100, 200, 2));
        Assert.assertEquals("160", RandomUtil.create(1234).getPaddedInt(100, 200, 3));
        Assert.assertEquals("0160", RandomUtil.create(1234).getPaddedInt(100, 200, 4));
    }


    @Test
    public void testGetPaddedInt_incorrectPaddingArgToSmall() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        RandomUtil.create(1234).getPaddedInt(100, 200, 0);
    }


    @Test
    public void testGetPaddedInt_incorrectPaddingArgToLarge() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        RandomUtil.create(1234).getPaddedInt(100, 200, 1000);
    }


}
