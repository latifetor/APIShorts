package com.cydeo.officeHours.morningSec;

import com.cydeo.utility.FruitAPITestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_Task3 extends FruitAPITestBase{

    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * GET /shop/categories
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - Print response
     *     - Content-Type is application/json; charset=utf-8
     *     - Status Code is 200
     *     - Get me first category name
     *     - Get me first category_url
     *     - Get me 2nd,3rd category name
     *     - Get me last category name
     *     - Get me all category name
     */


    // EXAMPLE OF WRITING TESTCASE
    /**
     * Given baseURI and basePath is set
     * And Accept Header is "application/json"
     * And Path Param is 1
     * When I send GET request "/shop/categories"
     * Then status code is 200
     * And content type is "application/json"
     * And each category name should not be null
     * And categoriees should contain followings ..
     */

    @Test
    public void responsePath() {

        Response response = given().accept(ContentType.JSON)
                .log().uri().
                when().get("/shop/categories/").prettyPeek();

        //     *     - Content-Type is application/json; charset=utf-8
        assertEquals("application/json; charset=utf-8",response.contentType());

        //     *     - Status Code is 200
        assertEquals(200,response.statusCode());
        //     *     - Get me first category name
        System.out.println("response.path(\"categories[0].name\") = " + response.path("categories[0].name"));
        System.out.println("response.path(\"categories.name[0]\") = " + response.path("categories.name[0]"));

        //     *     - Get me first category_url
        System.out.println((String)response.path("categories[0].category_url"));

        //     *     - Get me 2nd,3rd category name
        System.out.println("response.path(\"categories[1,2].name\") = " + response.path("categories[1,2].name"));

        //     *     - Get me last category name
        System.out.println("response.path(\"categories[-1].name\") = " + response.path("categories[-1].name"));

        //     *     - Get me all category name
        List<String> allCategoryName = response.path("categories.name");

        System.out.println("allCategoryName = " + allCategoryName);
    }


    @Test
    public void jsonPath() {

        Response response = given().log().uri().accept(ContentType.JSON)
                .log().uri().
                when().get("/shop/categories/").prettyPeek();

        //     *     - Content-Type is application/json; charset=utf-8
        assertEquals("application/json; charset=utf-8",response.contentType());

        //     *     - Status Code is 200
        assertEquals(200,response.statusCode());

        // CREATE JSON PATH OBJECT
        JsonPath jp = response.jsonPath();

        //     *     - Get me first category name
        System.out.println(jp.getString("categories[0].name"));

        //     *     - Get me first category_url
        System.out.println(jp.getString("categories[0].category_url"));

        //     *     - Get me 2nd,3rd category name
        System.out.println(jp.getList("categories[1,2].name"));

        //     *     - Get me last category name
        System.out.println(jp.getString("categories[-1].name"));

        //     *     - Get me all category name
        List<String> allCategoryNames = jp.getList("categories.name");
        System.out.println(allCategoryNames);

    }


}
