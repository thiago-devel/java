package br.com.rubyit.resseler.commons.kernel.dto;

import java.util.ArrayList;
import java.util.List;

public class Contacts {

    private List<ContactDTO> contacts = null;

    public List<ContactDTO> retrieveContacts() {
        if (contacts == null) {
            contacts = new ArrayList<ContactDTO>();
        }

        return contacts;
    }
}
