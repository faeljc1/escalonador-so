package br.com.unifor.escalonador.entidades;

import java.util.Random;

public class NumerosRandom {
  Random random;

  public NumerosRandom() {
    random = new Random();
  }

  public int getTempoTotalRandom() {
    return random.nextInt(10) + 4;
  }

  public int getPrioridadeRandom() {
    return random.nextInt(4);
  }

  public int getDeadLineRandom() {
    return random.nextInt(17) + 4;
  }

  public int getTamanhoBlocoRandom() {
    return (int) Math.pow(2, random.nextInt(5) + 3);
  }
}
