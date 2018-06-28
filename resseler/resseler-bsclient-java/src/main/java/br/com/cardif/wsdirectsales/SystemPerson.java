
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de systemPerson complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="systemPerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}personDTO"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="drtID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="login" type="{http://wsdirectsales.cardif.com.br/}loginDTO" minOccurs="0"/&gt;
 *         &lt;element name="mlID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="partner" type="{http://wsdirectsales.cardif.com.br/}partner" minOccurs="0"/&gt;
 *         &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SUP" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "systemPerson", propOrder = { "description", "drtID", "id", "login", "mlID", "partner", "role", "sup",
        "type" })
@XmlSeeAlso({ Customer.class, Operator.class })
public class SystemPerson extends PersonDTO {

    protected String description;
    protected Integer drtID;
    @XmlElement(name = "ID")
    protected Integer id;
    protected LoginDTO login;
    protected Integer mlID;
    protected Partner partner;
    protected String role;
    @XmlElement(name = "SUP")
    protected Integer sup;
    protected Integer type;

    /**
     * Obt�m o valor da propriedade description.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define o valor da propriedade description.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obt�m o valor da propriedade drtID.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getDrtID() {
        return drtID;
    }

    /**
     * Define o valor da propriedade drtID.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setDrtID(Integer value) {
        this.drtID = value;
    }

    /**
     * Obt�m o valor da propriedade id.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getID() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setID(Integer value) {
        this.id = value;
    }

    /**
     * Obt�m o valor da propriedade login.
     * 
     * @return possible object is {@link LoginDTO }
     * 
     */
    public LoginDTO getLogin() {
        return login;
    }

    /**
     * Define o valor da propriedade login.
     * 
     * @param value
     *            allowed object is {@link LoginDTO }
     * 
     */
    public void setLogin(LoginDTO value) {
        this.login = value;
    }

    /**
     * Obt�m o valor da propriedade mlID.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getMlID() {
        return mlID;
    }

    /**
     * Define o valor da propriedade mlID.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setMlID(Integer value) {
        this.mlID = value;
    }

    /**
     * Obt�m o valor da propriedade partner.
     * 
     * @return possible object is {@link Partner }
     * 
     */
    public Partner getPartner() {
        return partner;
    }

    /**
     * Define o valor da propriedade partner.
     * 
     * @param value
     *            allowed object is {@link Partner }
     * 
     */
    public void setPartner(Partner value) {
        this.partner = value;
    }

    /**
     * Obt�m o valor da propriedade role.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getRole() {
        return role;
    }

    /**
     * Define o valor da propriedade role.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Obt�m o valor da propriedade sup.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getSUP() {
        return sup;
    }

    /**
     * Define o valor da propriedade sup.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setSUP(Integer value) {
        this.sup = value;
    }

    /**
     * Obt�m o valor da propriedade type.
     * 
     * @return possible object is {@link Integer }
     * 
     */
    public Integer getType() {
        return type;
    }

    /**
     * Define o valor da propriedade type.
     * 
     * @param value
     *            allowed object is {@link Integer }
     * 
     */
    public void setType(Integer value) {
        this.type = value;
    }

}
