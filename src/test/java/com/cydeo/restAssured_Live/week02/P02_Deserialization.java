package com.cydeo.restAssured_Live.week02;

import com.cydeo.utility.CydeoTrainingTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class P02_Deserialization extends CydeoTrainingTestBase {

    /**
     * Create a test called getTeachers
     * 1. Send request to GET /teacher/all
     * 2. log uri to see
     * 3. Get all Json as a map and print out screen all the things with the help of  map
     * System.out.println("====== GET FIRST TEACHER INFO AS MAP  ======");
     * System.out.println("====== GET FIRST TEACHER NAME  ======");
     * System.out.println("====== GET ALL TEACHER INFO  AS LIST OF MAP======");
     * System.out.println("====== FIRST TEACHER INFO======");
     * System.out.println("====== FIRST TEACHER NAME ======");
     * System.out.println("====== LAST TEACHER NAME  ======");
     */

    @Test
    public void deserializeJava() {

        Response response = given().log().uri().
                when().get("/teacher/all").
                then().statusCode(200)
                .extract().response();


        // response.as("teachers[0]", Map.class);
        // response.as method doest not allow to retrieve partial part of JSON by providing GPATH
        // That is why we are not eligible to do it
        //     * System.out.println("====== GET FIRST TEACHER INFO AS MAP  ======");
        //     * System.out.println("====== GET FIRST TEACHER NAME  ======");


        Map<String, Object> allTeachers = response.as(Map.class);
        System.out.println("allTeachers = " + allTeachers);
        System.out.println("====== GET ALL TEACHER INFO  AS LIST OF MAP======");
        List<Map<String, Object>> teachers = (List<Map<String, Object>>) allTeachers.get("teachers");
        for (Map<String, Object> eachTeacher : teachers) {
            System.out.println(eachTeacher);
        }

        System.out.println("====== FIRST TEACHER INFO======");
        Map<String, Object> firstTeacher = teachers.get(0);
        System.out.println(firstTeacher);

        System.out.println("====== FIRST TEACHER NAME ======");
        System.out.println(firstTeacher.get("firstName"));

        System.out.println("====== LAST TEACHER NAME  ======");
        System.out.println(teachers.get(teachers.size() - 1).get("firstName"));


    }

    @Test
    public void deserializeJAVAJSONPath() {

        JsonPath jp = given().log().uri().
                when().get("/teacher/all").
                then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();

        System.out.println("====== GET FIRST TEACHER INFO AS MAP  ======");

        Map<String, Object> firstTeacher = jp.getMap("teachers[0]");
        System.out.println(firstTeacher);

        System.out.println("====== GET FIRST TEACHER NAME  ======");
        System.out.println(firstTeacher.get("firstName"));

        /*
        {
            "teacherId": 3,
            "firstName": "Tet",
            "lastName": "DS",
            "emailAddress": "vfomiuk",
            "joinDate": "04/09/2022",
            "birthDate": "05/17/2004",
            "password": "Dice#096",
            "phone": "435345345345",
            "subject": "hgghg",
            "gender": "Male",
            "department": "Computer",
            "salary": 100000,
            "batch": 1,
            "section": "456456456"
            "address : {
                       "permanentAddress": "45645 "
                        }
        }
        firstTeacher.get("address") --->  { "permanentAddress": "45645 " }
        Map<String,Object> teacherAddress= firstTeacher.get("address")
        teacherAddress.get("permanentAddress") --> 45645
         */


        System.out.println("====== GET ALL TEACHER INFO  AS LIST OF MAP======");
        List<Map<String, Object>> teachers = jp.getList("teachers");

        for (Map<String, Object> eachTeacher : teachers) {
            System.out.println(eachTeacher);
        }

        System.out.println("====== FIRST TEACHER INFO======");
        Map<String, Object> teacherFirst = teachers.get(0);
        System.out.println(teacherFirst);

        System.out.println("====== FIRST TEACHER NAME ======");
        System.out.println(teacherFirst.get("firstName"));

        System.out.println("====== LAST TEACHER NAME  ======");
        System.out.println(teachers.get(teachers.size() - 1).get("firstName"));

    }


}




