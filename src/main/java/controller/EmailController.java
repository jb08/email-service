package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.Email;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import service.EmailService;

@Component
@Log4j2
@Api(tags = "Emails", description = "Operations about Emails")
@RequestMapping(value = "/v1/emails", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "Send an email")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created an email")})
    public ResponseEntity<Void> post(@RequestBody Email email) {
        email.validate();
        email.removeHtmlTagsFromBody();
        emailService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
