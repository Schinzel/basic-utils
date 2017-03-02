package io.schinzel.basicutils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
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


    /**
     * Tess calls getArgs with empty argument.
     */
    @Test
    public void testGetArgs_noArgs() {
        String actual = Thrower.getArgs(null).getString();
        String expceted = EmptyObjects.EMPTY_STRING;
        Assert.assertEquals(expceted, actual);
        //
        actual = Thrower.getArgs(EmptyObjects.EMPTY_STRING_ARRAY).getString();
        expceted = EmptyObjects.EMPTY_STRING;
        Assert.assertEquals(expceted, actual);
    }


    @Test
    public void testGetArgs() {
        String actual = Thrower.getArgs("k1", "v1").getString();
        String expected = "Props:{k1:'v1'}";
        Assert.assertEquals(expected, actual);
        //
        actual = Thrower.getArgs("k1", "v1", "k2", "v2").getString();
        expected = "Props:{k1:'v1' k2:'v2'}";
        Assert.assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // Test of extensive error message
    //------------------------------------------------------------------------


    /**
     * Test class so that the position in the stacktrace from which for example method
     * name is extracted can be simulated.
     */
    private class MyTestClass {
        private void myMethod(boolean throwException, String exceptionMessage, String... keyValues) {
            Thrower.throwIfTrue(throwException, exceptionMessage, keyValues);
        }

    }


    /**
     * Validate a full message.
     */
    @Test
    public void testThrowIfTrue_extensiveErrorMessage() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("MyMessage Class:'ThrowerTest$MyTestClass' Method:'myMethod' Props:{k1:'v1'}");
        new MyTestClass().myMethod(true, "MyMessage", "k1", "v1");
    }


    /**
     * Verify that exception is not thrown if first arg is false.
     */
    @Test
    public void testThrowIfTrue_doNotThrow() {
        boolean exceptionThrown = false;
        try {
            new MyTestClass().myMethod(false, "MyMessage", "k1", "v1");
        } catch (RuntimeException e) {
            exceptionThrown = true;
        }
        Assert.assertFalse("Exception should not have been thrown", exceptionThrown);
    }


    @Test
    public void testThrowIfTrue_wrongNumberOfKeyValues() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("The number of key values is not even");
        new MyTestClass().myMethod(false, "MyMessage", "k1");
    }
}

