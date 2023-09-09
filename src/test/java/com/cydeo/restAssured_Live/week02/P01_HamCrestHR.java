package com.cydeo.restAssured_Live.week02;


import com.cydeo.utility.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_HamCrestHR extends HRTestBase {

    /*
       Given accept type is Json
       And parameters: q={"region_id":2}
       When users sends a GET request to "/countries"
       Then status code is 200
       And Content type is application/json
       And Date header is not null
       Verify
           /- count is 5
           /- hasMore is false
           /- first country id is AR
           /- country names have Canada
           /- country names have Canada,Mexico
           - total country size is 5
           - each country has country_id
           - each country region_id is 2
      - Print country names


     */

    @Test
    public void hamcrestHR(){

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header("Date", is(notNullValue()))
                .contentType(ContentType.JSON) // it is ready method that helps us to verify content
                .header("Content-Type", is("application/json")) // common mtheod for all headers
                .body("count", is(5))
                .body("hasMore",is(false))
                .body("items[0].country_id", is("AR"))
                .body("items.country_name", hasItem("Canada"))
                .body("items.country_name", hasItems("Canada","Mexico"))
                .body("items", hasSize(5))
                .body("items.country_id", everyItem(notNullValue()))
                .body("items.region_id", everyItem(equalTo(2)))
                .extract().response();


        List<String> allCountries = response.path("items.country_name");
        System.out.println("allCountries = " + allCountries);


    }


}
