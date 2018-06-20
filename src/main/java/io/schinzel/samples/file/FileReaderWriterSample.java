package io.schinzel.samples.file;

import io.schinzel.basicutils.file.FileReader2;
import io.schinzel.basicutils.file.FileWriter;

/**
 * Purpose of this class is to show how FileReader and FileWriter can be used.
 * <p>
 * Created by Schinzel on 2018-01-13
 */
public class FileReaderWriterSample {

    public static void main(String[] args) {
        writeAndRead();
        writeAndReadFileInAnotherDir();
    }


    private static void writeAndRead() {
        String fileName = "my_file.txt";
        FileWriter.tempFileWriter()
                .fileName(fileName)
                .stringToWrite("The first file content\n")
                .write();
        FileWriter.appender()
                .fileName(fileName)
                .stringToWrite("The second file content\n")
                .append();
        String fileContent = FileReader2
                .read(fileName)
                .asString();
        System.out.println(fileContent);
    }


    private static void writeAndReadFileInAnotherDir() {
        String fileName = "../../my_file.txt";
        FileWriter.tempFileWriter()
                .fileName(fileName)
                .stringToWrite("gibbon")
                .write();
        FileReader2
                .read(fileName)
                .asStr()
                .writeToSystemOut();
    }


}

