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
     * Sets a new ratio. The ration is set thread safe.
     *
     * @param numerator   The numerator to set.
     * @param denominator The denominator to set.
     * @return This for chaining.
     */
    default T setRatio(BigInteger numerator, BigInteger denominator) {
        Thrower.throwIfVarNull(numerator, "numerator");
        Thrower.throwIfVarNull(denominator, "denominator");
        if (BigInteger.ZERO.equals(denominator)) {
            throw new RuntimeException("Denominator cannot be zero");
        }
        BigInteger greatestCommonDenominator = numerator.gcd(denominator);
        BigInteger newNumerator = numerator.divide(greatestCommonDenominator);
        BigInteger newDenominator = denominator.divide(greatestCommonDenominator);
        synchronized (this) {
            this.setNumerator(newNumerator);
            this.setDenominator(newDenominator);
        }
        return this.getThis();
    }


    /**
     * Sets the numerator for this ratio. Use method setRatio rather than this.
     * <p>
     * This method should not really be public. But there are only public method interfaces.
     *
     * @param numerator The numerator to set.
     */
    void setNumerator(BigInteger numerator);


    /**
     * Sets the denominator for this ratio. Use method setRatio rather than this.
     * <p>
     * This method should not really be public. But there are only public method interfaces.
     *
     * @param denominator The denominator to set.
     */
    void setDenominator(BigInteger denominator);


    /**
     * @return The numerator of this ratio.
     */
    BigInteger getNumerator();


    /**
     * @return The denominator of this ratio.
     */
    BigInteger getDenominator();


    /**
     * Exists for programming technical reasons only. Allows implementing classes and extending interfaces to return
     * a "this" that refers to implementing class or extending interface, instead of the interface being implemented
     * or extended. This as opposed to casting and/or overloading methods just to return the correct type.
     * <p>
     * This should really be package private or protected. But as this is not an option, it has to be public.
     *
     * @return This for chaining.
     */
    T getThis();


}
