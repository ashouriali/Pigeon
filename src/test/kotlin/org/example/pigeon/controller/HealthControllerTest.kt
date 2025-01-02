package org.example.pigeon.controller

import io.restassured.RestAssured
import io.restassured.RestAssured.port
import org.example.pigeon.base.PigeonTest
import org.junit.jupiter.api.Test

@PigeonTest
internal class HealthControllerTest {

    @Test
    fun `app should start correctly`() {
        RestAssured
            .given()
            .baseUri("http://localhost:$port/health")
            .contentType("application/json")
            .get()
            .then()
            .statusCode(200)
    }
}