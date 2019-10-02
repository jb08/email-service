package utils;

import static org.mockito.Mockito.spy;

import java.util.Random;

import dto.Employee;

import com.github.javafaker.Faker;

public class TestUtils {

    private static final Faker FAKER = new Faker();

    public static Employee buildEmployee(){
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
}
