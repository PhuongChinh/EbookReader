package com.example.demo.repository;

import com.example.demo.model.Clazz;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends PagingAndSortingRepository<Clazz, String> {

    @Query(value = "select c from Clazz c where c.className = :clazzName")
    Page<Clazz> findClazzByClassName(String clazzName);

}
