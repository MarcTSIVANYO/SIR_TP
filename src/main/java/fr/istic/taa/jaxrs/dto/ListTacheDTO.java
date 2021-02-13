package fr.istic.taa.jaxrs.dto;

import java.util.Objects;

public class ListTacheDTO {

    String iduser;
    String nom;

    public ListTacheDTO() {
    }

    public ListTacheDTO(String iduser, String nom) {
        this.iduser = iduser;
        this.nom = nom;
    }

    public String getIduser() {
        return this.iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ListTacheDTO iduser(String iduser) {
        this.iduser = iduser;
        return this;
    }

    public ListTacheDTO nom(String nom) {
        this.nom = nom;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ListTacheDTO)) {
            return false;
        }
        ListTacheDTO listTacheDTO = (ListTacheDTO) o;
        return Objects.equals(iduser, listTacheDTO.iduser) && Objects.equals(nom, listTacheDTO.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, nom);
    }

    @Override
    public String toString() {
        return "{" +
            " iduser='" + getIduser() + "'" +
            ", nom='" + getNom() + "'" +
            "}";
    }

   

}