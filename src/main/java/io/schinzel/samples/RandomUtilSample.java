package io.schinzel.samples;

import io.schinzel.basicutils.RandomUtil;

import java.util.Arrays;

/**
 * Sample class to show how RandomUtil can be used.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
class RandomUtilSample {
    public static void main(String[] args) {
        //Get a random string with the length 12
        String str = RandomUtil.getRandomString(12);
        System.out.println(str);
        //Get a random number between 1-200 as a string padded to length 3
        //E.g. "009", "175", "035"
        String str2 = RandomUtil.create(1234).getPaddedInt(1, 200, 3);
        System.out.println(str2);
        //An array that has the argument number of cells which will be filled with
        //random numbers that will sum to the argument sum
        int arrayLength = 5;
        int arraySum = 200;
        int[] arr = RandomUtil.create().getIntArray(arrayLength, arraySum);
        System.out.println(Arrays.toString(arr));
    }

}
