package dto;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("Check Dto") //Renjie
public class Identity {
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;

    String fullname() {
        return middleName == null ?
                String.format("%s %s", firstName, lastName) :
                String.format("%s %s %s", firstName, middleName, lastName);
    }

    void validate() {
        if (firstName == null) {
            throw new BadRequest("firstName may not be null");
        }
        if (lastName == null) {
            throw new BadRequest("lastName may not be null");
        }
    }
}
