package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.swing.App;

import java.util.LinkedList;
import java.util.List;

public class RoundRobin extends Escalonador {

  public synchronized void iniciarAlgoritmo(int numeroCores) throws InterruptedException {
    Listas listas = Listas.getInstance();
    //quantum = listas.aptosGetProcesso(0).getQuantum();
    exibirTela(App.painelAptos, listas.aptos1);
    while (!listas.aptosEstaVazio() || !listas.coreEstaVazio()) {
      while (!listas.aptosEstaVazio() && listas.coreTamanho() < numeroCores) {
        listas.coreAddProcesso(listas.aptosRemoveProcesso(0));
        exibirTela(App.painelAptos, listas.aptos1);
        exibirTela(App.painelExecucao, listas.cores);
      }
      for (int i = 0; i < listas.cores.size(); i++) {
        if (!listas.coreEstaVazio()) {
          Processo p = listas.coreGetProcesso(i);
          int quantumRestante = p.getQuantum();
          int tempoRestante = p.getTempoRestante();
          p.setTempoRestante(--tempoRestante);
          p.setQuantum(--quantumRestante);
          exibirTela(App.painelExecucao, listas.cores);
          if (p.getTempoRestante() == 0) {
            p.setQuantidade(p.getQuantidade() + 1);
            listas.finalAddProcesso(listas.coreRemoveProcessoCore(i));
            exibirTela(App.painelAbortados, listas.finalAbortados);
            break;
          } else if (p.getQuantum() == 0) {
            p.setQuantum(p.getQuantumFinal());
            p.setQuantidade(p.getQuantidade() + 1);
            listas.aptosAddProcesso(listas.coreRemoveProcessoCore(i));
            break;
          }
        }
      }
      Thread.sleep(500);
    }
    App.painelExecucao.removeAll();
    App.painelAptos.doLayout();
    App.painelExecucao.doLayout();
    App.painelAptos.repaint();
    App.painelExecucao.repaint();
  }
}
