package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.entidades.Escalonador;
import br.com.unifor.escalonador.entidades.Listas;
import br.com.unifor.escalonador.entidades.Processo;
import br.com.unifor.escalonador.swing.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ActionAdicionarProcessos implements ActionListener {
  private static Random random = new Random();
  private int quantum;

  public void actionPerformed(ActionEvent e) {
    App.btnAdicionarProcessos.setEnabled(false);
    Listas listas = Listas.getInstance();
    int tempoTotal = random.nextInt(10) + 4;
    int prioridade = random.nextInt(4);
    int deadLine = random.nextInt(17) + 4;

    if (Escalonador.ltg == true) {
      quantum = 0;
      prioridade = 0;
    } else {
      quantum = Integer.parseInt(App.txfQuantum.getText());
      deadLine = 0;
    }

    Processo processo = new Processo(App.identificador, tempoTotal, tempoTotal, prioridade, quantum, quantum, deadLine, 0, false);
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
