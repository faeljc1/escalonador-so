package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.swing.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSelecionar implements ActionListener {

  public void actionPerformed(ActionEvent arg0) {
    App.btnSelecionar.setEnabled(false);
    App.comboBox.setEnabled(false);
    App.btnIniciar.setEnabled(true);
    App.btnParar.setEnabled(true);
    App.btnAdicionarProcessos.setEnabled(true);
    App.txfProcessosIniciais.setEditable(true);
    App.txfNumeroProcessadores.setEditable(true);
    App.txfMemoria.setEditable(true);
    App.txfProcessosIniciais.setFocusable(true);
    App.txfProcessosIniciais.setEnabled(true);
    App.txfNumeroProcessadores.setEnabled(true);
    App.txfQuantum.setEnabled(true);
    App.txfMemoria.setEnabled(true);
    App.txfLimiar.setEnabled(true);
    App.txfLimiar.setEditable(true);
    if (App.comboBox.getSelectedIndex() == 0) {
      App.txfQuantum.setEditable(false);
    } else {
      App.txfQuantum.setEditable(true);
    }
    App.txfProcessosIniciais.requestFocus();
  }

}
