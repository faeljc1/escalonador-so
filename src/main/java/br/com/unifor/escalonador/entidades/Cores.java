package br.com.unifor.escalonador.entidades;

import java.util.LinkedList;
import java.util.List;

public class Cores {
  private static List<Processo> cores = new LinkedList<>();
  private int quantidadeCores;
  private long tamanhoMemoria;
  private Memoria memoria;

  public Cores(int numeroCores, long tamanhoMemoria) {
    this.quantidadeCores = numeroCores;
    this.tamanhoMemoria = tamanhoMemoria;
    memoria = new Memoria(tamanhoMemoria);
  }

  public synchronized boolean coreCheio() {
    if (cores.size() < quantidadeCores) {
      return false;
    } else {
      return true;
    }
  }

  public synchronized void inserirCore(int indice, Processo processo) {
    cores.add(indice, processo);
  }

  public synchronized void inserirCore(Processo processo) {
    cores.add(processo);
  }

  public synchronized Processo removeCore(int indice) {
    return cores.remove(indice);
  }

  public synchronized Processo getProcessoCore(int indice) {
    return cores.get(indice);
  }

  public synchronized int numeroProcessos() {
    return cores.size();
  }

  public synchronized List<Processo> getCores() {
    return cores;
  }

  public synchronized void decrementaTempo(Listas lista) {
    if (!cores.isEmpty()) {
      for (int i = 0; i < cores.size(); i++) {
        Processo p = cores.get(i);
        int tempoRestante = p.getTempoRestante();
        p.setTempoRestante(--tempoRestante);
        int quantum = p.getQuantum();
        p.setQuantum(--quantum);
        if (tempoRestante <= 0) {
          lista.finalAddProcesso(p);
          if (!lista.aptosEstaVazio()) {
            cores.remove(i);
            cores.add(i, lista.aptosRemoveProcesso(0));
          } else {
            cores.remove(i);
          }
        } else if (quantum <= 0) {
          int quantumFinal = p.getQuantumFinal();
          p.setQuantum(quantumFinal);
          lista.aptosAddProcesso(p);
          cores.remove(i);
          cores.add(i, lista.aptosRemoveProcesso(0));
        }
      }
    }
  }

  public synchronized int retornaIndiceCore(Processo p) {
    return cores.indexOf(p);
  }

  public synchronized boolean coreEstaVazio() {
    if (cores.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public synchronized void insereCoreAll(Listas listas) {
    while (!coreCheio() && !listas.aptosEstaVazio()) {
      Processo p = listas.aptosRemoveProcesso(0);
      if (memoria.existeExpaco(p.getTamanhoMemoria())) {
        memoria.criaSetor(p.getTamanhoMemoria(), p);
        inserirCore(p);
      } else if (memoria.existeSetorVazio(p.getTamanhoMemoria())) {
        memoria.addElemento(p.getTamanhoMemoria(), p);
        inserirCore(p);
      } else {
        listas.finalAddProcesso(p);
      }
    }

  }

  public synchronized void decrementaTempoLtg(Listas lista) {
    if (!cores.isEmpty()) {
      for (int i = 0; i < cores.size(); i++) {
        Processo p = cores.get(i);
        int tempoRestante = p.getTempoRestante();
        p.setTempoRestante(--tempoRestante);
        if (tempoRestante <= 0) {
          lista.finalAddProcesso(cores.remove(i));
        }
      }
    }
  }

}
