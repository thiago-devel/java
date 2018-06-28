package br.com.rubyit.resseler.commons.kernel.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.rubyit.resseler.commons.kernel.enums.Gender;
import br.com.rubyit.resseler.commons.kernel.enums.MaritalStatus;

public class PersonDTO {

    private String fullName;
    private IdentityDTO identity;
    private Gender gender;
    private Calendar birthDate;
    private MaritalStatus maritalStatus;
    private String nationality;
    private List<ContactDTO> contacts = null;

    public List<ContactDTO> retrieveContacts() {
        if (contacts == null) {
            contacts = new ArrayList<ContactDTO>();
        }

        return contacts;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public IdentityDTO getIdentity() {
        return identity;
    }

    public void setIdentity(final IdentityDTO identity) {
        this.identity = identity;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(final MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
