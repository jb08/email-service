package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.Employee;
import exception.BadRequest;
import persistence.EmployeeDao;

@Component
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    public List<Employee> getEmployees() {
        return  employeeDao.getEmployees();
    }

    public Optional<Employee> getOne(long id) {
        return employeeDao.find(id);
    }

    public void putOne(long id, Employee employee) {
        employee.setId(id);
        Optional<Employee> optEmployee = employeeDao.find(id);
        if (optEmployee.isEmpty()) {
            throw new BadRequest(String.format("Employee does not exist with that id: %s", id));
        }
        employeeDao.update(employee);
    }


}
