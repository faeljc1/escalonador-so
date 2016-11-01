package br.com.unifor.escalonador.entidades;

public class IndiceBloco {
  private int indiceBloco;
  private Bloco setor;

  public IndiceBloco(int indiceBloco, Bloco setor) {
    this.indiceBloco = indiceBloco;
    this.setor = setor;
  }

  public IndiceBloco() {

  }

  public int getIndiceBloco() {
    return indiceBloco;
  }

  public void setIndiceBloco(int indiceBloco) {
    this.indiceBloco = indiceBloco;
  }

  public Bloco getSetor() {
    return setor;
  }

  public void setSetor(Bloco setor) {
    this.setor = setor;
  }
}