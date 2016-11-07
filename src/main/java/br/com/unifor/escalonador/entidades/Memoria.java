package br.com.unifor.escalonador.entidades;

import java.util.*;

public class Memoria {
  private final long tamanho;
  private long totalTamanho;
  private int indice;

  public static List<Bloco> listaMemoria = new LinkedList<>();
  private static List<List<IndiceBloco>> blocosVazios = new ArrayList<>();
  private static Map<Long, List<IndiceBloco>> blocoMap = new HashMap<>();
  private List<IndiceBloco> lista;

  public Memoria(long tamanho) {
    this.tamanho = tamanho;
    totalTamanho = 0;
    indice = 0;
  }

  public synchronized void criaSetor(long tamanhoBloco, Processo elemento) {
    if (existeExpaco(tamanhoBloco)) {
      Bloco b = new Bloco(tamanhoBloco, elemento, null, null);
      listaMemoria.add(indice, b);

      IndiceBloco ib = new IndiceBloco(indice, b);
      if (!existeBloco(tamanhoBloco)) {
        lista = new LinkedList<>();
        lista.add(ib);
        blocoMap.put(tamanhoBloco, lista);
      } else {
        lista = blocoMap.get(tamanhoBloco);
        lista.add(ib);
      }

      indice++;
      totalTamanho += tamanhoBloco;
    }
  }

  public synchronized void addElemento(long tamanhoBloco, Processo processo) {
    for (int i = 0; i < listaMemoria.size(); i++) {
      Bloco bloco = listaMemoria.get(i);
      if (bloco.getProcesso() == null && bloco.getTamanhoBloco() >= tamanhoBloco) {
        bloco.setProcesso(processo);
        bloco.getProcesso().setTamanhoMemoria(tamanhoBloco);

        lista = blocoMap.get(tamanhoBloco);
        for (IndiceBloco ib : lista) {
          if (ib.getIndiceBloco() == i) {
            ib.getBloco().setProcesso(processo);
            ib.getBloco().getProcesso().setTamanhoMemoria(tamanhoBloco);
          }
          break;
        }
        break;
      }
    }
  }

  public synchronized Processo removeElemento(Processo processo) {
    Processo aux = null;
    for (int i = 0; i < listaMemoria.size(); i++) {
      Bloco s = listaMemoria.get(i);
      if (s.getProcesso() != null && s.getProcesso().getIdentificador() == processo.getIdentificador()) {
        aux = s.getProcesso();
        s.setProcesso(null);

        lista = blocoMap.get(aux.getTamanhoMemoria());
        for (IndiceBloco ib : lista) {
          if (ib.getIndiceBloco() == i) {
            ib.getBloco().setProcesso(null);
          }
          break;
        }
      }
    }
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
    for (int i = 0; i < listaMemoria.size(); i++) {
      Bloco s = listaMemoria.get(i);
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
}
