
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de operator complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="operator"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://wsdirectsales.cardif.com.br/}systemPerson"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "operator", propOrder = { "operatorName" })
@XmlSeeAlso({ Salesman.class })
public class Operator extends SystemPerson {

    protected String operatorName;

    /**
     * Obt�m o valor da propriedade operatorName.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Define o valor da propriedade operatorName.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setOperatorName(String value) {
        this.operatorName = value;
    }

}
