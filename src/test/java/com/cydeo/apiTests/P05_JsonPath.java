package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P05_JsonPath {

    /** Test Case
     *  Give accept type is JSON
     *  And path param id is 60
     *  When user sends GET request /api/spartans/{id}
     *  Then response status code should be 200
     *  And response content type is application/json
     *  And response payload/body values are
            "id"    is 60,
            "name"  is "Elisabeth",
            "gender"is "Female",
            "phone" is 8165224005
     */

    @BeforeAll
    public static void init() {

        baseURI = "http://3.89.223.241:8000";

    }



    @Test
    public void getSingleSpartan() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 60).
                when().get("/api/spartans/{id}");

        response.prettyPrint();

        // Then response status code should be 200
        assertEquals(200,response.statusCode());

        // And response content type is application/json
        assertEquals(ContentType.JSON.toString(),response.contentType());

        /**And response payload/body values are
                * "id"    is 60,
                * "name"  is "Elisabeth",
                * "gender"is "Female",
                *  "phone" is 8165224005
         */
        // Create JSON PATH OBJECT
        JsonPath jsonPath = response.jsonPath();

        // Retrieve data by using JsonPath
        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(60,id);
        assertEquals("Elisabeth",name);
        assertEquals("Female",gender);
        assertEquals(8165224005l,phone);

    }
    // Retrieve the all the data information by using JSON PATH
    @Test
    public void getAllSpartans() {

        Response response = get("/api/spartans");

        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        /**Get 1st spartan ID
         * from path of the Array of spartan ID
         * initializing with local variable as a returnType=int
         */
        int firstSpartanID = jsonPath.getInt("id[0]");
        System.out.println("firstSpartanID = " + firstSpartanID);

        /**Get 2nd spartan name
         * from path of the Array of spartan name
         * initializing with local variable as a returnType=String
         */
        String secondSpartanName = jsonPath.getString("name[1]");
        System.out.println("secondSpartanName = " + secondSpartanName);

        /**Get last spartan gender
         * from path of the Array of spartan name using -1 as a generic
         * initializing with local variable as a returnType=String
         */
        String lastSpartanName = jsonPath.getString("name[-1]");
        System.out.println("lastSpartanName = " + lastSpartanName);

        /**Get all spartan names
         * from path of the Array of all spartan names
         * initializing with local variable as a returnType=List of String
         */
        List<String> allNames = jsonPath.getList("name");
        System.out.println("allNames = " + allNames);

        /**Get all spartan IDs
         * from path of the Array of all spartan IDs
         * initializing with local variable as a returnType=String
         */
        List<Integer> allIds = jsonPath.getList("id");
        System.out.println("allIds = " + allIds);


    }
}
