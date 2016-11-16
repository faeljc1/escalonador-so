package br.com.unifor.escalonador.algoritmos;

import br.com.unifor.escalonador.entidades.Cores;
import br.com.unifor.escalonador.entidades.Escalonador;
import br.com.unifor.escalonador.entidades.Listas;
import br.com.unifor.escalonador.swing.App;

public class LTG extends Escalonador {
  private static Cores cores;
  private static Listas listas;

  public synchronized void iniciarAlgoritmo(int numeroCores) throws InterruptedException {
    cores = new Cores(numeroCores, 0, 0);
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
