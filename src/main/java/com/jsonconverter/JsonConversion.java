package com.jsonconverter;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Collections;
import java.util.Map;

public class JsonConversion {
    private static final int INDENT_INCREMENT = 4;
    private StringBuilder builder = new StringBuilder();
    private int indent = 0;

    public JsonConversion(int indent) {
        this.indent = indent;
    }

    public JsonConversion() {
    }

    public String execute(JsonValue json) {
        convert(json);
        return builder.toString();
    }

    private void convert(JsonValue json) {
        if (json == null) {
            builder.append("null");
            return;
        }
        if (json instanceof JsonObject) {
            convert(((JsonObject) json));
            return;
        }
        if (json instanceof JsonArray) {
            convert(((JsonArray) json));
            return;
        }

        builder.append(json.toString());
    }

    private void convert(JsonArray json) {
        builder.append("Json.createArrayBuilder()\n");
        for (int i = 0; i < json.size(); i++) {
            builder.append(spaces() + indent() + ".add(" + new JsonConversion(indent + INDENT_INCREMENT).execute(json.get(i)) + ")");

            if(i != json.size() -1) {
                builder.append("\n");
            }
        }
    }

    private String spaces() {
        return String.join("", Collections.nCopies(indent, " "));
    }

    private String indent() {
        return String.join("", Collections.nCopies(INDENT_INCREMENT, " "));
    }

    private void convert(JsonObject json) {
        int i =0;
        builder.append("Json.createObjectBuilder()\n");
        for (Map.Entry<String, JsonValue> entry : json.entrySet()) {
            builder.append(spaces() + indent() + ".add(");
            String key = entry.getKey();
            JsonValue value = entry.getValue();
            builder.append('"');
            builder.append(key);
            builder.append('"');
            builder.append(", ");
            builder.append(new JsonConversion(indent + INDENT_INCREMENT).execute(value));
            builder.append(")");
            if(i++ != json.size() -1 ){
                builder.append("\n");
            }
        }
    }
}
