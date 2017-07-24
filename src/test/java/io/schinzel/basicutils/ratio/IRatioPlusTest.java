package io.schinzel.basicutils.ratio;

import lombok.experimental.Accessors;
import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;


@Accessors(prefix = "m")
public class IRatioPlusTest {


    private class RatioPlus extends AbstractRatio<RatioPlus> implements IRatioPlus<RatioPlus> {
        RatioPlus(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioPlus newInstance(BigInteger num, BigInteger den) {
            return new RatioPlus(num.intValue(), den.intValue());
        }

    }


    @Test
    public void plus_OneHalfPlusInt2_5over2() {
        String actual = new RatioPlus(1, 2)
                .plus(2)
                .getStr().toString();
        assertThat(actual).isEqualTo("5/2");
    }


    @Test
    public void plus_OneHalfPlusLong2_5over2() {
        String actual = new RatioPlus(1, 2)
                .plus(2L)
                .getStr().toString();
        assertThat(actual).isEqualTo("5/2");
    }


    @Test
    public void plus_OneHalfPlusBigInteger2_5over2() {
        String actual = new RatioPlus(1, 2)
                .plus(BigInteger.valueOf(2))
                .getStr().toString();
        assertThat(actual).isEqualTo("5/2");
    }


    @Test
    public void plus_OneHalfPlusRatio2_5over2() {
        String actual = new RatioPlus(1, 2)
                .plus(new RatioPlus(2, 1))
                .getStr().toString();
        assertThat(actual).isEqualTo("5/2");
    }


    @Test
    public void plus_OneHalfPlusRatio0_OneHalf() {
        String actual = new RatioPlus(1, 2)
                .plus(new RatioPlus(0, 1))
                .getStr().toString();
        assertThat(actual).isEqualTo("1/2");
    }


    @Test
    public void plus_10Over20plus4Over16_3Over4() {
        String actual = new RatioPlus(10, 20)
                .plus(new RatioPlus(4, 16))
                .getStr().toString();
        assertThat(actual).isEqualTo("3/4");
    }


}