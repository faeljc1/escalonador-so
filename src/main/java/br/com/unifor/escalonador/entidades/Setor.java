package br.com.unifor.escalonador.entidades;

public class Setor {
  private final long tamanhoSetor;
  private Object elemento;
  private Setor proximo;

  public Setor(long tamanhoSetor, Object elemento, Setor proximo, Setor anterior) {
    this.tamanhoSetor = tamanhoSetor;
    this.elemento = elemento;
    this.proximo = proximo;
  }

  public Setor(long tamanhoSetor, Object elemento) {
    this.tamanhoSetor = tamanhoSetor;
    this.elemento = elemento;
    this.proximo = null;
  }

  public long getTamanhoSetor() {
    return tamanhoSetor;
  }

  public Object getElemento() {
    return elemento;
  }

  public void setElemento(Object elemento) {
    this.elemento = elemento;
  }

  public Setor getProximo() {
    return proximo;
  }

  public void setProximo(Setor proximo) {
    this.proximo = proximo;
  }
}
