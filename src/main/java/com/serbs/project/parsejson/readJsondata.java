package com.serbs.project.parsejson;

// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class readJsondata {
  public static void main(String[] args) throws Exception {
    // parsing file "JSONExample.json"
    Object obj = new JSONParser().parse(new FileReader("beam.json"));

    // typecasting obj to JSONObject
    JSONObject jo = (JSONObject) obj;
    Map<String, Object> shape = ((Map) jo.get("shape"));

    // String j_data = (String) jo.get("type");

    System.out.println("" + jo.toJSONString());
    System.out.println("\n");
    // System.out.println(j_data);

    for (Map.Entry entryLevel0 : shape.entrySet()) {
      String abc = entryLevel0.getKey().toString();
      if (abc.equals("data")) {
        JSONObject joInside = new JSONObject();

        joInside.put(abc, entryLevel0.getValue());
        JSONArray data = (JSONArray) joInside.get("data");

        Map<String, Object> data2 = ((Map) joInside.get("data"));
        for (Map.Entry entryLevel1 : data2.entrySet()) {
          System.out.println(entryLevel1.getKey() + ":" + entryLevel1.getValue().toString());
        }
      } else break;
      // System.out.println(entryLevel0.getKey() + ":" + entryLevel0.getValue().toString());
    }
  }
}
