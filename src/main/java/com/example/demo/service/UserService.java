package com.example.demo.service;

import com.example.demo.client.AdressClient;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.converter.UserResponseToUserEntity.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AdressClient adressClient;

    public void criarUsuario(UserRequest userRequest) {
        var user = new UserModel(
                null,
                userRequest.nome(),
                userRequest.email()
        );

        var result = adressClient.getAdress("01001000");

        System.out.println(result.toString());

        repository.save(user);
    }

    public UserResponse buscarUsuario(Long id) {
        // select * from userModel um where um.id = :id
        return repository.findById(id)
                .map(userModel -> new UserResponse(
                        userModel.getId(),
                        userModel.getName(),
                        userModel.getEmail()
                        ))
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
    }

    public List<UserResponse> buscarUsuarios() {
        return repository.findAll()
                .stream()
                .map(userModel -> new UserResponse(
                        userModel.getId(),
                        userModel.getName(),
                        userModel.getEmail()
                ))
                .toList();
    }

    public void atualizarUsuario(Long id, UserRequest userRequest) {
        var user = buscarUsuario(id);
        repository.save(convert(user));
    }

    public void deletarUsuario(Long id) {
        var user = buscarUsuario(id);
        var userModel = convert(user);
        repository.delete(userModel);
    }
}
