package com.cydeo.utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class BoredTestBase {

    @BeforeAll
    public static void init(){

        baseURI="http://www.boredapi.com/api";

    }

    @AfterAll
    public static void destroy(){

        reset();

    }
}
