//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:06:21 PM CET 
//


package nl.games.xrebirth.generated.quotas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Orientation in space
 *       
 * 
 * <p>Java class for orientation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orientation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{}orientation"/>
 *       &lt;attribute name="comment" type="{}comment" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orientation")
public class Orientation {

    @XmlAttribute(name = "comment")
    protected String comment;
    @XmlAttribute(name = "orientation", required = true)
    protected Orientationlookup orientation;
    @XmlAttribute(name = "refobject")
    protected String refobject;
    @XmlAttribute(name = "refposition")
    protected String refposition;

    /**
     * Gets the value of the comment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link Orientationlookup }
     *     
     */
    public Orientationlookup getOrientation() {
        return orientation;
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Orientationlookup }
     *     
     */
    public void setOrientation(Orientationlookup value) {
        this.orientation = value;
    }

    /**
     * Gets the value of the refobject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefobject() {
        return refobject;
    }

    /**
     * Sets the value of the refobject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefobject(String value) {
        this.refobject = value;
    }

    /**
     * Gets the value of the refposition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefposition() {
        return refposition;
    }

    /**
     * Sets the value of the refposition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefposition(String value) {
        this.refposition = value;
    }

}