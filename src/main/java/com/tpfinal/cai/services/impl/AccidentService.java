package com.tpfinal.cai.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpfinal.cai.dto.AccidentDTO;
import com.tpfinal.cai.dto.PuntoPeligrosoDTO;
import com.tpfinal.cai.dto.FrecuenteDTO;
import com.tpfinal.cai.mapper.AccidentMapper;
import com.tpfinal.cai.mapper.FrecuenteMapeo;
import com.tpfinal.cai.model.Accident;
import com.tpfinal.cai.repository.AccidentRepository;
import com.tpfinal.cai.services.IAccidentService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


@Service
@Transactional
public class AccidentService implements IAccidentService {

    static final Logger LOGGER = LoggerFactory.getLogger(AccidentService.class);
    @Inject
    public AccidentRepository accidentRepository;

    @Override
    public Collection<AccidentDTO> getAccidents() {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();
        this.getAccidentRepository().findAll().stream().map(a -> new AccidentDTO(a))
                .collect(Collectors.toCollection(() -> result));
        return result;
    }

    @Override
    public AccidentDTO getOneById(String id) {
        Accident accident = this.getAccidentRepository().findById(id).get();
        return new AccidentDTO(accident);
    }

    @Override
    public Collection<AccidentDTO> accidentesEntreFechas(String desde, String hasta) {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

        long antes = new Date().getTime();
        this.getAccidentRepository().findByStartTimeBetween(desde, hasta).stream().map(AccidentDTO::new)
                .collect(Collectors.toCollection(() -> result));
        long despues = new Date().getTime();
        System.out.println("Tiempo accidentesEntreFechas( "+desde +" - "+hasta+" ): " + (despues - antes) + " milisegundos");
        System.out.println("Tiempo accidentesEntreFechas( "+desde +" - "+hasta+" ): " + (despues - antes)/1000 + " segundos");
        System.out.println("Tiempo accidentesEntreFechas( "+desde +" - "+hasta+" ): " + (despues - antes)/1000/3600 + " minutos");
        
        return result;
    }

    @Override
    public Collection<FrecuenteDTO> losMasFrecuentesPorTipo(String tipo, int limite) {
        Collection<FrecuenteDTO> result = new ArrayList<FrecuenteDTO>();

        long antes = new Date().getTime();
        result = this.getAccidentRepository().findFrecuentesPorTipo(tipo,limite).stream().map(FrecuenteMapeo::mapeo).collect(Collectors.toCollection(ArrayList::new));
        long despues = new Date().getTime();
        System.out.println("Tiempo losMasFrecuentesPorTipo( "+tipo +", "+limite+" ): " + (despues - antes) + " milisegundos");
        System.out.println("Tiempo losMasFrecuentesPorTipo( "+tipo +", "+limite+" ): " + (despues - antes)/1000 + " segundos");
        System.out.println("Tiempo losMasFrecuentesPorTipo( "+tipo +", "+limite+" ): " + (despues - antes)/1000/3600 + " minutos");
        

        return result;
    }

    @Override
    public Collection<AccidentDTO> accidentesPorPuntoYRadio(float lng, float lat, float radioKm) {
        Collection<AccidentDTO> result = new ArrayList<AccidentDTO>();

        long antes = new Date().getTime();
        result = this.getAccidentRepository().findAccidentesPorPuntoYRadio(lng, lat, radioKm).stream().map(AccidentMapper::mapeo).collect(Collectors.toCollection(ArrayList::new));
        long despues = new Date().getTime();
        System.out.println("Tiempo accidentesPorPuntoYRadio( ["+lng+", "+lat+"], "+radioKm+" ): " + (despues - antes) + " milisegundos");
        System.out.println("Tiempo accidentesPorPuntoYRadio( ["+lng+", "+lat+"], "+radioKm+" ): " + (despues - antes)/1000 + " segundos");
        System.out.println("Tiempo accidentesPorPuntoYRadio( ["+lng+", "+lat+"], "+radioKm+" ): " + (despues - antes)/1000/3600 + " minutos");
        
        return result;
    }

    @Override
    public Collection<PuntoPeligrosoDTO> puntosMasPeligrososPorRadio(float radioKm) {
        Collection<PuntoPeligrosoDTO> result = new ArrayList<PuntoPeligrosoDTO>();

        long antes = new Date().getTime();
        result = this.getAccidentRepository().findPuntosPeligrosos(radioKm);
        long despues = new Date().getTime();
        System.out.println("Tiempo puntosMasPeligrososPorRadio("+radioKm+"): " + (despues - antes) + " milisegundos");
        System.out.println("Tiempo puntosMasPeligrososPorRadio("+radioKm+"): " + (despues - antes)/1000 + " segundos");
        System.out.println("Tiempo puntosMasPeligrososPorRadio("+radioKm+"): " + (despues - antes)/1000/3600 + " minutos");
        
        return result;
    }

    @Override
    public Float distanciaPromedio() {
        Float avgDistance;

        long antes = new Date().getTime();
        avgDistance = this.getAccidentRepository().findDistanciaPromedio();
        long despues = new Date().getTime();
        System.out.println("Tiempo distanciaPromedio(): " + (despues - antes) + " milisegundos");
        System.out.println("Tiempo distanciaPromedio(): " + (despues - antes)/1000 + " segundos");
        System.out.println("Tiempo distanciaPromedio(): " + (despues - antes)/1000/3600 + " minutos");
        
        return avgDistance;
    }

    @Override
    public void saveAccident(String aReason) {
        Accident newAccident = new Accident("", aReason);
        this.getAccidentRepository().save(newAccident);

    }

    public AccidentRepository getAccidentRepository() {
        return this.accidentRepository;
    }

    public void setAccidentRepository(AccidentRepository aRepository) {
        this.accidentRepository = aRepository;
    }

}
