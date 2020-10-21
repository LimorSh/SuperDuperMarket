//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.07 at 04:17:44 PM IDT 
//


package course.java.sdm.engine.jaxb.schema.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}name"/>
 *         &lt;element ref="{}delivery-ppk"/>
 *         &lt;element ref="{}location"/>
 *         &lt;element ref="{}SDM-prices"/>
 *         &lt;element ref="{}SDM-discounts" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "deliveryPpk",
    "location",
    "sdmPrices",
    "sdmDiscounts"
})
@XmlRootElement(name = "SDM-store")
public class SDMStore {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(name = "delivery-ppk")
    protected int deliveryPpk;
    @XmlElement(required = true)
    protected Location location;
    @XmlElement(name = "SDM-prices", required = true)
    protected SDMPrices sdmPrices;
    @XmlElement(name = "SDM-discounts")
    protected SDMDiscounts sdmDiscounts;
    @XmlAttribute(name = "id", required = true)
    protected int id;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the deliveryPpk property.
     * 
     */
    public int getDeliveryPpk() {
        return deliveryPpk;
    }

    /**
     * Sets the value of the deliveryPpk property.
     * 
     */
    public void setDeliveryPpk(int value) {
        this.deliveryPpk = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the sdmPrices property.
     * 
     * @return
     *     possible object is
     *     {@link SDMPrices }
     *     
     */
    public SDMPrices getSDMPrices() {
        return sdmPrices;
    }

    /**
     * Sets the value of the sdmPrices property.
     * 
     * @param value
     *     allowed object is
     *     {@link SDMPrices }
     *     
     */
    public void setSDMPrices(SDMPrices value) {
        this.sdmPrices = value;
    }

    /**
     * Gets the value of the sdmDiscounts property.
     * 
     * @return
     *     possible object is
     *     {@link SDMDiscounts }
     *     
     */
    public SDMDiscounts getSDMDiscounts() {
        return sdmDiscounts;
    }

    /**
     * Sets the value of the sdmDiscounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link SDMDiscounts }
     *     
     */
    public void setSDMDiscounts(SDMDiscounts value) {
        this.sdmDiscounts = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

}
