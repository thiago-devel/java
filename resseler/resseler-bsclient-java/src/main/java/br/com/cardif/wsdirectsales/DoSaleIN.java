
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de doSaleIN complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="doSaleIN"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="doSaleRequest" type="{http://wsdirectsales.cardif.com.br/}doSaleRequest" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doSaleIN", propOrder = { "doSaleRequest" })
public class DoSaleIN {

    protected DoSaleRequest doSaleRequest;

    /**
     * Obt�m o valor da propriedade doSaleRequest.
     * 
     * @return possible object is {@link DoSaleRequest }
     * 
     */
    public DoSaleRequest getDoSaleRequest() {
        return doSaleRequest;
    }

    /**
     * Define o valor da propriedade doSaleRequest.
     * 
     * @param value
     *            allowed object is {@link DoSaleRequest }
     * 
     */
    public void setDoSaleRequest(DoSaleRequest value) {
        this.doSaleRequest = value;
    }

}
