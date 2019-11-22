package com.serbs.project.parsejson;
// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReadImpl {
  public static void main(String[] args) throws Exception {
    JSONObject level0JsonObj = null;
    JSONObject level1JsonObj = null;

    JSONParser parser = new JSONParser();
    Object shape = parser.parse(new FileReader("beam.json"));
    JSONObject shapeJsonObj = (JSONObject) shape;

    JSONArray shapesJsonArr = new JSONArray();
    shapesJsonArr.add(shapeJsonObj);
    Iterator<String> shapesItr = shapesJsonArr.iterator();
    while (shapesItr.hasNext()) {
      Object shapeItrObj = shapesItr.next();
      JSONObject shapeItrJsonObj = (JSONObject) shapeItrObj;
      level0JsonObj = (JSONObject) parser.parse(shapeItrJsonObj.get("shape").toString());
      JSONArray dataJsonArr = (JSONArray) level0JsonObj.get("data");

      for (int i = 0; i < dataJsonArr.size(); i++) {
        JSONObject dataJsonObj = (JSONObject) dataJsonArr.get(i);
        System.out.println("Level0: " + dataJsonObj.get("control_points"));

        // To get Coordinates of NURBS Curve/Plane
        level1JsonObj = (JSONObject) parser.parse(dataJsonObj.get("control_points").toString());
        JSONArray ctrPointsJsonArr = new JSONArray();
        ctrPointsJsonArr.add(level1JsonObj);
        for (int ctrPointsItr = 0; ctrPointsItr < ctrPointsJsonArr.size(); ctrPointsItr++) {
          JSONObject ctrPointsJsonObj = (JSONObject) ctrPointsJsonArr.get(ctrPointsItr);
          System.out.println("Level1: " + ctrPointsJsonObj.get("points"));

          // To get individual Coordinates of Nurbs Curve/Plane Vertices
          JSONArray pointsJsonArr = new JSONArray();
          pointsJsonArr.add(ctrPointsJsonObj.get("points"));
          Iterator<JSONArray> pointsItr = pointsJsonArr.iterator();

          while (pointsItr.hasNext()) {

            JSONArray pointsItrJsonArr = pointsItr.next();
            for (int getPointItr = 0; getPointItr < 4; getPointItr++)
              System.out.println(pointsItrJsonArr.get(getPointItr));
          }
          for (int i2 = 0; i2 < pointsJsonArr.size(); i2++) {

            System.out.println("Level2: " + pointsJsonArr.get(i2));
          }
        }
      }
    }
  }
}
