package com.radekrates.repository;

import com.radekrates.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    User save(User user);
    @Override
    void deleteById(Long userId);
    @Override
    Optional<User> findById(Long userId);
    @Override
    Set<User> findAll();
    @Override
    void deleteAll();
    @Override
    long count();

    Optional<User> findByEmail(String email);
    Optional<User> findByActivationCode(String activationcode);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
}
