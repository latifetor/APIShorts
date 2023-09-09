package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class FruitAPITestBase {

    @BeforeAll
    public static void init() {

        baseURI = "https://api.predic8.de";

    }

    @AfterAll
    public static void destroy() {

        reset();

    }


}
