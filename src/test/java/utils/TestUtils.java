package utils;

import java.util.Random;

import dto.Email;
import dto.Employee;

import com.github.javafaker.Faker;

public class TestUtils {

    private static final Faker FAKER = new Faker();

    public static Employee buildEmployee() {
        return Employee.builder()
                .id(new Random().nextInt())
                .name(FAKER.name().nameWithMiddle())
                .jobTitle(FAKER.job().title())
                .departmentId(new Random().nextInt())
                .fullTime(true)
                .salaried(true)
                .salary(new Random().nextDouble() * 1000)
                .build();
    }

    public static Email buildEmail() {
        return Email.builder()
                .to(FAKER.internet().emailAddress())
                .toName(FAKER.name().fullName())
                .from(FAKER.internet().emailAddress())
                .fromName(FAKER.name().fullName())
                .subject(FAKER.commerce().productName())
                .body(FAKER.company().catchPhrase())
                .build();
    }
}
