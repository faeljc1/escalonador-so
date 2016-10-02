package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.swing.App;

public class RoundRobin extends Escalonador {
  private static Listas listas;
  private static Cores cores;

  public synchronized void iniciarAlgoritmo(int numeroCores) throws InterruptedException {
    listas = Listas.getInstance();
    cores = new Cores(numeroCores);
    exibirTela(App.painelAptos, listas.aptos);

    cores.insereCoreAll(listas);
    exibirTela(App.painelAptos, listas.aptos);
    exibirTela(App.painelExecucao, cores.getCores());

    while (!cores.coreEstaVazio()) {
      cores.insereCoreAll(listas);
      Thread.sleep(1000);
      cores.decrementaTempo(listas);
      atualizaPaineis(cores, listas);
    }
  }
}