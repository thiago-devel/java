package br.com.resseler.directsales.sales.persistence.dao;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

/**
 * Class PaymentForm
 * @author b11527
 *
 */
public class PaymentForm extends Identification {

    /**
     *  toString
     *  @return String
     */
    @Override
    public String toString() {
        return "PaymentForm [getID()=" + getID() + ", getDescription()="
                + getDescription() + ", getClass()=" + getClass()
                + ", toString()=" + super.toString() + "]";
    }
}
