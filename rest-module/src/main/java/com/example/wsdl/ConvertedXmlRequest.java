
package com.example.wsdl;

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
 *         <element name="sourceXmlText" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "sourceXmlText"
})
@XmlRootElement(name = "convertedXmlRequest")
public class ConvertedXmlRequest {

    @XmlElement(required = true)
    protected String sourceXmlText;

    /**
     * Gets the value of the sourceXmlText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceXmlText() {
        return sourceXmlText;
    }

    /**
     * Sets the value of the sourceXmlText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceXmlText(String value) {
        this.sourceXmlText = value;
    }

}
