package com.woodwing.demo.controller;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.woodwing.demo.domain.RequestBodyInput;
import com.woodwing.demo.domain.RequestResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "8080")
public class ApiControllerTest {

    private RequestSpecBuilder builder;
    private RequestSpecification requestSpec;

    private static String baseUrl = "http://localhost:8080/sum";

    @Before
    public void setup() {
        builder = new RequestSpecBuilder();
        builder.setBaseUri(baseUrl);
        builder.setContentType(ContentType.JSON);
        requestSpec = builder.build();
    }

    @Test
    public void testApiSum() {
        RequestBodyInput input = new RequestBodyInput();
        input.setSumType("yards");
        input.setSummandOneType("yards");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(20);

        RequestResponse in = given().spec(requestSpec).content(input).when().put().then()
                .statusCode(HttpStatus.SC_OK).extract().as(RequestResponse.class);
        Assert.assertEquals(in.getSum(), 30.0, 0.0);
    }

    @Test
    public void testApiSumBadRequestMissingType() {
        RequestBodyInput input = new RequestBodyInput();
        input.setSumType("");
        input.setSummandOneType("yards");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(20);

        given().spec(requestSpec).content(input).when().put().then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testApiSumBadRequestNegativeValue() {
        RequestBodyInput input = new RequestBodyInput();
        input.setSumType("yards");
        input.setSummandOneType("yards");
        input.setSummandTwoType("yards");
        input.setSummandOneValue(-10);
        input.setSummandTwoValue(20);

        given().spec(requestSpec).content(input).when().put().then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void testApiSumBadRequestNullValue() {
        RequestBodyInput input = new RequestBodyInput();
        input.setSummandTwoType("yards");
        input.setSummandOneValue(10);
        input.setSummandTwoValue(20);

        given().spec(requestSpec).content(input).when().put().then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
