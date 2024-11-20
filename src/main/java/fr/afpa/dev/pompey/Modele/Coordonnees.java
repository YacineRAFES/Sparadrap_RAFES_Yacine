package fr.afpa.dev.pompey.Modele;

import fr.afpa.dev.pompey.Exception.SaisieException;

import java.io.Serializable;

public class Coordonnees<T> implements Serializable {

    private int id;
    private String email;
    private String telephone;

    public Coordonnees(int id, String email, String telephone) throws SaisieException {
        setId(id);
        setEmail(email);
        setTelephone(telephone);
    }

    public Coordonnees(String email, String telephone) throws SaisieException {
        setEmail(email);
        setTelephone(telephone);
    }

    public Coordonnees(int id) throws SaisieException {
        setId(id);
    }

    public Coordonnees() {

    }

    public void setTelephone(String telephone) throws SaisieException {
        if(telephone == null && telephone.isEmpty()){
            throw new SaisieException("Le téléphone ne doit pas être vide");
        }
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) throws SaisieException {
        if(email == null && email.isEmpty()){
            throw new SaisieException("L'email ne doit pas être vide");
        }
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws SaisieException {
        if(id < 0){
            throw new SaisieException("L'id doit être supérieur à 0");
        }
        this.id = id;
    }





}
