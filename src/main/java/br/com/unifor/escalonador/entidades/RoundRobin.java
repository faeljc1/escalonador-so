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
      cores.decrementaTempo();
      atualizaPaineis(cores, listas);

      for (int i = 0; i < cores.numeroProcessos(); i++) {
        Processo p = cores.getProcessoCore(i);
        if (p.getTempoRestante() <= 0) {
          listas.finalAddProcesso(p);
          if (!listas.aptosEstaVazio()) {
            cores.inserirCore(i, listas.aptosRemoveProcesso(0));
            cores.removeCore(i + 1);
          } else {
            p.setTempoRestante(0);
            cores.removeCore(i);
          }
        } else if (p.getQuantum() <= 0) {
          int quantumFinal = p.getQuantumFinal();
          p.setQuantum(quantumFinal);
          listas.aptosAddProcesso(p);
          cores.inserirCore(i, listas.aptosRemoveProcesso(0));
          cores.removeCore(i + 1);
        }
      }
      atualizaPaineis(cores, listas);
    }
  }
}