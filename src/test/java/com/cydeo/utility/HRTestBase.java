package com.cydeo.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public abstract class HRTestBase {

    @BeforeAll
    public static void init(){

        baseURI="http://3.89.223.241:1000";
        basePath="/ords/hr";

    }

    @AfterAll
    public static void destroy(){

        reset();

    }
}
