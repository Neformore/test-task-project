package com.example.service;

import com.example.dao.PersonDao;
import com.example.dao.DocumentDao;
import com.example.dto.PersonDto;
import com.example.dto.DocumentDto;
import com.example.model.Person;
import com.example.model.Document;
import com.example.util.ConverterPerson;
import com.example.wsdl.ConvertedXmlResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.StringReader;
import java.text.SimpleDateFormat;

@Service
@Transactional(readOnly = true)
@Slf4j
public class PersonService {

    private final PersonDao personDao;
    private final DocumentDao documentDao;
    private final ModelMapper modelMapper;
    private final ConverterPerson converterPerson;

    @Autowired
    public PersonService(PersonDao personDao, DocumentDao documentDao, ModelMapper modelMapper, ConverterPerson converterPerson) {
        this.personDao = personDao;
        this.documentDao = documentDao;
        this.modelMapper = modelMapper;
        this.converterPerson = converterPerson;
    }

    @Transactional
    public void saveAndSend(PersonDto personDto) {
        Person person = convertToClient(personDto);
        Document document = convertToDocument(personDto.getDocument());
        document.setPerson(person);

        personDao.save(person);
        documentDao.save(document);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            String xml = xmlMapper.writeValueAsString(personDto);

            ConvertedXmlResponse response = converterPerson.getConverted(xml);

            JAXBContext jaxbContext = JAXBContext.newInstance(PersonDto.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            PersonDto convertedPersonDto = (PersonDto) unmarshaller.unmarshal(new StringReader((response.getConvertedXmlText())));
            Person convertedPerson = new ObjectMapper().convertValue(convertedPersonDto, Person.class);
            System.out.println();
        } catch (JsonProcessingException | JAXBException e) {
            log.error("Выброшено исключение: ",e);
            throw new RuntimeException(e);
        }
    }

    private Person convertToClient(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    private Document convertToDocument(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
