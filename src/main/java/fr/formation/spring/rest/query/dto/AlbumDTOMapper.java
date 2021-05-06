package fr.formation.spring.rest.query.dto;

import java.util.List;

import org.mapstruct.Mapper;

import fr.formation.spring.rest.query.entity.Album;

@Mapper
public interface AlbumDTOMapper {

	public AlbumDTO fromAlbumToAlbumDTO(Album a);
	
	public List<AlbumDTO> fromAlbumToAlbumDTO(List<Album> a);
	
	public List<AlbumDTO> fromAlbumToAlbumDTO(Iterable<Album> a);
	
}
