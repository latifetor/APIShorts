package com.cydeo.restAssured_Live.week01;

import com.cydeo.utility.BoredTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_QueryParam extends BoredTestBase {

    /**
     *1- Given accept type is Json
     *2- And Query Parameters values are
     *     - minprice —> 0
     *     - maxprice —> 0.1
     *3- When user sends GET request to /activity
     *4- Print out Followings
     *     - response
     *     - activity
     *     - type
     *     - participants
     * 5- Verify followings
     *     - Status code should be 200
     *     - price is lower than 0.1
     */

    @Test
    public void getActivity() {

        Response response = given().accept(ContentType.JSON).log().parameters()
                .queryParam("minprice",0)
                .queryParam("maxprice",0.1)
                .when().get("/activity");

        response.prettyPrint();

        //  *4- Print out Followings
        //     *     - response
        //     *     - activity
        String activity = response.path("activity");
        System.out.println("activity = " + activity);

        //     *     - type
        String type = response.path("type");
        System.out.println("type = " + type);

        //     *     - participants
        int participants = response.path("participants");
        System.out.println("participants = " + participants);

        //     * 5- Verify followings
        //     *     - Status code should be 200
        assertEquals(200,response.statusCode());

        //     *     - price is lower than 0.1
        Object price = response.path("price");
        assertTrue(Double.parseDouble(String.valueOf(price)) <= 0.1);


    }

    @Test
    public void getActivityQueryParams() {

        Map<String,Double> queryParams=new HashMap<>();

        queryParams.put("minprice", (double) 0);
        queryParams.put("maxprice",  0.1);

        Response response = given().accept(ContentType.JSON).log().all()
                // .queryParam("minprice",0)
                // .queryParam("maxprice",0.1)
                .queryParams(queryParams)
                .when().get("/activity");

        response.prettyPrint();

        //  *4- Print out Followings
        //     *     - response

        JsonPath jp = response.jsonPath();

        //     *     - activity
        String activity = jp.getString("activity");
        System.out.println("activity = " + activity);

        //     *     - type
        String type = jp.getString("type");
        System.out.println(type);

        //     *     - participants
        int participants = jp.getInt("participants");
        System.out.println(participants);

        //     * 5- Verify followings
        //     *     - Status code should be 200
        assertEquals(200,response.statusCode());

        //     *     - price is lower than 0.1
        // This is the advantage o using JSONPATH over RESPONSE PATH
        double price = jp.getDouble("price");
        assertTrue(price<=0.1);


    }

}
