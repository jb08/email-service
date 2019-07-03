package dto;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("User Dto")
public class User {
    private String name;
    private String email;

    public void validate() {
        if (name == null) throw new BadRequest("User name may not be null");
        if (email == null) throw new BadRequest("User email may not be null");
    }
}
