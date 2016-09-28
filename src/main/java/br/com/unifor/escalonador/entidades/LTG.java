package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.swing.App;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;


public class LTG {
  // PROCESSO int identificador, int tempoTotal, int tempoRestante, String
  // estado, int prioridade, int deadLine, int inicio, int fim

  private List<Processo> filaAptos = new LinkedList<Processo>();
  // private List<Processo> filaEmExcecucao = new LinkedList<Processo>();
  JLabel[] cores;
  private Processo processo;
  private JLabel lblProcesso;

  static Random random = new Random();

  public List<Processo> iniciaAlgoritmo(int numProcessos) {
    for (int i = 0; i < numProcessos; i++) {
      int tempoTotal = random.nextInt(17) + 4;
      int prioridade = random.nextInt(4);
      int deadLine = random.nextInt(17) + 4;

      processo = new Processo(App.identificador, tempoTotal, tempoTotal, prioridade, 0, 0, deadLine, 0);
      filaAptos.add(processo);
      App.identificador++;
    }
    ordenaLista(null);
    return filaAptos;
  }

  public JLabel[] executaLTG(int numCores, int contador) {
    cores = new JLabel[numCores];
    for (int i = 0; i < cores.length; i++) {
      if (filaAptos.size() != 0) {
        processo = filaAptos.get(i);
        lblProcesso = new JLabel("<html><body>Processo: " + processo.getIdentificador() + "<br>Tempo Total: "
            + processo.getTempoTotal() + "<br>Tempo Restante: " + processo.getTempoRestante() + "<br>DeadLine: "
            + processo.getDeadLine() + "</body></html>");

        cores[i] = lblProcesso;
      }
    }
    return cores;
  }

	/*
   * public static void main(String[] args) { iniciaAlgoritmo(20); while (!filaAptos.isEmpty()) { processo = filaAptos.remove(0);
	 * 
	 * System.out.println(processo.getIdentificador() + " " + processo.getTempoTotal() + " " + processo.getTempoRestante() + " " + processo.getEstado() + " " + processo.getPrioridade() + "   " +
	 * processo.getDeadLine() + "    " + processo.getInicio() + " " + processo.getFim()); } }
	 */

  public void ordenaLista(List fila) {
    Collections.sort(filaAptos, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        Processo p1 = (Processo) o1;
        Processo p2 = (Processo) o2;
        return p1.getDeadLine() < p2.getDeadLine() ? -1 : (p1.getDeadLine() > p2.getDeadLine() ? +1 : 0);
      }
    });
  }

}
