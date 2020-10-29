package com.tpfinal.cai.dto;

public class FrecuenteDTO {

    public String tipo;
    public Integer total;

    public FrecuenteDTO(String tipo, Integer total) {
        this.tipo = tipo;
        this.total = total;
    }

    public static FrecuenteDTO mapeo(String tipo, Integer total) {
        return new FrecuenteDTO(tipo, total);
    }

    public String getState() {
        return tipo;
    }

    public Integer getTotal() {
        return total;
    }
}
