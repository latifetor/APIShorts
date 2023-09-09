package com.cydeo.officeHours.morningSec;

import com.cydeo.utility.BooksStoreTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


// for not to write unnecessary words in the test
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P02_Task2 extends BooksStoreTestBase {

    /**
     * Task 2:
     * Verify response body info of the first book
     *     When User sends GET request to receive first book info
     *     Then Verify that we have correct info about first book which we selected
     *     Book Information for validation
     *       | key          | value                                                                     |
     *       | isbn         | 9781449337711                                                             |
     *       | title        | Designing Evolvable Web APIs with ASP.NET                                 |
     *       | subTitle     | Harnessing the Power of the Web                                           |
     *       | author       | Glenn Block et al.                                                       |
     *       | publish_date | 2020-06-04T09:12:43.000Z                                                  |
     *       | publisher    | O'Reilly Media                                                            |
     *       | pages        | 238                                                                       |
     *       | description  | Design and build Web APIs for a broad range of clients—including browsers
     *                        and mobile devices—that can adapt to change over time. This practical,
     *                        hands-on guide takes you through the theory and tools you need to build
     *                        evolvable HTTP services with Microsoft|
     *       | website      | http://chimera.labs.oreilly.com/books/1234000001708/index.html            |
     */



    @Test
    public void getBook() {


        Response response = given().accept(ContentType.JSON).
                log().uri()
                .queryParam("ISBN", "9781449337711").
                when().get("BookStore/v1/Book").prettyPeek();

        // JSONPATH OBJECT
        JsonPath jp = response.jsonPath();

        // Status Code
        assertEquals(200,response.statusCode());

        // VERIFY FOLLOWINGS
        //     *     Book Information for validation


        //     *       | isbn         | 9781449337711
        assertEquals("9781449337711",jp.getString("isbn"));
        System.out.println(jp.getString("isbn"));

        //     *       | title        | Designing Evolvable Web APIs with ASP.NET
        assertEquals("Designing Evolvable Web APIs with ASP.NET",jp.getString("title"));

        //             | subTitle     | Harnessing the Power of the Web   |
        assertEquals("Harnessing the Power of the Web",jp.getString("subTitle"));


        // REST OF THEM SAME

    }
    @Test
    public void BooksTest01(){

        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .get("/BookStore/v1/Books");

        int expectedAmount = 8;
        List<Object> books = response.path("books");
        int actualAmount = books.size();

        assertEquals(expectedAmount,actualAmount);

        JsonPath jsonPath = response.jsonPath();
        // useful methods to read response object and store the data into JAVA data types
        actualAmount = jsonPath.getList("books").size();
        assertEquals(expectedAmount,actualAmount);

    }

    @Test
    public void BooksTest02(){
        Response response = given().log().uri()
                .accept(ContentType.JSON)
                .queryParam("ISBN","9781449325862")
                .get("/BookStore/v1/Book");
        response.prettyPrint();

        // How we are using jsonpath object: first we need to change response object to jsonpath object
        JsonPath jsonPath = response.jsonPath();
        String expectedISBN = "9781449325862";
        String actualISBN = jsonPath.getString("isbn");
        assertEquals(expectedISBN,actualISBN);

    }


}
