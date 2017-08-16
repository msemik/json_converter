package com.jsonconverter;


import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        try (JsonReader reader = Json.createReader(System.in)) {
            JsonStructure json = reader.read();

            System.out.println();
            System.out.println(new JsonConversion().execute(json));
        }

    }
}
