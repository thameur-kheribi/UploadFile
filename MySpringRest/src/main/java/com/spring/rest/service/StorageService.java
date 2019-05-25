package com.spring.rest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

	private final Path rootLocation = Paths.get("upload-dir");

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
}
