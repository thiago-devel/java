
package br.com.cardif.wsdirectsales;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java de maritalStatus.
 * 
 * <p>
 * O seguinte fragmento do esquema especifica o conte�do esperado contido dentro
 * desta classe.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="maritalStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="MARRIED"/&gt;
 *     &lt;enumeration value="SINGLE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "maritalStatus")
@XmlEnum
public enum MaritalStatus {

    MARRIED, SINGLE;

    public String value() {
        return name();
    }

    public static MaritalStatus fromValue(String v) {
        return valueOf(v);
    }

}
