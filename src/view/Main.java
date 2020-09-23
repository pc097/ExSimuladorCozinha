package view;

import java.util.concurrent.Semaphore;

import controller.Prato;

public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for (int idPrato = 0; idPrato < 5; idPrato++) {
			Thread prato = new Prato(idPrato, semaforo);
			prato.start();
		}
	}
}
