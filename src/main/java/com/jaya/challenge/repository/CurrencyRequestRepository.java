package com.jaya.challenge.repository;

import com.jaya.challenge.domain.model.CurrencyRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRequestRepository extends ReactiveMongoRepository<CurrencyRequest, Integer> {
}
