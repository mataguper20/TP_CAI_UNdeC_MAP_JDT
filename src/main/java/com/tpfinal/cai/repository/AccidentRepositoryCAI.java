package com.tpfinal.cai.repository;

import java.util.Collection;

import com.tpfinal.cai.dto.PuntoPeligrosoDTO;
import com.tpfinal.cai.model.Accident;
import com.tpfinal.cai.model.Frecuente;

public interface AccidentRepositoryCAI {

    Collection<Frecuente> findFrecuentesPorTipo(String tipo, int limite);

    Collection<Accident> findAccidentesPorPuntoYRadio(float lng, float lat, float radiusKm);

    Collection<PuntoPeligrosoDTO> findPuntosPeligrosos(float radiusKm);

    Float findDistanciaPromedio();
}
