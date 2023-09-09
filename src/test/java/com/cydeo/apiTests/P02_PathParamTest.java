package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P02_PathParamTest {

    /*
     * Given accepted type is JSON
     * And ID Parameter values is 60
     * When User sends GET request/api/spartans/{id}
     * Then response status code should be 200
     * And response content type is application JSON

        *  according to the test case:
        *  sending the REQ to accept HEADER as a JSON
        *  after that will send one of the PATH PARAMETER to get single resource from resources
        *  then will use GET request/ to this ENDPOINT: api/spartans/{id}
        *  and RESP status code need to be 200, and content type must be 'application/json

     * instead of writing the url as a String: there is variable in RestAssured to make it easy
     * by using @BeforeAll :  it will execute all the test that we have before every test in following class
     * in JUNIT 5 @BeforeAll: used to signal that the method should be executed before all test in the current class
     * also it should be stable
     */

    @BeforeAll
    public static void init() {

        /*
         * should be stable 'static'
         * for all the test case, that will be created in this class
         * will assign BASEUrl as a value that copy from the POSTMAN
         * in RestAssured there is method called 'baseURI'
         * when assigned value in baseURI as a 'baseURI' before each test,
         * it will assign 'BaseURL' to the test case 'BaseURL' with the help of 'baseURI'
         * we don't need to do any concatenation because when typing RestAssured.get()
         * it will automatically understand that this class already has 'baseURI'
         * no need to create path: spartanBaseURL, it will do behind the scene
         * ONLY providing: ENDPOINT
         */


        RestAssured.baseURI = "http://3.89.223.241:8000";

        // after adding URI all test cases in the current class which will assign automatically
        // then can start test case implementation
    }

    /**
     * TEST CASE: Given accepted type is JSON
     * RestAssured give the options to write the test case implementation in BDD structure
     * to increase readability and to be able to add some PARAMETERS and HEADERS
     */


    @Test
    public void pathParamTest() {

        Response response = RestAssured.given().accept(ContentType.JSON).
                and().pathParam("id",60).
                when().get("api/spartans/{id}");

        /*Given accepted type is JSON
         * .given()
            * can continuous to add REQ information
            * in the 1st part: Given accepted type is JSON
         * .accept()
            * it is one of the HEADER, that need to provided
            * there are 2 overloaded methods;
            * 1) by providing accepted type as a String ("application/json")
            * instead of using 'value' and avoid using 'hard coder' in the method,
            * 2) by getting accepted type from enum that already predefined information: 'ContentType.JSON'
         * .and()
            * it is 'Syntactic sugar' it will increase the readability
            * in terms of functionality, there is no difference functionality
            * for QUERY PARAMETER will not use just see different, there is NO functionality
         * .pathParam()
            * providing pathParameter name from DOC: "id" and value "3"
         * after prepared the REQ, it is ready to send
         * when()
            * it is time to send the REQ
         * get()
            * RestAssured is automatically recognized the 'BaseUrl'
            * since it is 'static' variable for RestAssured
            * then providing path of ENDPOINT: "/api/spartans/{id}
         * after this execution, when hover to get the return type which is 'response'
         * then initializing the local variable return type 'response'
         */

        // option #1
        int statusCode = response.statusCode();
        System.out.println(statusCode); // execution result as an actualResult

        Assertions.assertEquals(200,statusCode);

        // option #2
        Assertions.assertEquals(200,response.statusCode());
        // instead of doing these 3 lines
        // by getting the actualResult from response.statusCode();


        /*status code should be 200
         * to verify the status code= 200
         * response all ready has the information
         * by using statusCode() which will give all the information
         * then initializing with the local variable and print out
         * using junit.jupiter Assertion: what "expectedValue with actualValue"
         */

        // option #1
        Assertions.assertEquals("application/json",response.contentType());

        // option #2
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        /*And response content type is application JSON
         * expecting to see "application/json" and a result
            * instead of putting the 'hardcode', using enum 'ContentType' io.restassured.http
            * it give all contentType options as a default and select the contentType
         * verify the expectedResult with the real actualResult
         * the actualResult value comes from 'response.ContentType()'
         * there is yellow-highlight over 'assertEquals'
            * the actualResult:     response.contentType    comes as a String
            * the expectedResult:   ContentType.JSON        comes as a contentType
         * by converting the enum 'ContentType.JSON' toString()
         */

        response.prettyPrint();

        /*Print out response
         * using 'Pretty'
         * it will convert the String response and print out 'Pretty'
         */


    }
}
