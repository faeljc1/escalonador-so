package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.entidades.Escalonador;
import br.com.unifor.escalonador.swing.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionIniciar implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    Escalonador escalonador = new Escalonador();
    escalonador.execute();
  }
}
