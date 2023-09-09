package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public abstract class CydeoTrainingTestBase {


    @BeforeAll
    public static void init() {

        baseURI = "https://api.training.cydeo.com";

    }

    @AfterAll
    public static void destroy() {

        reset();

    }
}

