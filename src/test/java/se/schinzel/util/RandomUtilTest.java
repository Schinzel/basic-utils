package se.schinzel.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
                assertEquals(sequence[i], rand.getInt(min, max));
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
        assertEquals(arraySum, sumArray(result));
        assertEquals(arraySize, result.length);
        //Test one elem
        arraySum = 100;
        arraySize = 1;
        result = mRand.getIntArray(arraySize, arraySum);
        assertEquals(arraySum, sumArray(result));
        assertEquals(arraySize, result.length);
        //Test when sum is only slightly larger than size
        arraySum = 6;
        arraySize = 5;
        result = mRand.getIntArray(arraySize, arraySum);
        assertEquals(arraySum, sumArray(result));
        assertEquals(arraySize, result.length);
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
            assertTrue((result >= min) && (result <= max));
        }
        min = 10000;
        max = 10010;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            assertTrue((result >= min) && (result <= max));
        }

        min = 1;
        max = 2;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            assertTrue((result >= min) && (result <= max));
        }
        min = Integer.MAX_VALUE - 1;
        max = Integer.MAX_VALUE;
        for (int i = 0; i < 100; i++) {
            result = RandomUtil.getRandomNumber(min, max);
            assertTrue((result >= min) && (result <= max));
        }
    }


    /**
     * Checks that all numbers in the range are a possible output.
     */
    @Test
    public void testRandomNumberRange() {
        boolean hit3 = false;
        boolean hit4 = false;
        boolean hit5 = false;

        for (int i = 0; i < 1000; i++) {
            int number = RandomUtil.getRandomNumber(3, 5);
            if (number == 3) {
                hit3 = true;
            } else if (number == 4) {
                hit4 = true;
            } else if (number == 5) {
                hit5 = true;
            } else {
                //Should never get here. 
                assertTrue(false);
            }
            if (hit3 && hit4 && hit5) {
                assertTrue(true);
                return;
            }
        }
        //If got here, we have gone through all itterations without hitting 
        //all numbers in range
        assertTrue(false);
    }


    @Test(expected = RuntimeException.class)
    public void testGetRandomNumberException() {
        RandomUtil.getRandomNumber(100, 1);
        // Exception should be thrown before this line
        assertTrue(false);
    }


    @Test
    public void testGetRandomString() {
        String randomString;
        //Test that length is correct
        randomString = RandomUtil.getRandomString(5);
        assertTrue(randomString.length() == 5);
        //Test short 
        randomString = RandomUtil.getRandomString(1);
        assertTrue(randomString.length() == 1);
        //Test long
        randomString = RandomUtil.getRandomString(400);
        assertTrue(randomString.length() == 400);
        //Test that only alphnum chars lower case
        randomString = RandomUtil.getRandomString(100);
        Pattern pattern = Pattern.compile("^[a-z0-9]*");
        assertTrue(pattern.matcher(randomString).matches());

    }

}
