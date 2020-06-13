package com.curso.spring.services;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	
	BufferedImage getJpgImagemFromFile(MultipartFile uploadedFile);
	BufferedImage pngToJpg(BufferedImage img);
	BufferedImage cropSquare(BufferedImage img);
	BufferedImage resize(BufferedImage img, int size);
	InputStream getInputStream(BufferedImage img, String extension);

}
