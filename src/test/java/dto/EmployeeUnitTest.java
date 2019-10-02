package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.BadRequest;
import utils.TestUtils;

import com.github.javafaker.Faker;

class EmployeeUnitTest {

    private final Faker faker = new Faker();

    @Test
    void validate_salariedEmployee_Valid() {
        Employee employee = TestUtils.buildEmployee();
        employee.validate();

        employee.setSalaried(false);
        Exception e = assertThrows(BadRequest.class, ()-> employee.validate());
        assertEquals("Non-salaried employees may not have salaries.", e.getMessage());
    }

}
