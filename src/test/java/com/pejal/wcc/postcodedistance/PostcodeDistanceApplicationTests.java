package com.pejal.wcc.postcodedistance;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.CoreMatchers.;
import static org.hamcrest.number.IsCloseTo.closeTo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class PostcodeDistanceApplicationTests {

    private final static String BASE_URI = "http://localhost";
 
    @LocalServerPort
    private int port;
    
    @BeforeTestExecution
    public void configureRestAssured() {
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }
    
    @Test
    void checkLocationCount() {
        given().auth().preemptive().basic("user1", "p@ssw0rd")
                .when()
                .get("/postcode/count")
                .then()
                .assertThat().log().all().statusCode(200);
    }
    @Test
    void checkDistanceBetweenTwoPostcodes() {
        given().auth().preemptive().basic("user1", "p@ssw0rd")
                .when()
                .get("http://localhost:8080/postcode/distance?postcodeA=AB12 5GL&postcodeB=L2 2HF")
                .then()
                .assertThat()
                .log().all().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("unit", equalTo("km"))
                .body("distance", equalTo(411.23022F));
                
    }

}
