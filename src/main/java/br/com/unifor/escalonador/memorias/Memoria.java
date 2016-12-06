package br.com.unifor.escalonador.memorias;

import br.com.unifor.escalonador.entidades.Processo;

public interface Memoria {

  void criaSetor(long tamanhoBloco, Processo elemento);

  void addElemento(long tamanhoBloco, Processo processo);

  Processo removeElemento(Processo processo);

  boolean existeExpaco(long tamanhoBloco);

  boolean existeBlocoVazio(long tamanhoBloco);

  boolean excedeuLimiar();

}
