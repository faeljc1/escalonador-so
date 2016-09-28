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
    //App.txfNumeroProcessadores.setEditable(true);
    App.txfQuantum.setEditable(true);
    App.txfProcessosIniciais.setFocusable(true);
    App.txfProcessosIniciais.requestFocus();
  }

}
