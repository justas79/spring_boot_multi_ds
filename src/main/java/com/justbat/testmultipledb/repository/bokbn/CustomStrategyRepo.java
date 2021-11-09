package com.justbat.testmultipledb.repository.bokbn;

import com.justbat.testmultipledb.entities.CustomStrategy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomStrategyRepo extends CrudRepository<CustomStrategy, Integer> {
}
