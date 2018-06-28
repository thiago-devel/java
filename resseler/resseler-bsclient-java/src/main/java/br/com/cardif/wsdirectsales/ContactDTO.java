
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de contactDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="contactDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{http://wsdirectsales.cardif.com.br/}addressDTO" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://wsdirectsales.cardif.com.br/}phoneDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "contactDTO", propOrder = { "address", "phone" })
public class ContactDTO {

    protected AddressDTO address;
    protected PhoneDTO phone;

    /**
     * Obt�m o valor da propriedade address.
     * 
     * @return possible object is {@link AddressDTO }
     * 
     */
    public AddressDTO getAddress() {
        return address;
    }

    /**
     * Define o valor da propriedade address.
     * 
     * @param value
     *            allowed object is {@link AddressDTO }
     * 
     */
    public void setAddress(AddressDTO value) {
        this.address = value;
    }

    /**
     * Obt�m o valor da propriedade phone.
     * 
     * @return possible object is {@link PhoneDTO }
     * 
     */
    public PhoneDTO getPhone() {
        return phone;
    }

    /**
     * Define o valor da propriedade phone.
     * 
     * @param value
     *            allowed object is {@link PhoneDTO }
     * 
     */
    public void setPhone(PhoneDTO value) {
        this.phone = value;
    }

}
