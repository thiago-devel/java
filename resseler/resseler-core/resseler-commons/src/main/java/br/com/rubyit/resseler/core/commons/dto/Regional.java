package br.com.rubyit.resseler.core.commons.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.rubyit.resseler.commons.kernel.core.Identification;
import br.com.rubyit.resseler.commons.kernel.dto.GenericDTO;

/**
 * Class DTO
 * Regional
 */
public class Regional extends Identification implements GenericDTO {

    private List<Workshop> workshops;

    /**
     * Constructor default
     */
    public Regional() {
        super();
    }

    /**
     * the workshops - retorna
     * ao menos um workshop instanciado
     * e vazio
     * @return the workshops 
     */
    public List<Workshop> getWorkshops() {
        if (workshops == null) {
            workshops = new ArrayList<>();
        }
        final List<Workshop> newWorkshop = workshops;
        return newWorkshop;
    }

    /**
     * the workshops to set
     * @param workshops 
     */
    public void setWorkshops(final List<Workshop> workshops) {
        final List<Workshop> newWorkshop = new ArrayList<>(workshops);
        this.workshops = newWorkshop;
    }
}
