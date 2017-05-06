package io.schinzel.basicutils.crypto;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class VersionPrefixTest {

    @Property
    public void test(@InRange(min = "1") int version, String string) {
        //Delete any white spaces in argument string
        string = StringUtils.deleteWhitespace(string);
        String s = VersionPrefix.addVersionPrefix(version, string);
        VersionPrefix versionPrefix = new VersionPrefix(s);
        assertEquals(version, (long) versionPrefix.getVersion());
        assertEquals(string, versionPrefix.getString());
    }

}