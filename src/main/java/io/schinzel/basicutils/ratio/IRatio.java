package io.schinzel.basicutils.ratio;

import io.schinzel.basicutils.Thrower;

import java.math.BigInteger;

/**
 * This is the base interface.
 * <p>
 * Actual functionality are implemented in a set of interfaces that extends this
 * interface. The implementing class implements one or several of the interfaces that extended this.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatio<T extends IRatio<T>> {


    /**
     * This should really be package private or protected. But as this is not an option, it has to
     * be public.
     *
     * @param numerator
     * @param denominator
     * @return A new ratio instance.
     */
    T newInstance(BigInteger numerator, BigInteger denominator);


    default T newInstance2(BigInteger numerator, BigInteger denominator) {
        Thrower.throwIfVarNull(numerator, "numerator");
        Thrower.throwIfVarNull(denominator, "denominator");
        if (BigInteger.ZERO.equals(denominator)) {
            throw new RuntimeException("Denominator cannot be zero");
        }
        BigInteger greatestCommonDenominator = numerator.gcd(denominator);
        numerator = numerator.divide(greatestCommonDenominator);
        denominator = denominator.divide(greatestCommonDenominator);
        return this.newInstance(numerator, denominator);
    }


    /**
     * @return The numerator of this ratio.
     */
    BigInteger getNumerator();


    /**
     * @return The denominator of this ratio.
     */
    BigInteger getDenominator();


}
