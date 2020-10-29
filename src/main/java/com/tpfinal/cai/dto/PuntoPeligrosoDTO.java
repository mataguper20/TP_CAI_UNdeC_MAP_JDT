package com.tpfinal.cai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PuntoPeligrosoDTO {

    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("total")
    private Integer total;

    public PuntoPeligrosoDTO() {
    }

    public PuntoPeligrosoDTO(Double longitude, Double latitude, Integer total) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.total = total;
    }

    public static PuntoPeligrosoDTO mapeo(Double longitude, Double latitude, Integer total) {
        return new PuntoPeligrosoDTO(longitude, latitude, total);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
