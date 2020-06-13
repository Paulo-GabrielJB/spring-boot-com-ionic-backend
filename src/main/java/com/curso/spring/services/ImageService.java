package com.curso.spring.services;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	BufferedImage getJpgImagemFromFile(MultipartFile uploadedFile);
	BufferedImage pngToJpg(BufferedImage img);
	InputStream getInputStream(BufferedImage img, String extension);

}
