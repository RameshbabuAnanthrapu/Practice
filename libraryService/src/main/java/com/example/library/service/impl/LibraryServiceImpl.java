package com.example.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.dao.Book;
import com.example.library.repo.LibraryRepo;
import com.example.library.service.LibraryDataService;

@Service
public class LibraryServiceImpl implements LibraryDataService {

	@Autowired
	private LibraryRepo repo;
	
	@Override
	public List<Book> addBook(List<Book> books) {
		
		return repo.saveAll(books);
		
	}

	@Override
	public List<Book> getBookByName(String name) {
		System.out.println("DataService Call to get books for name" +name);
		return repo.findBookByName(name);
	}

}
