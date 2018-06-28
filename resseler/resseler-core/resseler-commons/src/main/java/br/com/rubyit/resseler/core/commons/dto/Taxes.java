package br.com.rubyit.resseler.core.commons.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Taxes
 * @author b11527
 *
 */
public class Taxes {

    protected List<Tax> tax;

    /**
     * the tax
     * @return the tax
     */
    public List<Tax> getTax() {
        if (tax == null) {
            tax = new ArrayList<>();
        }

        final List<Tax> newTax = tax;

        return newTax;
    }

    /**
     * the tax to set
     * @param tax 
     */
    public void setTax(final List<Tax> tax) {
        final List<Tax> newTax = new ArrayList<>(tax);

        this.tax = newTax;
    }
}
