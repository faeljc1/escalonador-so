package br.com.unifor.escalonador.actions;

import br.com.unifor.escalonador.swing.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionParar implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    App.btnSelecionar.setEnabled(true);
    App.comboBox.setEnabled(true);
    App.btnIniciar.setEnabled(false);
    App.btnParar.setEnabled(false);

    App.btnAdicionarProcessos.setEnabled(false);
    App.txfProcessosIniciais.setEditable(false);
    App.txfNumeroProcessadores.setEditable(false);
    App.txfMemoria.setEditable(false);
    App.txfQuantum.setEditable(false);

    App.txfProcessosIniciais.setText("");
    App.txfNumeroProcessadores.setText("");
    App.txfQuantum.setText("");
    App.txfMemoria.setText("");

    App.painelAptos.removeAll();
    App.painelAptos.doLayout();
    App.painelAptos.repaint();

    App.painelExecucao.removeAll();
    App.painelExecucao.doLayout();
    App.painelExecucao.repaint();
  }

}
