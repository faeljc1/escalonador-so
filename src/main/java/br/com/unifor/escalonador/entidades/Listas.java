package br.com.unifor.escalonador.entidades;

import java.util.LinkedList;
import java.util.List;

public class Listas {
  public List<Processo> aptos;
  public List<Processo> finalAbortados;
  private static Listas uniqueInstance = new Listas();

  private Listas() {
    finalAbortados = new LinkedList<>();
    aptos = Escalonador.filaProcessos;
  }

  public synchronized void decrementaDeadLine() {
    if (!aptos.isEmpty()) {
      for (int i = 0; i < aptos.size(); i++) {
        Processo p = aptos.get(i);
        int deadLine = p.getDeadLine();
        p.setDeadLine(--deadLine);
        if (deadLine <= 0) {
          p.setAbortados(true);
          finalAbortados.add(aptos.remove(i));
          ;
        }
      }
    }
  }

  public static Listas getInstance() {
    return uniqueInstance;
  }

  public synchronized void aptosAddProcesso(Processo p) {
    aptos.add(p);
  }

  public synchronized Processo aptosGetProcesso(int i) {
    return aptos.get(i);
  }

  public synchronized Processo aptosRemoveProcesso(int i) {
    return aptos.remove(i);
  }

  public synchronized boolean aptosEstaVazio() {
    return aptos.isEmpty();
  }

  public synchronized int aptosTamanho() {
    return aptos.size();
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
