package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private static int[][] tempo = new int[14][3];
	private static int posChegada;

	private Semaphore escuderia1;
	private Semaphore escuderia2;
	private Semaphore escuderia3;
	private Semaphore escuderia4;
	private Semaphore escuderia5;
	private Semaphore escuderia6;
	private Semaphore escuderia7;

	private Semaphore semaphore;
	private int escuderia;
	private int numero;

	public ThreadCarro(int escuderia, int numero, Semaphore semaphore, Semaphore escuderia1, Semaphore escuderia2,
			Semaphore escuderia3, Semaphore escuderia4, Semaphore escuderia5, Semaphore escuderia6, Semaphore escuderia7) {
		this.escuderia = escuderia;
		this.numero = numero;

		this.semaphore = semaphore;

		this.escuderia1 = escuderia1;
		this.escuderia2 = escuderia2;
		this.escuderia3 = escuderia3;
		this.escuderia4 = escuderia4;
		this.escuderia5 = escuderia5;
		this.escuderia6 = escuderia6;
		this.escuderia7 = escuderia7;
	}

	@Override
	public void run() {
		try {

			switch (this.escuderia) {
				case 1: escuderia1.acquire(); break;
				case 2: escuderia2.acquire(); break;
				case 3: escuderia3.acquire(); break;
				case 4: escuderia4.acquire(); break;
				case 5: escuderia5.acquire(); break;
				case 6: escuderia6.acquire(); break;
				case 7: escuderia7.acquire(); break;
			}
			semaphore.acquire();

			volta();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {

			switch (this.escuderia) {
				case 1: escuderia1.release(); break;
				case 2: escuderia2.release(); break;
				case 3: escuderia3.release(); break;
				case 4: escuderia4.release(); break;
				case 5: escuderia5.release(); break;
				case 6: escuderia6.release(); break;
				case 7: escuderia7.release(); break;
			}

			semaphore.release();
			chegada();

		}
	}

	private void volta() {
		int[] voltas = new int[3];
		System.out.println("O carro " + numero + " da escuderia " + escuderia + " entrou na pista!");

		for (int i = 0; i < 3; i++) {
			int tempoVolta = (int) (Math.random() * 3000 + 2000);

			try {
				sleep(tempoVolta);

				voltas[i] = tempoVolta;

				int minutos = (tempoVolta / 1000) / 60;
				int segundos = (tempoVolta / 1000) % 60;

				System.out.println("O carro " + numero + " da escuderia " + escuderia + " fez a " + (i + 1)
						+ "a volta!\nTEMPO DA VOLTA: " + String.format("%02d", minutos) + ":"
						+ String.format("%02d", segundos));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < 2; i++) {
			for (int j = i; j < 3; j++) {
				if (voltas[i] > voltas[j]) {
					int temp = voltas[i];
					voltas[i] = voltas[j];
					voltas[j] = temp;
				}
			}
		}

		int[] dados = { voltas[0], escuderia, numero };

		tempo[posChegada++] = dados;
	}

	private void chegada() {

		int minutos = (tempo[posChegada-1][0] / 1000) / 60;
		int segundos = (tempo[posChegada-1][0] / 1000) % 60;
		
		System.out.println("\nATUALIZAÇÃO: O carro " + numero + " da escuderia " + escuderia + " ultrapassou a ultima volta!\nMELHOR VOLTA: "+String.format("%02d", minutos)+":"+String.format("%02d", segundos)+"\n");

		if (posChegada == 14) {
			System.out.println("GRID DE LARGADA:");

			for (int i = 0; i < 13; i++) {
				for (int j = i; j < 14; j++) {
					if (tempo[i][0] > tempo[j][0]) {
						int[] temp = tempo[i];
						tempo[i] = tempo[j];
						tempo[j] = temp;
					}
				}
			}
			System.out.println("POSIÇÂO | ESCUDERIA | CARRO | TEMPO | TEMPO EM MS");
			for (int i = 0; i < 14; i++) {
				
				int minutosVolta = (tempo[i][0] / 1000) / 60;
				int segundosVolta = (tempo[i][0] / 1000) % 60;
				
				if(i < 9)
				System.out.println(
						(i + 1) + "         " + tempo[i][1] + "           " + tempo[i][2] + "       " +String.format("%02d", minutosVolta)+":"+String.format("%02d", segundosVolta)+"  "+tempo[i][0]);
				else
					System.out.println(
							(i + 1) + "        " + tempo[i][1] + "           " + tempo[i][2] + "       " +String.format("%02d", minutosVolta)+":"+String.format("%02d", segundosVolta)+"  "+tempo[i][0]);
				
			}
		}
	}

}
