package com.serbs.project.parsejson;
// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReadExample2 {
  public static void main(String[] args) throws Exception {
    JSONObject obj = null;
    JSONParser parser = new JSONParser();
    Object shape = parser.parse(new FileReader("beam.json"));
    JSONObject shapeJson = (JSONObject) shape;

    //	JSONObject jsonObj=(JSONObject)JSONValue.parse(new FileReader("beam.json"));

    // JSONArray shapes = (JSONArray) jsonObj.get("shape");
    JSONArray shapes = new JSONArray();
    shapes.add(shapeJson);
    Iterator<String> shapesIterator = shapes.iterator();
    while (shapesIterator.hasNext()) {
      Object uJson = shapesIterator.next();
      JSONObject uj = (JSONObject) uJson;
      obj = (JSONObject) parser.parse(uj.get("shape").toString());
      JSONArray jsonData = (JSONArray) obj.get("data");

      for (int i = 0; i < jsonData.size(); i++) {
        JSONObject site = (JSONObject) jsonData.get(i);

        System.out.println(site.get("control_points"));
      }
    }
  }
}
