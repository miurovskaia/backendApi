package com.example.demo.dto;



import java.time.Instant;



public class ProductAudDto {

    private Integer id;
    private String name;
    private Integer tariffId;
    private Instant timeAndDate;

    private Integer rev;
    private Integer revtype;
    private int version;

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public Integer getRevType() {
        return revtype;
    }

    public void setRevType(Integer revtype) {
        this.revtype = revtype;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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
                ", rev='" + rev + '\'' +
                ", revtype='" + revtype + '\'' +
                ", version='" + version + '\'' +
                ", timeAndDate='" + timeAndDate + '\'' +
                '}';
    }
}