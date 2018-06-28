package br.com.rubyit.resseler.core.commons.dto;

import java.util.List;

/**
 * Class ScorePagingDTO
 * @author b11527
 *
 */
public class ScorePagingDTO {

    protected final List<SalesmanScore> scores;
    protected final Integer pageCount;

    /**
     * Construct with params
     * @param scores
     * @param pageCount
     */
    public ScorePagingDTO(final List<SalesmanScore> scores,
            final int pageCount) {
        this.scores = scores;
        this.pageCount = pageCount;
    }

    /**
     * the scores
     * @return the scores
     */
    public List<SalesmanScore> getScores() {
        return scores;
    }

    /**
     * the pageCount
     * @return the pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

}
