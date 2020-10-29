package com.tpfinal.cai.mapper;

import com.tpfinal.cai.dto.AccidentDTO;
import com.tpfinal.cai.dto.FrecuenteDTO;
import com.tpfinal.cai.model.Accident;
import com.tpfinal.cai.model.Frecuente;

public class AccidentMapper {

    public static AccidentDTO mapeo(Accident accident) {
        return AccidentDTO.mapeo(accident.getId(), accident.getStartTime(), accident.getDescription(), accident.getStartLat(), accident.getStartLng(), accident.getEndLat(), accident.getEndLng());
    }
}
