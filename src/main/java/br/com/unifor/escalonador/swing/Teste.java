package br.com.unifor.escalonador.swing;

import java.util.Random;

public class Teste {
  public static void main(String[] args) {
    /*MemoriaBestFit memoria = new MemoriaBestFit(512);
    memoria.criaSetor(64, "A");
    memoria.criaSetor(32, "B");
    memoria.criaSetor(32, "C");
    memoria.criaSetor(64, "D");
    memoria.criaSetor(32, "E");
    memoria.criaSetor(32, "F");

    memoria.criaSetor(32, "G");
    memoria.criaSetor(32, "H");
    memoria.criaSetor(64, "I");
    memoria.criaSetor(32, "J");
    memoria.criaSetor(32, "K");
    memoria.criaSetor(64, "L");

    System.out.println(memoria.existeExpaco());

    memoria.removeElemento("C");

    System.out.println(memoria.existeSetorVazio(32));

    memoria.addElemento(32, "X");

    System.out.println(memoria.existeSetorVazio(32));*/

    Random random = new Random();
    for (int i = 0; i < 30; i++) {
      //int x = (int) Math.pow(2, random.nextInt(5) + 3);

      int x = random.nextInt(25) + 1;
      if (x > 5 && x < 11) {
        System.out.print(x + " ");

      }
    }
  }
}
