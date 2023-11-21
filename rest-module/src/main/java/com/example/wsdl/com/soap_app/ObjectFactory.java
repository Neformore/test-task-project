
package com.example.wsdl.com.soap_app;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soap_app package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soap_app
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConvertedXmlRequest }
     * 
     * @return
     *     the new instance of {@link ConvertedXmlRequest }
     */
    public ConvertedXmlRequest createConvertedXmlRequest() {
        return new ConvertedXmlRequest();
    }

    /**
     * Create an instance of {@link ConvertedXmlResponse }
     * 
     * @return
     *     the new instance of {@link ConvertedXmlResponse }
     */
    public ConvertedXmlResponse createConvertedXmlResponse() {
        return new ConvertedXmlResponse();
    }

}
