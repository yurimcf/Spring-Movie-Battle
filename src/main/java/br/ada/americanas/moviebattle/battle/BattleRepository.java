package br.ada.americanas.moviebattle.battle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Equivale ao DAO
@Repository
public interface BattleRepository extends CrudRepository<Battle, Long> {
}
