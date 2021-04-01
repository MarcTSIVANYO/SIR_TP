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

		test.listEmployees();
			
   	 manager.close();
		LOGGER.info(".. done");
	}

	private void populateDatabase(){

		User userPersisted = this.persistUser();

		Kanban tableauKanban = new Kanban();
		tableauKanban.setNom("Scrum Master");
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

		Fiche fiche1 = new Fiche("Test");
		fiche1.setOwner(userPersisted);
		Fiche fiche2 = new Fiche("Dossier");
		fiche2.setOwner(userPersisted);
		Fiche fiche3 = new Fiche("User");
		fiche3.setOwner(userPersisted);

		sectionEnAttente.addFiche(fiche1);
		sectionEnCours.addFiche(fiche2);
		sectionExecute.addFiche(fiche3);

		Tag tagOne = new Tag();
		tagOne.setName("TagOneName");
		tagOne.addFiche(fiche1);

		Tag tagTwo = new Tag();
		tagTwo.setName("TagTwotName");
		tagTwo.addFiche(fiche3);

		manager.persist(tableauKanban);

		manager.persist(sectionEnAttente);
		manager.persist(sectionEnCours);
		manager.persist(sectionExecute);

		manager.persist(fiche1);
		manager.persist(fiche2);
		manager.persist(fiche3);

		manager.persist(tagOne);
		manager.persist(tagTwo);
	}

	private User persistUser(){
		User user = new User();
		user.setName("Scrum Master");
		user.setEmail("scr.master@gmail.com");
		manager.persist(user);

		return user;
	}

	
	/*private void createKanbans() {
		int numOfKanbans = manager.createQuery("Select a From Section a", Section.class).getResultList().size();
		
		//User user = (User) manager.createQuery("Select u from User u where u.email='marctsivanyo@gmail.com'", User.class).getResultList();
		 
		User user =new User();
		user.setEmail("marctsivanyo@gmail.com");
		user.setName("Marc TSIVANYO");  
		user.setPassword("145-Kanban");  
		manager.persist(user);

		Tag coffee=new Tag("Coffee",null);
		Tag tea=new Tag("Tea",null);
		Tag web=new Tag("Web",null);
		Tag formation=new Tag("Formation",null);
		Tag business=new Tag("Businness",null); 
		manager.persist(business);
		 
			Kanban kan=new Kanban();
			kan.setNom("Google");
			kan.setAdmin(user); 
			
			Kanban kan2=new Kanban();
			kan2.setNom("Facebook"); 
			kan2.setAdmin(user); 

			manager.persist(kan);
			manager.persist(kan2);
			
			//Section sectionOne = new Section("En attente",1,kan);

			EnAttente sectionAttente = new EnAttente();
			sectionAttente.setLibelle("En attente");
			sectionAttente.setPosition(1);
			sectionAttente.setKanban(kan);
		//sectionAttente.setFiches(new ArrayList<>());

		manager.persist(sectionAttente);

			*//*Section sectionTwo =new Section();
			sectionTwo.setLibelle("En cours");
			sectionTwo.setPosition(2); 
			sectionTwo.setKanban(kan);

			Section sectionTree =new Section(); 
			sectionTree.setLibelle("R�alis�");
			sectionTree.setPosition(3); 
			sectionTwo.setKanban(kan2);  
			
			manager.persist(sectionAttente);
			manager.persist(sectionTwo);
			manager.persist(sectionTree);*//*
			 
			ArrayList<Tag> tags = new ArrayList() {{
				 add(coffee);
				 add(tea); 
				 add(formation); 
				}};
				for(Tag tag : tags) manager.persist(tag);

				Fiche fiche = new Fiche();
				fiche.setLibelle("Wait answer to request at Manager");
				fiche.setLieu("At work");
				fiche.setOwner(user);//User has to be persisted before persisting the Fiche
				fiche.setNote("Prioritaire");
				fiche.setSection(sectionAttente);
				fiche.setUrl("https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#:~:text=All%20ManyToMany%20relationships%20require%20a,object's%20primary%20key%20(%20inverseJoinColumns%20).");
				fiche.setDateButoire(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				fiche.setTags(tags);//Here the problem?
				

				manager.persist(fiche);
				
				*//*Fiche thisFiche = manager.find( Fiche.class, 1L	 );
	            System.out.println( "Tags associ�s � une fiche" );
	            for( Tag associatedTag : thisFiche.getTags() ) {
	                System.out.println( associatedTag );
	            }*//*
 
	}*/

	private void listEmployees() {
		List<Kanban> resultList = manager.createQuery("Select a From Kanban a", Kanban.class).getResultList();
		System.out.println("num of Kanbans:" + resultList.size());
		for (Kanban next : resultList) {
			System.out.println("next kanban: " + next);
		}
	}
}

 