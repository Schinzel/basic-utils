package io.schinzel.basicutils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ThrowerMessageTest {


    @Test
    public void message_ConstructorTrue_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> ThrowerMessage.create(true).message("My message"))
                .withMessageStartingWith("My message");
    }


    @Test
    public void message_ConstructorFalse_NoException() {
        assertThatCode(() ->
                new ThrowerMessage(false).message("My message")).doesNotThrowAnyException();
    }


    @Test
    public void message_CreateFalse_NoException() {
        assertThatCode(() ->
                ThrowerMessage.create(false).message("Any message")).doesNotThrowAnyException();
    }


    @Test
    public void message_Format_CorrectString() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> ThrowerMessage.create(true)
                        .message("The value %1$d in variable '%2$s' is too small. Min value is %3$d.", 123, "my_var", 100))
                .withMessageStartingWith("The value 123 in variable 'my_var' is too small. Min value is 100.");
    }


}