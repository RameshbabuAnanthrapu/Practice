package com.example.library.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.library.dao.Book;

@Repository
public interface LibraryRepo extends JpaRepository<Book, Long> {
	
	@Query("select b from Book b where b.name=?1")
    List<Book>findBookByName(String bookName);

}
