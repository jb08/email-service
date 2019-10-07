package dto;

import org.apache.commons.validator.routines.EmailValidator;
import org.jsoup.Jsoup;

import exception.BadRequest;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Builder
@ApiModel("Email Dto")
public class Email {
    private String to;
    @JsonProperty("to_name")
    private String toName;
    private String from;
    @JsonProperty("from_name")
    private String fromName;
    private String subject;
    private String body;

    public void validate() {
        if (to == null) throw new BadRequest("To may not be null.");
        if (toName == null) throw new BadRequest("To_name may not be null.");
        if (from == null) throw new BadRequest("From may not be null.");
        if (fromName == null) throw new BadRequest("From_name may not be null.");
        if (subject == null) throw new BadRequest("Subject may not be null.");
        if (body == null) throw new BadRequest("Body may not be null.");

        validateEmail(to);
        validateEmail(from);
    }

    public void removeHtmlTagsFromBody() {
        body = Jsoup.parse(body).text();
    }

    private static void validateEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
           throw new BadRequest(String.format("Email is invalid: %s.", email));
        }
    }

}
