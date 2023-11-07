package br.com.testbackend.javaspring.model.dto;

import br.com.testbackend.javaspring.model.GroupType;

public record PlayerDTO(String name,
                        String email,
                        String phoneNumber,
                        GroupType groupType) {
}
