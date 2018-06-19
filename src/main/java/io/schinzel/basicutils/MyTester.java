package io.schinzel.basicutils;

import io.schinzel.basicutils.file.*;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class MyTester {
    public static void main(String[] args) {
        ////////////////////////////////////////////////////////////////////
        //Alt 1
        ////////////////////////////////////////////////////////////////////
        FileWriter.write("myFileName.txt", "myString");
        FileWriter.append("myFileName.txt", "myString");
        FileWriter.writeToTempFile("myString");
        FileWriter.writeToTempFile("myFileName.txt", "myString");
        ////////////////////////////////////////////////////////////////////
        //Alt 2
        ////////////////////////////////////////////////////////////////////
        FileWriter2.builder()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .build()
                .write();
        FileWriter2.builder()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .build()
                .append();
        FileWriter2.builder()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .build()
                .writeToTempFile();
        FileWriter2.builder()
                .stringToWrite("myString")
                .build()
                .writeToTempFile();
        ////////////////////////////////////////////////////////////////////
        //Alt 3
        ////////////////////////////////////////////////////////////////////
        FileWriter3.builder()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .write();
        FileAppender.builder()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .append();
        FileAppender.builder()
                .fileName("myFileName.txt")
                .stringToWrite("myString")
                .append();
        ////////////////////////////////////////////////////////////////////
        //Alt 4
        ////////////////////////////////////////////////////////////////////
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
