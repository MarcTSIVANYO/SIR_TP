package jpa;

import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TestN1Select {

    private static Logger LOGGER = LoggerFactory.getLogger(TestN1Select.class);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();

        TypedQuery<Section> q = manager.createQuery("select s from Section s", Section.class);
        long start = System.currentTimeMillis();
        List<Section> resultList = q.getResultList();

        for (Section section : resultList) {
            for (Fiche fiche : section.getFiches()) {
                LOGGER.info("Test N+1 Select "+fiche.toString());
            }
        }

        long end = System.currentTimeMillis();
        long duree = end - start;
        LOGGER.info("Fin de l'execution. Le temps d'execution de la requete = " +  duree);

    }
}
