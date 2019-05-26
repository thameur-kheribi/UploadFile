package com.spring.rest.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	private final Path rootLocation = Paths.get("upload-dir");
	private final String emptySrc = "avatar.png";

	// STORE File
	public void store(MultipartFile file) throws IOException {
		Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
	}

	// INIT Directory
	public void init() {

		try {
			if (!Files.exists(rootLocation))
				Files.createDirectory(rootLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// LOAD Image
	public Resource LoadFile(String fileName) throws MalformedURLException
	{
		Path file = rootLocation.resolve(fileName);
		Resource resource = new UrlResource(file.toUri());
		if(resource.exists())
			return resource;
		else
		{
			return new UrlResource(rootLocation.resolve(emptySrc).toUri());
		}
	}
}
