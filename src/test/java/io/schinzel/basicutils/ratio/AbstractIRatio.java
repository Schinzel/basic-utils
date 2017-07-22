package io.schinzel.basicutils.ratio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Accessors(prefix = "m")
@AllArgsConstructor
abstract class AbstractIRatio<T extends IRatio<T>> implements IRatio<T> {
    @Getter private final BigInteger mNumerator;
    @Getter private final BigInteger mDenominator;


    AbstractIRatio(int num, int den) {
        mNumerator = BigInteger.valueOf(num);
        mDenominator = BigInteger.valueOf(den);
    }


    @Override
    public abstract T newInstance(BigInteger numerator, BigInteger denominator);

}
