package io.schinzel.basicutils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author schinzel
 */
public class ThrowerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testCheckEmptyArgumentString() {
        Thrower.throwErrorIfEmpty("monkey", "argumentName");
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwErrorIfEmpty("", "argumentName");
    }

    @Test
    public void testCheckEmptyArgumentStringNull() {
        Thrower.throwErrorIfEmpty("monkey", "argumentName");
        String s = null;
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'argumentName' cannot be empty");
        Thrower.throwErrorIfEmpty(s, "argumentName");
    }

    @Test
    public void testCheckNull() {
        Object object = null;
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwErrorIfEmpty(object, errorMessage);
    }

    @Test
    public void testThrowErrorIfOutsideRange() {
        Thrower.throwErrorIfOutsideRange(1, null, 1, 1);
        Thrower.throwErrorIfOutsideRange(1, null, 1, 10);
        Thrower.throwErrorIfOutsideRange(-1000, null, -1000, 10);
        Thrower.throwErrorIfOutsideRange(10, null, 1, 10);
        Thrower.throwErrorIfOutsideRange(-1000, null, -2000, -1000);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Error using method.");
        Thrower.throwErrorIfOutsideRange(-1000, null, 100, 10);
    }

    @Test
    public void testThrowErrorIfOutsideRange2() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwErrorIfOutsideRange(0, null, 1, 10);
    }

    @Test
    public void testThrowErrorIfOutsideRange3() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwErrorIfOutsideRange(11, null, 1, 10);
    }

    @Test
    public void testThrowErrorIfOutsideRange4() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwErrorIfOutsideRange(-101, null, -100, -10);
    }

    @Test
    public void testThrowErrorIfOutsideRange5() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwErrorIfOutsideRange(-9, null, -100, -10);
    }

    @Test
    public void testTrowErrorIfTrue() {
        Thrower.throwErrorIfTrue(false, null);
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwErrorIfTrue(true, errorMessage);
    }

    @Test
    public void testTrowErrorIfFalse() {
        Thrower.throwErrorIfFalse(true, null);
        String errorMessage = "my error message";
        exception.expect(RuntimeException.class);
        exception.expectMessage(errorMessage);
        Thrower.throwErrorIfFalse(false, errorMessage);
    }

}
