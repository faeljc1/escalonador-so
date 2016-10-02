package br.com.unifor.escalonador.entidades;

public class Processo {
  private int identificador;
  private int tempoTotal;
  private int tempoRestante;
  private int prioridade;
  private int quantumFinal;
  private int quantum;
  private int deadLine;
  private int quantidade;
  private boolean abortados;

  public Processo(int identificador, int tempoTotal, int tempoRestante, int prioridade, int quantum,
                  int quantumFinal, int deadLine, int quantidade, boolean abortados) {
    super();
    this.identificador = identificador;
    this.tempoTotal = tempoTotal;
    this.tempoRestante = tempoRestante;
    this.prioridade = prioridade;
    this.quantum = quantum;
    this.quantumFinal = quantumFinal;
    this.deadLine = deadLine;
    this.quantidade = quantidade;
    this.abortados = abortados;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public int getIdentificador() {
    return identificador;
  }

  public void setIdentificador(int identificador) {
    this.identificador = identificador;
  }

  public int getTempoTotal() {
    return tempoTotal;
  }

  public void setTempoTotal(int tempoTotal) {
    this.tempoTotal = tempoTotal;
  }

  public int getTempoRestante() {
    return tempoRestante;
  }

  public void setTempoRestante(int tempoRestante) {
    this.tempoRestante = tempoRestante;
  }

  public int getPrioridade() {
    return prioridade;
  }

  public int getQuantumFinal() {
    return quantumFinal;
  }

  public void setQuantumFinal(int quantumFinal) {
    this.quantumFinal = quantumFinal;
  }

  public void setPrioridade(int prioridade) {
    this.prioridade = prioridade;
  }

  public int getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(int deadLine) {
    this.deadLine = deadLine;
  }

  public int getQuantum() {
    return quantum;
  }

  public void setQuantum(int quantum) {
    this.quantum = quantum;
  }

  public boolean isAbortados() {
    return abortados;
  }

  public void setAbortados(boolean abortados) {
    this.abortados = abortados;
  }
}
