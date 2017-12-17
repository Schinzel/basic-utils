package io.schinzel.samples;

import io.schinzel.basicutils.EmptyObjects;

import java.util.List;
import java.util.Map;

public class EmptyObjectsSample {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        List<String> stringList = EmptyObjects.emptyList();
        Object[] objectArray = EmptyObjects.EMPTY_OBJECT_ARRAY;
        String string = EmptyObjects.EMPTY_STRING;
        Map<String, Integer> map = EmptyObjects.emptyMap();
    }
}
