package com.serbs.project.parsejson;
// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONReadImpl {
  public static void main(String[] args) throws Exception {
    JSONObject level0JsonObj = null;
    JSONObject level1JsonObj = null;

    JSONParser parser = new JSONParser();
    Object shape = parser.parse(new FileReader("platewith200mmhole.json"));
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

        // To get Coordinates of NURBS Curve/Plane
        level1JsonObj = (JSONObject) parser.parse(dataJsonObj.get("control_points").toString());
        JSONArray ctrPointsJsonArr = new JSONArray();
        ctrPointsJsonArr.add(level1JsonObj);
        for (int ctrPointsItr = 0; ctrPointsItr < ctrPointsJsonArr.size(); ctrPointsItr++) {
          JSONObject ctrPointsJsonObj = (JSONObject) ctrPointsJsonArr.get(ctrPointsItr);

          // To get individual Coordinates of Nurbs Curve/Plane Vertices
          JSONArray pointsJsonArr = new JSONArray();
          pointsJsonArr.add(ctrPointsJsonObj.get("points"));
          Iterator<JSONArray> pointsItr = pointsJsonArr.iterator();

          while (pointsItr.hasNext()) {

            JSONArray pointsItrJsonArr = pointsItr.next();
            Iterator<JSONArray> pointsItrJsonArrItr = pointsItrJsonArr.iterator();
            int itrbc = pointsItrJsonArr.size();
            for (int getPointItr = 0; getPointItr < itrbc; getPointItr++) {

              String xCord =
                  StringUtils.substringBetween(
                      pointsItrJsonArr.get(getPointItr).toString(), "[", ",");
              String yCord =
                  StringUtils.substringBetween(
                      pointsItrJsonArr.get(getPointItr).toString(), ",", ",");
              String zCord1 =
                  StringUtils.substringAfterLast(pointsItrJsonArr.get(getPointItr).toString(), ",");
              String zCord = StringUtils.substringBefore(zCord1, "]");
              System.out.print(xCord + " 	" + yCord + " 	" + zCord + " 	");
            }
            System.out.println();
          }
        }
      }
    }
  }
}
