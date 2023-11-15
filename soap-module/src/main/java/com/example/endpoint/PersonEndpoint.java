package com.example.endpoint;

import com.example.config.WebServiceConfig;
import com.example.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.soap_app.ConvertedXmlRequest;
import com.soap_app.ConvertedXmlResponse;

@Endpoint
public class ClientEndpoint {

    private final ConvertService convertService;

    @Autowired
    public ClientEndpoint(ConvertService convertService) {
        this.convertService = convertService;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "convertedXmlRequest")
    @ResponsePayload
    public ConvertedXmlResponse getClientInfo(@RequestPayload ConvertedXmlRequest request) {
        ConvertedXmlResponse response = new ConvertedXmlResponse();
        try {
            response.setConvertedXmlText(convertService.convertRequestToResponse(request.getSourceXmlText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
