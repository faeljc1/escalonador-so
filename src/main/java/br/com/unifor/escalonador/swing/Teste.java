package br.com.unifor.escalonador.swing;

import java.util.*;

public class Teste {
  static List<IntegerI> inteiroOrdenado = new ArrayList<>();

  public static void main(String[] args) {
    List<Integer> numeros = new ArrayList<>();
    numeros.add(8);
    numeros.add(10);
    numeros.add(4);
    numeros.add(5);
    numeros.add(2);
    numeros.add(4);
    numeros.add(10);
    numeros.add(8);
    numeros.add(7);
    numeros.add(5);
    numeros.add(9);
    numeros.add(8);
    numeros.add(8);
    numeros.add(6);

    Map<Integer, Integer> contagem = new HashMap<>();
//Passo 1: Montar um mapa que associa o valor a quantas vezes ele pareceu
    for (int valor : numeros) {
      if (!contagem.containsKey(valor)) {
        contagem.put(valor, 1);
      } else {
        contagem.put(valor, contagem.get(valor) + 1);
      }
    }

    Set<Integer> chaves = contagem.keySet();
    for (Integer valores : chaves) {
      inteiroOrdenado.add(new IntegerI(valores, contagem.get(valores)));
    }
    ordenaLista();
    while (!inteiroOrdenado.isEmpty()) {
      System.out.println(inteiroOrdenado.get(0).getTamanho() + " " + inteiroOrdenado.remove(0).getQuantidade());
    }
  }

  public static synchronized void ordenaLista() {
    Collections.sort(inteiroOrdenado, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        IntegerI p1 = (IntegerI) o1;
        IntegerI p2 = (IntegerI) o2;
        return p1.getQuantidade() > p2.getQuantidade() ? -1 : (p1.getQuantidade() < p2.getQuantidade() ? +1 : 0);
      }
    });
  }
}

class IntegerI {
  private Integer tamanho;
  private Integer quantidade;

  public IntegerI(Integer tamanho, Integer quantidade) {
    this.tamanho = tamanho;
    this.quantidade = quantidade;
  }

  public Integer getTamanho() {
    return tamanho;
  }

  public void setTamanho(Integer tamanho) {
    this.tamanho = tamanho;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }
}
