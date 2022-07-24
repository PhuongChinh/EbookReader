package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.MessageResp;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	
	private final static String sourceDirBook = "/uploads/books/";

	@Autowired
	private BookService bookService;

	@PostMapping("/upload")
	public ResponseEntity<MessageResp> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			MessageResp messageResp = new MessageResp();
			messageResp.setResult(bookService.uploadBook(file));
			return ResponseEntity.status(HttpStatus.OK).body(messageResp);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResp(message));
		}
	}
	
	@PostMapping("/save/info")
	public ResponseEntity<MessageResp> saveBookInfo(@RequestBody Book book) {
		String message = "";
		try {
			MessageResp messageResp = new MessageResp();
			messageResp.setResult(bookService.saveBookInfo(book));
			return ResponseEntity.status(HttpStatus.OK).body(messageResp);
		} catch (Exception e) {
			message = "Could not upload the book";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResp(message));
		}
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<MessageResp> getFile(@PathVariable String id) {
		MessageResp messageResp = new MessageResp();
		messageResp.setResult(bookService.findById(id));
		return ResponseEntity.status(HttpStatus.OK).body(messageResp);
	}
	
}
