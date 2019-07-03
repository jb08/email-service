package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import service.ConversationService;

@Component
@Log4j2
@Api(tags = "Conversations", description = "Operations about Conversations")
@RequestMapping(value = "/v1/conversations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @RequestMapping(value = "/findByParticipants/{participants}", method = RequestMethod.GET)
    @ApiOperation(value = "Get all messages with the given participants")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved messages")})
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable("participants")
            @ApiParam(value = "The comma-separated conversation participants" +
                    "(ie. \"Trump,Putin\", or \"Shelley,Jason\")",
                    required = true) String participants) {
        List<Message> messages = conversationService.getConversation(participants.split(","));
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
