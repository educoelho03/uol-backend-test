package br.com.testbackend.javaspring.service;

import br.com.testbackend.javaspring.infra.CodinameHandler;
import br.com.testbackend.javaspring.model.GroupType;
import br.com.testbackend.javaspring.model.Player;
import br.com.testbackend.javaspring.model.dto.PlayerDTO;
import br.com.testbackend.javaspring.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private CodinameHandler codinameHandler;

    public PlayerService(PlayerRepository playerRepository, CodinameHandler codinameHandler) {
        this.playerRepository = playerRepository;
        this.codinameHandler = codinameHandler;
    }

    public Player create(PlayerDTO playerDTO) {
        Player newPlayer = new Player(playerDTO);
        return playerRepository.save(newPlayer);
    }

    public List<Player> getAll(){
        return playerRepository.findAll();
    }

    private String getCodiname(GroupType groupType){

    }
}
