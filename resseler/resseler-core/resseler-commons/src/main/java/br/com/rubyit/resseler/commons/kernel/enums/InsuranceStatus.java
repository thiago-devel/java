package br.com.rubyit.resseler.commons.kernel.enums;

public enum InsuranceStatus {

    ACTIVE(1, "ACTIVE"), PENDING(2, "PENDING"), CANCELLED(3, "CANCELLED"), REFUSED(4, "REFUSED"), PROPOSAL(5,
            "PROPOSAL");

    private int code;
    private String description;

    private InsuranceStatus(final int code, final String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
