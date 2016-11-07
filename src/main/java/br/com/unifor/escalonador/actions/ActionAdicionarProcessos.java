package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.entidades.Escalonador;
import br.com.unifor.escalonador.entidades.Listas;
import br.com.unifor.escalonador.entidades.NumerosRandom;
import br.com.unifor.escalonador.entidades.Processo;
import br.com.unifor.escalonador.swing.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ActionAdicionarProcessos implements ActionListener {
  private int quantum;

  public void actionPerformed(ActionEvent e) {
    NumerosRandom random = new NumerosRandom();
    App.btnAdicionarProcessos.setEnabled(false);
    Listas listas = Listas.getInstance();
    int tempoTotal = random.getTempoTotalRandom();
    int prioridade = random.getPrioridadeRandom();
    int deadLine = random.getDeadLineRandom();
    int tamanhoBloco = random.getTamanhoBlocoRandom();

    if (Escalonador.ltg == true) {
      quantum = 0;
      prioridade = 0;
    } else {
      quantum = Integer.parseInt(App.txfQuantum.getText());
      deadLine = 0;
    }

    Processo processo = new Processo(App.identificador, tempoTotal, tempoTotal, prioridade, quantum, quantum, deadLine, 0, tamanhoBloco, false);
    App.identificador++;
    listas.aptosAddProcesso(processo);
    if (Escalonador.ltg == true) {
      Escalonador.ordenaLista();
    }
    try {
      Thread.sleep(250);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    App.btnAdicionarProcessos.setEnabled(true);
  }

}
