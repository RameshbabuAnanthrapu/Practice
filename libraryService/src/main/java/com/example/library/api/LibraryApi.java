package com.example.library.api;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dao.Book;
import com.example.library.service.LibraryDataService;

@RestController
@RequestMapping("/library")
public class LibraryApi {
	
	@Autowired
	private LibraryDataService service;

	@PostMapping("/addBooks")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> books){
		
		
		List<Book> addedBooks = service.addBook(books);
		
		return new ResponseEntity<List<Book>>(addedBooks, HttpStatus.OK);
		
		
	}
	
	@PostMapping("/getBooksByName/{name}")
	public ResponseEntity<List<Book>> getBooksByName(@PathVariable(value = "name") String bookName){
		
		
		System.out.println("bookName "+bookName);
		
		List<Book> addedBooks = service.getBookByName(bookName);
		
		return new ResponseEntity<List<Book>>(addedBooks, HttpStatus.OK);
		
		
	}
	
	
	

}