package br.com.unifor.escalonador.entidades;

public class IndiceBloco {
  private int indiceBloco;
  private Bloco bloco;

  public IndiceBloco(int indiceBloco, Bloco bloco) {
    this.indiceBloco = indiceBloco;
    this.bloco = bloco;
  }

  public IndiceBloco() {

  }

  public int getIndiceBloco() {
    return indiceBloco;
  }

  public void setIndiceBloco(int indiceBloco) {
    this.indiceBloco = indiceBloco;
  }

  public Bloco getBloco() {
    return bloco;
  }

  public void setBloco(Bloco bloco) {
    this.bloco = bloco;
  }
}