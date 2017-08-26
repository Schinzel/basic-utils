package io.schinzel.basicutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author schinzel
 */
public class ThrowerTest extends Thrower {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void throwIfVarNull_NonNullStringArray_NoException() {
        try {
            String[] stringArray = {"a"};
            Thrower.throwIfVarNull(stringArray, "argumentName");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfVarNull_NullStringArray_ThrowException() {
        String[] stringArray = null;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be null");
        Thrower.throwIfVarNull(stringArray, "argumentName");
    }


    @Test
    public void throwIfVarNull_NonNullString_NoException() {
        try {
            String string = "a";
            Thrower.throwIfVarNull(string, "argumentName");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfVarNull_NullString_ThrowsException() {
        String string = null;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be null");
        Thrower.throwIfVarNull(string, "argumentName");
    }


    @Test
    public void throwIfVarNull_AnObject_ReturnsSameObject() {
        RandomUtil randomUtilIn = RandomUtil.create(123);
        Object randomUtilOut = Thrower.throwIfVarNull(randomUtilIn, "");
        assertThat(randomUtilOut).isEqualTo(randomUtilIn);
    }


    @Test
    public void throwIfVarEmpty_StringIn_SameStringReturned() {
        String stringIn = "gibbon";
        String stringOut = Thrower.throwIfVarEmpty(stringIn, "");
        assertThat(stringOut).isEqualTo(stringIn);
    }


    @Test
    public void throwIfVarEmpty_NonEmptyString_NoException() {
        try {
            Thrower.throwIfVarEmpty("a", "argumentName");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwIfVarEmpty("", "argumentName");
    }


    @Test
    public void throwIfVarEmpty_EmptyString_ThrowException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwIfVarEmpty("", "argumentName");
    }


    @Test
    public void throwIfVarEmpty_NullString_ThrowException() {
        String string = null;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwIfVarEmpty(string, "argumentName");
    }


    @Test
    public void throwIfVarOutsideRange_IntIn_SameIntOut() {
        int intIn = RandomUtil.getRandomNumber(1000, 30000);
        int intOut = Thrower.throwIfVarOutsideRange(intIn, "var name", Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertThat(intOut).isEqualTo(intIn);
    }


    @Test
    public void throwIfVarOutsideRange_InSideRanges_NoException() {
        try {
            Thrower.throwIfVarOutsideRange(1, "varre", 1, 1);
            Thrower.throwIfVarOutsideRange(1, "varre", 1, 10);
            Thrower.throwIfVarOutsideRange(-1000, "varre", -1000, 10);
            Thrower.throwIfVarOutsideRange(10, "varre", 1, 10);
            Thrower.throwIfVarOutsideRange(-1000, "varre", -2000, -1000);
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfVarOutsideRange_MinLargerThanMax_ExceptionUsingMethod() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Error using method.");
        Thrower.throwIfVarOutsideRange(-1000, null, 100, 10);
    }


    @Test
    public void throwIfVarOutsideRange_Val0Min1_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value 0 in variable 'varre' is too small. Min value is 1.");
        Thrower.throwIfVarOutsideRange(0, "varre", 1, 10);
    }


    @Test
    public void throwIfVarOutsideRange_Val11Max10_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value ");
        Thrower.throwIfVarOutsideRange(11, "varre", 1, 10);
    }


    @Test
    public void throwIfVarOutsideRange_ValMinus101MinMinus100_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value ");
        Thrower.throwIfVarOutsideRange(-101, "varre", -100, -10);
    }


    @Test
    public void throwIfTrue_False_NoException() {
        try {
            Thrower.throwIfTrue(false, "");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfTrue_True_ThrowsException() {
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwIfTrue(true, errorMessage);
    }


    @Test
    public void throwIfFalse_True_NoException() {
        try {
            Thrower.throwIfFalse(true, null);
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfFalse_False_ThrowsException() {
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwIfFalse(false, errorMessage);
    }


    @Test
    public void throwIfVarTooSmall_Val9Min10_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value 9 in variable 'varre' is too small. Min value is 10.");
        Thrower.throwIfVarTooSmall(9, "varre", 10);
    }


    @Test
    public void testThrowIfTooSmall_Val10Min10_NoException() {
        try {
            Thrower.throwIfVarTooSmall(10, "varre", 10);
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfVarTooSmall_IntIn_SameIntOut() {
        int intIn = RandomUtil.getRandomNumber(1000, 30000);
        int intOut = Thrower.throwIfVarTooSmall(intIn, "", Integer.MIN_VALUE);
        assertThat(intOut).isEqualTo(intIn);
    }


    @Test
    public void throwIfVarTooLarge_Val11Max10_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value 11 in variable 'varre' is too large. Max value is 10.");
        Thrower.throwIfVarTooLarge(11, "varre", 10);
    }


    @Test
    public void throwIfVarTooLarge_IntIn_SameIntOut() {
        int intIn = RandomUtil.getRandomNumber(1000, 30000);
        int intOut = Thrower.throwIfVarTooLarge(intIn, "", Integer.MAX_VALUE);
        assertThat(intOut).isEqualTo(intIn);
    }


    @Test
    public void throwIfVarTooLarge_Val10Max10_NoException() {
        try {
            Thrower.throwIfVarTooLarge(10, "varre", 10);
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }
    //------------------------------------------------------------------------
    // Test of extensive error message
    //------------------------------------------------------------------------


    @Test
    public void throwIfTrue_ChainedMessageAndIsTrue_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("My message");
        Thrower.throwIfTrue(true).message("My message");
    }


    @Test
    public void throwIfTrue_ChainedMessageAndIsFalse_NoException() {
        try {
            Thrower.throwIfTrue(false)
                    .message("My message");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfFalse_ChainedMessageAndIsFalse_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("My message");
        Thrower.throwIfFalse(false).message("My message");
    }


    @Test
    public void throwIfFalse_ChainedMessageAndIsTrue_NoException() {
        try {
            Thrower.throwIfFalse(true).message("My message");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfNull_OneArgumentShouldThrow_ExceptionThrown() {
        try {
            Thrower.throwIfNull(null).message("My message");
            Assert.fail("Exception should be thrown");
        } catch (Throwable t) {
            Assert.assertEquals("My message", t.getMessage());
        }
    }


    @Test
    public void throwIfNull_OneArgumentShouldNotThrow_NoExceptionThrown() {
        try {
            Thrower.throwIfNull(new Object()).message("My message");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }


    @Test
    public void throwIfNull_TwoArgumentsAndShouldThrow_ExceptionThrown() {
        try {
            Thrower.throwIfNull(null, "My message");
            Assert.fail("Exception should be thrown");
        } catch (Throwable t) {
            Assert.assertEquals("My message", t.getMessage());
        }
    }


    @Test
    public void throwIfNull_TwoArgumentsAndShouldNotThrow_NoExceptionThrown() {
        try {
            Thrower.throwIfNull(new Object(), "My message");
        } catch (Throwable t) {
            Assert.fail("Exception should not be thrown");
        }
    }
}

