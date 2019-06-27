package controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Message;
import lombok.extern.log4j.Log4j2;
import service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Log4j2
@Api(tags = "Messages", description = "Operations about Messages")
@RequestMapping(value = "/v1/messages", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get the message with the given id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved message")})
    public Message getMessage(@PathVariable("id") @ApiParam(value = "The id of the message", required = true) UUID id) {
        return messageService.getMessage(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Create or update the message with the given id")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully updated message"),
            @ApiResponse(code = 201, message = "Successfully created message")})
    public ResponseEntity<Void> putMessage(@PathVariable("id") @ApiParam(value = "The id of the message", required = true) UUID id,
                                           @RequestBody @ApiParam(value = "A new or updated message", required = true) Message message) {
        boolean messageAlreadyExisted = messageService.putMessage(message);
        return ResponseEntity.status(messageAlreadyExisted ? HttpStatus.NO_CONTENT : HttpStatus.CREATED).build();
    }

}