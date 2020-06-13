package com.curso.spring.services.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.curso.spring.services.ImageService;
import com.curso.spring.services.exceptions.FileException;

@Service
public class ImageServiceImpl implements ImageService{

	@Override
	public BufferedImage getJpgImagemFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if(!ext.equals("png") && !ext.equals("jpg"))
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if(ext.equals("png"))
				img = pngToJpg(img);
			
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	@Override
	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}

	@Override
	public InputStream getInputStream(BufferedImage img, String extension) {
		try{
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch(IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}

	@Override
	public BufferedImage cropSquare(BufferedImage img) {
		int min =(img.getHeight() <= img.getWidth()) ? img.getHeight() : img.getWidth();
		return Scalr.crop(img, (img.getWidth() /2 ) - (min / 2), (img.getHeight() / 2) - (min / 2), min, min); 
	}

	@Override
	public BufferedImage resize(BufferedImage img, int size) {
		return Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, size);
	}

}
