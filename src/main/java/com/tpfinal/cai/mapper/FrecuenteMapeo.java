package com.tpfinal.cai.mapper;

import com.tpfinal.cai.dto.FrecuenteDTO;
import com.tpfinal.cai.model.Frecuente;

public class FrecuenteMapeo {

    public static FrecuenteDTO mapeo(Frecuente states) {
        return FrecuenteDTO.mapeo(states.getTipo(), states.getTotal());
    }
}
