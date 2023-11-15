package com.example.config;

import com.example.util.ConverterPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConverterPersonConfig {

    private final Environment environment;

    @Autowired
    public ConverterPersonConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.wsdl");
        return marshaller;
    }

    @Bean
    public ConverterPerson converterClient(Jaxb2Marshaller marshaller) {
        ConverterPerson client = new ConverterPerson();
        client.setDefaultUri(environment.getProperty("soap.uri"));
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
