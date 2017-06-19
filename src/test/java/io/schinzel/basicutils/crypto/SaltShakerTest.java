package io.schinzel.basicutils.crypto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by schinzel on 2017-05-12.
 */
public class SaltShakerTest {

    @Test
    public void getSalt_SaltSize64_ByteArrayLength64() {
        SaltShaker saltShaker = new SaltShaker(64);
        assertEquals(64, saltShaker.getSalt().length);
    }


}