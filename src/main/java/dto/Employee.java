package dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@ApiModel("Employee Dto")
public class Employee {
    private long id;
    private String name;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("department_id")
    private int departmentId;
    @JsonProperty("full_time")
    private boolean fullTime;
    @JsonInclude(NON_DEFAULT)
    private boolean salaried;
    @JsonInclude(NON_DEFAULT)
    private Double salary;

    @JsonProperty("weekly_hours")
    @JsonInclude(NON_DEFAULT)
    private Double weeklyHours;
    @JsonProperty("hourly_rate")
    @JsonInclude(NON_DEFAULT)
    private Double hourlyRate;

    public void validate() {
        if (name == null) throw new BadRequest("Employee name may not be null");
        if (jobTitle == null) throw new BadRequest("Employee job title may not be null");
        if (!salaried && salary != null) throw new BadRequest("Non-salaried employees may not have salaries.");
    }
}
