package io.schinzel.basicutils.ratio;

import lombok.experimental.Accessors;
import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;


@Accessors(prefix = "m")
public class IRatioTimesTest {

    private class RatioTimes extends AbstractRatio<RatioTimes> implements IRatioTimes<RatioTimes> {
        RatioTimes(int num, int den) {
            super(BigInteger.valueOf(num), BigInteger.valueOf(den));
        }


        @Override
        public RatioTimes newInstance(BigInteger num, BigInteger den) {
            return new RatioTimes(num.intValue(), den.intValue());
        }

    }


    @Test
    public void time_7over4timesInt3_21slash4() {
        String actual = new RatioTimes(7, 4)
                .times(3)
                .getStr().toString();
        assertThat(actual).isEqualTo("21/4");
    }


    @Test
    public void time_7over4timesLong3_21slash4() {
        String actual = new RatioTimes(7, 4)
                .times(3L)
                .getStr().toString();
        assertThat(actual).isEqualTo("21/4");
    }


    @Test
    public void time_7over4timesBigInteger3_21slash4() {
        String actual = new RatioTimes(7, 4)
                .times(3L)
                .getStr().toString();
        assertThat(actual).isEqualTo("21/4");
    }


    @Test
    public void time_7over4timesRatio3_21slash4() {
        String actual = new RatioTimes(7, 4)
                .times(new RatioTimes(3, 1))
                .getStr().toString();
        assertThat(actual).isEqualTo("21/4");
    }


    @Test
    public void time_7over4timesRatio2overMinus3_21slash4() {
        String actual = new RatioTimes(7, 4)
                .times(new RatioTimes(2, -3))
                .getStr().toString();
        assertThat(actual).isEqualTo("-7/6");
    }


    @Test
    public void time_7over4timesRatio0over1_0over1() {
        String actual = new RatioTimes(7, 4)
                .times(new RatioTimes(0, 1))
                .getStr().toString();
        assertThat(actual).isEqualTo("0/1");
    }


    @Test
    public void time_7over4timesRatio0overMinus1_0over1() {
        String actual = new RatioTimes(7, 4)
                .times(new RatioTimes(0, -11))
                .getStr().toString();
        assertThat(actual).isEqualTo("0/1");
    }


    @Test
    public void time_7over4timesInt0_0over1() {
        String actual = new RatioTimes(7, 4)
                .times(0)
                .getStr().toString();
        assertThat(actual).isEqualTo("0/1");
    }

}