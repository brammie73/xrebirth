//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 11:19:24 PM CET 
//


package nl.games.xrebirth.generated.md;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Match filter for a tradeoffer location
 *       
 * 
 * <p>Java class for matchtradeofferlocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchtradeofferlocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="distance" type="{}expression" />
 *       &lt;attribute name="viewangle" type="{}expression" />
 *       &lt;attribute name="wares" type="{}expression" />
 *       &lt;attribute name="any" use="required" type="{}booleanexpression" />
 *       &lt;attribute name="comment" type="{}comment" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchtradeofferlocation")
public class Matchtradeofferlocation {

    @XmlAttribute(name = "distance")
    protected String distance;
    @XmlAttribute(name = "viewangle")
    protected String viewangle;
    @XmlAttribute(name = "wares")
    protected String wares;
    @XmlAttribute(name = "any", required = true)
    protected String any;
    @XmlAttribute(name = "comment")
    protected String comment;

    /**
     * Gets the value of the distance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistance(String value) {
        this.distance = value;
    }

    /**
     * Gets the value of the viewangle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewangle() {
        return viewangle;
    }

    /**
     * Sets the value of the viewangle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewangle(String value) {
        this.viewangle = value;
    }

    /**
     * Gets the value of the wares property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWares() {
        return wares;
    }

    /**
     * Sets the value of the wares property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWares(String value) {
        this.wares = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAny(String value) {
        this.any = value;
    }

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

}
