package io.schinzel.basicutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ThrowerMessageTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void message_ConstructorTrue_ThrowsException() {
        ThrowerMessage throwerMessage = new ThrowerMessage(true);
        exception.expect(RuntimeException.class);
        exception.expectMessage("My message");
        throwerMessage.message("My message");
    }


    @Test
    public void message_ConstructorFalse_NoException() {
        try {
            new ThrowerMessage(false).message("My message");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void message_ThrowerDud_NoException() {
        try {
            ThrowerMessage.THROWER_DUD.message("Any message");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }

}