
package br.com.cardif.wsdirectsales;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de capitalSeries complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="capitalSeries"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}identification"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="capitalNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "capitalSeries", propOrder = { "capitalNumber" })
public class CapitalSeries extends Identification {

    protected BigInteger capitalNumber;

    /**
     * Obt�m o valor da propriedade capitalNumber.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCapitalNumber() {
        return capitalNumber;
    }

    /**
     * Define o valor da propriedade capitalNumber.
     * 
     * @param value
     *            allowed object is {@link BigInteger }
     * 
     */
    public void setCapitalNumber(BigInteger value) {
        this.capitalNumber = value;
    }

}
