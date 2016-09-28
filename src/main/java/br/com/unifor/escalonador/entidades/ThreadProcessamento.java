package br.com.unifor.escalonador.entidades;

public class ThreadProcessamento extends Thread {

  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    super.run();
  }
}
