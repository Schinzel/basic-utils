package io.schinzel.basicutils.thrower;

import lombok.val;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;


/**
 * @author schinzel
 */
public class ThrowerTest extends Thrower {

    @Test
    public void throwIfNotMatchesRegex_MatchesRegEx_ReturnsArgumentString() {
        val regEx = Pattern.compile("[a-zA-Z0-9_-]{1,100}");
        final String argumentString = "any_string";
        assertThatCode(() -> {
            final String returnString = Thrower.throwIfNotMatchesRegex(argumentString, "myVar", regEx);
            assertThat(returnString).isEqualTo(argumentString);
        }).doesNotThrowAnyException();
    }


    @Test
    public void throwIfNotMatchesRegex_DoesNotMatchRegEx_Exception() {
        val regEx = Pattern.compile("[a-zA-Z0-9_-]{1,100}");
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfNotMatchesRegex("åäö", "myVar", regEx))
                .withMessageStartingWith("Argument 'myVar' does not match regex '[a-zA-Z0-9_-]{1,100}'");
    }


    @Test
    public void throwIfTrue_ChainedMessageAndIsTrue_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfTrue(true).message("My message"))
                .withMessage("My message");
    }


    @Test
    public void throwIfTrue_ChainedMessageAndIsFalse_NoException() {
        assertThatCode(() -> Thrower.throwIfTrue(false).message("My message"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfFalse_ChainedMessageAndIsFalse_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfFalse(false).message("My message"))
                .withMessage("My message");
    }


    @Test
    public void throwIfFalse_ChainedMessageAndIsTrue_NoException() {
        assertThatCode(() -> Thrower.throwIfFalse(true).message("My message"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfNull_OneArgumentShouldThrow_ExceptionThrown() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfNull(null).message("My message"))
                .withMessage("My message");
    }


    @Test
    public void throwIfNull_OneArgumentShouldNotThrow_NoExceptionThrown() {
        assertThatCode(() -> Thrower.throwIfNull(new Object()).message("My message"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfNull_TwoArgumentsAndShouldThrow_ExceptionThrown() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfNull(null, "My message"))
                .withMessageStartingWith("My message");
    }


    @Test
    public void throwIfNull_TwoArgumentsAndShouldNotThrow_NoExceptionThrown() {
        assertThatCode(() -> Thrower.throwIfNull(new Object(), "My message"))
                .doesNotThrowAnyException();
    }

    @Test
    public void getErrorMessage_AnyNameEmptyState_CorrectMessage() {
        String errorMessage = Thrower.getErrorMessage("MyVar", "empty");
        assertThat(errorMessage).isEqualTo("Argument 'MyVar' cannot be empty");

    }
}

