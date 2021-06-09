# spring-rest-query-language-with-querydsl-web-support

Exemple d'une API utilisant l'annotation @QuerydslPredicate DSL pour utiliser des critères de recherche directement dans l'API

## Fonctionnement

http://localhost:8080/albums?[NOM_DU_CHAMP_ENTITE]=[VALEUR_DU_CHAMP]

Exemple d'utilisation simple :
* `nom=Anomie` -> recherche des albums dont le nom est Anomie
* `nom=Ano*` -> recherche des albums dont le nom commence par Ano
* `nom=*mie` -> recherche des albums dont le nom finit par mie
* `nom=*mie*` -> recherche des albums dont le nom contient mie
 
Combinaison de critères de recherche :
* `nom=*e*&genre=*bla*` -> recherche des albums dont le nom contient un e et dont le genre contient bla
 
Les limitations :
* on ne peut pas faire mettre deux critères sur le même champ. Ainsi `nom=*Ano* & nom=Wel*` ne fonctionne pas
* pas de gestion du ou

## Setup
mvn clean install
