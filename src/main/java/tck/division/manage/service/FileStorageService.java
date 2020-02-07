package tck.division.manage.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import tck.division.manage.exception.FileStorageException;
import tck.division.manage.exception.MyFileNotFoundException;


@Service
public class FileStorageService {
	
	@Value("${file.upload-dir}")
	private String uploadRootPath;
	
	public Path mkdir(String savePath){
		
		String uploadPath = uploadRootPath + "/" + savePath + "/";
		Path fileStorageLocation = Paths.get(uploadPath).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
		
		return fileStorageLocation;
	}
	
	public String storeFile(MultipartFile file, String savePath) {
		
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		// 확장자 불리
		String ext = "";
		try{
			int idx = fileName.lastIndexOf(".");
			if(idx > 0) ext = fileName.substring(idx+1);
		}catch(Exception e){}
		
		UUID uuid = UUID.randomUUID();
		String saveName = uuid.toString() + "_" + System.currentTimeMillis() + "." + ext;;
		
		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// 업로드 경로에 파일명을 추가하여 경로 수정
			Path targetLocation = mkdir(savePath).resolve(saveName);
			Files.copy(file.getInputStream(), targetLocation);

			return saveName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName, String savePath) {
		try {
			Path filePath = mkdir(savePath).resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}
}