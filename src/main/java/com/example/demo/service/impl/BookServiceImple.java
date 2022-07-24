package com.example.demo.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.common.Constants;
import com.example.demo.common.FileUtil;
import com.example.demo.common.ObjectIDGenerator;
import com.example.demo.model.Book;
import com.example.demo.model.PageBook;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PageRepository;
import com.example.demo.service.BookService;

@Service
public class BookServiceImple implements BookService {

	private final static String rootPathFileAPI = "uploads/books/";
	
	private final static String destinationDir = "uploads/pages/";

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PageRepository pageRepository;

	@Override
	public Book uploadBook(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path uploadPath = Paths.get(rootPathFileAPI);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = file.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
		
		Book book = new Book();
		book.setPathBook(StringUtils.cleanPath(rootPathFileAPI + fileName));
		book.setBookName(StringUtils.split(fileName,".")[0]);
		book  = bookRepository.save(book);
		
		book.setNumberOfPage(convertPdfToImage(book));
		
		return bookRepository.save(book);
	}

	@Override
	public Book findById(String id) {
		Book book = bookRepository.findById(id).get();
		if (book != null) {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(book.getPathBook())
					.toUriString();
			book.setPathBook(fileDownloadUri);
			return book;
		}
		
		return null;
	}
	
	private Integer convertPdfToImage(Book book) {
		int totalPage = 0;
		try {

			File sourceFile = new File(book.getPathBook());
			
			String destinationDirFile =  destinationDir + book.getBookName() +"/";
			
			File destinationFile = new File(StringUtils.cleanPath(destinationDirFile));
			if (!destinationFile.exists()) {
				destinationFile.mkdir();
				System.out.println("Folder Created -> " + destinationFile.getAbsolutePath());
			}
			if (sourceFile.exists()) {
				System.out.println("Images copied to Folder: " + destinationFile.getName());
				PDDocument document = PDDocument.load(sourceFile);
				List<PDPage> list = document.getDocumentCatalog().getAllPages();
				totalPage = list.size();
				System.out.println("Total files to be converted -> " + list.size());

				String fileName = sourceFile.getName().replace(".pdf", "");
				int pageNumber = 1;
				for (PDPage page : list) {
					BufferedImage image = page.convertToImage();
					File outputfile = new File(StringUtils.cleanPath(destinationDirFile) + fileName + "_" + pageNumber + ".png");
					ImageIO.write(image, "png", outputfile);
					PageBook pageOfBook = new PageBook();
					pageOfBook.setBookId(book.getId());
					pageOfBook.setFilePath(StringUtils.cleanPath(outputfile.getPath()));
					pageOfBook.setPageOder(pageNumber);
					pageRepository.save(pageOfBook);
					
					pageNumber++;
				}
				document.close();
				System.out.println("Converted Images are saved at -> " + destinationFile.getAbsolutePath());
			} else {
				System.err.println(sourceFile.getName() + " File not exists");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPage;
	}
	
	

	@Override
	public Book saveBookInfo(Book book) {
		return bookRepository.save(book);
	}

}
