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
			createKanbans();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
 
		//manager.close();
		//EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
	} 
	
	
	private static void createKanbans() {
		int numOfKanbans = manager.createQuery("Select a From Section a", Section.class).getResultList().size();
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
		manager.persist(sectionAttente); 
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
	}
}
