package com.example.demo.controller;

import com.example.demo.dto.CreateTariffDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final RestTemplate restTemplate;

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @PostMapping("/createTariff")
    public ResponseEntity<?> сreateTariff(@RequestBody CreateTariffDto dto) {
        String targetUrl = "http://backendTarriffs:8091/tariff/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateTariffDto> requestEntity = new HttpEntity<>(dto, headers);

        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(
                targetUrl,
                requestEntity,
                Integer.class
        );

        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }


    @PutMapping("/updateTariff/{id}")
    public ResponseEntity<?> updateTariff(
            @RequestBody CreateTariffDto dto,
            @PathVariable String id) {

        String targetUrl = "http://backendTarriffs:8091/tariff/update/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateTariffDto> requestEntity = new HttpEntity<>(dto, headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                targetUrl,
                HttpMethod.PUT,
                requestEntity,
                Void.class
        );

        return ResponseEntity.status(responseEntity.getStatusCode()).build();
    }

    @DeleteMapping("/deleteTariff/{id}")
    public ResponseEntity<?> deleteTariff(@PathVariable String id) {

        String targetUrl = "http://backendTarriffs:8091/tariff/delete/{id}";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                targetUrl,
                HttpMethod.DELETE,
                requestEntity,
                Void.class,
                id
        );
        return ResponseEntity.status(responseEntity.getStatusCode()).build();
    }

}
