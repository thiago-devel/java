package br.com.rubyit.resseler.core.commons.dto;

import br.com.rubyit.resseler.commons.kernel.core.Identification;
import br.com.rubyit.resseler.commons.kernel.dto.GenericDTO;

/**
 * Class Role
 * @author b11527
 *
 */
public class Role extends Identification implements GenericDTO {

    protected String roleType;

    /**
     * the roleType
     * @return the roleType
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * the roleType to set
     * @param roleType 
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

}
