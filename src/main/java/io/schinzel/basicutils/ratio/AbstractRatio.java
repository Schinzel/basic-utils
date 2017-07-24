package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.Thrower;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * The purpose of this class is hold the constructor for Ratio.
 * The constructor is isolated in a class so that when testing individual
 * interfaces - for example IRatioMinus - the test class only needs to extend
 * 1) this class
 * 2) the interface to be tested.
 * <p>
 * Created by schinzel on 2017-07-22.
 */
@Accessors(prefix = "m")
abstract class AbstractRatio<T extends IRatio<T>> implements IRatio<T> {
    @Getter private final BigInteger mNumerator;
    @Getter private final BigInteger mDenominator;


    /**
     * @param num The numerator
     * @param den The denominator
     */
    AbstractRatio(BigInteger num, BigInteger den) {
        Thrower.throwIfVarNull(num, "num");
        Thrower.throwIfVarNull(den, "den");
        //If den is zero
        if (den.equals(BigInteger.ZERO)) {
            //A legal fraction cannot have a zero denominator
            throw new RuntimeException("Denominator cannot be zero");
        }
        //If the numerator is zero
        if (num.equals(BigInteger.ZERO)) {
            //Then the fraction is equal to all other fractions with zero num
            num = BigInteger.ZERO;
            den = BigInteger.ONE;
        }
        //If den is negative and the num is not zero
        if (den.signum() == -1 && !num.equals(BigInteger.ZERO)) {
            /*
             * We know that the numerator is not zero.
             * The cases are:
             * 1) the num is positive and the den is negative, then the minus sign should be moved from den to num
             * 2) both num and den are negative, then both should be made positive.
             * Thus is both cases the num and den should be negated.
             */
            num = num.negate();
            den = den.negate();
        }
        BigInteger greatestCommonDenominator = num.gcd(den);
        mNumerator = num.divide(greatestCommonDenominator);
        mDenominator = den.divide(greatestCommonDenominator);

    }


}
