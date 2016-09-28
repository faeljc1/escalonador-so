package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.swing.App;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionTxfQuantum implements KeyListener {
  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    if (!App.txfQuantum.getText().equals("")) {
      if (e.getSource() == App.txfQuantum) {
        if (Integer.parseInt(App.txfQuantum.getText()) >= 4) {
          App.btnIniciar.setEnabled(true);
        } else {
          App.btnIniciar.setEnabled(false);
        }
      }
    }
  }
}
