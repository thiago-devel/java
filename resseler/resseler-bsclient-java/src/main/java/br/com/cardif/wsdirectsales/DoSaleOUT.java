
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de doSaleOUT complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="doSaleOUT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="doSaleResponse" type="{http://wsdirectsales.cardif.com.br/}doSaleResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doSaleOUT", propOrder = { "doSaleResponse" })
public class DoSaleOUT {

    protected DoSaleResponse doSaleResponse;

    /**
     * Obt�m o valor da propriedade doSaleResponse.
     * 
     * @return possible object is {@link DoSaleResponse }
     * 
     */
    public DoSaleResponse getDoSaleResponse() {
        return doSaleResponse;
    }

    /**
     * Define o valor da propriedade doSaleResponse.
     * 
     * @param value
     *            allowed object is {@link DoSaleResponse }
     * 
     */
    public void setDoSaleResponse(DoSaleResponse value) {
        this.doSaleResponse = value;
    }

}
