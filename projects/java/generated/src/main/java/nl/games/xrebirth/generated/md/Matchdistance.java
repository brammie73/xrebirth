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
 *         Match by distance filter. Distance can be checked either to an object or to a position. The reference space, if supplied, must contain the searched objects. min and/or max must be set.
 *       
 * 
 * <p>Java class for matchdistance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchdistance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="x" type="{}expression" />
 *       &lt;attribute name="y" type="{}expression" />
 *       &lt;attribute name="z" type="{}expression" />
 *       &lt;attribute name="value" type="{}expression" />
 *       &lt;attribute name="object" type="{}expression" />
 *       &lt;attribute name="space" type="{}expression" />
 *       &lt;attribute name="min" type="{}expression" />
 *       &lt;attribute name="max" type="{}expression" />
 *       &lt;attribute name="comment" type="{}comment" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchdistance")
public class Matchdistance {

    @XmlAttribute(name = "x")
    protected String x;
    @XmlAttribute(name = "y")
    protected String y;
    @XmlAttribute(name = "z")
    protected String z;
    @XmlAttribute(name = "value")
    protected String value;
    @XmlAttribute(name = "object")
    protected String object;
    @XmlAttribute(name = "space")
    protected String space;
    @XmlAttribute(name = "min")
    protected String min;
    @XmlAttribute(name = "max")
    protected String max;
    @XmlAttribute(name = "comment")
    protected String comment;

    /**
     * Gets the value of the x property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX(String value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setY(String value) {
        this.y = value;
    }

    /**
     * Gets the value of the z property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZ() {
        return z;
    }

    /**
     * Sets the value of the z property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZ(String value) {
        this.z = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the object property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObject() {
        return object;
    }

    /**
     * Sets the value of the object property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObject(String value) {
        this.object = value;
    }

    /**
     * Gets the value of the space property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpace() {
        return space;
    }

    /**
     * Sets the value of the space property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpace(String value) {
        this.space = value;
    }

    /**
     * Gets the value of the min property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMin() {
        return min;
    }

    /**
     * Sets the value of the min property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMin(String value) {
        this.min = value;
    }

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMax(String value) {
        this.max = value;
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