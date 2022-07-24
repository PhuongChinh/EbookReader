package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.demo.model.PageBook;

public interface PageService {
	
	List<PageBook> findByBookId(String id,Pageable paging);
}
