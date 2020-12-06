package ru.netology.web.data;
import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import static io.restassured.RestAssured.given;
import java.util.Locale;

@Data
@AllArgsConstructor

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static void setUpAll(AuthPath userData) {
        given()
                .spec(requestSpec)
                .body(userData)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static AuthPath generateNewAuth() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.expression("vasya");
        String password = faker.expression("password");
        setUpAll(new AuthPath(login, password, "active"));
        return new AuthPath(login, password, "active");
    }

    public static AuthPath generateNewAuthReject() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        setUpAll(new AuthPath(login, password, "blocked"));
        return new AuthPath(login, password, "blocked");
    }

    public static AuthPath generateNewAuthRejectWrongLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.expression("password");
        String status = "active";
        setUpAll(new AuthPath(login, password, status));
        return new AuthPath("Иваныч", password, status);
    }

    public static AuthPath generateNewAuthRejectWrongPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.expression("vasya");
        String password = faker.internet().password();
        String status = "active";
        setUpAll(new AuthPath(login, password, status));
        return new AuthPath(login, "invalidPassword", status);
    }
}
