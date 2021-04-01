package fr.istic.kanban.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException; 
import fr.istic.kanban.dao.FicheDao;
import fr.istic.kanban.dto.FicheDto;
import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FicheService {
	
	private final FicheDao ficheDao = new FicheDao();
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);
	
	/*
	 * Enregistrer une entit�
	 */
	public void save(FicheDto ficheDto) { 
		Fiche fiche=new Fiche(ficheDto.getLibelle(),ficheDto.getLieu(), ficheDto.getUrl(), ficheDto.getDateButoire(), ficheDto.getUrl(), ficheDto.getOwner(),ficheDto.getSection(),ficheDto.getTags());
		ficheDao.save(fiche);
    }	

	/*
	 * Recup�rer la liste de l'entit�
	 */
	public List<FicheDto> findAll() { 
        List<Fiche> fiches=ficheDao.getAllFiches();
		List<FicheDto> fichesDto = new ArrayList<>(); 
		fiches.forEach(fiche-> fichesDto.add(new FicheDto(fiche.getId(),fiche.getLibelle(),fiche.getLieu(), fiche.getUrl(), fiche.getDateButoire(), fiche.getUrl(), fiche.getOwner(),null,null)) );
		return fichesDto; 
    }	 

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException
	 */
	public FicheDto getById(Long id) {
		LOGGER.info("Recuperation d'une fiche par son ID "+id);
		CustomException.isValid(id); 
		FicheDto ficheDto; 
		try {
			Fiche fiche=ficheDao.findOne(id);
			if(fiche==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			ficheDto=new FicheDto(fiche.getId(),fiche.getLibelle(),fiche.getLieu(), fiche.getUrl(), fiche.getDateButoire(), fiche.getUrl(), fiche.getOwner(),fiche.getSection());
	    }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
	    } 
		return ficheDto; 
	}
	
	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public FicheDto update(Long id, FicheDto ficheDto) {
		LOGGER.info("Update de la fiche avec l'ID" +id);
		CustomException.isValid(id);  
		try {
			Fiche fiche=ficheDao.findOne(id);
			if(fiche==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			fiche.setLibelle(ficheDto.getLibelle());
			fiche.setLieu(ficheDto.getLieu());
			fiche.setNote(ficheDto.getNote());
			fiche.setUrl(ficheDto.getUrl());
			fiche.setDateButoire(ficheDto.getDateButoire());
			fiche.setDureeminute(ficheDto.getDureeminite());
			fiche.setOwner(ficheDto.getOwner());
			fiche.setSection(ficheDto.getSection());
			fiche.setTags(ficheDto.getTags());
			ficheDto.setId(id);
        }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		return ficheDto; 
	}
	/*
	 * Supprimer une entit�
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
		CustomException.isValid(id);  
		ficheDao.deleteById(id);
	}
 
}
