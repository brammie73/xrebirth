//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:06:21 PM CET 
//


package nl.games.xrebirth.generated.quotas;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Crate quota definition
 *       
 * 
 * <p>Java class for quota_crates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quota_crates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crate" type="{}quota_crate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="docklimit" type="{}quota_limit" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quota_crates", propOrder = {
    "crate"
})
public class QuotaCrates {

    protected List<QuotaCrate> crate;
    @XmlAttribute(name = "docklimit")
    protected String docklimit;

    /**
     * Gets the value of the crate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QuotaCrate }
     * 
     * 
     */
    public List<QuotaCrate> getCrate() {
        if (crate == null) {
            crate = new ArrayList<QuotaCrate>();
        }
        return this.crate;
    }

    /**
     * Gets the value of the docklimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocklimit() {
        return docklimit;
    }

    /**
     * Sets the value of the docklimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocklimit(String value) {
        this.docklimit = value;
    }

}
