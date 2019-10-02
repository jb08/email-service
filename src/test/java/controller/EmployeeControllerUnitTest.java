package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dto.Employee;
import service.EmployeeService;

import com.github.javafaker.Faker;


@ExtendWith(MockitoExtension.class)
class EmployeeControllerUnitTest {

    private static final Faker FAKER = new Faker();

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    @Test
    void putEmployee_updated_NoContent() {
        Employee employee = spy(Employee.builder()
                .id(new Random().nextInt())
                .name(FAKER.name().nameWithMiddle())
                .jobTitle(FAKER.job().title())
                .departmentId(new Random().nextInt())
                .fullTime(true)
                .salaried(true)
                .salary(new Random().nextDouble() * 1000)
                .build());

        ResponseEntity<Void> response = employeeController.put(employee.getId(), employee);
        verify(employee, times(1)).validate();
        verify(employeeService, times(1)).putOne(employee.getId(), employee);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
