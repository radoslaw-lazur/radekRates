package com.radekrates.repository;

import com.radekrates.domain.AdminRatio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AdminRatioRepository extends CrudRepository<AdminRatio, Long> {
    @Override
    AdminRatio save(AdminRatio adminRatio);
    @Override
    Set<AdminRatio> findAll();
    @Override
    void deleteAll();

    Optional<AdminRatio> findByKey(String key);
}
