package com.curso.spring.services;

import java.net.URI;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	URI uploadFile(MultipartFile multipartFile);
	
}
