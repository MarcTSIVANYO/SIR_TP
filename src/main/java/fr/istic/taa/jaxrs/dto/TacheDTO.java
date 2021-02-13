package fr.istic.taa.jaxrs.dto;

import java.util.Date;
import java.util.Objects;

public class TacheDTO {


    long idListTache;
    String intitule;
    Date dateEcheance;

    public TacheDTO() {
    }

    public TacheDTO(long idListTache, String intitule, Date dateEcheance) {
        this.idListTache = idListTache;
        this.intitule = intitule;
        this.dateEcheance = dateEcheance;
    }

    public long getIdListTache() {
        return this.idListTache;
    }

    public void setIdListTache(long idListTache) {
        this.idListTache = idListTache;
    }

    public String getIntitule() {
        return this.intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Date getDateEcheance() {
        return this.dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public TacheDTO idListTache(long idListTache) {
        this.idListTache = idListTache;
        return this;
    }

    public TacheDTO intitule(String intitule) {
        this.intitule = intitule;
        return this;
    }

    public TacheDTO dateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TacheDTO)) {
            return false;
        }
        TacheDTO tacheDTO = (TacheDTO) o;
        return idListTache == tacheDTO.idListTache && Objects.equals(intitule, tacheDTO.intitule) && Objects.equals(dateEcheance, tacheDTO.dateEcheance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idListTache, intitule, dateEcheance);
    }

    @Override
    public String toString() {
        return "{" +
            " idListTache='" + getIdListTache() + "'" +
            ", intitule='" + getIntitule() + "'" +
            ", dateEcheance='" + getDateEcheance() + "'" +
            "}";
    }
}