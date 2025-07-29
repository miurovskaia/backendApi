package com.example.demo.dto;
import java.time.Instant;


public class CreateProductDto {
    private String name;
    private Integer tariffId;
    private Instant timeAndDate;


    public CreateProductDto(String name, String conditions, Integer tariffId, Instant timeAndDate) {
        this.name = name;
        this.tariffId = tariffId;
        this.timeAndDate = timeAndDate;
    }

    public String getName() {
        return name;
    }


    public Integer getTariffId() {
        return tariffId;
    }

    public Instant getTimeAndDate() {
        return timeAndDate;
    }
}