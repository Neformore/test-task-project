package com.example.endpoint;

import com.example.config.WebServiceConfig;
import com.example.service.ConvertService;
import com.soap_app.GetConvertedXmlRequest;
import com.soap_app.GetConvertedXmlResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEndpoint {

    private final ConvertService convertService;

    @Autowired
    public ClientEndpoint(ConvertService convertService) {
        this.convertService = convertService;
    }

    @PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "convertedXmlRequest")
    @ResponsePayload
    public GetConvertedXmlResponse getClientInfo(@RequestPayload GetConvertedXmlRequest request) {
        GetConvertedXmlResponse response = new GetConvertedXmlResponse();
        try {
            response.setConvertedXmlText(convertService.convertRequestToResponse(request.getSourceXmlText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
