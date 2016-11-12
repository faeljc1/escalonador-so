package br.com.unifor.escalonador.entidades;

import br.com.unifor.escalonador.memorias.MemoriaBestFit;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cores {
  public static List<Processo> cores = new LinkedList<>();
  private int quantidadeCores;
  private long tamanhoMemoria;
  private MemoriaBestFit memoria;

  public Cores(int numeroCores, long tamanhoMemoria) {
    this.quantidadeCores = numeroCores;
    this.tamanhoMemoria = tamanhoMemoria;
    memoria = new MemoriaBestFit(tamanhoMemoria);
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
          memoria.removeElemento(p);
          if (!lista.aptosEstaVazio()) {
            cores.remove(i);
            Processo aux = lista.aptosRemoveProcesso(0);
            addMemoria(lista, aux, i);
          } else {
            cores.remove(i);
          }
        } else if (quantum <= 0) {
          int quantumFinal = p.getQuantumFinal();
          p.setQuantum(quantumFinal);
          memoria.removeElemento(p);
          lista.aptosAddProcesso(p);
          cores.remove(i);
          Processo aux = lista.aptosRemoveProcesso(0);
          addMemoria(lista, aux, i);
        } else {
          Random r = new Random();
          int requisicao = r.nextInt(25) + 1;
          if (requisicao > 5 && requisicao < 11) {
            addMemoriaRequisicao(lista, p);
          }
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
      addMemoria(listas, p, null);
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

  public synchronized void addMemoria(Listas lista, Processo aux, Integer i) {
    if (memoria.existeExpaco(aux.getTamanhoMemoria())) {
      memoria.criaSetor(aux.getTamanhoMemoria(), aux);
      if (i == null) {
        inserirCore(aux);
      } else {
        cores.add(i, aux);
      }
    } else if (memoria.existeBlocoVazio(aux.getTamanhoMemoria())) {
      memoria.addElemento(aux.getTamanhoMemoria(), aux);
      if (i == null) {
        inserirCore(aux);
      } else {
        cores.add(i, aux);
      }
    } else {
      aux.setAbortados(true);
      lista.finalAddProcesso(aux);
    }
  }

  public synchronized void addMemoriaRequisicao(Listas lista, Processo aux) {
    long tamanho = new NumerosRandom().getTamanhoBlocoRandom();
    System.out.println("Processo: " + aux.getIdentificador() + " / Tamanho Inicial: " + aux.getTamanhoMemoria() + " / Novo Tamanho: " + tamanho);
    aux.setTamanhoMemoria(tamanho);
    if (memoria.existeExpaco(aux.getTamanhoMemoria())) {
      memoria.criaSetor(aux.getTamanhoMemoria(), aux);
    } else if (memoria.existeBlocoVazio(aux.getTamanhoMemoria())) {
      memoria.addElemento(aux.getTamanhoMemoria(), aux);
    } else {
      aux.setDeadLine(99);
      aux.setAbortados(true);
      lista.finalAddProcesso(aux);
      cores.remove(aux);
      memoria.removeElemento(aux);
    }
  }
}
