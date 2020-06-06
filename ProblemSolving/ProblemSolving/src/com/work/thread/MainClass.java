package com.work.thread;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {
		
		//Comparator<Student> stComp = 
		
		List<Student> stList = new ArrayList<>();
		stList.add(new Student(4, "s4"));
		stList.add(new Student(2, "s2"));
		stList.add(new Student(3, "s3"));
		
		//lamda way of comparing
		stList.sort((Student s1, Student s2)->{
			return s1.getId()-s2.getId();
		});
		stList.forEach(s-> System.out.println(s.toString()));
		
		
		
		

	}

}
