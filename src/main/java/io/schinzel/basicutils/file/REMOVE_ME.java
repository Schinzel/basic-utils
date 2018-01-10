package io.schinzel.basicutils.file;

/**
 * Purpose of this class is ...
 * <p>
 * Created by Schinzel on 2018-01-10
 */
public class REMOVE_ME {
    public static void main(String[] args) {
        //
        FileRW.readAsString("myfile.txt");
        FileRW.write("myfile.txt", "my content");
        //
        FileReader.create("myfile.txt").readAsString();
        FileWriter.create("myfile.txt").write("my content");
        //
        FileReader2.readAsString("myfile.txt");
        FileWriter2.write("myfile.txt", "my content");
    }
}
