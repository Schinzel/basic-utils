package io.schinzel.basicutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class ThrowerTest extends Thrower {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testCheckEmptyArgumentString() {
        Thrower.throwIfEmpty("monkey", "argumentName");
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwIfEmpty("", "argumentName");
    }


    @Test
    public void testCheckEmptyArgumentStringNull() {
        Thrower.throwIfEmpty("monkey", "argumentName");
        String s = null;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwIfEmpty(s, "argumentName");
    }


    @Test
    public void testCheckNull() {
        Object object = null;
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwIfEmpty(object, errorMessage);
    }


    @Test
    public void testThrowErrorIfOutsideRange() {
        Thrower.throwIfOutsideRange(1, "varre", 1, 1);
        Thrower.throwIfOutsideRange(1, "varre", 1, 10);
        Thrower.throwIfOutsideRange(-1000, "varre", -1000, 10);
        Thrower.throwIfOutsideRange(10, "varre", 1, 10);
        Thrower.throwIfOutsideRange(-1000, "varre", -2000, -1000);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Error using method.");
        Thrower.throwIfOutsideRange(-1000, null, 100, 10);
    }


    @Test
    public void testThrowErrorIfOutsideRange_testFullMessage() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value 0 in variable 'varre' is too small. Min value is 1.");
        Thrower.throwIfOutsideRange(0, "varre", 1, 10);
    }


    @Test
    public void testThrowErrorIfOutsideRange2() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value ");
        Thrower.throwIfOutsideRange(0, "varre", 1, 10);
    }


    @Test
    public void testThrowErrorIfOutsideRange3() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value ");
        Thrower.throwIfOutsideRange(11, "varre", 1, 10);
    }


    @Test
    public void testThrowErrorIfOutsideRange4() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value ");
        Thrower.throwIfOutsideRange(-101, "varre", -100, -10);
    }


    @Test
    public void testThrowErrorIfOutsideRange5() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value ");
        Thrower.throwIfOutsideRange(-9, "varre", -100, -10);
    }


    @Test
    public void testTrowErrorIfTrue() {
        Thrower.throwIfTrue(false, null);
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwIfTrue(true, errorMessage);
    }


    @Test
    public void testTrowErrorIfFalse() {
        Thrower.throwIfFalse(true, null);
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwIfFalse(false, errorMessage);
    }


    @Test
    public void testThrowIfTooSmall_message() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value 9 in variable 'varre' is too small. Min value is 10.");
        Thrower.throwIfTooSmall(9, "varre", 10);
    }


    @Test
    public void testThrowIfTooLarge_message() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The value 11 in variable 'varre' is too large. Max value is 10.");
        Thrower.throwIfTooLarge(11, "varre", 10);
    }


    @Test
    public void testThrowIfTooSmall_limitValue() {
        Thrower.throwIfTooSmall(10, "varre", 10);
        Assert.assertTrue("Should get here as 10 is not to small", true);
    }


    @Test
    public void testThrowIfTooLarge_limitValue() {
        Thrower.throwIfTooLarge(10, "varre", 10);
        Assert.assertTrue("Should get here as 10 is not to large", true);
    }
}
