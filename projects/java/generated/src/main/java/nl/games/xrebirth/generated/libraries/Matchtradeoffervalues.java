//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.28 at 10:04:53 PM CET 
//


package nl.games.xrebirth.generated.libraries;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for matchtradeoffervalues complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="matchtradeoffervalues">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="amount" type="{}range"/>
 *         &lt;element name="minamount" type="{}range"/>
 *         &lt;element name="totalprice" type="{}range"/>
 *         &lt;element name="minprice" type="{}range"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchtradeoffervalues", propOrder = {
    "amountOrMinamountOrTotalprice"
})
public class Matchtradeoffervalues {

    @XmlElementRefs({
        @XmlElementRef(name = "totalprice", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "amount", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "minprice", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "minamount", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<Range>> amountOrMinamountOrTotalprice;

    /**
     * Gets the value of the amountOrMinamountOrTotalprice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amountOrMinamountOrTotalprice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmountOrMinamountOrTotalprice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Range }{@code >}
     * {@link JAXBElement }{@code <}{@link Range }{@code >}
     * {@link JAXBElement }{@code <}{@link Range }{@code >}
     * {@link JAXBElement }{@code <}{@link Range }{@code >}
     * 
     * 
     */
    public List<JAXBElement<Range>> getAmountOrMinamountOrTotalprice() {
        if (amountOrMinamountOrTotalprice == null) {
            amountOrMinamountOrTotalprice = new ArrayList<JAXBElement<Range>>();
        }
        return this.amountOrMinamountOrTotalprice;
    }

}
