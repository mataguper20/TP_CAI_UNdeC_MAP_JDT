package com.tpfinal.cai.services;

import java.util.Collection;

import com.tpfinal.cai.dto.AccidentDTO;
import com.tpfinal.cai.dto.PuntoPeligrosoDTO;
import com.tpfinal.cai.dto.FrecuenteDTO;

public interface IAccidentService {

    public Collection<AccidentDTO> getAccidents();

    public AccidentDTO getOneById(String id);

    public Collection<AccidentDTO> accidentesEntreFechas(String dateFrom, String dateTo);

    public Collection<FrecuenteDTO> losMasFrecuentesPorTipo(String tipo, int limite);

    public Collection<AccidentDTO> accidentesPorPuntoYRadio(float lng, float lat, float radiusKm);

    public Collection<PuntoPeligrosoDTO> puntosMasPeligrososPorRadio(float radiusKm);

    public Float distanciaPromedio();

    public void saveAccident(String reason);

}
