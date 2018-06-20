package io.schinzel;

import io.schinzel.basicutils.file.FileReader2;
import io.schinzel.basicutils.file.ResourceReader2;

public class MyTester {
    public static void main(String[] args) {
        FileReader2.read("").asString();
        FileReader2.read("").asStr().writeToSystemOut();
        ResourceReader2.read("").asStr();

    }
}
