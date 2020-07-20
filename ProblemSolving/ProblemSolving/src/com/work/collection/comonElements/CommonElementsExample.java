package com.work.collection.comonElements;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonElementsExample {

	public static void main(String[] args) {

		List<String> list1 = new ArrayList<>();
		list1.add("red");
		list1.add("blue");
		list1.add("yellow");
		List<String> list2 = new ArrayList<>();
		list2.add("red");
		list2.add("blue");
		list2.add("white");
		list2.add("red");

		list1.retainAll(list2);
		System.out.println("using retainAll method");
		list1.forEach(System.out::println);

		System.out.println("using streams");
		List<String> list3 = new ArrayList<>();
		list3.add("red");
		list3.add("green");
		list3.add("black");

		List<String> finalList = list2.stream().distinct().filter(list3::contains).collect(Collectors.toList());
		finalList.forEach(System.out::println);

		/*
		 * print only distinct elements
		 * 
		 * 
		 */

		List<Integer> distinct = new ArrayList<>();
		distinct.add(2);
		distinct.add(4);
		distinct.add(5);
		distinct.add(4);
		Long count = distinct.stream().distinct().count();
		System.out.println("distinct count" + count);
		List<Integer> distinctList = distinct.stream().distinct().collect(Collectors.toList());
		System.out.println("distinct elements are");
		distinctList.forEach(System.out::println);

		int N = 2;
		IntStream.range(1, 11).forEach(i -> System.out.println(N + "X" + i + "=" + N * i));

	}

}
