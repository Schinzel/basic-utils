package io.schinzel.basicutils;

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
        Thrower.throwIfOutsideRange(1, null, 1, 1);
        Thrower.throwIfOutsideRange(1, null, 1, 10);
        Thrower.throwIfOutsideRange(-1000, null, -1000, 10);
        Thrower.throwIfOutsideRange(10, null, 1, 10);
        Thrower.throwIfOutsideRange(-1000, null, -2000, -1000);
        exception.expect(RuntimeException.class);
        exception.expectMessage("Error using method.");
        Thrower.throwIfOutsideRange(-1000, null, 100, 10);
    }

    @Test
    public void testThrowErrorIfOutsideRange2() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwIfOutsideRange(0, null, 1, 10);
    }

    @Test
    public void testThrowErrorIfOutsideRange3() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwIfOutsideRange(11, null, 1, 10);
    }

    @Test
    public void testThrowErrorIfOutsideRange4() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwIfOutsideRange(-101, null, -100, -10);
    }

    @Test
    public void testThrowErrorIfOutsideRange5() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Requested value ");
        Thrower.throwIfOutsideRange(-9, null, -100, -10);
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

}
