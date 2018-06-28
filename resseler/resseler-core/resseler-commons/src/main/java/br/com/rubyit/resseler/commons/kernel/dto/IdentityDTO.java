package br.com.rubyit.resseler.commons.kernel.dto;

import br.com.rubyit.resseler.commons.kernel.enums.Document;

public class IdentityDTO {

    private Document documentType;
    private String documentValue;

    public Document getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Document documentType) {
        this.documentType = documentType;
    }

    public String getDocumentValue() {
        return documentValue;
    }

    public void setDocumentValue(String documentValue) {
        this.documentValue = documentValue;
    }
}
