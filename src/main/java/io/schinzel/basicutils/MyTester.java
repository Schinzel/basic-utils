package io.schinzel.basicutils;

import io.schinzel.basicutils.file.*;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class MyTester {
    public static void main(String[] args) {
        FileWriter4.appender()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .append();
        FileWriter4.writer()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .write();
        FileWriter4.tempFileWriter()
                .stringToWrite("myString")
                .write();
        FileWriter4.tempFileWriter()
                .fileName("tempFile.txt")
                .stringToWrite("myString")
                .write();
    }
}
