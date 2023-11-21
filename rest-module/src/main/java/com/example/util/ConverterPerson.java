package com.example.util;

import com.example.wsdl.com.soap_app.ConvertedXmlRequest;
import com.example.wsdl.com.soap_app.ConvertedXmlResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ConverterPerson extends WebServiceGatewaySupport {

    public ConvertedXmlResponse getConverted(String xml) {
        ConvertedXmlRequest request = new ConvertedXmlRequest();
        request.setSourceXmlText(xml);

        ConvertedXmlResponse response = (ConvertedXmlResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/service/convert",
                        request,
                        new SoapActionCallback("http://www.soap-app.com/ConvertedXmlRequest"));

        return response;
    }
}
