package fr.istic.kanban.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "EnAttente")
public class EnAttente extends Section implements Serializable {

    public EnAttente(){
        super();
    }
}
