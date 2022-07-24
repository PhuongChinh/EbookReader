package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.PageBook;
import com.example.demo.repository.PageRepository;
import com.example.demo.service.PageService;

@Service
public class PageServiceImpl implements PageService{
	
	@Autowired
	private PageRepository pageRepository;

	@Override
	public List<PageBook> findByBookId(String id,Pageable paging) {
		
		Page<PageBook> list = pageRepository.findByBookId(id,paging);
		for(PageBook page : list) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(StringUtils.cleanPath(page.getFilePath()))
					.toUriString();
			page.setFilePath(fileDownloadUri);
		}
		
		return list.getContent();
	}
	
	

}
