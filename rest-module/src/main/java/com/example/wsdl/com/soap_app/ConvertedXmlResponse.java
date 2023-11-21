
package com.example.wsdl.com.soap_app;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="convertedXmlText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "convertedXmlText"
})
@XmlRootElement(name = "convertedXmlResponse")
public class ConvertedXmlResponse {

    @XmlElement(required = true)
    protected String convertedXmlText;

    /**
     * Gets the value of the convertedXmlText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConvertedXmlText() {
        return convertedXmlText;
    }

    /**
     * Sets the value of the convertedXmlText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConvertedXmlText(String value) {
        this.convertedXmlText = value;
    }

}
