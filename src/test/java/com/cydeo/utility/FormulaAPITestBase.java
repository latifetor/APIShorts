package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.reset;

public abstract class FormulaAPITestBase {

    @BeforeAll
    public static void init(){

        baseURI="http://ergast.com/api/f1";

    }

    @AfterAll
    public static void destroy(){

        reset();

    }

}
