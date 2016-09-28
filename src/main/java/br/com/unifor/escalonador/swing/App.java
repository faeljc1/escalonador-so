package br.com.unifor.escalonador.swing;


import br.com.unifor.escalonador.actions.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class App extends JFrame {

  private static final long serialVersionUID = 1L;

  public static Thread processamento;

  public static int identificador;

  public static JPanel painelParametros;
  public static JPanel painelExecucao;
  public static JPanel painelAptos;
  public static JPanel painelAbortados;
  public static JScrollPane barraRolagem;

  public static JTextField txfProcessosIniciais;
  public static JTextField txfNumeroProcessadores;
  public static JTextField txfQuantum;
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

  String[] algoritmos = {"Least Time to Go", "Round Robin"};

  public App() {
    this.setExtendedState(MAXIMIZED_BOTH);
    getContentPane().setLayout(null);

    painelParametros = new JPanel();
    painelParametros.setBorder(new TitledBorder(null, "",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));
    painelParametros.setBounds(10, 11, 1352, 120);
    getContentPane().add(painelParametros);
    painelParametros.setLayout(null);

    lblProcessosIniciais = new JLabel("Processos iniciais");
    lblProcessosIniciais.setBounds(499, 15, 101, 17);
    lblProcessosIniciais.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblProcessosIniciais);

    txfProcessosIniciais = new JTextField();
    txfProcessosIniciais.addKeyListener(new ActionTextFiled());
    txfProcessosIniciais.setEditable(false);
    txfProcessosIniciais.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfProcessosIniciais
        .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfProcessosIniciais.setColumns(10);
    txfProcessosIniciais.setBounds(608, 11, 47, 25);
    painelParametros.add(txfProcessosIniciais);

    lblNumeroProcessadores = new JLabel("N\u00FAmero de processadores");
    lblNumeroProcessadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblNumeroProcessadores.setBounds(440, 47, 160, 17);
    painelParametros.add(lblNumeroProcessadores);

    txfNumeroProcessadores = new JTextField();
    txfNumeroProcessadores.setEditable(false);
    txfNumeroProcessadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfNumeroProcessadores
        .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfNumeroProcessadores.setColumns(10);
    txfNumeroProcessadores.setBounds(608, 43, 47, 25);
    painelParametros.add(txfNumeroProcessadores);

    lblQtdNumeroProcessos = new JLabel("(1, 64)");
    lblQtdNumeroProcessos.setBounds(659, 50, 33, 14);
    painelParametros.add(lblQtdNumeroProcessos);

    lblQuantum = new JLabel("Quantum");
    lblQuantum.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblQuantum.setBounds(542, 79, 58, 17);
    painelParametros.add(lblQuantum);

    txfQuantum = new JTextField();
    txfQuantum.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfQuantum.setEditable(false);
    txfQuantum.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfQuantum.setColumns(10);
    txfQuantum.setBounds(608, 75, 47, 25);
    painelParametros.add(txfQuantum);

    lblQtdQuantum = new JLabel("(2, 20)");
    lblQtdQuantum.setBounds(659, 82, 33, 14);
    painelParametros.add(lblQtdQuantum);

    comboBox = new JComboBox(algoritmos);
    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
    comboBox.setBounds(23, 44, 243, 20);
    painelParametros.add(comboBox);

    btnSelecionar = new JButton("Selecionar");
    btnSelecionar.addActionListener(new ActionSelecionar());
    btnSelecionar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnSelecionar.setBounds(276, 43, 115, 23);
    painelParametros.add(btnSelecionar);

    btnIniciar = new JButton("Iniciar");
    btnIniciar.addActionListener(new ActionIniciar());
    btnIniciar.setEnabled(false);
    btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnIniciar.setBounds(762, 43, 90, 25);
    painelParametros.add(btnIniciar);

    btnAdicionarProcessos = new JButton("Adicionar Processos");
    btnAdicionarProcessos.addActionListener(new ActionAdicionarProcessos());
    btnAdicionarProcessos.setEnabled(false);
    btnAdicionarProcessos.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnAdicionarProcessos.setBounds(862, 43, 183, 25);
    painelParametros.add(btnAdicionarProcessos);

    btnParar = new JButton("Parar");
    btnParar.addActionListener(new ActionParar());
    btnParar.setEnabled(false);
    btnParar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnParar.setBounds(1055, 43, 89, 25);
    painelParametros.add(btnParar);

    painelExecucao = new JPanel();
    painelExecucao.setBorder(new TitledBorder(UIManager
        .getBorder("TitledBorder.border"),
        "Processos em execu\u00E7\u00E3o", TitledBorder.LEADING,
        TitledBorder.TOP, null, null));
    painelExecucao.setBounds(10, 131, 1352, 219);
    getContentPane().add(painelExecucao);

    painelAptos = new JPanel();
    painelAptos.setBorder(new TitledBorder(null, "Processos Aptos",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));
    painelAptos.setBounds(10, 348, 1352, 204);
    getContentPane().add(painelAptos);

    painelAbortados = new JPanel();
    painelAbortados.setBorder(new TitledBorder(null, "Processos Abortados",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));
    painelAbortados.setBounds(10, 590, 1352, 111);
    getContentPane().add(painelAbortados);
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
