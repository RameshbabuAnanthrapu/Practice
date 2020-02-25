package com.example.demo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monitor")
public class FileMonitor {
	
	@Value("${filepath}")
	private String path;
	
	@Value("${filepattern}")
	private String patter;
	
	@Autowired
	private EmailService service;
	
	@GetMapping("/file")
	public String minitorFile(){
		
		System.out.println("path" +path);
		
		File file =  new File(path);
		
		String [] files = file.list();
		
		for(String s: files) {
			System.out.print("caling mail service with file name" + s);
			service.sendSimpleMessage(s);
			
		}
		
		return "file Found";
		
	}

}
