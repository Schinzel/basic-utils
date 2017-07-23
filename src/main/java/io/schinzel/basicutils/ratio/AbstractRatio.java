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
public abstract class AbstractRatio<T extends IRatio<T>> implements IRatio<T> {
    @Getter private final BigInteger mNumerator;
    @Getter private final BigInteger mDenominator;


    AbstractRatio(BigInteger numerator, BigInteger denominator) {
        Thrower.throwIfVarNull(numerator, "numerator");
        Thrower.throwIfVarNull(denominator, "denominator");
        if (denominator.equals(BigInteger.ZERO)) {
            throw new RuntimeException("Denominator cannot be zero");
        }
        if (numerator.equals(BigInteger.ZERO)) {
            mNumerator = BigInteger.ZERO;
            mDenominator = BigInteger.ONE;
        } else {
            //If both num and den are negative
            if (numerator.signum() == -1 && denominator.signum() == -1) {
                //Make both den and num postive
                numerator = numerator.negate();
                denominator = denominator.negate();
            }
            BigInteger greatestCommonDenominator = numerator.gcd(denominator);
            mNumerator = numerator.divide(greatestCommonDenominator);
            mDenominator = denominator.divide(greatestCommonDenominator);
        }
    }


}
