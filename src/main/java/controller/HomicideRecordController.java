package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dto.HomicideRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import persistence.HomicideDao;

@Component
@Log4j2
@Api(tags = "Homicides", description = "Operations about Homicides")
@RequestMapping(value = "/v1/homicides", produces = {MediaType.APPLICATION_JSON_VALUE})
public class HomicideRecordController {

    @Autowired
    private HomicideDao homicideDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get Homicide Records")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved homicide records")})
    public ResponseEntity<List<HomicideRecord>> get() {
        return new ResponseEntity<List<HomicideRecord>>(homicideDao.getHomicides(), HttpStatus.OK);
    }
}
