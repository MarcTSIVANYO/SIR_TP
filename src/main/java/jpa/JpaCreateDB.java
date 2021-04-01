package jpa;
 
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.kanban.entity.EnAttente;
import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.Tag;
import fr.istic.kanban.entity.User;
 
public class JpaCreateDB {
	/**
	 * @param args
	 */
	

	private final static  EntityManagerFactory factory =   
 			 Persistence.createEntityManagerFactory("mysql");
	private final static EntityManager manager = factory.createEntityManager();
	private final static EntityTransaction tx = manager.getTransaction();
	
	public static void run() {
		tx.begin(); 
		try { 
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
 
		//manager.close();
		//EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	} 
	
}
