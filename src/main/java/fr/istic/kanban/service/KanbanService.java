package fr.istic.kanban.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException; 
import fr.istic.kanban.dao.KanbanDao;
import fr.istic.kanban.dao.SectionDao;
import fr.istic.kanban.dto.KanbanDto;
import fr.istic.kanban.dto.SectionDto;
import fr.istic.kanban.entity.*;
import fr.istic.kanban.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KanbanService {
	private final KanbanDao kanbanDao = new KanbanDao();
	private final SectionDao sectionDao = new SectionDao();
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);
	
	/*
	 * Enregistrer une entit�
	 */
	public void save(KanbanDto kanbanDto) {
		Kanban kanban;
		if(kanbanDto.getAdmin().getId() == 0) {

			kanban = new Kanban(kanbanDto.getNom(), kanbanDto.getAdmin().convertToEntity());
		}
		else {
			kanban = new Kanban();
			kanban.setNom(kanbanDto.getNom());
			kanban.setAdmin(kanbanDto.getAdmin().convertToEntity());
		}
		// on creer automatiquement les 3 sections pour le nouveau tableau kanban
		EnAttente sectionEnAttente = new EnAttente();
		sectionEnAttente.setLibelle("En attente");
		sectionEnAttente.setPosition(1);

		Execute sectionExecute = new Execute();
		sectionExecute.setLibelle("Execute");
		sectionExecute.setPosition(3);

		EnCours sectionEnCours = new EnCours();
		sectionEnCours.setLibelle("En cours");
		sectionEnCours.setPosition(2);

		List<Section> sections = new ArrayList<>();
		sections.add(sectionEnAttente);
		sections.add(sectionEnCours);
		sections.add(sectionExecute);

		sectionEnAttente.setKanban(kanban);
		sectionEnCours.setKanban(kanban);
		sectionExecute.setKanban(kanban);

		sectionDao.save(sectionEnAttente);
		sectionDao.save(sectionEnCours);
		sectionDao.save(sectionExecute);

		kanban.setSections(sections);
		kanbanDao.save(kanban);


    }	
	
	/*
	 * Recup�rer la liste de l'entit�
	 */ 
	public List<KanbanDto> findAll() {    
		LOGGER.info("Recuperation des tableaux Kanban");
       // List<Kanban> kanbans=kanbanDao.findAll();
        List<Kanban> kanbans=kanbanDao.getAllKanban();
		List<KanbanDto> kanbansDto = new ArrayList<>();
		kanbans.forEach(kanban-> kanbansDto.add(new KanbanDto(kanban))); 

		return kanbansDto; 
    }	 

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException
	 */
	public KanbanDto getById(Long id) {
		LOGGER.info("Recuperation du tableau Kanban avec ID = " +id);

		CustomException.isValid(id); 
		KanbanDto kanbanDto; 
		try {
			Kanban kanban=kanbanDao.findOne(id);
			if(kanban==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			kanbanDto=new KanbanDto(kanban);
        }catch (Exception e){
            LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
        } 
		return kanbanDto; 
	}

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public KanbanDto update(Long id, KanbanDto kanbanDto) {
		LOGGER.info("Update du avec Teableau Kanban dont l'ID = "+id);
		CustomException.isValid(id);  
		try {
			Kanban kanban=kanbanDao.findOne(id);
			if(kanban==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			kanban.setNom(kanbanDto.getNom());
			kanban.setAdmin(kanbanDto.getAdmin().convertToEntity());
			kanbanDao.update(kanban);
			kanbanDto.setId(id);
        }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		return kanbanDto; 
	}
	
	/*
	 * Supprimer une entit�
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
		LOGGER.info("Suppression du tableau kanban avec l'ID = "+id);
		CustomException.isValid(id);  
		kanbanDao.deleteById(id);
	}
	
	public List<SectionDto> getSectionById(Long id){
		CustomException.isValid(id);  

		List<SectionDto> sectionsDto = new ArrayList<>(); 
		try {
			Kanban kanban=kanbanDao.findOne(id);
			if(kanban==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			if(kanban.getSections()==null) {
				return sectionsDto;
			}
			for(Section section : kanban.getSections()) {
				KanbanDto kanbanDto=new KanbanDto(kanban);
				sectionsDto.add(new SectionDto(section.getId(),section.getLibelle(),section.getPosition(), kanbanDto ));
			}
			return sectionsDto;  
        }catch (Exception e){
            LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		
	}
	
}
