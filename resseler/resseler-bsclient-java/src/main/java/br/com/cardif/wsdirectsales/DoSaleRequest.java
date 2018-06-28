
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de doSaleRequest complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="doSaleRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sale" type="{http://wsdirectsales.cardif.com.br/}sale" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doSaleRequest", propOrder = { "sale" })
public class DoSaleRequest {

    protected Sale sale;

    /**
     * Obt�m o valor da propriedade sale.
     * 
     * @return possible object is {@link Sale }
     * 
     */
    public Sale getSale() {
        return sale;
    }

    /**
     * Define o valor da propriedade sale.
     * 
     * @param value
     *            allowed object is {@link Sale }
     * 
     */
    public void setSale(Sale value) {
        this.sale = value;
    }

}
