package com.example.library.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="bookDetails")
public class Book {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String author;
	private String publication;
	private Integer yearOfPublish;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublication() {
		return publication;
	}
	public void setPublication(String publication) {
		this.publication = publication;
	}
	public Integer getYearOfPublish() {
		return yearOfPublish;
	}
	public void setYearOfPublish(Integer yearOfPublish) {
		this.yearOfPublish = yearOfPublish;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", publication=" + publication
				+ ", yearOfPublish=" + yearOfPublish + "]";
	}
	
	

}
