package io.schinzel.basicutils.json;

import org.json.JSONObject;
import java.util.LinkedHashMap;

public class JsonOrdered extends JSONObject {

    public JsonOrdered() {
        this.map = new LinkedHashMap<>();
    }
}

