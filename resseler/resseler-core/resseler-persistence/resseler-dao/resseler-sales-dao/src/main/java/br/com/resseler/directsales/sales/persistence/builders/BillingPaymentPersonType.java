package br.com.resseler.directsales.sales.persistence.builders;

/**
 * Class BillingPaymentPersonType
 * @author b11527
 *
 */
public class BillingPaymentPersonType {

    private String nomeDoSegurado = null;
    private String sexo = null;
    private String email = null;
    private String cpfCnpj = null;
    private String civilState = null;

    /**
     * the nomeDoSegurado
     * @return the nomeDoSegurado
     */
    public String getNomeDoSegurado() {
        return nomeDoSegurado;
    }

    /**
     * the nomeDoSegurado to set
     * @param nomeDoSegurado 
     */
    public void setNomeDoSegurado(String nomeDoSegurado) {
        this.nomeDoSegurado = nomeDoSegurado;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * the email to set
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * the cpfCnpj
     * @return the cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * the cpfCnpj to set
     * @param cpfCnpj 
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * the civilState
     * @return the civilState
     */
    public String getCivilState() {
        return civilState;
    }

    /**
     * the civilState to set
     * @param civilState 
     */
    public void setCivilState(String civilState) {
        this.civilState = civilState;
    }

    /**
     * the sexo
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * the sexo to set
     * @param sexo 
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
