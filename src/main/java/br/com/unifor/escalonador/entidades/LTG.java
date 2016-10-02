package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.swing.App;

public class LTG extends Escalonador {
  private static Cores cores;
  private static Listas listas;

  public synchronized void iniciarAlgoritmo(int numeroCores) throws InterruptedException {
    cores = new Cores(numeroCores);
    ordenaLista();
    listas = Listas.getInstance();
    exibirTela(App.painelAptos, listas.aptos);

    cores.insereCoreAll(listas);
    exibirTela(App.painelAptos, listas.aptos);
    exibirTela(App.painelExecucao, cores.getCores());

    while (!cores.coreEstaVazio() || !listas.aptosEstaVazio()) {
      cores.insereCoreAll(listas);
      Thread.sleep(1000);
      listas.decrementaDeadLine();
      cores.decrementaTempoLtg(listas);
      atualizaPaineis(cores, listas);

    }
  }
}
