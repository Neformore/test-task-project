package com.example.endpoint;

import com.example.config.WebServiceConfig;
import com.example.service.ConvertService;
import com.example.wsdl.com.soap_app.ConvertedXmlRequest;
import com.example.wsdl.com.soap_app.ConvertedXmlResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class PersonEndpoint {

    private final ConvertService convertService;

    @Autowired
    public PersonEndpoint(ConvertService convertService) {
        this.convertService = convertService;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "convertedXmlRequest")
    @ResponsePayload
    public ConvertedXmlResponse getPersonInfo(@RequestPayload ConvertedXmlRequest request) {
        log.info("Начало конвертации");
        ConvertedXmlResponse response = new ConvertedXmlResponse();
        try {
            response.setConvertedXmlText(convertService.convertRequestToResponse(request.getSourceXmlText()));
        } catch (Exception e) {
            log.error("Выброшено исключение: ", e);
            e.printStackTrace();
        }
        return response;
    }
}
