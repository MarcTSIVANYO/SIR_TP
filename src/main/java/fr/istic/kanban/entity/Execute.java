package fr.istic.kanban.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "Execute")
public class Execute extends Section implements Serializable {

    public Execute(){
        super();
    }
}
