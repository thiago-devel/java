
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de addressDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="addressDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="addressDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="addressPostalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="addressReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="neighborhood" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addressDTO", propOrder = { "addressDetail", "addressNumber", "addressPostalCode", "addressReference",
        "city", "neighborhood", "state" })
public class AddressDTO {

    protected String addressDetail;
    protected Integer addressNumber;
    protected String addressPostalCode;
    protected String addressReference;
    protected String city;
    protected String neighborhood;
    protected String state;

    /**
     * Obt�m o valor da propriedade addressDetail.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * Define o valor da propriedade addressDetail.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAddressDetail(String value) {
        this.addressDetail = value;
    }

    /**
     * Obt�m o valor da propriedade addressNumber.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getAddressNumber() {
        return addressNumber;
    }

    /**
     * Define o valor da propriedade addressNumber.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setAddressNumber(Integer value) {
        this.addressNumber = value;
    }

    /**
     * Obt�m o valor da propriedade addressPostalCode.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAddressPostalCode() {
        return addressPostalCode;
    }

    /**
     * Define o valor da propriedade addressPostalCode.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAddressPostalCode(String value) {
        this.addressPostalCode = value;
    }

    /**
     * Obt�m o valor da propriedade addressReference.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAddressReference() {
        return addressReference;
    }

    /**
     * Define o valor da propriedade addressReference.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAddressReference(String value) {
        this.addressReference = value;
    }

    /**
     * Obt�m o valor da propriedade city.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCity() {
        return city;
    }

    /**
     * Define o valor da propriedade city.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Obt�m o valor da propriedade neighborhood.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * Define o valor da propriedade neighborhood.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNeighborhood(String value) {
        this.neighborhood = value;
    }

    /**
     * Obt�m o valor da propriedade state.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getState() {
        return state;
    }

    /**
     * Define o valor da propriedade state.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setState(String value) {
        this.state = value;
    }

}
