package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import dto.Employee;

@Component
public class EmployeeDao {

    @Autowired
    NamedParameterJdbcOperations jdbcOperations;
    EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();

    public Optional<Employee> find(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        String sql = "SELECT * FROM Employees WHERE id = :id";
        List<Employee> output = jdbcOperations.query(sql, namedParameters, employeeRowMapper);
        return output.isEmpty() ? Optional.empty() : Optional.of(output.get(0));
    }

    public List<Employee> getEmployees() {

        SqlParameterSource namedParameters = new MapSqlParameterSource();
        List<Employee> output = jdbcOperations.query("SELECT * FROM Employees;", namedParameters, employeeRowMapper);
        return output;
    }

    public void update(Employee employee) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", employee.getId());
        namedParameters.addValue("name", employee.getName());
        namedParameters.addValue("job_title", employee.getJobTitle());
        namedParameters.addValue("dept_id", employee.getDepartmentId());
        namedParameters.addValue("full_time", employee.isFullTime());
        namedParameters.addValue("salaried", employee.isSalaried());
        namedParameters.addValue("typical_hours", employee.getWeeklyHours());
        namedParameters.addValue("annual_salary", employee.getSalary());
        namedParameters.addValue("hourly_rate", employee.getHourlyRate());
        jdbcOperations.update("UPDATE employees SET name = :name, job_title = :job_title, " +
                "dept_id = :dept_id, full_time = :full_time, salaried = :salaried, typical_hours = :typical_hours, " +
                "annual_salary = :annual_salary, hourly_rate = :hourly_rate " +
                "WHERE id IN (:id)", namedParameters);

    }

    class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Employee.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .jobTitle(rs.getString("job_title"))
                    .departmentId(rs.getInt("dept_id"))
                    .fullTime(rs.getBoolean("full_time"))
                    .salaried(rs.getBoolean("salaried"))
                    .weeklyHours(rs.getDouble("typical_hours"))
                    .salary(rs.getDouble("annual_salary"))
                    .hourlyRate(rs.getDouble("hourly_rate"))
                    .build();
        }
    }
}
