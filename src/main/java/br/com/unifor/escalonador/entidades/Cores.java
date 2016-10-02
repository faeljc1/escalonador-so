package br.com.unifor.escalonador.entidades;

import java.util.LinkedList;
import java.util.List;

public class Cores {
  private static List<Processo> cores = new LinkedList<>();
  private int quantidadeCores;

  public Cores(int numeroCores) {
    this.quantidadeCores = numeroCores;
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

  public synchronized void decrementaTempo() {
    if (!cores.isEmpty()) {
      for (Processo p : cores) {
        int tempoRestante = p.getTempoRestante();
        p.setTempoRestante(--tempoRestante);
        int quantum = p.getQuantum();
        p.setQuantum(--quantum);
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
      inserirCore(listas.aptosRemoveProcesso(0));
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
