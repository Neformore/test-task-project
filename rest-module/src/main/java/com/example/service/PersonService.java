package com.example.service;

import com.example.dao.PersonDao;
import com.example.dao.DocumentDao;
import com.example.dto.PersonDto;
import com.example.dto.DocumentDto;
import com.example.model.Person;
import com.example.model.Document;
import com.example.util.ConverterPerson;
import com.example.wsdl.com.soap_app.ConvertedXmlResponse;
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

    public String saveAndSend(PersonDto personDto) {
        log.info("Сохранение в БД №1");
        save(personDto);

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            log.info("Конвертация объекта в xml");
            String xml = xmlMapper.writeValueAsString(personDto);

            log.info("Отправка и получение ответа");
            ConvertedXmlResponse response = converterPerson.getConverted(xml);

            JAXBContext jaxbContext = JAXBContext.newInstance(PersonDto.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            log.info("Собираем объект из полученного ответа");
            PersonDto convertedPersonDto = (PersonDto) unmarshaller.unmarshal(new StringReader((response.getConvertedXmlText())));

            log.info("Сохранение в БД №2");
            save(convertedPersonDto);
            return response.getConvertedXmlText();
        } catch (JsonProcessingException | JAXBException e) {
            log.error("Выброшено исключение: ",e);
            throw new RuntimeException(e);
        }
    }

    public void save(PersonDto personDto) {
        Person person = convertToClient(personDto);
        Document document = convertToDocument(personDto.getDocument());
        document.setPerson(person);

        personDao.save(person);
        documentDao.save(document);
    }

    private Person convertToClient(PersonDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    private Document convertToDocument(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }
}
