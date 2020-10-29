package com.tpfinal.cai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpfinal.cai.model.Accident;

import java.util.Date;
import java.util.List;

@Repository
public interface AccidentRepository extends MongoRepository<Accident, String> , AccidentRepositoryCAI {

    List<Accident> findByStartTimeBetween(String dateFrom, String dateTo);



}
