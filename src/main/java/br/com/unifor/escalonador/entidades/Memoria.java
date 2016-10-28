package br.com.unifor.escalonador.entidades;

import java.util.*;

public class Memoria {
  private final long tamanho;
  private long totalTamanho;
  private int indice;

  private static List<Setor> listaMemoria = new LinkedList<>();
  private static List<IndiceSetor> setoresVazios = new ArrayList<>();

  public Memoria(long tamanho) {
    this.tamanho = tamanho;
    totalTamanho = 0;
    indice = 0;
  }

  public synchronized void criaSetor(long tamanhoSetor, Object elemento) {
    if (existeExpaco()) {
      Setor s = new Setor(tamanhoSetor, elemento, null, null);
      listaMemoria.add(indice, s);
      indice++;
      totalTamanho += tamanhoSetor;
    }
  }

  public synchronized void addElemento(long tamanhoSetor, Object elemento) {
    ordenaSetoresVazios();
    for (IndiceSetor is : setoresVazios) {
      if (is.getSetor().getTamanhoSetor() >= tamanhoSetor) {
        listaMemoria.get(is.getIndiceSetor()).setElemento(elemento);
        break;
      }
    }
  }

  public synchronized Object removeElemento(Object elemento) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Setor s = listaMemoria.get(i);
      if (s.getElemento().equals(elemento)) {
        Object aux = s.getElemento();
        s.setElemento(null);
        return aux;
      }
    }
    return null;
  }

  public synchronized long getTamanho(long tamanho) {
    return tamanho;
  }

  public synchronized boolean existeExpaco() {
    if (this.totalTamanho < this.tamanho) {
      return true;
    } else {
      return false;
    }
  }

  public synchronized boolean existeSetorVazio(long tamanhoSetor) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Setor s = listaMemoria.get(i);
      if (s.getElemento() == null && s.getTamanhoSetor() == tamanhoSetor) {
        return true;
      }
    }
    return false;
  }

  public synchronized void ordenaSetoresVazios() {
    setoresVazios = new ArrayList<>();
    for (int i = 0; i < listaMemoria.size(); i++) {
      Setor s = listaMemoria.get(i);
      if (s.getElemento() == null) {
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

class IndiceSetor {
  private int indiceSetor;
  private Setor setor;

  public IndiceSetor(int indiceSetor, Setor setor) {
    this.indiceSetor = indiceSetor;
    this.setor = setor;
  }

  public IndiceSetor() {

  }

  public int getIndiceSetor() {
    return indiceSetor;
  }

  public void setIndiceSetor(int indiceSetor) {
    this.indiceSetor = indiceSetor;
  }

  public Setor getSetor() {
    return setor;
  }

  public void setSetor(Setor setor) {
    this.setor = setor;
  }
}
