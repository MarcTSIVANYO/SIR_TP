package fr.istic.taa.jaxrs.run;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.istic.taa.jaxrs.dao.EntityManagerHelper;
import fr.istic.taa.jaxrs.domain.Bateau;
import fr.istic.taa.jaxrs.domain.PersonnePhysique;
import fr.istic.taa.jaxrs.domain.Sponsor;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			
			//Personne p = new Personne();
			
			Sponsor s = new Sponsor();
			s.setName("Hugo Boss");
			
			PersonnePhysique o = new PersonnePhysique();
			o.setFirstname("Olivier");
			o.setLastname("Barais");
			EntityManagerHelper.getEntityManager().persist(s);
			EntityManagerHelper.getEntityManager().persist(o);
			
			Bateau b = new Bateau();
			b.setNom("Hugo");
			
			EntityManagerHelper.getEntityManager().persist(b);
			b.setSkipper(o);
			

			
			//"select * from Personne where dtype='PeronnePhysique' and firstname = 'Olivier'
		
	//		"select * from PersonnePhysique firstname = 'Olivier' 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		String q  = "select p from PersonnePhysique as p where p.firstname = 'Olivier'";
		List<PersonnePhysique> res=   EntityManagerHelper.getEntityManager().
				createQuery(q, PersonnePhysique.class ).getResultList();

		//select personneph0_.id as id2_6_, personneph0_.mail as mail3_6_, personneph0_.firstname as firstnam5_6_, personneph0_.lastname as lastname6_6_ from Personne personneph0_ where personneph0_.DTYPE='PersonnePhysique' and personneph0_.firstname='Olivier
		
		System.err.println(res.size());
		manager.close();
	}

}
