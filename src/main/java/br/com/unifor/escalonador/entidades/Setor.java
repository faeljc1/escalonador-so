package br.com.unifor.escalonador.entidades;

public class Setor {
  private final long tamanhoSetor;
  private Processo processo;
  private Setor proximo;

  public Setor(long tamanhoSetor, Processo processo, Setor proximo, Setor anterior) {
    this.tamanhoSetor = tamanhoSetor;
    this.processo = processo;
    this.proximo = proximo;
  }

  public Setor(long tamanhoSetor, Processo elemento) {
    this.tamanhoSetor = tamanhoSetor;
    this.processo = elemento;
    this.proximo = null;
  }

  public long getTamanhoSetor() {
    return tamanhoSetor;
  }

  public Processo getProcesso() {
    return processo;
  }

  public void setProcesso(Processo processo) {
    this.processo = processo;
  }

  public Setor getProximo() {
    return proximo;
  }

  public void setProximo(Setor proximo) {
    this.proximo = proximo;
  }
}
