package br.com.unifor.escalonador.swing;

import br.com.unifor.escalonador.actions.ActionAdicionarProcessos;
import br.com.unifor.escalonador.actions.ActionIniciar;
import br.com.unifor.escalonador.actions.ActionParar;
import br.com.unifor.escalonador.actions.ActionSelecionar;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class App extends JFrame {

  private static final long serialVersionUID = 1L;

  public static Thread processamento;

  public static int identificador;

  public static JPanel painelParametros;
  public static JPanel painelExecucao;
  public static JPanel painelAptos;
  public static JPanel painelAbortados;
  public static JPanel painelMemoria;
  public static JPanel painelCima;
  public static JPanel painelCentro;
  public static JPanel painelBaixo;
  public static JPanel painelListaMemoria;

  public static JTextField txfProcessosIniciais;
  public static JTextField txfNumeroProcessadores;
  public static JTextField txfQuantum;
  public static JTextField txfMemoria;
  public static JLabel lblProcessosIniciais;
  public static JLabel lblNumeroProcessadores;
  public static JLabel lblQtdNumeroProcessos;
  public static JLabel lblQuantum;
  public static JLabel lblQtdQuantum;
  public static JButton btnSelecionar;
  public static JButton btnIniciar;
  public static JButton btnAdicionarProcessos;
  public static JButton btnParar;
  public static JComboBox<String[]> comboBox;

  String[] algoritmos = {"Least Time to Go", "Round Robin - BestFit",
      "Round Robin - MergeFit", "Round Robin - QuickFit"};

  public App() {
    this.setExtendedState(MAXIMIZED_BOTH);
    getContentPane().setLayout(null);

    painelCima = new JPanel();
    painelCima.setBounds(0, 0, 1362, 274);
    getContentPane().add(painelCima);
    painelCima.setLayout(new BoxLayout(painelCima, BoxLayout.X_AXIS));

    painelParametros = new JPanel();
    painelCima.add(new JScrollPane(painelParametros));
    painelParametros.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
        TitledBorder.TOP, null, null));
    painelParametros.setLayout(null);

    lblProcessosIniciais = new JLabel("Processos iniciais");
    lblProcessosIniciais.setBounds(10, 72, 101, 17);
    lblProcessosIniciais.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblProcessosIniciais);

    txfProcessosIniciais = new JTextField();
    txfProcessosIniciais.setBounds(216, 68, 58, 25);
    txfProcessosIniciais.setEditable(false);
    txfProcessosIniciais.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfProcessosIniciais
        .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfProcessosIniciais.setColumns(10);
    painelParametros.add(txfProcessosIniciais);

    lblNumeroProcessadores = new JLabel("N\u00FAmero de processadores");
    lblNumeroProcessadores.setBounds(10, 100, 160, 17);
    lblNumeroProcessadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblNumeroProcessadores);

    txfNumeroProcessadores = new JTextField();
    txfNumeroProcessadores.setBounds(216, 96, 58, 25);
    txfNumeroProcessadores.setEditable(false);
    txfNumeroProcessadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfNumeroProcessadores
        .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfNumeroProcessadores.setColumns(10);
    painelParametros.add(txfNumeroProcessadores);

    lblQtdNumeroProcessos = new JLabel("(1, 64)");
    lblQtdNumeroProcessos.setBounds(174, 103, 47, 14);
    painelParametros.add(lblQtdNumeroProcessos);

    lblQuantum = new JLabel("Quantum");
    lblQuantum.setBounds(10, 156, 58, 17);
    lblQuantum.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblQuantum);

    txfQuantum = new JTextField();
    txfQuantum.setBounds(216, 152, 58, 25);
    txfQuantum.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfQuantum.setEditable(false);
    txfQuantum.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfQuantum.setColumns(10);
    painelParametros.add(txfQuantum);

    lblQtdQuantum = new JLabel("(2, 20)");
    lblQtdQuantum.setBounds(72, 159, 58, 14);
    painelParametros.add(lblQtdQuantum);

    comboBox = new JComboBox(algoritmos);
    comboBox.setBounds(10, 13, 243, 20);
    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(comboBox);

    btnSelecionar = new JButton("Selecionar");
    btnSelecionar.setBounds(263, 12, 115, 23);
    btnSelecionar.addActionListener(new ActionSelecionar());
    btnSelecionar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(btnSelecionar);

    btnIniciar = new JButton("Iniciar");
    btnIniciar.setBounds(10, 213, 90, 25);
    btnIniciar.addActionListener(new ActionIniciar());
    btnIniciar.setEnabled(false);
    btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(btnIniciar);

    btnAdicionarProcessos = new JButton("Adicionar Processos");
    btnAdicionarProcessos.setBounds(110, 213, 183, 25);
    btnAdicionarProcessos.addActionListener(new ActionAdicionarProcessos());
    btnAdicionarProcessos.setEnabled(false);
    btnAdicionarProcessos.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(btnAdicionarProcessos);

    btnParar = new JButton("Parar");
    btnParar.setBounds(303, 213, 89, 25);
    btnParar.addActionListener(new ActionParar());
    btnParar.setEnabled(false);
    btnParar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(btnParar);

    JLabel lblMemoria = new JLabel("Memoria");
    lblMemoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblMemoria.setBounds(10, 128, 64, 17);
    painelParametros.add(lblMemoria);

    txfMemoria = new JTextField();
    txfMemoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfMemoria.setEditable(false);
    txfMemoria.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfMemoria.setColumns(10);
    txfMemoria.setBounds(216, 124, 58, 25);
    painelParametros.add(txfMemoria);

    painelMemoria = new JPanel();
    painelCima.add(new JScrollPane(painelMemoria));
    painelMemoria.setBorder(new TitledBorder(null, "Memoria",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));

    painelListaMemoria = new JPanel();
    painelListaMemoria.setBorder(new TitledBorder(UIManager
        .getBorder("TitledBorder.border"), "Listas Mem\u00F3ria",
        TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    painelCima.add(new JScrollPane(painelListaMemoria));

    painelCentro = new JPanel();
    painelCentro.setBounds(0, 276, 1362, 172);
    getContentPane().add(painelCentro);
    painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.X_AXIS));

    painelExecucao = new JPanel();
    JScrollPane scrollPane = new JScrollPane(painelExecucao);
    painelCentro.add(scrollPane);
    painelExecucao.setBorder(new TitledBorder(UIManager
        .getBorder("TitledBorder.border"), "Processos em execu\u00E7\u00E3o",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));

    painelBaixo = new JPanel();
    painelBaixo.setBounds(0, 450, 1362, 253);
    getContentPane().add(painelBaixo);
    painelBaixo.setLayout(new BoxLayout(painelBaixo, BoxLayout.X_AXIS));

    painelAptos = new JPanel();
    FlowLayout flowLayout = (FlowLayout) painelAptos.getLayout();
    painelBaixo.add(new JScrollPane(painelAptos));
    painelAptos.setBorder(new TitledBorder(null, "Processos Aptos",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));

    painelAbortados = new JPanel();
    painelBaixo.add(new JScrollPane(painelAbortados));
    painelAbortados.setBorder(new TitledBorder(null,
        "Processos Finalizados / Abortados", TitledBorder.LEADING,
        TitledBorder.TOP, null, null));
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          App frame = new App();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Thread getProcessamento() {
    return processamento;
  }

  public void setProcessamento(Thread processamento) {
    this.processamento = processamento;
  }
}
