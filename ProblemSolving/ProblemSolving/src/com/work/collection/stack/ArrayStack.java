package com.work.collection.stack;

import java.util.NoSuchElementException;

public class ArrayStack {

	int[] arr;
	int index = -1;
	int size = 0;
	int length = 0;

	public ArrayStack(int n) {
		size = n;
		arr = new int[size];

	}

	public void push(int i1) {
		if (index + 1 >= size) {
			throw new IndexOutOfBoundsException("OverFlow Exception");
		}

		arr[++index] = i1;
		++length;
		System.out.println("Current index for push" + index);

	}

	public int peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("UnderFlow exception");
		}

		return arr[index];
	}

	public int pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("UnderFlow exception");
		}
		--length;
		System.out.println("present index is" + index);
		return arr[index--];

	}

	public boolean isEmpty() {

		return index == -1;

	}

	public boolean isFull() {

		return index == size - 1;

	}

	public void display() {

		if (isEmpty()) {
			System.out.println("No elements Found");
			return;
		}

		for (int i = index; i >= 0; i--) {
			System.out.println(arr[i] + " ");

		}

	}

	public int size() {
		System.out.println("index is" + index);
		return length;
	}
}
