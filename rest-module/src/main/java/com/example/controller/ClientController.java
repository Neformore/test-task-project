package com.example.controller;

import com.example.dto.ClientDto;
import com.example.dto.DocumentDto;
import com.example.model.Client;
import com.example.model.Document;
import com.example.service.ClientService;
import com.example.service.DocumentService;
import com.example.util.ClientErrorResponse;
import com.example.util.ClientNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final DocumentService documentService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, DocumentService documentService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ClientDto clientDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append("-")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new ClientNotCreatedException(errorMsg.toString());
        }

        Client client = convertToClient(clientDto);
        Document document = convertToDocument(clientDto.getDocument());
        document.setClient(client);

        clientService.save(client);
        documentService.save(document);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Client convertToClient(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    private Document convertToDocument(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
