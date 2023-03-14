package br.ada.americanas.moviebattle.player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * classe responsavel por fazer acesso ao banco de dados através do Spring por meio da NOTAÇÃO e a
 * extenção da Classe "CrudRepository"
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}
