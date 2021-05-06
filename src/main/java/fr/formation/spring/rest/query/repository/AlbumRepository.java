package fr.formation.spring.rest.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import fr.formation.spring.rest.query.entity.Album;
import fr.formation.spring.rest.query.entity.QAlbum;

public interface AlbumRepository
		extends
			JpaRepository<Album, Long>,
			QuerydslPredicateExecutor<Album>,
			QuerydslBinderCustomizer<QAlbum> {

	@Override
	default public void customize(QuerydslBindings bindings, QAlbum album) {

		// Toutes les recherches sont insensibles à la casse
		// si présence * au début et à la fin 
		// --> alors recherche de type containsIgnoreCase
		// si présence * au début 
		// --> alors recherche de type startsWithIgnoreCase
		// si présence * à la fin.
		// --> alors recherche de type endsWithIgnoreCase
		// sinon recherche de type equalsIgnoreCase
		bindings.bind(String.class).first(
				(StringPath path, String value) -> {
					boolean presenceCaractereAnyAuDebut = value.startsWith("*");
					boolean presenceCaractereAnyALaFin = value.endsWith("*");
					if (presenceCaractereAnyAuDebut) {
						value = value.substring(1);
					}
					if (presenceCaractereAnyALaFin) {
						value = value.substring(0, value.length() - 1);
					}

					if (presenceCaractereAnyAuDebut
							&& presenceCaractereAnyALaFin) {
						return path.containsIgnoreCase(value);
					} else if (presenceCaractereAnyAuDebut) {
						return path.endsWithIgnoreCase(value);
					} else if (presenceCaractereAnyALaFin) {
						return path.startsWithIgnoreCase(value);
					} else {
						return path.equalsIgnoreCase(value);
					}

				});

		// exclusion de urlImage du Predicate (on ne veut pas autoriser une
		// recherche sur le champ urlImage)
		bindings.excluding(album.urlImage);
	}

}
