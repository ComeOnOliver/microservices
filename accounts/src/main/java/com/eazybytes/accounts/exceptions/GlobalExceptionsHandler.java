package com.eazybytes.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.eazybytes.accounts.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionsHandler {
    
    @ExceptionHandler(CustomerAleareadyExisted.class)
    public ResponseEntity<ErrorDto> handleCustomerAlreadyExistsException(CustomerAleareadyExisted exception,
                                                                                 WebRequest webRequest){
        ErrorDto errorResponseDTO = new ErrorDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CannotFind.class)
    public ResponseEntity<ErrorDto> handleCannotFindException(CannotFind exception,
                                                                                 WebRequest webRequest){
        ErrorDto errorResponseDTO = new ErrorDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }
}
