package io.schinzel.basicutils.ratio;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * The purpose of this class enable the basic arithmetic operations on rational numbers and not lose precision.
 * Precision might be lost as decimal numbers cannot be represented in binary code.
 * <p>
 * Formulas from here
 * https://en.wikipedia.org/wiki/Rational_number#Arithmetic
 * <p>
 *
 * @author schinzel
 */
@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
@Accessors(prefix = "m")
public class Ratio implements IRatioCompare<Ratio>, IRatioDividedBy<Ratio>, IRatioMinus<Ratio>,
        IRatioOutput<Ratio>, IRatioPlus<Ratio>, IRatioTimes<Ratio> {
    @Getter @Setter
    private BigInteger mNumerator;
    @Getter @Setter
    private BigInteger mDenominator;


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     */
    private Ratio(BigInteger numerator, BigInteger denominator) {
        if (BigInteger.ZERO.equals(denominator)) {
            throw new RuntimeException("Denominator cannot be zero");
        }
        BigInteger greatestCommonDenominator = numerator.gcd(denominator);
        mNumerator = numerator.divide(greatestCommonDenominator);
        mDenominator = denominator.divide(greatestCommonDenominator);
    }


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new instance
     */
    public static Ratio create(int numerator, int denominator) {
        return new Ratio(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new instance
     */
    public static Ratio create(long numerator, long denominator) {
        return new Ratio(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }


    /**
     * @param numerator   The numerator
     * @param denominator The denominator
     * @return A new instance
     */
    public static Ratio create(BigInteger numerator, BigInteger denominator) {
        return new Ratio(numerator, denominator);
    }


    @Override
    public Ratio getThis() {
        return this;
    }


    @Override
    public String toString() {
        return this.getString();
    }


    @Override
    public boolean equals(Object o){
        return (o instanceof Ratio) && this.equals((Ratio)o);
    }
}
