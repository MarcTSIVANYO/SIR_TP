package fr.istic.kanban.service;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;  
import fr.istic.kanban.dao.SectionDao; 
import fr.istic.kanban.dto.SectionDto; 
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.exceptions.CustomException; 

public class SectionService {
	private final SectionDao sectionDao = new SectionDao(); 
	
	/*
	 * Enregistrer une entité
	 */
	public void save(SectionDto sectionDto) { 
		Section section=new Section(sectionDto.getLibelle(), sectionDto.getPosition(), sectionDto.getKanban());
       sectionDao.save(section);
   }	
	
	/*
	 * Recupérer la liste de l'entité
	 */
	public List<SectionDto> findAll() { 
       List<Section> sections=sectionDao.findAll(); 
		List<SectionDto> sectionsDto = new ArrayList<>(); 
		sections.forEach(section-> sectionsDto.add(new SectionDto(section.getId(),section.getLibelle(),section.getPosition(),section.getKanban())) );
		return sectionsDto; 
   }	 

	/*
	 * Recupérer une valeur à partir d'un id
	 * NotFundException
	 */
	public SectionDto getById(Long id) {
		CustomException.isValid(id); 
		SectionDto sectionDto; 
		try {
			Section section=sectionDao.findOne(id);
			if(section==null) { 
				throw new NotFoundException("Aucun resultat pour l'élement avec l'identifiant "+id);
			}
			sectionDto=new SectionDto(section.getId(),section.getLibelle(),section.getPosition(),section.getKanban());
       }catch (Exception e){
           System.err.println("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'élement avec l'identifiant "+id);
       } 
		return sectionDto; 
	}

	/*
	 * Recupérer une valeur à partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public SectionDto update(Long id, SectionDto sectionDto) {
		CustomException.isValid(id);  
		try {
			Section section=sectionDao.findOne(id);
			if(section==null) { 
				throw new NotFoundException("Aucun resultat pour l'élement avec l'identifiant "+id);
			}
			section.setLibelle(sectionDto.getLibelle());
			section.setPosition(sectionDto.getPosition());
			section.setKanban(sectionDto.getKanban());
			sectionDao.update(section);
			sectionDto.setId(id);
       }catch (Exception e){
           System.err.println("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
       } 
		return sectionDto; 
	}
	
	/*
	 * Supprimer une entité
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
		CustomException.isValid(id);  
		sectionDao.deleteById(id);
	}
	
}
