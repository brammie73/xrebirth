//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:06:21 PM CET 
//


package nl.games.xrebirth.generated.quotas;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Overrides the general actor weight for the specified dock population type
 *       
 * 
 * <p>Java class for quota_dock complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quota_dock">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="type" use="required" type="{}quota_docktype" />
 *       &lt;attribute name="weight" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quota_dock")
public class QuotaDock {

    @XmlAttribute(name = "type", required = true)
    protected QuotaDocktype type;
    @XmlAttribute(name = "weight", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger weight;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link QuotaDocktype }
     *     
     */
    public QuotaDocktype getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuotaDocktype }
     *     
     */
    public void setType(QuotaDocktype value) {
        this.type = value;
    }

    /**
     * Gets the value of the weight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWeight() {
        return weight;
    }

    /**
     * Sets the value of the weight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWeight(BigInteger value) {
        this.weight = value;
    }

}