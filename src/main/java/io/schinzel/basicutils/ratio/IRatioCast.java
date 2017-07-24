package io.schinzel.basicutils.ratio;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Handles the casting of the ratio.
 * <p>
 * Created by schinzel on 2017-03-19.
 */
interface IRatioCast<T extends IRatio<T>> extends IRatio<T> {

    /**
     * @return The value held by this instance
     */
    default BigDecimal getBigDecimal() {
        BigDecimal num = new BigDecimal(this.getNumerator());
        BigDecimal dec = new BigDecimal(this.getDenominator());
        return num.divide(dec, MathContext.DECIMAL128);
    }


    /**
     * @return The value held by this instance
     */
    default double getDouble() {
        return this.getBigDecimal().doubleValue();
    }







}
