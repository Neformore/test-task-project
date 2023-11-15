package com.example.service;

import com.example.dao.ClientDao;
import com.example.dao.DocumentDao;
import com.example.dto.ClientDto;
import com.example.dto.DocumentDto;
import com.example.model.Client;
import com.example.model.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientDao clientDao;
    private final DocumentDao documentDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientDao clientDao, DocumentDao documentDao, ModelMapper modelMapper) {
        this.clientDao = clientDao;
        this.documentDao = documentDao;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void saveAndSend(ClientDto clientDto) {
        Client client = convertToClient(clientDto);
        Document document = convertToDocument(clientDto.getDocument());
        document.setClient(client);

        clientDao.save(client);
        documentDao.save(document);


    }

    private Client convertToClient(ClientDto clientDto) {
        return modelMapper.map(clientDto, Client.class);
    }

    private Document convertToDocument(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
