package br.com.unifor.escalonador.entidades;

import java.util.*;

public class Memoria {
  private final long tamanho;
  private long totalTamanho;
  private int indice;

  public static List<Setor> listaMemoria = new LinkedList<>();
  private static List<IndiceSetor> setoresVazios = new ArrayList<>();

  public Memoria(long tamanho) {
    this.tamanho = tamanho;
    totalTamanho = 0;
    indice = 0;
  }

  public synchronized void criaSetor(long tamanhoSetor, Processo elemento) {
    if (existeExpaco(tamanhoSetor)) {
      Setor s = new Setor(tamanhoSetor, elemento, null, null);
      listaMemoria.add(indice, s);
      indice++;
      totalTamanho += tamanhoSetor;
    }
  }

  public synchronized void addElemento(long tamanhoSetor, Processo elemento) {
    ordenaSetoresVazios();
    for (IndiceSetor is : setoresVazios) {
      if (is.getSetor().getTamanhoSetor() >= tamanhoSetor) {
        listaMemoria.get(is.getIndiceSetor()).setProcesso(elemento);
        listaMemoria.get(is.getIndiceSetor()).getProcesso().setTamanhoMemoria(tamanhoSetor);
        break;
      }
    }
  }

  public synchronized Processo removeElemento(Processo elemento) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Setor s = listaMemoria.get(i);
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

  public synchronized boolean existeExpaco(long tamanhoSetor) {
    if (this.totalTamanho < this.tamanho && tamanhoSetor <= (tamanho - totalTamanho)) {
      return true;
    } else {
      return false;
    }
  }

  public synchronized boolean existeSetorVazio(long tamanhoSetor) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Setor s = listaMemoria.get(i);
      if (s.getProcesso() == null && s.getTamanhoSetor() >= tamanhoSetor) {
        return true;
      }
    }
    return false;
  }

  public synchronized void ordenaSetoresVazios() {
    setoresVazios = new ArrayList<>();
    for (int i = 0; i < listaMemoria.size(); i++) {
      Setor s = listaMemoria.get(i);
      if (s.getProcesso() == null) {
        IndiceSetor is = new IndiceSetor(i, s);
        setoresVazios.add(is);
      }
    }
    Collections.sort(setoresVazios, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        IndiceSetor p1 = (IndiceSetor) o1;
        IndiceSetor p2 = (IndiceSetor) o2;
        return p1.getSetor().getTamanhoSetor() < p2.getSetor().getTamanhoSetor() ? -1 : (p1.getSetor().getTamanhoSetor() > p2.getSetor().getTamanhoSetor() ? +1 : 0);
      }
    });
  }
}
