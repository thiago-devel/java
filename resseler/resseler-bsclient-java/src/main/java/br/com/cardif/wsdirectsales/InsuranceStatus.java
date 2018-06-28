
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de insuranceStatus.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="insuranceStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ACTIVE"/&gt;
 *     &lt;enumeration value="PENDING"/&gt;
 *     &lt;enumeration value="CANCELLED"/&gt;
 *     &lt;enumeration value="REFUSED"/&gt;
 *     &lt;enumeration value="PROPOSAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "insuranceStatus")
@XmlEnum
public enum InsuranceStatus {

    ACTIVE, PENDING, CANCELLED, REFUSED, PROPOSAL;

    public String value() {
        return name();
    }

    public static InsuranceStatus fromValue(String v) {
        return valueOf(v);
    }

}
