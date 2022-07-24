package com.example.demo.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Book;
import com.example.demo.repository.PageRepository;

public class FileUtil {
	
	@Autowired
	private PageRepository pageRepository;

	public static byte[] getImageWithMediaType(String name) throws IOException {
		
		File file = new File(name);
		FileInputStream fl = new FileInputStream(file);
		byte[] arr = new byte[(int) file.length()];
		fl.read(arr);
		fl.close();
		return arr;
	}
	


}
