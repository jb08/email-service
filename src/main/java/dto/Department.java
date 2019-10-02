package dto;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("Department Dto")
public class Department {
    private int id;
    private String name;

    public void validate() {
        if (name == null) throw new BadRequest("Department name may not be null");
    }
}
