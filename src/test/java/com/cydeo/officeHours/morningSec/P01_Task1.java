package com.cydeo.officeHours.morningSec;

import com.cydeo.utility.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class P01_Task1 extends HRTestBase{

    /**
     * Send request to HR API URL  and save the response
     * Path Param job_id is IT_PROG
     * GET /jobs/{job_id}
     * Store the response in Response Object that comes from get Request
     * Verify followings
     *     - Print response
     *     - Status Code is 200
     *     - Content-Type is application/json
     *     - job_id is IT_PROG
     *     - job_title is Programmer
     *     - Get me first rel information
     *     - Get me 2,3 rel information
     * 	   - Get me all rel information
     */
    @Test
    public void getSingleJob() {

        Response response = given().log().uri()
                .and()
                .pathParam("id", "IT_PROG")
                .when().get("/jobs/{id}").prettyPeek();


        //    * Verify followings
        //     *     - Status Code is 200
        Assertions.assertEquals(HttpStatus.SC_OK,response.statusCode());

        //     *     - Content-Type is application/json
        Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

        // CREATE JSON PATH OBJECT
        JsonPath jp = response.jsonPath();

        //     *     - job_id is IT_PROG
        Assertions.assertEquals("IT_PROG",jp.getString("job_id"));

        //     *     - job_title is Programmer
        Assertions.assertEquals("Programmer",jp.getString("job_title"));

        //     *     - Get me first rel information
        System.out.println("jp.getString(\"links[0].rel\") = " + jp.getString("links[0].rel"));

        //     *     - Get me 2,3 rel information
        System.out.println("jp.getList(\"links[1,2].rel\") = " + jp.getList("links[1,2].rel"));

        //     * 	 - Get me all rel information
        List<String> allRels = jp.getList("links.rel");

        System.out.println("allRels = " + allRels);


    }

    @Test
    public void getWithID(){
        Response response = given().log().uri().accept(ContentType.JSON)
                .and().pathParam("job_id","IT_PROG")
                .when().get("/jobs/{job_id}");
        // response.prettyPrint(); // it is holding a JSON object

        System.out.println("response.statusCode() = " + response.statusCode()); // the method is ready to read status code
        System.out.println("response.contentType() = " + response.contentType()); // ready

        System.out.println("response.headers().hasHeaderWithName(\"Date\") = " + response.headers().hasHeaderWithName("Date")); // ready in REstAssured library

        // What you will learn this week and next week will focus on reading response BODY

        // path method
        System.out.println("response.path(\"job_id\") = " + response.path("job_id"));
        // I want to reach first Array Object element "rel" - property
        System.out.println("response.path(\"links[0].rel\") = " + response.path("links[0].rel"));

        System.out.println("response.path(\"links\") = " + response.path("links"));

        System.out.println("response.path(\"links[1,2]\") = " + response.path("links[1,2].rel"));

        List<String> expectedRelValues = new ArrayList<>(Arrays.asList("self","edit","describedby","collection"));
        List<String> actualRelValues ;
        actualRelValues = response.path("links.rel");

        Assertions.assertEquals(expectedRelValues,actualRelValues);

        // let's check min salary
        //  String actualMinSalary = response.path("min_salary"); // java.lang.Integer cannot be cast to class java.lang.String
        int actualMinSalary = response.path("min_salary");
        System.out.println("actualMinSalary = " + actualMinSalary);

    }


}
