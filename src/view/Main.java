package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCarro;

public class Main {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(5);

		Semaphore escuderia1 = new Semaphore(1);
		Semaphore escuderia2 = new Semaphore(1);
		Semaphore escuderia3 = new Semaphore(1);
		Semaphore escuderia4 = new Semaphore(1);
		Semaphore escuderia5 = new Semaphore(1);
		Semaphore escuderia6 = new Semaphore(1);
		Semaphore escuderia7 = new Semaphore(1);
		
		
		for (int i = 0; i < 7; i++) {
			for(int j = 0; j < 2; j++) {
				
				Thread carro = new ThreadCarro(i+1, j+1, semaphore, escuderia1, escuderia2, escuderia3, escuderia4, escuderia5, escuderia6, escuderia7);
				carro.start();
			}
			
			
		}

	}

}
