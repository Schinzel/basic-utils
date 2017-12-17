package io.schinzel.basicutils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author schinzel
 */
public class EmptyObjectsTest extends EmptyObjects {

    @Test
    public void testSomeMethod() {
        assertThat(EmptyObjects.EMPTY_BYTE_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_INT_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_STRING_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_OBJECT_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_DOUBLE_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_FLOAT_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_LONG_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_BOOLEAN_ARRAY)
                .isNotNull()
                .isEmpty();
        assertThat(EmptyObjects.EMPTY_STRING)
                .isEqualTo("");
    }


    @Test
    public void emptyList_EmptyNonNull() {
        assertThat(EmptyObjects.emptyList())
                .isNotNull()
                .isEmpty();
    }


    @Test
    public void emptyMap_EmptyNonNull() {
        assertThat(EmptyObjects.emptyMap())
                .isNotNull()
                .isEmpty();
    }


    @Test
    public void emptySet_EmptyNonNull() {
        assertThat(EmptyObjects.emptySet())
                .isNotNull()
                .isEmpty();
    }
}