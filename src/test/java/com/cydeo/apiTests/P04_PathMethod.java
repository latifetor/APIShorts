package com.cydeo.apiTests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_PathMethod {

    /** Test Case
     *  Given accept type is JSON
     *  And part param id is 3
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

        // there is RestAssured in @BeforeAll, instead of repeating
        // by deleting the RestAssured and import this into the current class
        // as a 'static' import for RestAssured: import static io.restassured.RestAssured.*;
        // there are methods, with will be able to used in our class
    }

    @Test
    public void pathMethod() {

        // implementing to prepare the REQ
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 60).
                when().get("api/spartans/{id}");

        response.prettyPrint();

        /**Then response status code should be 200
         * using Assertion with assertEquals but imported as 'static' by deleting Assertion
         * and import static org.junit.jupiter.api.Assertions.*;
         * expectedValue= 200 and actualValue=response.statusCode
         */
        assertEquals(200,response.statusCode());

         /** And response content type is application/json
          *  using Assertion with assertEquals but imported as 'static' by deleting Assertion
          *  and import static org.junit.jupiter.api.Assertions.*;
          *  expectedValue= contentType with converted String and actualValue=response.ContentType
          */
        assertEquals(ContentType.JSON.toString(),response.contentType());

         /**  And response payload/body values are
                "id"    is 60,
                "name"  is "Elisabeth",
                "gender"is "Female",
                "phone" is 8165224005

          * to retrieve the data from RESP, there is called as a path();
          * with the help of path() method, able to specify KEY and put it the parameter
          * which will grab the VALUE from a certain field
          */

        /** "id": 60,
         * the KEY is "id" will grab value as a 60, and will assign to variable
         * path() is returning T which is one of the generic exparameter
         * after using the method, will store variable into integer, String, or long
         * T type generic will help us to store variable without doing any casting
         */
        int id = response.path("id");
        System.out.println("id = " + id);
        assertEquals(60,id);

        /**  "name"  is "Elisabeth",
         *  the KEY is "name"  and returnType: String
         */
        String name = response.path("name");
        System.out.println("name = " + name);
        assertEquals("Elisabeth",name);


        /** "gender" is "Female",
         *  the KEY is "name"  and returnType: String
         */
        String gender = response.path("gender");
        System.out.println("gender = " + gender);
        assertEquals("Female",gender);


        /** "phone" is 8165224005
         *  the KEY is "name"  and returnType: long
         *  convert the phone number to be long by adding l at the end of the number
         */
        long phone = response.path("phone");
        System.out.println("phone = " + phone);
        assertEquals(8165224005l,phone);

    }

    /** using key path syntax: which is path expression language
     * to retrieve the data from the RESP with more than one JSON objects into the RES
     * need help from some logic to locate specific JSON field
     */

    @Test
    public void getAllSpartans() {

        // send one simple REQ and initializing with local variable
        Response response = get("api/spartans/");

        response.prettyPrint();
        // print out from the console what is the RES
        // getting RESP 100 spartans information as a JSON objects

        // we need to install these 100 information in one place with is Arrays [100 JSON objects]

        // Get me 1st spartan ID //response.path("id[0]") = 10
        System.out.println("response.path(\"id[0]\") = " + response.path("id[0]"));

        // Get me 2nd spartan name // response.path("name[1]") = Nona
        System.out.println("response.path(\"name[1]\") = " + response.path("name[1]"));

        // Get me last spartan name // response.path("name[-1]") = Terence
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));

        // Get me all spartan names
        List<String> allNames = response.path("name");
        System.out.println("******* NAMES *******");
        System.out.println(allNames);

        // Get me all spartan IDs
        List<Integer> allIds = response.path("id");
        System.out.println("******* IDs *******");
        System.out.println(allIds);


    }
}
