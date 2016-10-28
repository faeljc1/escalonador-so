package br.com.unifor.escalonador.entidades;

public class IndiceSetor {
  private int indiceSetor;
  private Setor setor;

  public IndiceSetor(int indiceSetor, Setor setor) {
    this.indiceSetor = indiceSetor;
    this.setor = setor;
  }

  public IndiceSetor() {

  }

  public int getIndiceSetor() {
    return indiceSetor;
  }

  public void setIndiceSetor(int indiceSetor) {
    this.indiceSetor = indiceSetor;
  }

  public Setor getSetor() {
    return setor;
  }

  public void setSetor(Setor setor) {
    this.setor = setor;
  }
}