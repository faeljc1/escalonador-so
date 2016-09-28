package br.com.unifor.escalonador.actions;


import br.com.unifor.escalonador.swing.App;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rafael on 08/09/2016.
 */
public class ActionTextFiled implements KeyListener {
  public void keyTyped(KeyEvent e) {

  }

  public void keyPressed(KeyEvent e) {

  }

  public void keyReleased(KeyEvent e) {
    if (e.getSource() == App.txfProcessosIniciais) {
      if (!App.txfProcessosIniciais.getText().isEmpty()) {
        App.txfNumeroProcessadores.setEditable(true);
      } else {
        App.txfNumeroProcessadores.setEditable(false);
      }
    }
  }
}
