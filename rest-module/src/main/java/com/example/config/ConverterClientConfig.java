package com.example.config;

import com.example.util.ConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ConverterClientConfig {

    private final Environment environment;

    @Autowired
    public ConverterClientConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.wsdl");
        return marshaller;
    }

    @Bean
    public ConverterClient soapConverterClient(Jaxb2Marshaller marshaller) {
        ConverterClient client = new ConverterClient();
        client.setDefaultUri(environment.getProperty("soap.uri"));
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
