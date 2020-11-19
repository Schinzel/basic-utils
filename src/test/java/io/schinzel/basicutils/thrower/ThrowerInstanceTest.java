package io.schinzel.basicutils.thrower;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.val;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author Schinzel
 */
@SuppressWarnings("ConstantConditions")
public class ThrowerInstanceTest {

    @Test
    public void throwIfNotMatchesRegex_MatchesRegEx_ReturnsArgumentString() {
        val regEx = Pattern.compile("[a-zA-Z0-9_-]{1,100}");
        val instance = Thrower.createInstance();
        assertThatCode(() ->
                instance.throwIfNotMatchesRegex("any_string", "myVar", regEx)
        ).doesNotThrowAnyException();
    }

    @Test
    public void throwIfNotMatchesRegex_DoesNotMatchRegEx_Exception() {
        val regEx = Pattern.compile("[a-zA-Z0-9_-]{1,100}");
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfNotMatchesRegex("åäö", "myVar", regEx));
    }


    @Test
    public void throwIfVarNull_NonNullStringArray_NoException() {
        String[] stringArray = {"a"};
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfVarNull(stringArray, "argumentName"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarNull_NullStringArray_ThrowException() {
        String[] stringArray = null;
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarNull(stringArray, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be null");
    }


    @Test
    public void throwIfVarNull_NonNullString_NoException() {
        String string = "a";
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfVarNull(string, "argumentName"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarNull_NullString_ThrowsException() {
        String string = null;
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarNull(string, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be null");
    }


    @Test
    public void throwIfVarEmpty_NonEmptyString_NoException() {
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfVarEmpty("a", "argumentName"))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarEmpty_EmptyString_ThrowException() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarEmpty("", "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NullString_ThrowException() {
        String string = null;
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarEmpty(string, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NullList_ThrowException() {
        List<String> list = null;
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarEmpty(list, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_EmptyList_ThrowException() {
        List<String> list = new ArrayList<>();
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarEmpty(list, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NonEmptyList_NoException() {
        List<String> list = new ImmutableList.Builder<String>()
                .add("A")
                .add("B")
                .build();
        val instance = Thrower.createInstance();
        assertThatCode(() ->
                instance.throwIfVarEmpty(list, "argumentName")).doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarEmpty_NullMap_ThrowException() {
        Map<String, String> map = null;
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarEmpty(map, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_EmptyMap_ThrowException() {
        Map<String, String> map = new HashMap<>();
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarEmpty(map, "argumentName"))
                .withMessage("Argument 'argumentName' cannot be empty");
    }


    @Test
    public void throwIfVarEmpty_NonEmptyMap_NoException() {
        Map<String, String> map = ImmutableMap.<String, String>builder()
                .put("a", "b")
                .build();
        val instance = Thrower.createInstance();
        assertThatCode(() ->
                instance.throwIfVarEmpty(map, "argumentName")
        ).doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarOutsideRange_InSideRanges_NoException() {
        assertThatCode(() -> {
            Thrower.createInstance().throwIfVarOutsideRange(1, "myVariable", 1, 1);
            Thrower.createInstance().throwIfVarOutsideRange(1, "myVariable", 1, 10);
            Thrower.createInstance().throwIfVarOutsideRange(-1000, "myVariable", -1000, 10);
            Thrower.createInstance().throwIfVarOutsideRange(10, "myVariable", 1, 10);
            Thrower.createInstance().throwIfVarOutsideRange(-1000, "myVariable", -2000, -1000);
        }).doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarOutsideRange_MinLargerThanMax_ExceptionUsingMethod() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarOutsideRange(-1000, null, 100, 10))
                .withMessageStartingWith("Error using method.");
    }


    @Test
    public void throwIfVarOutsideRange_Val0Min1_ThrowsException() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarOutsideRange(0, "myVariable", 1, 10))
                .withMessage("The value 0 in variable 'myVariable' is too small. Min value is 1.");
    }


    @Test
    public void throwIfVarOutsideRange_Val11Max10_ThrowsException() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarOutsideRange(11, "myVariable", 1, 10))
                .withMessageStartingWith("The value ");
    }


    @Test
    public void throwIfVarOutsideRange_ValMinus101MinMinus100_ThrowsException() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarOutsideRange(-101, "myVariable", -100, -10))
                .withMessageStartingWith("The value ");

    }


    @Test
    public void throwIfTrue_False_NoException() {
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfTrue(false, ""))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfTrue_True_ThrowsException() {
        String errorMessage = "my error message";
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfTrue(true, errorMessage))
                .withMessage(errorMessage);
    }


    @Test
    public void throwIfFalse_True_NoException() {
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfFalse(true, null))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfFalse_False_ThrowsException() {
        String errorMessage = "my error message";
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                instance.throwIfFalse(false, errorMessage));
    }


    @Test
    public void throwIfVarTooSmall_Val9Min10_ThrowsException() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarTooSmall(9, "myVariable", 10))
                .withMessage("The value 9 in variable 'myVariable' is too small. Min value is 10.");
    }


    @Test
    public void testThrowIfTooSmall_Val10Min10_NoException() {
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfVarTooSmall(10, "myVariable", 10))
                .doesNotThrowAnyException();
    }


    @Test
    public void throwIfVarTooLarge_Val11Max10_ThrowsException() {
        val instance = Thrower.createInstance();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> instance.throwIfVarTooLarge(11, "myVariable", 10))
                .withMessageStartingWith("The value 11 in variable 'myVariable' is too large. Max value is 10.");
    }


    @Test
    public void throwIfVarTooLarge_Val10Max10_NoException() {
        val instance = Thrower.createInstance();
        assertThatCode(() -> instance.throwIfVarTooLarge(10, "myVariable", 10))
                .doesNotThrowAnyException();
    }


}