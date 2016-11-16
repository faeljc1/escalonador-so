package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.algoritmos.LTG;
import br.com.unifor.escalonador.algoritmos.RoundRobin;
import br.com.unifor.escalonador.memorias.MemoriaBestFit;
import br.com.unifor.escalonador.swing.App;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Escalonador extends SwingWorker<Void, Void> {
  private Processo processo;
  public static List<Processo> filaProcessos;

  public static boolean ltg = false;

  public static boolean flag;

  private int tipoAlgoritmo;
  private int numeroCores;
  private int numeroProcessos;
  private int quantum;
  private long memoria;

  private JLabel lblProcesso;

  public Escalonador() {
    this.tipoAlgoritmo = App.comboBox.getSelectedIndex();
    this.numeroCores = Integer.parseInt(App.txfNumeroProcessadores.getText());
    this.numeroProcessos = Integer.parseInt(App.txfProcessosIniciais.getText());
    this.memoria = Long.parseLong(App.txfMemoria.getText());
    flag = true;
  }

  @Override
  protected Void doInBackground() {
    estadoIniciar();
    try {
      switch (tipoAlgoritmo) {
        case 0:
          this.ltg = true;
          criarProcessos();
          LTG ltg = new LTG();
          ltg.iniciarAlgoritmo(numeroCores);
          break;
        case 1:
          this.ltg = false;
          this.quantum = Integer.parseInt(App.txfQuantum.getText());
          criarProcessos();
          System.out.println(quantum);
          RoundRobin roundRobin = new RoundRobin();
          roundRobin.iniciarAlgoritmo(numeroCores, memoria, tipoAlgoritmo);
          break;
        case 2:
          this.ltg = false;
          this.quantum = Integer.parseInt(App.txfQuantum.getText());
          criarProcessos();
          System.out.println(quantum);
          RoundRobin roundRobinMergeFit = new RoundRobin();
          roundRobinMergeFit.iniciarAlgoritmo(numeroCores, memoria, tipoAlgoritmo);
          break;
        case 3:
          this.ltg = false;
          this.quantum = Integer.parseInt(App.txfQuantum.getText());
          criarProcessos();
          System.out.println(quantum);
          RoundRobin roundRobinQuickFit = new RoundRobin();
          roundRobinQuickFit.iniciarAlgoritmo(numeroCores, memoria, tipoAlgoritmo);
          break;

        default:
          break;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    estadoFinalizou();
    return null;
  }

  protected void done() {
    try {
      estadoParou();
      /*App.painelAbortados.removeAll();
      App.painelAptos.removeAll();
      App.painelExecucao.removeAll();
      App.painelMemoria.removeAll();

      App.painelAbortados.doLayout();
      App.painelAbortados.repaint();
      App.painelAptos.doLayout();
      App.painelAptos.repaint();
      App.painelExecucao.doLayout();
      App.painelExecucao.repaint();
      App.painelMemoria.doLayout();
      App.painelMemoria.repaint();*/

      // Descobre como está o processo. É responsável por lançar
      // as exceptions
      get();

    } catch (ExecutionException e) {
      e.printStackTrace();
      final String msg = String.format("Processo de Escalonamento abortado! \n %s", e.getCause().toString());
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
        }
      });
    } catch (InterruptedException e) {
      System.out.println("Processo foi abortado");
    }
  }

  private void criarProcessos() {
    NumerosRandom random = new NumerosRandom();
    filaProcessos = new LinkedList<>();
    for (int i = 0; i < numeroProcessos; i++) {
      int tempoTotal = random.getTempoTotalRandom();
      int prioridade = random.getPrioridadeRandom();
      int deadLine = random.getDeadLineRandom();
      int quantumAux;
      int tamanhoBloco = random.getTamanhoBlocoRandom();

      if (this.ltg == true) {
        quantumAux = 0;
        prioridade = 0;
      } else {
        if (prioridade == 0) {
          quantumAux = quantum + 3;
        } else if (prioridade == 1) {
          quantumAux = quantum + 2;
        } else if (prioridade == 2) {
          quantumAux = quantum + 1;
        } else {
          quantumAux = quantum;
        }
        deadLine = 0;
      }
      processo = new Processo(App.identificador, tempoTotal, tempoTotal, prioridade, quantumAux, quantumAux, deadLine, 0, tamanhoBloco, false);
      filaProcessos.add(processo);
      App.identificador++;
    }
  }

  public static synchronized void ordenaLista() {
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
          + p.getDeadLine() + "<br>Prioridade: " + p.getPrioridade() + "</body></html>");

      setaCores(lblProcesso, p);

      painel.add(lblProcesso);
      painel.doLayout();
      painel.repaint();
    }
    painel.doLayout();
    painel.repaint();
  }

  public synchronized void exibirMemoria(JPanel painel) {
    painel.removeAll();
    for (Bloco s : MemoriaBestFit.listaMemoria) {
      if (s.getProcesso() != null) {
        lblProcesso = new JLabel("<html><body>Processo: " + s.getProcesso().getIdentificador() + "<br>Memória Processo: "
            + s.getProcesso().getTamanhoMemoria() + "<br>Bloco Memória: " + s.getTamanhoBloco() + "</body></html>");

        painel.add(lblProcesso);
        painel.doLayout();
        painel.repaint();
      }
    }
    painel.doLayout();
    painel.repaint();
  }

  public synchronized void atualizaPaineis(Cores cores, Listas listas) {
    exibirMemoria(App.painelMemoria);
    exibirTela(App.painelExecucao, cores.getCores());
    exibirTela(App.painelAptos, listas.aptos);
    exibirTela(App.painelAbortados, listas.finalAbortados);
  }

  public void estadoIniciar() {
    App.btnIniciar.setEnabled(false);
    App.txfQuantum.setEnabled(false);
    App.txfProcessosIniciais.setEnabled(false);
    App.txfNumeroProcessadores.setEnabled(false);
    App.txfMemoria.setEnabled(false);
  }

  public void estadoFinalizou() {
    App.btnIniciar.setEnabled(true);
    App.txfQuantum.setEnabled(true);
    App.txfProcessosIniciais.setEnabled(true);
    App.txfNumeroProcessadores.setEnabled(true);
    App.txfMemoria.setEnabled(true);
  }

  public void estadoParou() {
    App.btnSelecionar.setEnabled(true);
    App.comboBox.setEnabled(true);
    App.btnIniciar.setEnabled(false);
    App.btnParar.setEnabled(false);

    App.btnAdicionarProcessos.setEnabled(false);
    App.txfProcessosIniciais.setEditable(false);
    App.txfNumeroProcessadores.setEditable(false);
    App.txfMemoria.setEditable(false);
    App.txfQuantum.setEditable(false);

    App.txfProcessosIniciais.setText("");
    App.txfNumeroProcessadores.setText("");
    App.txfQuantum.setText("");
    App.txfMemoria.setText("");
  }

  public synchronized void setaCores(JLabel label, Processo p) {
    /*if (ltg == false) {
      if (p.getPrioridade() == 0) {
        label.setForeground(Color.red);
      } else if (p.getPrioridade() == 1) {
        label.setForeground(Color.blue);
      } else if (p.getPrioridade() == 2) {
        label.setForeground(Color.orange);
      }
    } else if (p.isAbortados() == true) {
      label.setForeground(Color.red);
    }*/

    if (p.isAbortados() == true) {
      label.setForeground(Color.red);
    }
    if (p.getDeadLine() == 99) {
      label.setForeground(new Color(150, 0, 0));
    }
  }
}
