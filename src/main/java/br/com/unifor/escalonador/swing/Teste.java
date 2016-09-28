package br.com.unifor.escalonador.swing;

import java.util.Random;

public class Teste {
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(new Random().nextInt(19) + 2);
		}
	}
}
