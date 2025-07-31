package com.example.demo.controller;

import com.example.demo.dto.CreateProductDto;
import com.example.demo.dto.CreateTariffDto;
import com.example.demo.dto.ProductAudDto;
import com.example.demo.dto.ProductDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductDto dto) throws Exception{
        String targetUrl = "http://backendProducts:8093/product/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateProductDto> requestEntity = new HttpEntity<>(dto, headers);

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

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(
            @RequestBody CreateProductDto dto,
            @PathVariable String id) {

        String targetUrl = "http://backendProducts:8093/product/update/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateProductDto> requestEntity = new HttpEntity<>(dto, headers);

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

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {

        String targetUrl = "http://backendProducts:8093/product/delete/{id}";

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

    @GetMapping("/getCurrentProductVersion/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        String targetUrl = "http://backendProducts:8093/product/{id}";
        ResponseEntity<ProductDto> responseEntity = restTemplate.exchange(
                targetUrl,
                HttpMethod.GET,
                requestEntity,
                ProductDto.class,
                id
        );

        return ResponseEntity.status(responseEntity.getStatusCode())
                .body(responseEntity.getBody());
    }

    @GetMapping("/getPreviousProductVersions/{id}")
    public ResponseEntity<Set<ProductAudDto>> getPreviousProductVersions(@PathVariable("id") Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        String targetUrl = "http://backendProducts:8093/product/peviousVersions/{id}";
        //Set<ProductAudDto> productAudDtoSet = new HashSet<>();
        ResponseEntity<Set<ProductAudDto>> responseEntity = restTemplate.exchange(
                targetUrl,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Set<ProductAudDto>>() {},
                id
        );

        return ResponseEntity.status(responseEntity.getStatusCode())
                .body(responseEntity.getBody());
    }

    @GetMapping("/getVersionsForPeriod/{id}")
    public ResponseEntity<Set<ProductAudDto>> getVersionsForPeriod(
            @PathVariable("id") Integer id,
            @RequestParam Instant startTimeDate,
            @RequestParam Instant endTimeDate
    ) {
        String targetUrl = "http://backendProducts:8093/product/versionsForPeriod/{id}";
        String url = UriComponentsBuilder.fromHttpUrl(targetUrl)
                .queryParam("startTimeDate", startTimeDate.toString())
                .queryParam("endTimeDate", endTimeDate.toString())
                .buildAndExpand(id)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Set<ProductAudDto>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Set<ProductAudDto>>() {}
        );

        return ResponseEntity.status(responseEntity.getStatusCode())
                .body(responseEntity.getBody());
    }

}
