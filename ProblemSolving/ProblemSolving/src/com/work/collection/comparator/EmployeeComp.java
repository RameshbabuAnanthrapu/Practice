package com.work.collection.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeComp {

	public static void main(String[] args) {
		Employee e1=  new Employee(1, "Ramesh","synechron");
		Employee e2=  new Employee(3, "sindhu","fdb");
	
		Employee e3 = new Employee(2, "minnu","google");
		
		
		List<Employee> eList =  new ArrayList<>();
		eList.add(e1);
		eList.add(e2);
		eList.add(e3);
		
		System.out.println("before sort");
		eList.forEach(e-> System.out.println(e.toString()));
		/*
		 * 
		 * I have used static method of Comparator class to generate an employee comparator object. 
		 * 
		 * */
		Comparator<Employee>  empComp = Comparator.comparingInt(Employee::getId);
		
		Collections.sort(eList, empComp);
		System.out.println("After Sort");
		eList.forEach(e->System.out.println(e.toString()));
		
		
		/*
		 * another way using lamda
		 * 
		 * */
		
		
		eList.sort((Employee emp1, Employee emp2) -> emp1.getId() - emp2.getId());
		
		

	}

}
