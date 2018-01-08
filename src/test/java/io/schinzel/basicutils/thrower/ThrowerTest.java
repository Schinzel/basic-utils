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


    @Test
    public void throwIfVarNull_NonNullStringArray_NoException() {
        String[] stringArray = {"a"};
        assertThatCode(() -> Thrower.throwIfVarNull(stringArray, "argumentName"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarNull_NullStringArray_ThrowException() {
        String[] stringArray = null;
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarNull(stringArray, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be null");
    }


    @Test
    public void throwIfVarNull_NonNullString_NoException() {
        String string = "a";
        assertThatCode(() -> Thrower.throwIfVarNull(string, "argumentName"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarNull_NullString_ThrowsException() {
        String string = null;
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarNull(string, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be null");
    }

    
    @Test
    public void throwIfVarEmpty_NonEmptyString_NoException() {
        assertThatCode(() -> Thrower.throwIfVarEmpty("a", "argumentName"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarEmpty_EmptyString_ThrowException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarEmpty("", "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NullString_ThrowException() {
        String string = null;
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarEmpty(string, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NullList_ThrowException() {
        List<String> list = null;
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarEmpty(list, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_EmptyList_ThrowException() {
        List<String> list = new ArrayList<>();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarEmpty(list, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NonEmptyList_NoException() {
        List<String> list = new ImmutableList.Builder<String>()
                .add("A")
                .add("B")
                .build();
        assertThatCode(() ->
                Thrower.throwIfVarEmpty(list, "argumentName")).doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarEmpty_NullMap_ThrowException() {
        Map<String, String> map = null;
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarEmpty(map, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_EmptyMap_ThrowException() {
        Map<String, String> map = new HashMap<>();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarEmpty(map, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NonEmptyMap_NoException() {
        Map<String, String> map = ImmutableMap.<String, String>builder()
                .put("a", "b")
                .build();
        assertThatCode(() ->
                Thrower.throwIfVarEmpty(map, "argumentName")
        ).doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarOutsideRange_InSideRanges_NoException() {
        assertThatCode(() -> {
            Thrower.throwIfVarOutsideRange(1, "myVariable", 1, 1);
            Thrower.throwIfVarOutsideRange(1, "myVariable", 1, 10);
            Thrower.throwIfVarOutsideRange(-1000, "myVariable", -1000, 10);
            Thrower.throwIfVarOutsideRange(10, "myVariable", 1, 10);
            Thrower.throwIfVarOutsideRange(-1000, "myVariable", -2000, -1000);
        }).doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarOutsideRange_MinLargerThanMax_ExceptionUsingMethod() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarOutsideRange(-1000, null, 100, 10))
                .withMessageStartingWith("Error using method.");
    }


    @Test
    public void throwIfVarOutsideRange_Val0Min1_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarOutsideRange(0, "myVariable", 1, 10))
                .withMessage("The value 0 in variable 'myVariable' is too small. Min value is 1.");
    }


    @Test
    public void throwIfVarOutsideRange_Val11Max10_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarOutsideRange(11, "myVariable", 1, 10))
                .withMessageStartingWith("The value ");
    }


    @Test
    public void throwIfVarOutsideRange_ValMinus101MinMinus100_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarOutsideRange(-101, "myVariable", -100, -10))
                .withMessageStartingWith("The value ");

    }


    @Test
    public void throwIfTrue_False_NoException() {
        assertThatCode(() -> Thrower.throwIfTrue(false, ""))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfTrue_True_ThrowsException() {
        String errorMessage = "my error message";
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfTrue(true, errorMessage))
                .withMessage(errorMessage);
    }


    @Test
    public void throwIfFalse_True_NoException() {
        assertThatCode(() -> Thrower.throwIfFalse(true, null))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfFalse_False_ThrowsException() {
        String errorMessage = "my error message";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                Thrower.throwIfFalse(false, errorMessage));
    }


    @Test
    public void throwIfVarTooSmall_Val9Min10_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarTooSmall(9, "myVariable", 10))
                .withMessage("The value 9 in variable 'myVariable' is too small. Min value is 10.");
    }


    @Test
    public void testThrowIfTooSmall_Val10Min10_NoException() {
        assertThatCode(() -> Thrower.throwIfVarTooSmall(10, "myVariable", 10))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarTooLarge_Val11Max10_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> Thrower.throwIfVarTooLarge(11, "myVariable", 10))
                .withMessageStartingWith("The value 11 in variable 'myVariable' is too large. Max value is 10.");
    }


    @Test
    public void throwIfVarTooLarge_Val10Max10_NoException() {
        assertThatCode(() -> Thrower.throwIfVarTooLarge(10, "myVariable", 10))
                .doesNotThrowAnyException();
    }
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

