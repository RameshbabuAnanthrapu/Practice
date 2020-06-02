package com.work.collection.stack;

import java.util.Stack;
import java.util.stream.IntStream;

public class StackOperations {
	
	/*
	 * Basic stack operations covered here.
	 * pop will get the top most element from stack and the item will be removed from stack
	 * peek will get the top most elenent but I will not delete element from stack.
	 * 
	 * 
	 * */
	
	static void  push(Stack<Integer> intStack) {
		
		IntStream.range(0, 10).forEach(i -> intStack.push(i));
		intStack.forEach(System.out::println);
		
	}
	
	static void pop(Stack<Integer> intStack) {
		IntStream.range(0, intStack.size()).forEach(i -> System.out.println(intStack.pop()));
		System.out.println("Size of stack after pop "+intStack.size());	}
	
	static void peek(Stack<Integer> intStack) {
		
		Integer item = intStack.peek();
		System.out.println("item  after peek "+item);
		System.out.println("Size of Stack after peek" +intStack.size());
	}
	
	static void search(Stack<Integer> intStack, Integer elementtoSearch) {
		
		Integer pos = intStack.search(elementtoSearch);
		if(pos == -1) {
			System.out.println("Item not found");
		}else {
			System.out.println("Item found at position" +pos);
		}
	}
	
	
	
	public static void main(String[] args) {
		Stack<Integer> intStack = new Stack<>();
		push(intStack);
		peek(intStack);
		search(intStack, 5);
		pop(intStack);
		
		
	}

}
