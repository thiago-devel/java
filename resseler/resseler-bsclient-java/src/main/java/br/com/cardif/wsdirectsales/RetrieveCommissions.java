
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCommissions complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCommissions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="retrieveCommissionsRequest" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="salesman" type="{http://wsdirectsales.cardif.com.br/}salesman" minOccurs="0"/&gt;
 *                   &lt;element name="operator" type="{http://wsdirectsales.cardif.com.br/}operator" minOccurs="0"/&gt;
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
@XmlType(name = "retrieveCommissions", propOrder = { "retrieveCommissionsRequest" })
public class RetrieveCommissions {

    protected RetrieveCommissions.RetrieveCommissionsRequest retrieveCommissionsRequest;

    /**
     * Obt�m o valor da propriedade retrieveCommissionsRequest.
     * 
     * @return possible object is
     *         {@link RetrieveCommissions.RetrieveCommissionsRequest }
     * 
     */
    public RetrieveCommissions.RetrieveCommissionsRequest getRetrieveCommissionsRequest() {
        return retrieveCommissionsRequest;
    }

    /**
     * Define o valor da propriedade retrieveCommissionsRequest.
     * 
     * @param value
     *            allowed object is
     *            {@link RetrieveCommissions.RetrieveCommissionsRequest }
     * 
     */
    public void setRetrieveCommissionsRequest(RetrieveCommissions.RetrieveCommissionsRequest value) {
        this.retrieveCommissionsRequest = value;
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
     *         &lt;element name="salesman" type="{http://wsdirectsales.cardif.com.br/}salesman" minOccurs="0"/&gt;
     *         &lt;element name="operator" type="{http://wsdirectsales.cardif.com.br/}operator" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "salesman", "operator" })
    public static class RetrieveCommissionsRequest {

        protected Salesman salesman;
        protected Operator operator;

        /**
         * Obt�m o valor da propriedade salesman.
         * 
         * @return possible object is {@link Salesman }
         * 
         */
        public Salesman getSalesman() {
            return salesman;
        }

        /**
         * Define o valor da propriedade salesman.
         * 
         * @param value
         *            allowed object is {@link Salesman }
         * 
         */
        public void setSalesman(Salesman value) {
            this.salesman = value;
        }

        /**
         * Obt�m o valor da propriedade operator.
         * 
         * @return possible object is {@link Operator }
         * 
         */
        public Operator getOperator() {
            return operator;
        }

        /**
         * Define o valor da propriedade operator.
         * 
         * @param value
         *            allowed object is {@link Operator }
         * 
         */
        public void setOperator(Operator value) {
            this.operator = value;
        }

    }

}
