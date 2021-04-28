package jpa;
 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.kanban.entity.*;
import fr.istic.kanban.service.FicheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaRun {

	private EntityManager manager;
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);

	public JpaRun(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory =   
 			 Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaRun test = new JpaRun(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.populateDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		//test.listEmployees();
			
   	 manager.close();
		LOGGER.info(".. done");
	}

	private void populateDatabase(){

		User userPersisted = this.persistUser();
		
		User userTwo=this.persistUser2();

		Kanban tableauKanban = new Kanban();
		tableauKanban.setNom("Cours SIR 2021");
		tableauKanban.setAdmin(userPersisted);

		EnAttente sectionEnAttente = new EnAttente();
		sectionEnAttente.setLibelle("En attente");
		sectionEnAttente.setPosition(1);

		EnCours sectionEnCours = new EnCours();
		sectionEnCours.setLibelle("En cours");
		sectionEnCours.setPosition(2);

		Execute sectionExecute = new Execute();
		sectionExecute.setLibelle("Execute");
		sectionExecute.setPosition(3);

		tableauKanban.add(sectionEnAttente);
		tableauKanban.add(sectionEnCours);
		tableauKanban.add(sectionExecute);

		//Creation des fiches
		List<Fiche> fiches = new ArrayList<>();
		Fiche fiche1 = new Fiche("JPA");
		fiche1.setOwner(userPersisted);
		fiche1.setDureeminute(50);
		fiche1.setLieu("ISTIC");
		fiche1.setNote("Prioritaire");
		fiche1.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche1.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche1);

		Fiche fiche2 = new Fiche("JaxRS et OpenAPI");
		fiche2.setOwner(userPersisted);
		fiche2.setDureeminute(45);
		fiche2.setLieu("ISTIC");
		fiche2.setNote("Prioritaire");
		fiche2.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche2.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche2);

		Fiche fiche3 = new Fiche("Front Angular");
		fiche3.setOwner(userPersisted);
		fiche3.setDureeminute(30);
		fiche3.setLieu("Distanciel");
		fiche3.setNote("Prioritaire");
		fiche3.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche3.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche3);

		Fiche fiche4 = new Fiche("Front VueJS");
		fiche4.setOwner(userPersisted);
		fiche4.setDureeminute(20);
		fiche4.setLieu("Distanciel");
		fiche4.setNote("Prioritaire");
		fiche4.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche4.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche4);

		Fiche fiche5 = new Fiche("Front React");
		fiche5.setOwner(userPersisted);
		fiche5.setDureeminute(18);
		fiche5.setLieu("Distanciel");
		fiche5.setNote("Prioritaire");
		fiche5.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche5.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche5);

		Fiche fiche6 = new Fiche("Maven");
		fiche6.setOwner(userPersisted);
		fiche6.setDureeminute(33);
		fiche6.setLieu("ISTIC");
		fiche6.setNote("Prioritaire");
		fiche6.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche6.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche6);

		Fiche fiche7 = new Fiche("Spring");
		fiche7.setLieu("ISTIC");
		fiche7.setNote("Important");
		fiche7.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche7.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiche7.setOwner(userPersisted);
		fiche7.setDureeminute(35);
		fiches.add(fiche7);

		Fiche fiche8 = new Fiche("NoSQL");
		fiche8.setOwner(userPersisted);
		fiche8.setDureeminute(34);
		fiche8.setLieu("Distanciel");
		fiche8.setNote("Prioritaire");
		fiche8.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche8.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche8);

		Fiche fiche9 = new Fiche("Docker");
		fiche9.setOwner(userPersisted);
		fiche9.setDureeminute(36);
		fiche9.setLieu("Distanciel");
		fiche9.setNote("Hors Scope");
		fiche9.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche9.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche9);

		Fiche fiche10 = new Fiche("API REST");
		fiche10.setOwner(userPersisted);
		fiche10.setDureeminute(30);
		fiche10.setLieu("ISTIC");
		fiche10.setNote("Prioritaire");
		fiche10.setUrl("https://teams.microsoft.com/_#/school/conversations/G%C3%A9n%C3%A9ral?threadId=19:3733cf5795d445b7855cafdbc1a29dfd@thread.tacv2&ctx=channel");
		fiche10.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		fiches.add(fiche10);

		//Ajout des fiches au differentes Sections
		sectionEnAttente.addFiche(fiche10);
		sectionEnCours.addFiche(fiche9);
		sectionEnCours.addFiche(fiche8);
		sectionEnCours.addFiche(fiche7);
		sectionEnCours.addFiche(fiche6);
		sectionExecute.addFiche(fiche5);
		sectionExecute.addFiche(fiche4);
		sectionExecute.addFiche(fiche3);
		sectionExecute.addFiche(fiche2);
		sectionExecute.addFiche(fiche1);

		//Creation des Tags
		Tag tagOne = new Tag();
		tagOne.setName("TP 2-4");
		tagOne.addFiche(fiche1);

		Tag tagTwo = new Tag();
		tagTwo.setName("TP5");
		tagTwo.addFiche(fiche3);

		Tag tagThree = new Tag();
		tagThree.setName("TP7");
		tagThree.addFiche(fiche3);

		manager.persist(tableauKanban);

		manager.persist(sectionEnAttente);
		manager.persist(sectionEnCours);
		manager.persist(sectionExecute);

		//Insertion des Fiches en BDD
		for (Fiche f: fiches) {
			manager.persist(f);
		}

		manager.persist(tagOne);
		manager.persist(tagTwo);
		manager.persist(tagThree);
	}

	private User persistUser(){
		User user = new User();
		user.setName("Scrum Master");
		user.setEmail("scr.master@gmail.com");
		manager.persist(user);

		return user;
	}

	private User persistUser2(){
		User user = new User();
		user.setName("Head of departement");
		user.setEmail("head-depart@gmail.com");
		manager.persist(user);

		return user;
	}
}

 