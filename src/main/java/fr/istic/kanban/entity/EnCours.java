package fr.istic.kanban.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "EnCours")
public class EnCours extends Section implements Serializable {

    public EnCours(){
        super();
    }
}
