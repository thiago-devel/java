package br.com.rubyit.resseler.core.commons.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Coverages
 * @author b11527
 *
 */
public class Coverages {
    protected List<Coverage> coverage;

    /**
     * the coverage
     * @return the coverage
     */
    public List<Coverage> getCoverage() {
        if (coverage == null) {
            coverage = new ArrayList<>();
        }

        final List<Coverage> newCoverage = coverage;

        return newCoverage;
    }

    /**
     * the coverage to set
     * @param coverage 
     */
    public void setCoverage(final List<Coverage> coverage) {
        final List<Coverage> newCoverage = new ArrayList<>(coverage);

        this.coverage = newCoverage;
    }
}
