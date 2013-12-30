//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:04:53 PM CET 
//


package nl.games.xrebirth.generated.libraries;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Match by view angle filter. Can be checked either to an object or to a position AND rotation. The reference space, if supplied, must contain the searched objects.
 *       
 * 
 * <p>Java class for matchview complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchview">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="x" type="{}expression" />
 *       &lt;attribute name="y" type="{}expression" />
 *       &lt;attribute name="z" type="{}expression" />
 *       &lt;attribute name="pitch" type="{}expression" />
 *       &lt;attribute name="yaw" type="{}expression" />
 *       &lt;attribute name="roll" type="{}expression" />
 *       &lt;attribute name="object" type="{}expression" />
 *       &lt;attribute name="space" type="{}expression" />
 *       &lt;attribute name="vertical" use="required" type="{}expression" />
 *       &lt;attribute name="horizontal" use="required" type="{}expression" />
 *       &lt;attribute name="comment" type="{}comment" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchview")
public class Matchview {

    @XmlAttribute(name = "x")
    protected String x;
    @XmlAttribute(name = "y")
    protected String y;
    @XmlAttribute(name = "z")
    protected String z;
    @XmlAttribute(name = "pitch")
    protected String pitch;
    @XmlAttribute(name = "yaw")
    protected String yaw;
    @XmlAttribute(name = "roll")
    protected String roll;
    @XmlAttribute(name = "object")
    protected String object;
    @XmlAttribute(name = "space")
    protected String space;
    @XmlAttribute(name = "vertical", required = true)
    protected String vertical;
    @XmlAttribute(name = "horizontal", required = true)
    protected String horizontal;
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
     * Gets the value of the pitch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPitch() {
        return pitch;
    }

    /**
     * Sets the value of the pitch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPitch(String value) {
        this.pitch = value;
    }

    /**
     * Gets the value of the yaw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYaw() {
        return yaw;
    }

    /**
     * Sets the value of the yaw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYaw(String value) {
        this.yaw = value;
    }

    /**
     * Gets the value of the roll property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoll() {
        return roll;
    }

    /**
     * Sets the value of the roll property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoll(String value) {
        this.roll = value;
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
     * Gets the value of the vertical property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVertical() {
        return vertical;
    }

    /**
     * Sets the value of the vertical property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVertical(String value) {
        this.vertical = value;
    }

    /**
     * Gets the value of the horizontal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontal() {
        return horizontal;
    }

    /**
     * Sets the value of the horizontal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontal(String value) {
        this.horizontal = value;
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
