package com.fintech.fundManagement.repository;

import com.fintech.fundManagement.model.Fund;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends ReactiveMongoRepository<Fund, String> {
}
