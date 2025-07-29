package com.example.demo.dto;

import java.time.Instant;



public class ProductDto {

    private Integer id;
    private String name;
    private Integer tariffId;
    private Instant timeAndDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Instant getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Instant timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tariffId='" + tariffId + '\'' +
                ", timeAndDate='" + timeAndDate + '\'' +
                '}';
    }
}
