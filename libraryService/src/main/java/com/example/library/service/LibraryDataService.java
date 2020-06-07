package com.example.library.service;

import java.util.List;

import com.example.library.dao.Book;

public interface LibraryDataService  {
	
	List<Book> addBook(List<Book> books);
	List<Book> getBookByName(String name);

}
