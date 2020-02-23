package com.radekrates.repository;

import com.radekrates.domain.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
    @Override
    Log save(Log log);
    @Override
    Set<Log> findAll();
}
