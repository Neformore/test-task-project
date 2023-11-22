package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
@Slf4j
public class ConvertService {

    public String convertRequestToResponse(String requestXml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Source xslt = new StreamSource(new ClassPathResource("convertRequestToResponse.xsl").getInputStream());

            Transformer transformer = transformerFactory.newTransformer(xslt);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            Source xsltSource = new StreamSource(new StringReader(requestXml));
            StringWriter resultWriter = new StringWriter();
            transformer.transform(xsltSource, new StreamResult(resultWriter));

            log.info("Конвертация завершена успешно!");
            return resultWriter.toString();
        } catch (TransformerException | IOException e) {
            log.error("Выброшено исключение: {}", e.getMessage(), e);
            return null;
        }
    }
}
