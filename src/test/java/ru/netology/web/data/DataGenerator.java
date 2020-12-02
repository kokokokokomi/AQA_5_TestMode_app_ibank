package ru.netology.web.data;
import com.github.javafaker.Faker;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {}

    public static AuthPath generateNewAuth() {
        Faker faker = new Faker(new Locale("en"));
        return new AuthPath(faker.expression("vasya"),
                faker.expression("password"),
                faker.expression("active"));
    }

    public static AuthPath generateNewAuthReject() {
        Faker faker = new Faker(new Locale("ru"));
        return new AuthPath(faker.expression("иван_иванов"),
                faker.expression("пароль"),
                faker.expression("blocked"));
    }
}
