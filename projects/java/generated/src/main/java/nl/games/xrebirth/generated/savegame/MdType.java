//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:51:43 PM CET 
//


package nl.games.xrebirth.generated.savegame;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mdType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mdType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="script" type="{}scriptType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="vars" type="{}varsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mdType", propOrder = {
    "script",
    "vars"
})
public class MdType {

    protected List<ScriptType> script;
    @XmlElement(required = true)
    protected VarsType vars;

    /**
     * Gets the value of the script property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the script property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScript().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ScriptType }
     * 
     * 
     */
    public List<ScriptType> getScript() {
        if (script == null) {
            script = new ArrayList<ScriptType>();
        }
        return this.script;
    }

    /**
     * Gets the value of the vars property.
     * 
     * @return
     *     possible object is
     *     {@link VarsType }
     *     
     */
    public VarsType getVars() {
        return vars;
    }

    /**
     * Sets the value of the vars property.
     * 
     * @param value
     *     allowed object is
     *     {@link VarsType }
     *     
     */
    public void setVars(VarsType value) {
        this.vars = value;
    }

}