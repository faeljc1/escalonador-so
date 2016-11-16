package br.com.unifor.escalonador.swing;

import br.com.unifor.escalonador.entidades.QuantidadeRequisicao;

import java.util.*;

public class Teste2 {
  public static void main(String[] args) {
    List<QuantidadeRequisicao> lista = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      System.out.println(Math.pow(2, new Random().nextInt(5) + 3));
    }



    /*for (int i = 0; i < 14; i++) {
      Scanner s = new Scanner(System.in);
      long entrada = s.nextLong();
      System.out.println(Math.pow(2, new Random().nextInt(4) + 4));

      if (!existeQuantidadeRequisicao(entrada, lista)) {
        lista.add(new QuantidadeRequisicao(entrada, 1));
      } else {
        for (QuantidadeRequisicao q : lista) {
          if (q.getTamanho() == entrada) {
            q.setQuantidade(q.getQuantidade() + 1);
            break;
          }
        }
      }
    }
    for (QuantidadeRequisicao q : lista) {
      System.out.print(q.getTamanho() + " " + q.getQuantidade() + " - ");
    }
    ordenaLista(lista);
    System.out.println();
    for (QuantidadeRequisicao q : lista) {
      System.out.print(q.getTamanho() + " " + q.getQuantidade() + " - ");
    }
  }

  public synchronized static boolean existeQuantidadeRequisicao(Long tamanho, List<QuantidadeRequisicao> lista) {
    for (QuantidadeRequisicao q : lista) {
      if (q.getTamanho() == tamanho) {
        return true;
      }
    }
    return false;
  }


  public static synchronized void ordenaLista(List<QuantidadeRequisicao> lista) {
    Collections.sort(lista, new Comparator<Object>() {
      public int compare(Object o1, Object o2) {
        QuantidadeRequisicao p1 = (QuantidadeRequisicao) o1;
        QuantidadeRequisicao p2 = (QuantidadeRequisicao) o2;
        return p1.getQuantidade() > p2.getQuantidade() ? -1 : (p1.getQuantidade() < p2.getQuantidade() ? +1 : 0);
      }
    });
  }*/
  }
}
