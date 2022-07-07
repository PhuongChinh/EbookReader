package com.example.demo.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RepositoryRestResource(collectionResourceRel = "users", path = "users", exported = true)
@Cacheable(value = "GlobalCache")
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    @Query(value = "select u from User u where u.password = :password and u.username = :username")
    Optional<User> findByPasswordAndUsername(@Param("password") String password, @Param("username") String username);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query(value = "select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

}
