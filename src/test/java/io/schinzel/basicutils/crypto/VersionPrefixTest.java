package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import org.junit.Assert;
import org.junit.Test;

public class VersionPrefixTest {

    @Test
    public void getVersion_VersionSetWithAddVersionPrefix_OutputShouldBeSameAsInput() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String string = funnyChars.getString();
            int version = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
            String s = VersionPrefix.addVersionPrefix(version, string);
            Assert.assertEquals(version, (long) new VersionPrefix(s).getVersion());

        }
    }


    @Test
    public void getString_StringSetWithAddVersionPrefix_OutputShoudlBeSameAsInput() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String input = funnyChars.getString();
            int version = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
            String s = VersionPrefix.addVersionPrefix(version, input);
            Assert.assertEquals(input, new VersionPrefix(s).getString());
        }
    }

}