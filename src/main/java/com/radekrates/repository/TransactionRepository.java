package com.radekrates.repository;

import com.radekrates.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    Transaction save(Transaction transaction);
    @Override
    void deleteById(Long transactionId);
    @Override
    Optional<Transaction> findById(Long transactionId);
    @Override
    Set<Transaction> findAll();
    @Override
    void deleteAll();
    @Override
    long count();

    Optional<Transaction> findByUniqueKeyChain(String uniqueKeyChain);
}
