package io.schinzel.basicutils;

import io.schinzel.basicutils.thrower.Thrower;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Random;

/**
 * The purpose of this class is to generate random strings. This class offers
 * random static methods and
 *
 * @author schinzel
 */
@SuppressWarnings("WeakerAccess")
public class RandomUtil {

    private final Random mRandom;
    /** Holds the chars that can be a part of the random string. */
    private static final char[] SYMBOLS;


    //Set up the chars that can be used in the random string.
    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch) {
            tmp.append(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            tmp.append(ch);
        }
        SYMBOLS = tmp.toString().toCharArray();
    }
    //*************************************************************************
    //* Construction
    //*************************************************************************


    private RandomUtil(long seed) {
        mRandom = new Random(seed);
    }


    /**
     * @return A newly created instance with random seed.
     */
    public static RandomUtil create() {
        return new RandomUtil(RandomUtil.generateSeed());
    }


    /**
     * @param seed The seed for random values
     * @return An newly created instance with the argument seed.
     */
    public static RandomUtil create(long seed) {
        return new RandomUtil(seed);
    }


    /**
     * The returned seed is compiled from the nano time.
     *
     * @return A seed.
     */
    static long generateSeed() {
        long seed = 1;
        //Get nanos
        long nanos = System.nanoTime();
        long divider = 1_000_000;
        nanos -= (nanos / divider) * divider;
        seed += nanos;
        //Get the millis of current second
        long milliOfSecond = Instant.now().getLong(ChronoField.MILLI_OF_SECOND);
        seed *= milliOfSecond;
        return seed;
    }
    //*************************************************************************
    //* Random methods
    //*************************************************************************


    /**
     * @param min The min (inclusive) of the returned number
     * @param max The max (inclusive) of the returned number
     * @return A random number
     */
    public int getInt(int min, int max) {
        if (min > max) {
            throw new RuntimeException("Max needs to be larger than min");
        }
        Thrower.throwIfVarTooSmall(min, "min", -100);
        Thrower.throwIfVarTooSmall(max, "max", 1);
        return mRandom.nextInt(max - min + 1) + min;
    }


    /**
     * Randomize a double
     *
     * @param min The min (inclusive) of returned value
     * @param max The max (inclusive) of returned value
     * @return A random number
     */
    public double getDouble(double min, double max) {
        return min + (max - min) * mRandom.nextDouble();
    }


    /**
     * @param length The length of the string to return.
     * @return A string with random chars.
     */
    public String getString(int length) {
        Thrower.throwIfVarOutsideRange(length, "length", 0, 500);
        char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = SYMBOLS[mRandom.nextInt(SYMBOLS.length)];
        }
        return new String(buf);
    }


    /**
     * Adds leading zeros to returned number so that returned string always is
     * same or larger than the argument padding.
     * <p>
     * For example, min=1 and max=9 and padding 2 yields a string "04" for
     * example.
     * <p>
     * If padding is smaller than the length of the returned value, the value is
     * returned as is. For example, the random generated is 123 and the padding
     * argument is 1, then "123" will be returned.
     *
     * @param min     The minimum value of the returned number
     * @param max     The maximum value of the returned number
     * @param padding The returned string will at least have this length. Min 1
     *                and max 100.
     * @return A padded int as a string.
     */
    public String getPaddedInt(int min, int max, int padding) {
        Thrower.throwIfVarOutsideRange(padding, "padding", 1, 100);
        String paddingFormat = "%0" + padding + "d";
        return String.format(paddingFormat, this.getInt(min, max));
    }


    /**
     * Populates an integer array with random values.
     *
     * @param arraySize The number of elements in the returned array.
     * @param arraySum  The sum of the values in the returned array.
     * @return A random integer array.
     */
    public int[] getIntArray(int arraySize, int arraySum) {
        Thrower.throwIfVarOutsideRange(arraySize, "arraySize", 1, Integer.MAX_VALUE);
        int sum = arraySum;
        if (sum <= arraySize) {
            throw new RuntimeException("Array size needs to be larger than sum");
        }
        int[] values = new int[arraySize];
        sum -= arraySize;
        for (int i = 0; i < arraySize - 1; ++i) {
            values[i] = mRandom.nextInt(sum);
        }
        values[arraySize - 1] = sum;
        Arrays.sort(values);
        for (int i = arraySize - 1; i > 0; --i) {
            values[i] -= values[i - 1];
        }
        for (int i = 0; i < arraySize; ++i) {
            ++values[i];
        }
        return values;
    }
    //*************************************************************************
    //* Static Random methods
    //*************************************************************************


    /**
     * @param min The min value of the returned number
     * @param max The max value of the returned number
     * @return A random number
     */
    public static int getRandomNumber(int min, int max) {
        return RandomUtil.create().getInt(min, max);
    }


    /**
     * @param length The length of the string to return.
     * @return A string with random chars.
     */
    public static String getRandomString(int length) {
        return RandomUtil.create().getString(length);
    }

}
