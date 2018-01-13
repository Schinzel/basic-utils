package io.schinzel.samples.file;

import io.schinzel.basicutils.file.ResourceReader;

/**
 * Purpose of this class is to provide sample usage of ResourceReader
 * <p>
 * Created by Schinzel on 2018-01-13
 */
public class ResourceReaderSample {

    public static void main(String[] args) {
        String read = ResourceReader.read("io/schinzel/samples/sample_resource.txt");
        System.out.println(read);
    }
}
