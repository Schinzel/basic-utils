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
        RatioMinus r1 = new RatioMinus(7, 4);
        String actual = r1.minus(2).getStr().toString();
        assertThat(actual).isEqualTo("-1/4");
    }


    @Test
    public void test_7over4minusLong2_Minus1over4() {
        RatioMinus r1 = new RatioMinus(7, 4);
        String actual = r1.minus(2L).getStr().toString();
        assertThat(actual).isEqualTo("-1/4");
    }


    @Test
    public void test_7over4minusBigInteger2_Minus1over4() {
        RatioMinus r1 = new RatioMinus(7, 4);
        String actual = r1.minus(2L).getStr().toString();
        assertThat(actual).isEqualTo("-1/4");
    }


    @Test
    public void test_2minusRatioZero_2() {
        RatioMinus r1 = new RatioMinus(2, 1);
        RatioMinus r2 = new RatioMinus(0, 2);
        String actual = r1.minus(r2).getStr().toString();
        assertThat(actual).isEqualTo("2/1");
    }


    @Test
    public void test_Ratio7over4minus5over2_Minus3over4() {
        RatioMinus r1 = new RatioMinus(7, 4);
        RatioMinus r2 = new RatioMinus(5, 2);
        String actual = r1.minus(r2).getStr().toString();
        assertThat(actual).isEqualTo("-3/4");
    }


}
