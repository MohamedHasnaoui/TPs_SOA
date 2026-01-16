
package org.example.client.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNoteFinalAvecAbsence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="getNoteFinalAvecAbsence">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNoteFinalAvecAbsence", propOrder = {
    "cne"
})
public class GetNoteFinalAvecAbsence {

    protected String cne;

    /**
     * Gets the value of the cne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCne() {
        return cne;
    }

    /**
     * Sets the value of the cne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCne(String value) {
        this.cne = value;
    }

}
