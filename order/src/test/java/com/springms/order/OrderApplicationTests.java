package com.springms.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static org.hamcrest.MatcherAssert.assertThat;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    static {
        mySQLContainer.start();
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldPlaceOrder() {
        String placeOrderBody = """
                {
                   "skuCode": "iphone 2",
                   "price": 1999,
                   "quantity":1
                 }
                """;
        var responseBodyString = RestAssured.given()
                .contentType("application/json")
                .body(placeOrderBody)
                .when()
                .post("/api/order")
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .body()
                .asString();
        assertThat(responseBodyString, Matchers.is("Order placed successfully"));
    }

}
