package fr.formation.spring.rest.query.controller;

import java.util.List;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import fr.formation.spring.rest.query.dto.AlbumDTO;
import fr.formation.spring.rest.query.dto.AlbumDTOMapper;
import fr.formation.spring.rest.query.entity.Album;
import fr.formation.spring.rest.query.repository.AlbumRepository;

@RestController
@RequestMapping("/albums")
public class AlbumController {

	private AlbumRepository albumRepository;

	private AlbumDTOMapper albumDTOMapper;

	public AlbumController(AlbumRepository albumRepository,
			AlbumDTOMapper albumDTOMapper) {
		this.albumRepository = albumRepository;
		this.albumDTOMapper = albumDTOMapper;
	}
	
	/**
	 * API permettant d'utiliser queryDSL depuis l'API
	 * 
	 * @param search 
	 * [NOM_DU_CHAMP_ENTITE]=[VALEUR_DU_CHAMP]
	 * 
	 * Exemple d'utilisation simple :
	 * nom=Anomie -> recherche des albums dont le nom est Anomie
	 * nom=Ano* -> recherche des albums dont le nom commence par Ano
	 * nom=*mie -> recherche des albums dont le nom finit par mie
	 * nom=*mie* -> recherche des albums dont le nom contient mie
	 * 
	 * Combinaison de critères de recherche :
	 * nom=*e*&genre=*bla* -> recherche des albums dont le nom contient un e et dont le genre contient bla
	 * 
	 * Combinaison de critères de recherche impossible :
	 * nom=*Ano* & nom=Wel* -> Impossible (on ne peut pas faire mettre deux critères sur le même champ
	 * pas de gestion du ou
	 * 
	 * @return les albums correspondants aux critères de recherche
	 */
	@GetMapping
	public List<AlbumDTO> getAllAlbums(
			@Nullable @QuerydslPredicate(root = Album.class) Predicate predicate) {
		
		return albumDTOMapper
				.fromAlbumToAlbumDTO(albumRepository.findAll(predicate));
	}

}
