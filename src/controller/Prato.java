package controller;

import java.util.concurrent.Semaphore;

public class Prato extends Thread {

	private int idPrato;
	private Semaphore semaforo;

	public Prato(int idPrato, Semaphore semaforo) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}

	public void run() {
		cozimento();
	}

	private void cozimento() {
		String nomePrato;
		long tempoPreparo;
		if (idPrato % 2 == 1) {
			nomePrato = "Sopa de Cebola";
			tempoPreparo = (int) ((Math.random() * 301) + 500);
		} else {
			nomePrato = "Lasanha a Bolonhesa";
			tempoPreparo = (int) ((Math.random() * 601) + 600);
		}
		System.out.println("O prato #" + idPrato + " (" + nomePrato + ") " + " começou a ser preparado!");
		double porcentagem = 100/(tempoPreparo/100);
		int numSleep = 100;
		int aux = 1;
		while (numSleep < tempoPreparo) {
			try {
				sleep(100);
				System.out.println("Prato #" + idPrato + " (" + nomePrato + ") " + " -> " + (porcentagem*aux));
				numSleep += 100;
				aux++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("O prato #" + idPrato + " está pronto!");
		try {
			semaforo.acquire();
			entregarPrato(nomePrato);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void entregarPrato(String nomePrato) {
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("O prato #" + idPrato + " (" + nomePrato + ") " + " está servido!");
		}
	}
}
