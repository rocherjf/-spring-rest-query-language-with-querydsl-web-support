package fr.formation.spring.rest.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {

	private long id;

	private String nom;

	private String genre;

	private String description;
	private String urlImage;

}
