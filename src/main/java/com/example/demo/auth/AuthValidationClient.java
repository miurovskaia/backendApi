package com.example.demo.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class AuthValidationClient {

    private final RestTemplate restTemplate;
    private final String authServiceUrl = "http://backendAuth:8094/api/auth/validate";
    private final String serviceId = "api-service";

    public AuthValidationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ValidationResponse validateToken(String jwtToken) {
        try {
            ValidationRequest request = new ValidationRequest(jwtToken, serviceId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ValidationRequest> entity = new HttpEntity<>(request, headers);

            return restTemplate.postForObject(authServiceUrl, entity, ValidationResponse.class);
        } catch (Exception e) {
            return new ValidationResponse(false, "Auth service unreachable: " + e.getMessage());
        }
    }

    public static class ValidationRequest {
        public String token;
        public String serviceId;
        public ValidationRequest(String token, String serviceId) {
            this.token = token;
            this.serviceId = serviceId;
        }
    }

    public static class ValidationResponse {
        public boolean valid;
        public String detail;
        public ValidationResponse() {}
        public ValidationResponse(boolean valid, String detail) {
            this.valid = valid;
            this.detail = detail;
        }
    }
}