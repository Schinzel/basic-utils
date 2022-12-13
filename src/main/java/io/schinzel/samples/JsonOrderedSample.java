package io.schinzel.samples;

import io.schinzel.basicutils.json.JsonOrdered;
import org.json.JSONObject;

@SuppressWarnings("DuplicatedCode")
public class JsonOrderedSample {
    public static void main(String[] args) {
        final JSONObject jsonOriginal = new JSONObject();
        jsonOriginal.put("c", "3");
        jsonOriginal.put("b", "2");
        jsonOriginal.put("a", "1");
        jsonOriginal.put("d", "4");
        jsonOriginal.put("f", "6");
        jsonOriginal.put("e", "5");
        System.out.println(jsonOriginal.toString(1));

        final JSONObject jsonOrdered1 = new JsonOrdered();
        jsonOrdered1.put("c", "3");
        jsonOrdered1.put("b", "2");
        jsonOrdered1.put("a", "1");
        jsonOrdered1.put("d", "4");
        jsonOrdered1.put("f", "6");
        jsonOrdered1.put("e", "5");
        System.out.println(jsonOrdered1.toString(1));


        final JsonOrdered jsonOrdered2 = new JsonOrdered();
        jsonOrdered2.put("c", "3");
        jsonOrdered2.put("b", "2");
        jsonOrdered2.put("a", "1");
        jsonOrdered2.put("d", "4");
        jsonOrdered2.put("f", "6");
        jsonOrdered2.put("e", "5");
        System.out.println(jsonOrdered2.toString(1));
    }
}
