package com.mattvoget.infomanager.controllers;

import com.mattvoget.sarlacc.client.SarlaccUserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlingController {

    @ExceptionHandler({SarlaccUserException.class})
    void handleBadUserRequests(SarlaccUserException sue, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),sue.getMessage());
    }
}
