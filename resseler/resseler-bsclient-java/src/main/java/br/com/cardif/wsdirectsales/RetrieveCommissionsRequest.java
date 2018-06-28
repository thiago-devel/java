
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de retrieveCommissionsRequest complex type.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * 
 * <pre>
 * &lt;complexType name="retrieveCommissionsRequest"&gt;
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
@XmlType(name = "retrieveCommissionsRequest", propOrder = { "salesman", "operator" })
public class RetrieveCommissionsRequest {

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
