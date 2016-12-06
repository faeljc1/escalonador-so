package br.com.unifor.escalonador.memorias;

import br.com.unifor.escalonador.entidades.*;
import br.com.unifor.escalonador.swing.App;

import java.util.ArrayList;
import java.util.List;

public class DiscoRigido {
  private int indice;
  private static List<List<IndiceBloco>> blocosVazios = new ArrayList<>();
  Listas lista;

  public DiscoRigido() {
    lista = Listas.getInstance();
    indice = 0;
    lista.listaDiscoRigido = new ArrayList<>();
  }

  public synchronized void addElemento(Processo processo) {
    lista.listaDiscoRigido.add(processo);
    App.lblEspacoUsadoHD.setText("Espaco Usado no Disco Rígido: " + espacoUsadoDiscoRigido());
    Escalonador.exibirDiscoRigido(App.painelListaMemoria);
  }

  public synchronized Processo removeElemento(Processo processo) {
    Processo aux = null;
    for (int i = 0; i < lista.listaDiscoRigido.size(); i++) {
      if (lista.listaDiscoRigido.get(i).getIdentificador() == processo.getIdentificador()) {
        aux = lista.listaDiscoRigido.remove(i);
      }
    }
    App.lblEspacoUsadoHD.setText("Espaco Usado no Disco Rígido: " + espacoUsadoDiscoRigido());
    Escalonador.exibirDiscoRigido(App.painelListaMemoria);
    return aux;
  }

  public synchronized boolean existeProcessoDisco(Processo processo) {
    for (int i = 0; i < lista.listaDiscoRigido.size(); i++) {
      if (lista.listaDiscoRigido.get(i).getIdentificador() == processo.getIdentificador()) {
        return true;
      }
    }
    return false;
  }

  public synchronized long espacoUsadoDiscoRigido() {
    long tamanhoAux = 0;
    for (int i = 0; i < lista.listaDiscoRigido.size(); i++) {
      Processo p = lista.listaDiscoRigido.get(i);
      tamanhoAux += p.getTamanhoMemoria();
    }
    return tamanhoAux;
  }
}
