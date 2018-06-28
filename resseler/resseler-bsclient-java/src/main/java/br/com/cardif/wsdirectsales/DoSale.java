
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de doSale complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="doSale"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="doSaleRequest" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="sale" type="{http://wsdirectsales.cardif.com.br/}sale" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "doSale", propOrder = { "doSaleRequest" })
public class DoSale {

    protected DoSale.DoSaleRequest doSaleRequest;

    /**
     * Obt�m o valor da propriedade doSaleRequest.
     * 
     * @return possible object is {@link DoSale.DoSaleRequest }
     * 
     */
    public DoSale.DoSaleRequest getDoSaleRequest() {
        return doSaleRequest;
    }

    /**
     * Define o valor da propriedade doSaleRequest.
     * 
     * @param value
     *            allowed object is {@link DoSale.DoSaleRequest }
     * 
     */
    public void setDoSaleRequest(DoSale.DoSaleRequest value) {
        this.doSaleRequest = value;
    }

    /**
     * <p>
     * Classe Java de anonymous complex type.
     * 
     * <p>
     * O seguinte fragmento do esquema especifica o conte�do esperado contido
     * dentro desta classe.
     * 
     * <pre>
     * &lt;complexType&gt;
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
    @XmlType(name = "", propOrder = { "sale" })
    public static class DoSaleRequest {

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

}
