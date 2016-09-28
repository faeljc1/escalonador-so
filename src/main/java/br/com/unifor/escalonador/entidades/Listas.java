package br.com.unifor.escalonador.entidades;

import java.util.LinkedList;
import java.util.List;

public class Listas {
  public List<Processo> cores;
  public List<Processo> aptos1;
  private List<Processo> aptos2;
  private List<Processo> aptos3;
  private List<Processo> aptos4;
  public List<Processo> finalAbortados;
  private static Listas uniqueInstance = new Listas();

  private Listas() {
    cores = new LinkedList<>();
    finalAbortados = new LinkedList<>();
    aptos1 = Escalonador.filaProcessos;
  }

  public static Listas getInstance() {
    return uniqueInstance;
  }

  public synchronized void coreAddProcesso(Processo p) {
    cores.add(p);
  }

  public synchronized Processo coreGetProcesso(int i) {
    return cores.get(i);
  }

  public synchronized Processo coreRemoveProcessoCore(int i) {
    return cores.remove(i);
  }

  public synchronized boolean coreEstaVazio() {
    return cores.isEmpty();
  }

  public synchronized int coreTamanho() {
    return cores.size();
  }

  public synchronized void aptosAddProcesso(Processo p) {
    aptos1.add(p);
  }

  public synchronized Processo aptosGetProcesso(int i) {
    return aptos1.get(i);
  }

  public synchronized Processo aptosRemoveProcesso(int i) {
    return aptos1.remove(i);
  }

  public synchronized boolean aptosEstaVazio() {
    return aptos1.isEmpty();
  }

  public synchronized int aptosTamanho() {
    return aptos1.size();
  }

  public synchronized void finalAddProcesso(Processo p) {
    finalAbortados.add(p);
  }

  public synchronized Processo finalGetProcesso(int i) {
    return finalAbortados.get(i);
  }

  public synchronized Processo finalRemoveProcesso(int i) {
    return finalAbortados.remove(i);
  }

  public synchronized boolean finalEstaVazio() {
    return finalAbortados.isEmpty();
  }

  public synchronized int finalTamanho() {
    return finalAbortados.size();
  }
}
