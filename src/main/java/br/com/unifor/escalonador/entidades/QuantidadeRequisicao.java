package br.com.unifor.escalonador.entidades;

public class QuantidadeRequisicao {
  private long tamanho;
  private Integer quantidade;

  public QuantidadeRequisicao(long tamanho, Integer quantidade) {
    this.tamanho = tamanho;
    this.quantidade = quantidade;
  }

  public long getTamanho() {
    return tamanho;
  }

  public void setTamanho(long tamanho) {
    this.tamanho = tamanho;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

}
