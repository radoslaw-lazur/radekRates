package com.radekrates.repository;

import com.radekrates.domain.Iban;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IbanRepository extends CrudRepository<Iban, Long> {
    @Override
    Iban save(Iban iban);
    @Override
    void deleteById(Long ibanId);
    @Override
    Optional<Iban> findById(Long ibanId);
    @Override
    Set<Iban> findAll();
    @Override
    void deleteAll();
    @Override
    long count();

    Optional<Iban> findByIbanNumber(String ibannumber);
    boolean existsByIbanNumber(String ibannumber);
}
