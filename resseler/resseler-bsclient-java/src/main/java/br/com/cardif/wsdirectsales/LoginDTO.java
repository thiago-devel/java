
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de loginDTO complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="loginDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}identification"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loginDTO", propOrder = { "password", "username" })
public class LoginDTO extends Identification {

    protected String password;
    protected String username;

    /**
     * Obt�m o valor da propriedade password.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define o valor da propriedade password.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Obt�m o valor da propriedade username.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getUsername() {
        return username;
    }

    /**
     * Define o valor da propriedade username.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setUsername(String value) {
        this.username = value;
    }

}
