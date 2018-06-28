package br.com.rubyit.resseler.commons.kernel.dto;

import br.com.rubyit.resseler.commons.kernel.core.Identification;

public class LoginDTO extends Identification {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
