package io.schinzel.basicutils.ratio;


import io.schinzel.basicutils.RandomUtil;

import java.math.BigInteger;
import java.util.Random;

/**
 * Generates random big integers
 *
 * Created by schinzel on 2017-03-20.
 */
class RandomBigIntUtil {
    private final Random mBigIntRand;
    private final RandomUtil mBitLength;


    RandomBigIntUtil() {
        mBigIntRand = new Random(RandomUtil.getRandomNumber(1000, 10000000));
        mBitLength = RandomUtil.create(mBigIntRand.nextInt());
    }


    BigInteger getNext() {
        return new BigInteger(mBitLength.getInt(2, 100), mBigIntRand);
    }
}
