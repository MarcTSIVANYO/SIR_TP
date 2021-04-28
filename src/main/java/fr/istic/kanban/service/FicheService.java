package fr.istic.kanban.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import fr.istic.kanban.dao.FicheDao;
import fr.istic.kanban.dao.SectionDao;
import fr.istic.kanban.dao.TagDao;
import fr.istic.kanban.dto.FicheDto;
import fr.istic.kanban.dto.TagDto;
import fr.istic.kanban.dto.UserDto;
import fr.istic.kanban.entity.Fiche;
import fr.istic.kanban.entity.Section;
import fr.istic.kanban.entity.Tag;
import fr.istic.kanban.entity.User;
import fr.istic.kanban.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FicheService {

	private final FicheDao ficheDao = new FicheDao();
	private final TagDao tagDao = new TagDao();
	private final SectionDao sectionDao = new SectionDao();
	private static Logger LOGGER = LoggerFactory.getLogger(FicheService.class);

	/*
	 * Enregistrer une entitï¿½
	 */
	public void save(FicheDto ficheDto) {

		List<Tag> tags = new ArrayList<>();
		List<TagDto> tagsDto = ficheDto.getTags();
		tagsDto.forEach(tagDto -> tags.add(tagDao.findOne(tagDto.getId())));
		Fiche fiche = new Fiche(ficheDto.getLibelle(), ficheDto.getLieu(), ficheDto.getUrl(), ficheDto.getDateButoire(),
				ficheDto.getUrl(), new User(ficheDto.getOwner().getEmail(),ficheDto.getOwner().getName() ), sectionDao.findOne(ficheDto.getSectionId()),
				tags);
		ficheDao.save(fiche);
	}

	/*
	 * Recupï¿½rer la liste de l'entitï¿½
	 */
	public List<FicheDto> findAll() {
		List<Fiche> fiches = ficheDao.findAll();
		List<FicheDto> fichesDto = new ArrayList<>();
		for (Fiche fiche : fiches) {
			FicheDto ficheDto=new FicheDto(fiche.getId(), fiche.getLibelle(), fiche.getLieu(), fiche.getUrl(),
					fiche.getDateButoire(), fiche.getNote(), fiche.getSection().getId(),
					new UserDto(fiche.getOwner().getEmail(), fiche.getOwner().getName()));
			List<TagDto> tagsDto = new ArrayList<>();
			fiche.getTags().forEach(tag -> tagsDto.add(new TagDto(tag.getId(), tag.getName())));
			ficheDto.setTags(tagsDto);
			fichesDto.add(ficheDto);
		}
		return fichesDto;
	}

	/*
	 * Recupï¿½rer une valeur ï¿½ partir d'un id NotFundException
	 */
	public FicheDto getById(Long id) {
		LOGGER.info("Recuperation d'une fiche par son ID " + id);
		CustomException.isValid(id);
		FicheDto ficheDto;
		try {
			Fiche fiche = ficheDao.findOne(id);
			if (fiche == null) {
				throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant " + id);
			}
			ficheDto = new FicheDto(fiche.getId(), fiche.getLibelle(), fiche.getLieu(), fiche.getUrl(),
					fiche.getDateButoire(), fiche.getNote(), fiche.getSection().getId(), new UserDto(fiche.getOwner().getEmail(), fiche.getOwner().getName()));

			List<TagDto> tagsDto = new ArrayList<>();
			fiche.getTags().forEach(tag -> tagsDto.add(new TagDto(tag.getId(), tag.getName())));
			ficheDto.setTags(tagsDto);
			
		} catch (Exception e) {
			LOGGER.error("Error : " + e.getMessage());
			throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant " + id);
		}
		return ficheDto;
	}

	/*
	 * Recupï¿½rer une valeur ï¿½ partir d'un id NotFundException if id is'nt valid
	 * or not found
	 */
	public FicheDto update(Long id, FicheDto ficheDto) {
		LOGGER.info("Update de la fiche avec l'ID" + id);
		CustomException.isValid(id);
		try {
			Fiche fiche = ficheDao.findOne(id);
			if (fiche == null) {
				throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant " + id);
			}

			List<Tag> tags = new ArrayList<>();
			fiche.setLibelle(ficheDto.getLibelle());
			fiche.setLieu(ficheDto.getLieu());
			fiche.setNote(ficheDto.getNote());
			fiche.setUrl(ficheDto.getUrl());
			fiche.setDateButoire(ficheDto.getDateButoire());
			fiche.setDureeminute(ficheDto.getDureeminite());
			fiche.setOwner(ficheDto.getOwner().convertToEntity());

			List<TagDto> tagsDto = ficheDto.getTags();
			tagsDto.forEach(tagDto -> tags.add(tagDao.findOne(tagDto.getId())));

			fiche.setTags(tags);
			ficheDto.setId(id);
		} catch (Exception e) {
			LOGGER.error("Error : " + e.getMessage());
			throw new NotFoundException("" + e.getMessage());
		}
		return ficheDto;
	}

	/*
	 * Supprimer une entitï¿½ NotFundException if id is'nt valid or not found
	 */
	public void deleteById(Long id) {
		CustomException.isValid(id);
		ficheDao.deleteById(id);
	}

	/*
	 * Récupérer la liste des tags à partir d'un id de fiche
	 */
	public List<TagDto> getTagsByFicheId(Long id){
		CustomException.isValid(id);  
		List<TagDto> tagsDto = new ArrayList<>(); 
		try {
			Fiche fiche=ficheDao.findOne(id);
			if(fiche==null) { 
				throw new NotFoundException("Aucun resultat pour l'ï¿½lement avec l'identifiant "+id);
			}
			if(fiche.getTags()==null) {
				return tagsDto;
			}
			
			List<Tag> tags = fiche.getTags(); 
			for(Tag tag : tags) {
				tagsDto.add(new TagDto(tag.getId(), tag.getName()));
			}
			return tagsDto;  
        }catch (Exception e){
            LOGGER.error("Error : " +e.getMessage());
			throw new NotFoundException(""+e.getMessage());
        }  
	}
}
