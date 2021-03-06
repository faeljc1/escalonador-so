package br.com.unifor.escalonador.memorias;

import br.com.unifor.escalonador.entidades.Bloco;
import br.com.unifor.escalonador.entidades.IndiceBloco;
import br.com.unifor.escalonador.entidades.Listas;
import br.com.unifor.escalonador.entidades.Processo;
import br.com.unifor.escalonador.swing.App;

import java.util.*;

public class MemoriaBestFit implements Memoria {
  private final long tamanho;
  private long totalTamanho;
  private int indice;
  private int limiar;

  private static List<List<IndiceBloco>> blocosVazios = new ArrayList<>();
  private static Map<Long, List<IndiceBloco>> blocoMap = new HashMap<>();
  private List<IndiceBloco> lista;

  public MemoriaBestFit(long tamanho) {
    this.tamanho = tamanho;
    totalTamanho = 0;
    indice = 0;
    Listas.getInstance().listaMemoria = new ArrayList<>();
    limiar = Integer.parseInt(App.txfLimiar.getText());
  }

  public synchronized void criaSetor(long tamanhoBloco, Processo elemento) {
    if (existeExpaco(tamanhoBloco)) {
      Bloco b = new Bloco(tamanhoBloco, elemento, null, null);
      while (indice > Listas.getInstance().listaMemoria.size()) {
        --indice;
      }
      Listas.getInstance().listaMemoria.add(indice, b);
      indice++;
      totalTamanho += tamanhoBloco;
    }
    App.lblEspacoUsadoMemoria.setText("Espaco Usado na Mem\u00F3ria: " + espacoUsadoMemoria());
  }

  public synchronized void addElemento(long tamanhoBloco, Processo processo) {
    for (int i = 0; i < Listas.getInstance().listaMemoria.size(); i++) {
      Bloco bloco = Listas.getInstance().listaMemoria.get(i);
      if (bloco.getProcesso() == null && bloco.getTamanhoBloco() >= tamanhoBloco) {
        bloco.setProcesso(processo);
        bloco.getProcesso().setTamanhoMemoria(tamanhoBloco);
        break;
      }
    }
    App.lblEspacoUsadoMemoria.setText("Espaco Usado na Mem\u00F3ria: " + espacoUsadoMemoria());
  }

  public synchronized Processo removeElemento(Processo processo) {
    Processo aux = null;
    for (int i = 0; i < Listas.getInstance().listaMemoria.size(); i++) {
      Bloco s = Listas.getInstance().listaMemoria.get(i);
      if (s.getProcesso() != null && s.getProcesso().getIdentificador() == processo.getIdentificador()) {
        aux = s.getProcesso();
        s.setProcesso(null);
      }
    }
    App.lblEspacoUsadoMemoria.setText("Espaco Usado na Mem\u00F3ria: " + espacoUsadoMemoria());
    return aux;
  }

  public synchronized long getTamanho(long tamanho) {
    return tamanho;
  }

  public synchronized boolean existeExpaco(long tamanhoBloco) {
    if (this.totalTamanho < this.tamanho && tamanhoBloco <= (tamanho - totalTamanho)) {
      return true;
    } else {
      return false;
    }
  }

  public synchronized boolean existeBlocoVazio(long tamanhoBloco) {
    for (int i = 0; i < Listas.getInstance().listaMemoria.size(); i++) {
      Bloco s = Listas.getInstance().listaMemoria.get(i);
      if (s.getProcesso() == null && s.getTamanhoBloco() >= tamanhoBloco) {
        return true;
      }
    }
    return false;
  }

  public synchronized boolean existeBloco(long tamanhoBloco) {
    if (blocoMap.containsKey(tamanhoBloco)) {
      return true;
    }
    return false;
  }

  public synchronized boolean excedeuLimiar() {
    if ((espacoUsadoMemoria() * 100) / tamanho >= limiar) {
      return true;
    } else {
      return false;
    }
  }

  public synchronized long espacoUsadoMemoria() {
    long tamanhoAux = 0;
    for (int i = 0; i < Listas.getInstance().listaMemoria.size(); i++) {
      Bloco s = Listas.getInstance().listaMemoria.get(i);
      if (s.getProcesso() != null) {
        tamanhoAux += s.getTamanhoBloco();
      }
    }
    return tamanhoAux;
  }
}
