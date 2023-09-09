package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class BooksStoreTestBase {


    @BeforeAll
    public static void init() {

        baseURI = "https://demoqa.com";

    }

    @AfterAll
    public static void destroy() {

        reset();

    }


}
