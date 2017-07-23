package io.schinzel.basicutils.ratio;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;


public class IRatioCastTest {


    private class RatioCast extends AbstractRatio<RatioCast> implements IRatioCast<RatioCast> {
        RatioCast(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioCast newInstance(BigInteger num, BigInteger den) {
            return new RatioCast(num.intValue(), den.intValue());
        }
    }


    @Test
    public void getBigDecimal_num1den2_0dot5() {
        BigDecimal actual = new RatioCast(1, 2).getBigDecimal();
        assertThat(actual).isEqualTo("0.5");
    }


    @Test
    public void getStr_num3debn7_3slash7() {
        String actual = new RatioCast(3, 7).getStr().getString();
        assertThat(actual).isEqualTo("3/7");
    }


    @Test
    public void getDouble_num1den2_0dot5() {
        double actual = new RatioCast(1, 2).getDouble();
        assertThat(actual).isEqualTo(0.5d);
    }


    @Test
    public void getDouble_num1den3_0dot3333333() {
        double actual = new RatioCast(1, 3).getDouble();
        assertThat(actual).isCloseTo(0.33333d, within(0.00001d));
    }

}