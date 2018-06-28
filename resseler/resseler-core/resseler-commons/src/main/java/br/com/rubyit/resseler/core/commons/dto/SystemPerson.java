package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.core.Identification;
import br.com.rubyit.resseler.commons.kernel.dto.LoginDTO;
import br.com.rubyit.resseler.commons.kernel.dto.PersonDTO;

/**
 * class SystemPerson
 * 
 * @author b11527
 *
 */
public class SystemPerson extends PersonDTO {

    private final Identification ident;
    private Partner partner;
    private LoginDTO login;
    private Integer SUP;
    private Integer drtID;
    private Integer mlID;
    private String role;
    private Integer type;

    public SystemPerson() {
        ident = new Identification() {}; //usando classe anonima aqui
    }

    /**
     * the ID
     * @return the ID
     */
    public Long getID() {
        return ident.getID();
    }

    /**
     * the ID to set
     * @param ID
     *            
     */
    public void setID(final Long ID) {
        ident.setID(ID);
    }

    /**
     * the partner
     * @return the partner
     */
    public Partner getPartner() {
        return partner;
    }

    /**
     * the partner to set
     * @param partner
     *            
     */
    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    /**
     * the login
     * @return the login
     */
    public LoginDTO getLogin() {
        return login;
    }

    /**
     * the login to set
     * @param login
     *            
     */
    public void setLogin(LoginDTO login) {
        this.login = login;
    }

    /**
     * the sUP
     * @return the sUP
     */
    public Integer getSUP() {
        return SUP;
    }

    /**
     * the sUP to set
     * @param sUP
     *            
     */
    public void setSUP(Integer sUP) {
        SUP = sUP;
    }

    /**
     * the drtID
     * @return the drtID
     */
    public Integer getDrtID() {
        return drtID;
    }

    /**
     * the drtID to set
     * @param drtID
     *            
     */
    public void setDrtID(Integer drtID) {
        this.drtID = drtID;
    }

    /**
     * the mlID
     * @return the mlID
     */
    public Integer getMlID() {
        return mlID;
    }

    /**
     * the mlID to set
     * @param mlID
     *            
     */
    public void setMlID(Integer mlID) {
        this.mlID = mlID;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * the role to set
     * @param role
     *            
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * the type
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * the type to set
     * @param type
     *            
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * description to set
     * @return description to set
     */
    public String getDescription() {
        return ident.getDescription();
    }

    /**
     * description
     * @param description
     */
    public void setDescription(final String description) {
        ident.setDescription(description);
    }

    /**
     * Método toString da SystemPerson.class
     */
    @Override
    public String toString() {
        return "SystemPerson [ident=" + ident + ", partner=" + partner
                + ", login=" + login + ", SUP=" + SUP + ", drtID=" + drtID
                + ", mlID=" + mlID + ", role=" + role + ", type=" + type
                + ", getFullName()=" + getFullName() + ", getIdentity()="
                + getIdentity() + ", getContacts()=" + retrieveContacts()
                + ", getGender()=" + getGender() + ", getBirthDate()="
                + getBirthDate() + ", getMaritalStatus()=" + getMaritalStatus()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}
