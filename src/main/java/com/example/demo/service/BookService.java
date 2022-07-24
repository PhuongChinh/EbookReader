package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;

public interface BookService {
	
	
	Book uploadBook(MultipartFile file) throws IOException;
	
	Book saveBookInfo(Book book);
	
	
	Book findById(String id);

}
