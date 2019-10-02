package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import dto.DriverCheck;
import dto.Employee;
import dto.Identity;
import persistence.EmployeeDao;

@Component
public class DriverCheckService {

    EmployeeDao employeeDao;

    /**
     * Upload identities.
     *
     */
    public List<Employee> getEmployees() {
        return  employeeDao.getEmployees();
    }

    public Optional<Employee> getOne(long id) {
        return employeeDao.find(id);
    }

    public void processCheck(Identity identity, DriverCheck driverCheck) {

    }

}
