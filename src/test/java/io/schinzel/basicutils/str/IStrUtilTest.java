package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

public class IStrUtilTest {
    private class StrUtil extends AbstractIStr<StrUtil> implements IStrUtil<StrUtil> {
        @Override
        public StrUtil getThis() {
            return this;
        }
    }


    @Test
    public void isEmpty_emptyString() throws Exception {
        Assert.assertTrue("Is empty should return true", Str.create().isEmpty());
    }


    @Test
    public void isEmpty_nonEmptyString() throws Exception {
        Assert.assertFalse("Is empty should return false", Str.create("bapp").isEmpty());
    }
}