package com.cydeo.restAssured_Live.week02;

import com.cydeo.utility.FormulaAPITestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_DeserializationPOJO extends FormulaAPITestBase{

    /*
        - ERGAST API
        - Given accept type is json
        - When user send request /status.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 137
        - And limit is 30
        - And each status has statusId
     */

    @Test
    public void POJO() {

        JsonPath jsonPath = given().log().uri().
                when().get("/status.json").prettyPeek().
                then().statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

    }
}
