package br.com.testbackend.javaspring.controller;


import br.com.testbackend.javaspring.model.Player;
import br.com.testbackend.javaspring.model.dto.PlayerDTO;
import br.com.testbackend.javaspring.service.PlayerService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody @Valid PlayerDTO playerDTO){
        Player newPlayer = playerService.create(playerDTO);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }


    // TODO: GET PLAYERS BY GROUPTYPE

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers(){
        try {
            List<Player> players = playerService.getAll();
            return ResponseEntity.ok(players);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
