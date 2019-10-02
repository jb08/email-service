package service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static utils.TestUtils.buildEmployee;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dto.Employee;
import exception.BadRequest;
import persistence.EmployeeDao;

import com.github.javafaker.Faker;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceUnitTest {

    @Mock
    EmployeeDao employeeDao;

    @InjectMocks
    EmployeeService employeeService;

    private static final Faker FAKER = new Faker();

    @Test
    void putOne_DoesntExist_Throws() {
        Employee employee = buildEmployee();
        when(employeeDao.find(employee.getId())).thenReturn(Optional.empty());
        assertThrows(BadRequest.class, () -> employeeService.putOne(employee.getId(), employee));
    }

    @Test
    void putOne_Exists_Updates() {
        Employee employee = spy(buildEmployee());
        long id = employee.getId();
        when(employeeDao.find(employee.getId())).thenReturn(Optional.of(employee));
        employee.setJobTitle("Manager");
        employeeService.putOne(id, employee);
        verify(employee, times(1)).setId(id);
        verify(employeeDao, times(1)).update(employee);
    }

}
