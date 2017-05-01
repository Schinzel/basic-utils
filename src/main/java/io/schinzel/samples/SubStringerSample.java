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
        String queryString = SubString
                .create(input)
                .startDelimiter("?")
                .getString();
        System.out.println("Query string: " + queryString);
        //Get everything before question mark, i.e. "http://www.example.com/index.html"
        String url = SubString
                .create(input)
                .endDelimiter("?")
                .getString();
        System.out.println("URL: " + url);
        //Get host, i.e. "www.example.com"
        String host = SubString
                .create(input)
                .startDelimiter("http://")
                .endDelimiter("/index")
                .getString();
        System.out.println("Host: " + host);
        //First get "www.example.com/index.html", then get everything after the slash, i.e. "index.html"
        String page = SubString
                .create(input)
                .startDelimiter("//")
                .endDelimiter("?")
                .newSubString()
                .startDelimiter("/")
                .getString();
        System.out.println("Page: " + page);
    }
}
