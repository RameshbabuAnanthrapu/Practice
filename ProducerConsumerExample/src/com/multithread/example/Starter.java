package com.multithread.example;

public class Starter {

	public static void main(String[] args) {
		
		
		ProducerAndConsumer pc = new ProducerAndConsumer();
		
		Runnable r1 = () -> {
			try {
				pc.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
	
		Runnable r2 = () -> {
			try {
				pc.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();

	}

}
