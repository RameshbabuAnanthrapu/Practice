package com.work.collection.stack;

import java.util.stream.IntStream;

public class ArrayAsStack {

	/*
	 * Stack functionality implemented using Array
	 * 
	 */

	public static void main(String[] args) {

		ArrayStack arrst = new ArrayStack(5);
		IntStream.range(0, 5).forEach(arrst::push);
		System.out.println("Response of peek " + arrst.peek());
		System.out.println("Response of pop" + arrst.pop());
		System.out.println("push one random number in the middle");
		arrst.push(10);
		System.out.println("printing stack after first pop");
		arrst.display();

		IntStream.range(0, arrst.size()).forEach(i -> arrst.pop());

		System.out.println("isEmpty after poping all the elements ???" + arrst.isEmpty());

		arrst.display();

	}

}
