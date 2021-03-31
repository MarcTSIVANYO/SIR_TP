package fr.istic.kanban.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException; 
import fr.istic.kanban.dao.KanbanDao;
import fr.istic.kanban.dto.KanbanDto;
import fr.istic.kanban.dto.SectionDto;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.exceptions.CustomException; 

public class KanbanService {
	private final KanbanDao kanbanDao = new KanbanDao(); 
	
	/*
	 * Enregistrer une entit�
	 */
	public void save(KanbanDto kanbanDto) { 
        Kanban kanban=new Kanban(kanbanDto.getNom(), kanbanDto.getAdmin());
        kanbanDao.save(kanban);
    }	
	
	/*
	 * Recup�rer la liste de l'entit�
	 */
	public List<KanbanDto> findAll() { 

       // List<Kanban> kanbans=kanbanDao.findAll();
        List<Kanban> kanbans=kanbanDao.getAllKanban();
		List<KanbanDto> kanbansDto = new ArrayList<>();
		kanbans.forEach(kanban-> kanbansDto.add(new KanbanDto(kanban.getId(),kanban.getNom(),kanban.getAdmin())) );

		return kanbansDto; 
    }	 

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException
	 */
	public KanbanDto getById(Long id) {
		CustomException.isValid(id); 
		KanbanDto kanbanDto; 
		try {
			Kanban kanban=kanbanDao.findOne(id);
			if(kanban==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			kanbanDto=new KanbanDto(kanban);
        }catch (Exception e){
            System.err.println("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
        } 
		return kanbanDto; 
	}

	/*
	 * Recup�rer une valeur � partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public KanbanDto update(Long id, KanbanDto kanbanDto) {
		CustomException.isValid(id);  
		try {
			Kanban kanban=kanbanDao.findOne(id);
			if(kanban==null) { 
				throw new NotFoundException("Aucun resultat pour l'�lement avec l'identifiant "+id);
			}
			kanban.setNom(kanbanDto.getNom());
			kanban.setAdmin(kanbanDto.getAdmin());
			kanbanDao.update(kanban);
			kanbanDto.setId(id);
        }catch (Exception e){
            System.err.println("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		return kanbanDto; 
	}
	
	/*
	 * Supprimer une entit�
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
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
            System.err.println("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        } 
		
	}
	
}
