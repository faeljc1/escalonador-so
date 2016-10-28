package br.com.unifor.escalonador.swing;

import br.com.unifor.escalonador.entidades.Memoria;

public class Teste {
  public static void main(String[] args) {
    Memoria memoria = new Memoria(512);
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

    System.out.println(memoria.existeSetorVazio(32));

  }
}
