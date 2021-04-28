package fr.istic.kanban.service;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;  
import fr.istic.kanban.dao.SectionDao; 
import fr.istic.kanban.dto.SectionDto;
import fr.istic.kanban.dto.TagDto;
import fr.istic.kanban.dto.UserDto;
import fr.istic.kanban.entity.EnAttente;
import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.Kanban;
import fr.istic.kanban.dto.FicheDto;
import fr.istic.kanban.dto.KanbanDto;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SectionService {
	private final SectionDao sectionDao = new SectionDao();
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);
	
	/*
	 * Enregistrer une entitï¿½
	 */
	public void save(SectionDto sectionDto) { 
		//Section section=new Section(sectionDto.getLibelle(), sectionDto.getPosition(), sectionDto.getKanban());
		EnAttente section = new EnAttente();
		section.setLibelle(sectionDto.getLibelle());
		section.setPosition(sectionDto.getPosition());
		section.setKanban(sectionDto.getKanban().convertToEntity());
		//section.setFiches(new ArrayList<>());

       sectionDao.save(section);
   }	
	
	/*
	 * Recupï¿½rer la liste de l'entitï¿½
	 */
	public List<SectionDto> findAll() { 
       List<Section> sections=sectionDao.findAll(); 
		List<SectionDto> sectionsDto = new ArrayList<>();  
		for(Section section : sections) {
			KanbanDto kanbanDto=new KanbanDto(section.getKanban());
			sectionsDto.add(new SectionDto(section.getId(),section.getLibelle(),section.getPosition(), kanbanDto ));
		}
		return sectionsDto; 
   }	 
	/*
	 * Récupérer la liste des fiches à partir d'un id de section
	 */
	public List<FicheDto> getFichesBySectionId(Long id){
		CustomException.isValid(id);  

		List<FicheDto> fichesDto = new ArrayList<>(); 
		try {
			Section section=sectionDao.findOne(id);
			if(section==null) { 
				throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant "+id);
			}
			if(section.getFiches()==null) {
				return fichesDto;
			}
			
			List<Fiche> fiches = section.getFiches(); 
			for(Fiche fiche : fiches) {
				FicheDto ficheDto=new FicheDto(fiche.getId(), fiche.getLibelle(), fiche.getLieu(), fiche.getUrl(),
						fiche.getDateButoire(), fiche.getUrl(), fiche.getSection().getId(),
						new UserDto(fiche.getOwner().getEmail(), fiche.getOwner().getName()));
				
				
				List<TagDto> tagsDto = new ArrayList<>();
				fiche.getTags().forEach(tag -> tagsDto.add(new TagDto(tag.getId(), tag.getName())));
				ficheDto.setTags(tagsDto);
				fichesDto.add(ficheDto);
			}
			return fichesDto;  
        }catch (Exception e){
            LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        }  
	}
	/*
	 * Recupï¿½rer une valeur ï¿½ partir d'un id
	 * NotFundException
	 */
	public SectionDto getById(Long id) {
		CustomException.isValid(id); 
		SectionDto sectionDto; 
		try {
			Section section=sectionDao.findOne(id);
			if(section==null) { 
				throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant "+id);
			}
			sectionDto=new SectionDto(section.getId(),section.getLibelle(),section.getPosition(),new KanbanDto(section.getKanban()));
       }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant "+id);
       } 
		return sectionDto; 
	}

	/*
	 * Recupï¿½rer une valeur ï¿½ partir d'un id
	 * NotFundException if id is'nt valid or not found 
	 */
	public SectionDto update(Long id, SectionDto sectionDto) {
		CustomException.isValid(id);  
		try {
			Section section=sectionDao.findOne(id);
			if(section==null) { 
				throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant "+id);
			}
			section.setLibelle(sectionDto.getLibelle());
			section.setPosition(sectionDto.getPosition());
			section.setKanban(sectionDto.getKanban().convertToEntity());
			sectionDao.update(section);
			sectionDto.setId(id);
       }catch (Exception e){
			LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
       } 
		return sectionDto; 
	}
	
	/*
	 * Supprimer une entitï¿½
	 * NotFundException if id is'nt valid or not found 
	 */
	public void deleteById(Long id) {
		CustomException.isValid(id);  
		sectionDao.deleteById(id);
	}
	
}
