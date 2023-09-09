package com.cydeo.apiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class P03_QueryParamTest {

    /**TestCase:
     * Given accept type is JSON
     * And Query Parameter values are
     * gender       | Female
     * nameContains | J
     * When user sends GET request /api/spartans/search
     * Then response status code should be 200
     * And response content type is application JSON
     * And "Female" should be in response
     * Ane "Janette" should be in response
     */

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://3.89.223.241:8000";

    }

    @Test
    public void queryParamTest() {

        /** Using RestAssured for given()
         *  to prepare the REQ with the additional information
         *  to increase the readability
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .queryParam("nameContains", "J")
                .queryParam("gender", "Female").
                // ready to send
                when().get("api/spartans/search");


        /** Then response status code should be 200
         *  using Asserting.junit.jupiter
         *  expectedResult:  TestCase
         *  actualResult:    response
         */
        Assertions.assertEquals(200,response.statusCode());

         /** And response content type is application JSON
          *  using Asserting.junit.jupiter
          *  expectedResult: 'ContentType' ENUM or String (TestCase)
          *  actualResult:   response.contentType()
          */
         Assertions.assertEquals(ContentType.JSON.toString(),response.contentType());

         /** And "Female" should be in response
          *  using Response object= response with asString() method will convert data to the String
          *  after that point using 'contains() method' with will return 'boolean'
          *  using Assertion to assertTrue
                *  if the boolean condition is 'true' --> Passed
                *  if the boolean condition is 'false'--> Failed
          */
         Assertions.assertTrue(response.asString().contains("Female"));

         /** Ane "Janette" should be in response
          *  using Response object= response with asString() method will convert data to the String
          *  after that point using 'contains() method' with will return 'boolean'
          *  using Assertion to assertTrue
                *  if the boolean condition is 'true' --> Passed
                *  if the boolean condition is 'false'--> Failed
         */

        Assertions.assertTrue(response.asString().contains("Janette"));

        response.prettyPrint();

        /** will NOT use mostly .contains() method
         *  because it does not guaranty to get exacted the single resource
         *  only checking a random data in the RESP, do we have 'expected data' resource or not
         *  only making Assertion to see how it's working , how can we reach body itself
         *  it is okay to used .contains() method
         *  instead of using this .contains()
         *  will learn more sorted way to the find specific source data
         *  with will be checking  that 'gender' need to be 'Female'
         *  will be reading from Key and Value structure from the RESP
         */

    }
}
