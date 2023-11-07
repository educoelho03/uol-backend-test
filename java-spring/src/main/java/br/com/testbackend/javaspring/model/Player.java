package br.com.testbackend.javaspring.model;

import br.com.testbackend.javaspring.model.dto.PlayerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity(name = "players")
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

    private String phoneNumber;
    private String codiname;
    private GroupType groupType;


    public Player(PlayerDTO playerDTO) {
        this.name = playerDTO.name();
        this.email = playerDTO.email();
        this.phoneNumber = playerDTO.phoneNumber();
        this.groupType = playerDTO.groupType();
    }
}
