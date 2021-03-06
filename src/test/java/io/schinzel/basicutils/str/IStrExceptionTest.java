package io.schinzel.basicutils.str;

import io.schinzel.basicutils.RandomUtil;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class IStrExceptionTest {

    private static class StrException extends AbstractIStr<StrException> implements IStrException<StrException> {
        @Override
        public StrException getThis() {
            return this;
        }
    }

    @Test
    public void throwRuntime_GenericException_ExceptionIsThrown() {
        final StrException strException = new StrException().a("My error message");
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(strException::throwRuntime);
    }


    @Test
    public void throwRuntime_RandomErrorMessage_ErrorMessagePresent() {
        String errorMessage = RandomUtil.getRandomString(10);
        final StrException strException = new StrException().a(errorMessage);
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(strException::throwRuntime)
                .withMessage(errorMessage);
    }


    @Test
    public void throwRuntime_GenericException_StrShouldNotBeInStacktrace() {
        try {
            new StrException().a("My message").throwRuntime();
        } catch (RuntimeException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement ste : stackTrace) {
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
            assertThat(newStackTrace).hasSize(stackTrace.length - 1);
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