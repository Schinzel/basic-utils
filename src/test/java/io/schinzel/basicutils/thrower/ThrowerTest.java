package io.schinzel.basicutils.thrower;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/**
 * @author schinzel
 */
@SuppressWarnings("ConstantConditions")
public class ThrowerTest extends Thrower {


    //------------------------------------------------------------------------
    // Test of extensive error message
    //------------------------------------------------------------------------


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
}

