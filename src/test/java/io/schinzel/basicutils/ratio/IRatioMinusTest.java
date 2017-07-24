package io.schinzel.basicutils.ratio;

import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;


public class IRatioMinusTest {

    private class RatioMinus extends AbstractRatio<RatioMinus> implements IRatioMinus<RatioMinus> {
        RatioMinus(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioMinus newInstance(BigInteger num, BigInteger den) {
            return new RatioMinus(num.intValue(), den.intValue());
        }

    }


    @Test
    public void test_7over4minusInt2_Minus1over4() {
        String actual = new RatioMinus(7, 4)
                .minus(2)
                .getStr().toString();
        assertThat(actual).isEqualTo("-1/4");
    }


    @Test
    public void test_7over4minusLong2_Minus1over4() {
        String actual = new RatioMinus(7, 4)
                .minus(2L)
                .getStr().toString();
        assertThat(actual).isEqualTo("-1/4");
    }


    @Test
    public void test_7over4minusBigInteger2_Minus1over4() {
        String actual = new RatioMinus(7, 4)
                .minus(2L)
                .getStr().toString();
        assertThat(actual).isEqualTo("-1/4");
    }


    @Test
    public void test_2minusRatioZero_2() {
        String actual = new RatioMinus(2, 1)
                .minus(new RatioMinus(0, 2))
                .getStr().toString();
        assertThat(actual).isEqualTo("2/1");
    }


    @Test
    public void test_Ratio7over4minus5over2_Minus3over4() {
        String actual = new RatioMinus(7, 4)
                .minus(new RatioMinus(5, 2))
                .getStr().toString();
        assertThat(actual).isEqualTo("-3/4");
    }


}
