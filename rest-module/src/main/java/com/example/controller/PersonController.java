package com.example.controller;

import com.example.dto.PersonDto;
import com.example.service.PersonService;
import com.example.util.PersonConvertException;
import com.example.util.PersonErrorResponse;
import com.example.util.PersonNotCreatedException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
@Slf4j
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> create(@RequestBody @Valid PersonDto personDto, BindingResult bindingResult) {
        log.info("Начало работы");
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }

            log.error("Ошибка {}", errorMsg);
            throw new PersonNotCreatedException(errorMsg.toString());
        }

        String response = personService.saveAndSend(personDto);
        log.info("Успешно!");

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleCreatedException(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleConvertException(PersonConvertException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
