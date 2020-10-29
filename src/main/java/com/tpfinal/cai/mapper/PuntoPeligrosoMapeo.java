package com.tpfinal.cai.mapper;

import com.tpfinal.cai.dto.PuntoPeligrosoDTO;
import com.tpfinal.cai.model.PuntoPeligroso;

public class PuntoPeligrosoMapeo {

    public static PuntoPeligrosoDTO mapeo(PuntoPeligroso puntoPeligroso) {
        return PuntoPeligrosoDTO.mapeo(puntoPeligroso.getStartLocation().getX(), puntoPeligroso.getStartLocation().getY(), puntoPeligroso.getTotal());
    }
}
