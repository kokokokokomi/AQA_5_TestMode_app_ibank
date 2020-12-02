package ru.netology.web.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.AuthElements;
import ru.netology.web.data.AuthPath;
import ru.netology.web.data.DataGenerator;

import static io.restassured.RestAssured.given;

public class AuthTest {
    AuthElements auth = new AuthElements();

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeAll
    static void setUpAll() {
        given()
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new AuthPath("vasya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldConfirmAuthRequest() {
        auth.openBrowser();
        auth.sendAllFieldsCorrectFilledRequest();
    }

    @Test
    void shouldNotConfirmWrongLoginAuthRequest() {
        auth.openBrowser();
        auth.sendWrongLoginRequest();
        auth.findRejectMessage();
    }

    @Test
    void shouldNotConfirmWrongPasswordAuthRequest() {
        auth.openBrowser();
        auth.sendWrongPasswordRequest();
        auth.findRejectMessage();
    }

    @Test
    void shouldNotConfirmEmptyLoginFieldAuthRequest() {
        auth.openBrowser();
        auth.sendEmptyLoginFieldRequest();
        auth.findRejectEmptyLoginFieldMessage();
    }

    @Test
    void shouldNotConfirmEmptyPasswordFieldAuthRequest() {
        auth.openBrowser();
        auth.sendEmptyPasswordFieldRequest();
        auth.findRejectEmptyPasswordFieldMessage();
    }
}
