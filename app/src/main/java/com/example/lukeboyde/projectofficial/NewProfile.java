package com.example.lukeboyde.projectofficial;

/**
 * Created by lukeboyde on 03/05/2018.
 */

public class NewProfile {

    private String firstname;
    private String lastname;

    public NewProfile(String firstname, String lastname) {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "NewProfile{" +
                "firstname:='" + firstname + '\'' +
                ", lastname:='" + lastname + '\'' +
                '}';
    }
}
