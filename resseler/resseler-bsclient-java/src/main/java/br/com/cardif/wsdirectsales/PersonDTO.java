
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java de personDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="personDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="contacts" type="{http://wsdirectsales.cardif.com.br/}contacts" minOccurs="0"/&gt;
 *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="gender" type="{http://wsdirectsales.cardif.com.br/}gender" minOccurs="0"/&gt;
 *         &lt;element name="identity" type="{http://wsdirectsales.cardif.com.br/}identityDTO" minOccurs="0"/&gt;
 *         &lt;element name="maritalStatus" type="{http://wsdirectsales.cardif.com.br/}maritalStatus" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "personDTO", propOrder = { "birthDate", "contacts", "fullName", "gender", "identity", "maritalStatus" })
@XmlSeeAlso({ SystemPerson.class })
public class PersonDTO {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthDate;
    protected Contacts contacts;
    protected String fullName;
    @XmlSchemaType(name = "string")
    protected Gender gender;
    protected IdentityDTO identity;
    @XmlSchemaType(name = "string")
    protected MaritalStatus maritalStatus;

    /**
     * Obt�m o valor da propriedade birthDate.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Define o valor da propriedade birthDate.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * Obt�m o valor da propriedade contacts.
     * 
     * @return possible object is {@link Contacts }
     * 
     */
    public Contacts getContacts() {
        return contacts;
    }

    /**
     * Define o valor da propriedade contacts.
     * 
     * @param value
     *            allowed object is {@link Contacts }
     * 
     */
    public void setContacts(Contacts value) {
        this.contacts = value;
    }

    /**
     * Obt�m o valor da propriedade fullName.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Define o valor da propriedade fullName.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Obt�m o valor da propriedade gender.
     * 
     * @return possible object is {@link Gender }
     * 
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Define o valor da propriedade gender.
     * 
     * @param value
     *            allowed object is {@link Gender }
     * 
     */
    public void setGender(Gender value) {
        this.gender = value;
    }

    /**
     * Obt�m o valor da propriedade identity.
     * 
     * @return possible object is {@link IdentityDTO }
     * 
     */
    public IdentityDTO getIdentity() {
        return identity;
    }

    /**
     * Define o valor da propriedade identity.
     * 
     * @param value
     *            allowed object is {@link IdentityDTO }
     * 
     */
    public void setIdentity(IdentityDTO value) {
        this.identity = value;
    }

    /**
     * Obt�m o valor da propriedade maritalStatus.
     * 
     * @return possible object is {@link MaritalStatus }
     * 
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Define o valor da propriedade maritalStatus.
     * 
     * @param value
     *            allowed object is {@link MaritalStatus }
     * 
     */
    public void setMaritalStatus(MaritalStatus value) {
        this.maritalStatus = value;
    }

}
