package com.tpfinal.cai.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;

import com.tpfinal.cai.dto.PuntoPeligrosoDTO;
import com.tpfinal.cai.model.Accident;
import com.tpfinal.cai.model.DistanciaPromedio;
import com.tpfinal.cai.model.PuntoPeligroso;
import com.tpfinal.cai.model.Frecuente;
import com.tpfinal.cai.repository.AccidentRepositoryCAI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AccidentRepositoryImpl implements AccidentRepositoryCAI {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public AccidentRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Collection<Frecuente> findFrecuentesPorTipo(String tipo, int limite) {
		MatchOperation match = match(new Criteria(tipo).ne(null));
		GroupOperation group = group(tipo).count().as("total");
		SortOperation sort = sort(Sort.Direction.DESC, "total");
		LimitOperation limit = limit(limite);
		Aggregation aggregation = newAggregation(match, group, sort, limit);
		AggregationResults<Frecuente> result = mongoTemplate.aggregate(aggregation, "accident", Frecuente.class);
		return result.getMappedResults().stream().collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Collection<Accident> findAccidentesPorPuntoYRadio(float lng, float lat, float radiusKm) {
		BasicQuery basicQuery = new BasicQuery("{start_location: { $geoWithin: { $centerSphere: [ [" + lng + "," + lat + "], " + radiusKm / 6371 + "] }}})");
		List<Accident> result = mongoTemplate.find(basicQuery, Accident.class);
		return result;
	}

	@Override
	public Collection<PuntoPeligrosoDTO> findPuntosPeligrosos(float radiusKm) {
		GroupOperation group = group("start_location").first("start_location").as("start_location");
		Aggregation aggregation = newAggregation(group).withOptions(newAggregationOptions().allowDiskUse(true).build());
		AggregationResults<PuntoPeligroso> puntosDistintos = mongoTemplate.aggregate(aggregation, "accident", PuntoPeligroso.class);
		float dist = radiusKm / 6371;
		List<PuntoPeligrosoDTO> puntosPeligrosos = puntosDistintos.getMappedResults().parallelStream().map(accident -> {
			String query =  "{start_location: { $geoWithin: { $centerSphere: [ [" + accident.getStartLocation().getX() + "," + accident.getStartLocation().getY() + "], " + dist + "] }}})";
			BasicQuery queryTotal = new BasicQuery(query);
			return PuntoPeligrosoDTO.mapeo(accident.getStartLocation().getX(), accident.getStartLocation().getY(), (int) mongoTemplate.count(queryTotal, PuntoPeligroso.class));
		}).collect(Collectors.toCollection(ArrayList::new));

		puntosPeligrosos.sort(Comparator.comparing(PuntoPeligrosoDTO::getTotal).reversed());
		puntosPeligrosos = puntosPeligrosos.subList(0, 10);
		return puntosPeligrosos;
	}

	@Override
	public Float findDistanciaPromedio() {
		Float distanciaPromedio = null;

		MatchOperation match = match(new Criteria("Distance(mi)").ne(null));
		GroupOperation avg = Aggregation.group()
				.avg("Distance(mi)")
				.as("avgDistance");
		Aggregation aggregation = newAggregation(match, avg);
		distanciaPromedio = mongoTemplate.aggregate(aggregation, "accident", DistanciaPromedio.class)
				.getUniqueMappedResult()
				.getAvgDistance();

		return distanciaPromedio;
	}

}
