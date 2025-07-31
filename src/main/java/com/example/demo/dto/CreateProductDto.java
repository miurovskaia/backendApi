package com.example.demo.dto;
import java.time.Instant;


public class CreateProductDto {
    private String name;
    private Integer tariffId;
    private Instant timedate;


    public CreateProductDto(String name, String conditions, Integer tariffId, Instant timedate) {
        this.name = name;
        this.tariffId = tariffId;
        this.timedate = timedate;
    }

    public String getName() {
        return name;
    }


    public Integer getTariffId() {
        return tariffId;
    }

    public Instant getTimedate() {
        return timedate;
    }
}