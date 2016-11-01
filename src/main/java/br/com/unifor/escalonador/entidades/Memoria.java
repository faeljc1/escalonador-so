package br.com.unifor.escalonador.entidades;

import java.util.*;

public class Memoria {
  private final long tamanho;
  private long totalTamanho;
  private int indice;

  public static List<Bloco> listaMemoria = new LinkedList<>();
  private static List<IndiceBloco> blocosVazios = new ArrayList<>();

  public Memoria(long tamanho) {
    this.tamanho = tamanho;
    totalTamanho = 0;
    indice = 0;
  }

  public synchronized void criaSetor(long tamanhoBloco, Processo elemento) {
    if (existeExpaco(tamanhoBloco)) {
      Bloco s = new Bloco(tamanhoBloco, elemento, null, null);
      listaMemoria.add(indice, s);
      indice++;
      totalTamanho += tamanhoBloco;
    }
  }

  public synchronized void addElemento(long tamanhoBloco, Processo elemento) {
    ordenaSetoresVazios();
    for (IndiceBloco is : blocosVazios) {
      if (is.getSetor().getTamanhoBloco() >= tamanhoBloco) {
        listaMemoria.get(is.getIndiceBloco()).setProcesso(elemento);
        listaMemoria.get(is.getIndiceBloco()).getProcesso().setTamanhoMemoria(tamanhoBloco);
        break;
      }
    }
  }

  public synchronized Processo removeElemento(Processo elemento) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Bloco s = listaMemoria.get(i);
      if (s.getProcesso() != null && s.getProcesso().equals(elemento)) {
        Processo aux = s.getProcesso();
        s.setProcesso(null);
        return aux;
      }
    }
    return null;
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

  public synchronized boolean existeSetorVazio(long tamanhoBloco) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Bloco s = listaMemoria.get(i);
      if (s.getProcesso() == null && s.getTamanhoBloco() >= tamanhoBloco) {
        return true;
      }
    }
    return false;
  }

  public synchronized void ordenaSetoresVazios() {
    blocosVazios = new ArrayList<>();
    for (int i = 0; i < listaMemoria.size(); i++) {
      Bloco s = listaMemoria.get(i);
      if (s.getProcesso() == null) {
        IndiceBloco is = new IndiceBloco(i, s);
        blocosVazios.add(is);
      }
    }
    Collections.sort(blocosVazios, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        IndiceBloco p1 = (IndiceBloco) o1;
        IndiceBloco p2 = (IndiceBloco) o2;
        return p1.getSetor().getTamanhoBloco() < p2.getSetor().getTamanhoBloco() ? -1 : (p1.getSetor().getTamanhoBloco() > p2.getSetor().getTamanhoBloco() ? +1 : 0);
      }
    });
  }
}
