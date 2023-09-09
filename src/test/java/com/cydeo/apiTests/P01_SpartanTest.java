package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class P01_SpartanTest {

    // create global variable with spartans.url
    String spartanBaseURL = "http://3.89.223.241:8000";

    // generate the testcase using junit.jupiter
    @Test
    public void getAllSpartans() {

        // Sending REQ to API to get all the spartans information
        // 1st:  checking ENDPOINT from the spartans DOC
        // using get(url+"endpoint") adding ENDPOINT by using concatenation to provided

        Response response = RestAssured.get(spartanBaseURL + "/api/spartans");
        // after sending the REQ, will get Response to store in to response variable


        // after sent the REQ will get one RESP
        // there is a special type in RestAssured called 'RESPONSE'
        // it will get whatever we have, when sending the REQ from the POSTMEN into the RESP
        // it will store all the Body, Headers, and Status Code as a RESP

        // click over .get(); show the returnType response type
        // or create by typing Response response = RestAssured.get();

        // Status CODE: there are 2 methods 1) using statusCode(); returnType int
        int statusCode = response.statusCode();
        System.out.println("statusCode = " + statusCode);

        Assertions.assertEquals(200,statusCode); //statusCode = 200
            // EXPECTED comes from testcase, ACTUAL will get from 'response'

        // instead of using statusCode(); method  response has also 2).getStatusCode();
        // both of the methods will give the same result
        // int statusCode1 = response.getStatusCode();


        //CONTENT TYPE: there are 2 methods 1) .contentType();  2) .getContentType()
        //both will return contentType that having from the RESPONSE which we store in the POSTMAN
        //as an application/json

        String contentType = response.contentType();
        System.out.println("contentType = " + contentType);  // contentType = application/json

        Assertions.assertEquals("application/json",contentType);
            // EXPECTED_DATA needs to be "application/json" format
            // ACTUAL_DATA is the 'contentType' from the RESPONSE


        // Print BODY: using asString();
        // print out into the screen, what we are getting as a Body
        // getting 100 spartans information
        // with println(.sout) will see all the contents in String format on the screen

        System.out.println("*********RESPONSE asString***********");
        System.out.println(response.asString());

        // instead of using that, there is some and special method that come from the response
        // by using .prettyPrint();
        // it will show on the screen whatever having in the POSTMAN in the same structure

        System.out.println("*********RESPONSE prettyPrint***********");
        System.out.println(response.prettyPrint());

        // when running your code into the POSTMAN
        // we were getting result in more readable way
        // when using prettyPrint into the RestAssured in your project
        // it will give the result in the same format just to make it more readable
        // when running from response.asString() it will give the result as String, which not readable



    }
}
