package io.schinzel.samples;

import io.schinzel.basicutils.substring.SubString;

/**
 * The purpose of this file is to show SubStringer sample code.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
public class SubStringerSample {
    public static void main(String[] args) {
        String input = "http://www.example.com/index.html?key1=val1&key2=val2";
        //Get everything after question mark, i.e. "key1=val1&key2=val2"
        SubString.create(input)
                .startDelimiter("?")
                .getStr()
                .plnWithPrefix("Query string: ");
        //Get everything before question mark, i.e. "http://www.example.com/index.html"
        SubString.create(input)
                .endDelimiter("?")
                .getStr()
                .plnWithPrefix("URL: ");
        //Get host, i.e. "www.example.com"
        SubString.create(input)
                .startDelimiter("http://")
                .endDelimiter("/index")
                .getStr()
                .plnWithPrefix("Host: ");
        //First get "www.example.com/index.html", then get everything after the slash, i.e. "index.html"
        SubString.create(input)
                .startDelimiter("//")
                .endDelimiter("?")
                .newSubString()
                .startDelimiter("/")
                .getStr()
                .plnWithPrefix("Page: ");
        //Get everything after the second equals sign
        SubString.create(input)
                .startDelimiter("=")
                .startOccurrence(2)
                .getStr()
                .plnWithPrefix("Second value: ");
    }
}
