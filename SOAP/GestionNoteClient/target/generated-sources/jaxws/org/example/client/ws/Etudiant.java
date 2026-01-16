
package org.example.client.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for etudiant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="etudiant">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="prenom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cne" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="note1" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="note2" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "etudiant", propOrder = {
    "nom",
    "prenom",
    "cne",
    "note1",
    "note2"
})
public class Etudiant {

    protected String nom;
    protected String prenom;
    protected String cne;
    protected double note1;
    protected double note2;

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Gets the value of the prenom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Sets the value of the prenom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrenom(String value) {
        this.prenom = value;
    }

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

    /**
     * Gets the value of the note1 property.
     * 
     */
    public double getNote1() {
        return note1;
    }

    /**
     * Sets the value of the note1 property.
     * 
     */
    public void setNote1(double value) {
        this.note1 = value;
    }

    /**
     * Gets the value of the note2 property.
     * 
     */
    public double getNote2() {
        return note2;
    }

    /**
     * Sets the value of the note2 property.
     * 
     */
    public void setNote2(double value) {
        this.note2 = value;
    }

}
