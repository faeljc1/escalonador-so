package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.swing.App;

import java.awt.*;
import java.util.*;
import java.util.List;


import javax.swing.*;

public class Escalonador extends SwingWorker<Void, Void> {
  private static Random random = new Random();
  private Processo processo;
  public static List<Processo> filaProcessos;

  static boolean flag;

  private int tipoAlgoritmo;
  private int numeroCores;
  private int numeroProcessos;
  private int quantum;

  private JLabel lblProcesso;

  public Escalonador() {
    this.tipoAlgoritmo = App.comboBox.getSelectedIndex();
    this.numeroCores = Integer.parseInt(App.txfNumeroProcessadores.getText());
    this.numeroProcessos = Integer.parseInt(App.txfProcessosIniciais.getText());
    this.quantum = Integer.parseInt(App.txfQuantum.getText());
    flag = true;
  }

  @Override
  protected Void doInBackground() throws Exception {
    try {
      criarProcessos();
      switch (tipoAlgoritmo) {
        case 1:
          RoundRobin rr = new RoundRobin();
          rr.iniciarAlgoritmo(numeroCores);
          break;

        default:
          break;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void criarProcessos() {
    filaProcessos = new LinkedList<>();
    for (int i = 0; i < numeroProcessos; i++) {
      int tempoTotal = random.nextInt(10) + 4;
      int prioridade = random.nextInt(4);
      int deadLine = random.nextInt(17) + 4;
      int quantumAux = quantum - prioridade;

      processo = new Processo(App.identificador, tempoTotal, tempoTotal, prioridade, quantumAux, quantumAux, deadLine, 0);
      filaProcessos.add(processo);
      App.identificador++;
    }
  }

  public void ordenaLista() {
    Collections.sort(filaProcessos, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        Processo p1 = (Processo) o1;
        Processo p2 = (Processo) o2;
        return p1.getDeadLine() < p2.getDeadLine() ? -1 : (p1.getDeadLine() > p2.getDeadLine() ? +1 : 0);
      }
    });
  }

  public synchronized void exibirTela(JPanel painel, List<Processo> lista) {
    painel.removeAll();
    for (Processo p : lista) {
      lblProcesso = new JLabel("<html><body>Processo: " + p.getIdentificador() + "<br>Tempo Total: "
          + p.getTempoTotal() + "<br>Tempo Restante: " + p.getTempoRestante() + "<br>DeadLine: "
          + p.getDeadLine() + "<br>Prioridade: " + p.getPrioridade() + "<br>Quantidade: " + p.getQuantidade() + "</body></html>");

      if (p.getQuantumFinal() == quantum) {
        lblProcesso.setForeground(Color.red);
      } else if (p.getQuantumFinal() == quantum - 1) {
        lblProcesso.setForeground(Color.blue);
      } else if (p.getQuantumFinal() == quantum - 2) {
        lblProcesso.setForeground(Color.orange);
      }

      painel.add(lblProcesso);
      painel.doLayout();
      painel.repaint();
    }
    painel.doLayout();
    painel.repaint();
  }
}
