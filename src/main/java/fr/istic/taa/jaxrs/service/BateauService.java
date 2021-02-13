package fr.istic.taa.jaxrs.service;

import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.dao.BateauDao;
import fr.istic.taa.jaxrs.dao.SponsorDAO;
import fr.istic.taa.jaxrs.domain.Bateau;
import fr.istic.taa.jaxrs.dto.BateauDTO;

public class BateauService {
	
	
	BateauDao dao = new BateauDao();
	SponsorDAO daosponsor;
	
	public List<BateauDTO> getBateauByName(String name){
		
		List<Bateau> res =  dao.getBateauByName(name);
		List<BateauDTO> res1 = new ArrayList<BateauDTO>();
		res.forEach(b-> res1.add(new BateauDTO(b.getNom(),b.getTaille(), 
				b.getSkipper().getFirstname() + " " +b.getSkipper().getLastname())) );
		return res1;
		
		
	}
	
	public BateauDTO createBateau(BateauDTO b){
		Bateau b1 = new Bateau();
				b1.setNom(b.getNom());
				b1.setTaille(b.getTaille());

				dao.save(b1);
				
				
				return b;
	
	
	}

}
