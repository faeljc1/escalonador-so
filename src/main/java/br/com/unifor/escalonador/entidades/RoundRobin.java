package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.swing.App;

public class RoundRobin extends Escalonador {
  private static Listas listas;
  private static Cores cores;

  public synchronized void iniciarAlgoritmo(int numeroCores, long memoria) throws InterruptedException {
    listas = Listas.getInstance();
    cores = new Cores(numeroCores, memoria);
    exibirTela(App.painelAptos, listas.aptos);

    cores.insereCoreAll(listas);
    exibirMemoria(App.painelMemoria);
    exibirTela(App.painelAptos, listas.aptos);
    exibirTela(App.painelExecucao, cores.getCores());

    while (!cores.coreEstaVazio()) {
      cores.insereCoreAll(listas);
      Thread.sleep(1000);
      cores.decrementaTempo(listas);
      atualizaPaineis(cores, listas);
    }
    atualizaPaineis(cores, listas);
  }
}