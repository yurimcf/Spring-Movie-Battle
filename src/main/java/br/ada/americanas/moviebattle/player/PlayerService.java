package br.ada.americanas.moviebattle.player;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

/**
 * Classe responsavel por representar o Controller, classe responsavel por receber as requisições
 * do View, processalas e coordenar ações necesárias (regras de negocio) para atender as
 * requisições. (Funcionas como uma ponte entre o View e o Modelo(Repository e Player))
 */

@Service
@Validated
public class PlayerService {

    //ligação direta com o modelo
    private PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    //@Valid valida o tipo e as propriedade de acordo com a entidade no BD ou no modelo
    public Player add(@Valid Player player) {
        return repository.save(player);
    }

    public Player update(@Valid Player player) {
        return repository.save(player);
    }

    public Iterable<Player> list() {
        return this.repository.findAll();
    }

    public Optional<Player> findById(Long id) {
        return this.repository.findById(id);
    }

    public Optional<Player> delete(Long id) {
        Optional<Player> deleted = findById(id);
        this.repository.deleteById(id);
        return deleted;
    }
}
