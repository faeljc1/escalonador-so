package br.com.unifor.escalonador.memorias;

import br.com.unifor.escalonador.entidades.*;
import br.com.unifor.escalonador.swing.App;

import java.util.*;

public class MemoriaQuickFit implements Memoria {
  private final long tamanho;
  private long totalTamanho;
  private int indice;
  private static int numeroRequisicao;

  private static List<List<IndiceBloco>> blocosVazios = new ArrayList<>();
  private static List<QuantidadeRequisicao> listaRequisicoes = new ArrayList<>();

  Listas listas = Listas.getInstance();

  public MemoriaQuickFit(long tamanho) {
    this.tamanho = tamanho;
    totalTamanho = 0;
    indice = 0;
    numeroRequisicao = 0;
    listas.listaMemoria = new ArrayList<>();
    listas.blocoMap = new HashMap<>();
  }

  public synchronized void criaSetor(long tamanhoBloco, Processo elemento) {
    if (existeExpaco(tamanhoBloco)) {
      Bloco b = new Bloco(tamanhoBloco, elemento, null, null);
      listas.listaMemoria.add(indice, b);
      indice++;
      totalTamanho += tamanhoBloco;

      addRequisicao(tamanhoBloco);

      numeroRequisicao++;
      if (numeroRequisicao == 10) {
        mapeiaLista();
      }
    }
  }

  public synchronized void addElemento(long tamanhoBloco, Processo processo) {
    for (int i = 0; i < listas.listaMemoria.size(); i++) {
      Bloco bloco = listas.listaMemoria.get(i);
      if (bloco.getProcesso() == null && bloco.getTamanhoBloco() >= tamanhoBloco) {
        bloco.setProcesso(processo);
        bloco.getProcesso().setTamanhoMemoria(tamanhoBloco);

        addRequisicao(tamanhoBloco);

        numeroRequisicao++;
        if (numeroRequisicao == 10) {
          mapeiaLista();
        }
        break;
      }
    }
  }

  public synchronized Processo removeElemento(Processo processo) {
    Processo aux = null;
    for (int i = 0; i < listas.listaMemoria.size(); i++) {
      Bloco s = listas.listaMemoria.get(i);
      if (s.getProcesso() != null && s.getProcesso().getIdentificador() == processo.getIdentificador()) {
        aux = s.getProcesso();
        s.setProcesso(null);
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
    for (int i = 0; i < listas.listaMemoria.size(); i++) {
      Bloco s = listas.listaMemoria.get(i);
      if (s.getProcesso() == null && s.getTamanhoBloco() >= tamanhoBloco) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean excedeuLimiar() {
    return false;
  }

  public synchronized boolean existeBloco(long tamanhoBloco) {
    if (listas.blocoMap.containsKey(tamanhoBloco)) {
      return true;
    }
    return false;
  }

  public synchronized void addRequisicao(Long tamanhoMemoria) {
    if (!existeQuantidadeRequisicao(tamanhoMemoria)) {
      listaRequisicoes.add(new QuantidadeRequisicao(tamanhoMemoria, 1));
    } else {
      for (QuantidadeRequisicao q : listaRequisicoes) {
        if (q.getTamanho() == tamanhoMemoria) {
          q.setQuantidade(q.getQuantidade() + 1);
          break;
        }
      }
    }
    ordenaLista();
  }

  public synchronized static boolean existeQuantidadeRequisicao(Long tamanho) {
    for (QuantidadeRequisicao q : listaRequisicoes) {
      if (q.getTamanho() == tamanho) {
        return true;
      }
    }
    return false;
  }

  public synchronized void ordenaLista() {
    Collections.sort(listaRequisicoes, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        QuantidadeRequisicao p1 = (QuantidadeRequisicao) o1;
        QuantidadeRequisicao p2 = (QuantidadeRequisicao) o2;
        return p1.getQuantidade() > p2.getQuantidade() ? -1 : (p1.getQuantidade() < p2.getQuantidade() ? +1 : 0);
      }
    });
  }

  public synchronized void mapeiaLista() {
    numeroRequisicao = 0;
    for (int i = 0; i < listaRequisicoes.size(); i++) {
      if (i < 3) {
        listas.blocoMap.put(listaRequisicoes.get(i).getTamanho(), new ArrayList<IndiceBloco>());
      } else {
        listas.blocoMap.put((long) 1000, new ArrayList<IndiceBloco>());
        break;
      }
    }

    for (int i = 0; i < listas.listaMemoria.size(); i++) {
      Bloco b = listas.listaMemoria.get(i);
      if (listas.blocoMap.containsKey(b.getTamanhoBloco())) {
        listas.blocoMap.get(b.getTamanhoBloco()).add(new IndiceBloco(i, b));
      } else {
        listas.blocoMap.get((long) 1000).add(new IndiceBloco(i, b));
      }
    }
    Escalonador.exibirDiscoRigido(App.painelListaMemoria);
    listaRequisicoes = new ArrayList<>();
    Listas.getInstance().blocoMap = new HashMap<>();
  }
}
