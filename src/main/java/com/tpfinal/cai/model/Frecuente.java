package com.tpfinal.cai.model;

import org.springframework.data.annotation.Id;

public class Frecuente {

    @Id
    private String tipo;
    private Integer total;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}


}
