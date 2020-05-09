package com.marcosjr.carros.domain.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcosjr.carros.domain.User;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class UserDTO {
    private String login;
    private String nome;
    private String email;

    // token jwt
    private String token;

    public static UserDTO create(User user, String token) {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}