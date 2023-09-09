package com.cydeo.restAssured_Live.week01;

import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_TestBaseExample extends HRTestBase {

    /**
     * 1. Send request to HR url and save the response
     * 2. GET /regions
     * 3. Store the response in Response Object that comes from GET Request
     * 4. Print out followings
     *     - Headers
     *     - Content-Type
     *     - Status Code
     *     - Response
     *     - Date
     *     - Verify response body has "Europe"
     *     - Verify response has Date
     */

    @Test
    public void simpleGETRequest() {


        Response response=get("/regions");

        //     *     - Response
        response.prettyPrint();


        //  Print out followings
        //     *     - Headers
        System.out.println("response.getHeaders() = " + response.getHeaders());
        System.out.println("response.headers() = " + response.headers());

        //     *     - Content-Type
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.getContentType() = " + response.getContentType());

        //     *     - Status Code
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusCode() = " + response.getStatusCode());

        //     *     - Date
        System.out.println("response.header(\"Date\") = " + response.header("Date"));


        //     *     - Verify response has Date
        System.out.println("response.getHeaders().hasHeaderWithName(\"Date\") = " + response.getHeaders().hasHeaderWithName("Date"));

        //     *     - Verify response body has "Europe"
        System.out.println("response.asString().contains(\"Europe\") = " + response.asString().contains("Europe"));



    }


    /**
     * 1. Send request to HR url and save the response
     *    - Accept type is application json
     * 2. GET /employees/100
     * 3. Store the response in Response Object that comes from get Request
     * 4. Print out followings
     *     - First Name
     *     - Last Name
     *     - Verify status code is 200
     *     - Verify First Name is "Steven"
     *     - Verify content-Type is application/json
     */
    @DisplayName("GET /employees/100 --> GET Request Practice ")
    @Test
    public void getOneEmployees() {

        Response response = given().log().uri().accept(ContentType.JSON) // Hey API I need response in JSON format
                .when().get("/employees/100");

        response.prettyPrint();

        // * 4. Print out followings
        //     *     - First Name

        System.out.println("response.path(\"firstname\") = " + response.path("firstname"));
        // NULL


        //     *     - Verify First Name is "Steven"
        String first_name = response.path("first_name");
        System.out.println("first_name = " + first_name);
        assertEquals("Steven",first_name);


        //     *     - Last Name
        String last_name = response.path("last_name");
        System.out.println("last_name = " + last_name);
        assertEquals("King",last_name);

        //     *     - Verify status code is 200
        int statusCode = response.statusCode();
        assertEquals(200,statusCode);
        assertEquals(HttpStatus.SC_OK,statusCode);


        //     *     - Verify content-Type is application/json
        String contentType = response.contentType();
        assertEquals("application/json",contentType);
        assertEquals(ContentType.JSON.toString(),contentType);

    }


}
