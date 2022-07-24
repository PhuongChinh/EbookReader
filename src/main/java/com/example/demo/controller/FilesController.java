package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Constants;
import com.example.demo.common.FileUtil;
import com.example.demo.dto.MessageResp;
import com.example.demo.service.PageService;

@RestController
public class FilesController {
	
	private final static String destinationDir = "uploads/pages/";
	
	@Autowired
	private PageService pageService;

	
	
	@GetMapping("/api/v1/book/{id}/page")
	public ResponseEntity<MessageResp> getFile(@PathVariable String id, 
			@RequestParam(value = "pageNo", defaultValue = Constants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		MessageResp messageResp = new MessageResp();
		messageResp.setResult(pageService.findByBookId(id,paging));
		return ResponseEntity.status(HttpStatus.OK).body(messageResp);
	}

	

	@GetMapping(value="/uploads/pages/{bookName}/{imageName}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String bookName,@PathVariable String imageName) throws IOException {
		return FileUtil.getImageWithMediaType(StringUtils.cleanPath(destinationDir + bookName + "/" + imageName));
	}
}
