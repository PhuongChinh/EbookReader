package com.example.demo.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PageBook;


@Repository
public interface PageRepository extends PagingAndSortingRepository<PageBook, String>{
	
	Page<PageBook> findByBookId(String bookId,Pageable pageable);

}
