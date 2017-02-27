package io.schinzel.samples;

import io.schinzel.basicutils.SubStringer;

/**
 * The purpose of this file is to show SubStringer sample code.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
public class SubStringerSample {
    public static void main(String[] args) {
        String input = "http://www.example.com/index.html?key1=val1&key2=val2";
        //Get everything after question mark, i.e. "key1=val1&key2=val2"
        String queryString = SubStringer.create(input)
                .start("?")
                .toString();
        //Get everything before question mark, i.e. "http://www.example.com/index.html"
        String url = SubStringer.create(input)
                .end("?")
                .toString();
        //Get host, i.e. "www.example.com"
        String host = SubStringer.create(input)
                .start("http://")
                .end("/index")
                .toString();
        //First get "www.example.com/index.html", then get everything after the slash, i.e. "index.html"
        String page = SubStringer.create(input)
                .start("//").end("?")
                .getSubStringer()
                .start("/")
                .toString();
    }
}
