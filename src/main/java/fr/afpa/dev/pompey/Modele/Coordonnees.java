package fr.afpa.dev.pompey.Modele;

import java.io.Serializable;

public class Coordonnees<T> implements Serializable {

    private int id;
    private String email;
    private String telephone;


    public Coordonnees(String email, String telephone) {
        setEmail(email);
        setTelephone(telephone);
    }

    public Coordonnees(int id) {
        setId(id);
    }

    public Coordonnees() {

    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
