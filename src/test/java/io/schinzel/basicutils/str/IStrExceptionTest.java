package io.schinzel.basicutils.str;

import io.schinzel.basicutils.RandomUtil;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


public class IStrExceptionTest {

    private class StrException extends AbstractIStr<StrException> implements IStrException<StrException> {
        @Override
        public StrException getThis() {
            return this;
        }
    }

    @Test
    public void throwRuntime_GenericException_ExceptionIsThrown() {
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> new StrException().a("My error message").throwRuntime());
    }


    @Test
    public void throwRuntime_RandomErrorMessage_ErrorMessagePresent() {
        String errorMessage = RandomUtil.getRandomString(10);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> new StrException().a(errorMessage).throwRuntime())
                .withMessage(errorMessage);
    }


    @Test
    public void throwRuntime_GenericException_StrShouldNotBeInStacktrace() {
        try {
            new StrException().a("My message").throwRuntime();
        } catch (RuntimeException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement ste : stackTrace) {
                String s = ste.getClassName();
                assertThat(ste.getClassName()).isNotEqualTo(IStrException.class.getCanonicalName());
            }
        }
    }


    @Test
    public void removeFirstElement_StackTrace_StackTraceLengthMinusOne() {
        try {
            new StrException().a("My message").throwRuntime();
        } catch (RuntimeException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StackTraceElement[] newStackTrace = IStrException.removeFirstElement(stackTrace);
            assertThat(newStackTrace.length).isEqualTo(stackTrace.length - 1);
        }
    }


    @Test
    public void removeFirstElement_StackTrace_FirstElementInStackTraceIsSecondInOld() {
        try {
            new StrException().a("My message").throwRuntime();
        } catch (RuntimeException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StackTraceElement[] newStackTrace = IStrException.removeFirstElement(stackTrace);
            assertThat(newStackTrace[0]).isEqualTo(stackTrace[1]);
        }
    }

}