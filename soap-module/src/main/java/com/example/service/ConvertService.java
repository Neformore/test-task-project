package com.example.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
public class ConvertService {

    public String convertRequestToResponse(String requestXml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Source xslt = new StreamSource(new ClassPathResource("convertRequestToResponse.xsl").getInputStream());

            Transformer transformer = transformerFactory.newTransformer(xslt);

            Source xsltSource = new StreamSource(new StringReader(requestXml));
            StringWriter resultWriter = new StringWriter();
            transformer.transform(xsltSource, new StreamResult(resultWriter));

            return resultWriter.toString();
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
