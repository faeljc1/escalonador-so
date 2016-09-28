package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.entidades.Listas;
import br.com.unifor.escalonador.entidades.Processo;
import br.com.unifor.escalonador.swing.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ActionAdicionarProcessos implements ActionListener {
  private static Random random = new Random();

  public void actionPerformed(ActionEvent e) {
    App.btnAdicionarProcessos.setEnabled(false);
    Listas listas = Listas.getInstance();
    int tempoTotal = random.nextInt(10) + 4;
    int prioridade = random.nextInt(4);
    int deadLine = random.nextInt(17) + 4;
    int quantum = Integer.parseInt(App.txfQuantum.getText());

    Processo processo = new Processo(App.identificador, tempoTotal, tempoTotal, prioridade, quantum, quantum, deadLine, 0);
    App.identificador++;
    listas.aptosAddProcesso(processo);
    try {
      Thread.sleep(250);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    App.btnAdicionarProcessos.setEnabled(true);
  }

}
