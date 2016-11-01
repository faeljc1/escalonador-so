package br.com.unifor.escalonador.entidades;

public class Bloco {
  private final long tamanhoBloco;
  private Processo processo;
  private Bloco proximo;

  public Bloco(long tamanhoBloco, Processo processo, Bloco proximo, Bloco anterior) {
    this.tamanhoBloco = tamanhoBloco;
    this.processo = processo;
    this.proximo = proximo;
  }

  public Bloco(long tamanhoBloco, Processo elemento) {
    this.tamanhoBloco = tamanhoBloco;
    this.processo = elemento;
    this.proximo = null;
  }

  public long getTamanhoBloco() {
    return tamanhoBloco;
  }

  public Processo getProcesso() {
    return processo;
  }

  public void setProcesso(Processo processo) {
    this.processo = processo;
  }

  public Bloco getProximo() {
    return proximo;
  }

  public void setProximo(Bloco proximo) {
    this.proximo = proximo;
  }
}
