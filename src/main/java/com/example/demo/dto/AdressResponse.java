package com.example.demo.dto;

public record AdressResponse(
        String cep,
        String logradouro,
        String bairro,
        String estado
) {
}
