package io.schinzel.basicutils;

import io.schinzel.basicutils.file.*;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class MyTester {
    public static void main(String[] args) {
        FileWriter.appender()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .append();
        FileWriter.writer()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .write();
        FileWriter.tempFileWriter()
                .stringToWrite("myString")
                .write();
        FileWriter.tempFileWriter()
                .fileName("tempFile.txt")
                .stringToWrite("myString")
                .write();
    }
}
