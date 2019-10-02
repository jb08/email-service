package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.Employee;
import exception.NotFound;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import service.EmployeeService;

@Component
@Log4j2
@Api(tags = "Employee", description = "Operations about Employees")
@RequestMapping(value = "/v1/employees", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get Employees")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved employees")})
    public ResponseEntity<List<Employee>> get() {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get One Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved employees")})
    public ResponseEntity<Employee> get(@PathVariable("id") long id) {
        Optional<Employee> optionalEmployee = employeeService.getOne(id);
        if (optionalEmployee.isPresent()) {
            return new ResponseEntity<>(optionalEmployee.get(), HttpStatus.OK);
        } else {
            throw new NotFound();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Put One Employee")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Successfully updated employees")})
    public ResponseEntity<Void> put(@PathVariable("id") long id, @RequestBody Employee employee) {
        employee.validate();
        employeeService.putOne(id, employee);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
