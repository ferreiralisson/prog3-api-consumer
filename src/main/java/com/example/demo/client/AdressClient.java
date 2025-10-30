package com.example.demo.client;

import com.example.demo.dto.AdressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "adressClient", url = "https://viacep.com.br")
public interface AdressClient {
    @GetMapping("/ws/{cep}/json")
    AdressResponse getAdress(@PathVariable String cep);
}
