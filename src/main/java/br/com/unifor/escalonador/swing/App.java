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
import javax.swing.SwingConstants;

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
  public static JPanel painelDadosMemoria;

  public static JTextField txfProcessosIniciais;
  public static JTextField txfNumeroProcessadores;
  public static JTextField txfQuantum;
  public static JTextField txfMemoria;
  public static JTextField txfLimiar;
  public static JLabel lblProcessosIniciais;
  public static JLabel lblNumeroProcessadores;
  public static JLabel lblQtdNumeroProcessos;
  public static JLabel lblQuantum;
  public static JLabel lblQtdQuantum;
  public static JLabel lblLimiar;
  public static JLabel lblEspacoUsadoMemoria;
  public static JLabel lblEspacoUsadoHD;

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
    painelCima.setLayout(null);

    painelParametros = new JPanel();
    JScrollPane scrollPane_1 = new JScrollPane(painelParametros);
    scrollPane_1.setBounds(0, 0, 440, 274);
    painelCima.add(scrollPane_1);
    painelParametros.setBorder(new TitledBorder(null, "",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));
    painelParametros.setLayout(null);

    lblProcessosIniciais = new JLabel("Processos iniciais");
    lblProcessosIniciais.setBounds(10, 64, 101, 17);
    lblProcessosIniciais.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblProcessosIniciais);

    txfProcessosIniciais = new JTextField();
    txfProcessosIniciais.setBounds(216, 60, 58, 25);
    txfProcessosIniciais.setEditable(false);
    txfProcessosIniciais.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfProcessosIniciais
        .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfProcessosIniciais.setColumns(10);
    painelParametros.add(txfProcessosIniciais);

    lblNumeroProcessadores = new JLabel("N\u00FAmero de processadores");
    lblNumeroProcessadores.setBounds(10, 96, 160, 17);
    lblNumeroProcessadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblNumeroProcessadores);

    txfNumeroProcessadores = new JTextField();
    txfNumeroProcessadores.setBounds(216, 92, 58, 25);
    txfNumeroProcessadores.setEditable(false);
    txfNumeroProcessadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfNumeroProcessadores
        .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfNumeroProcessadores.setColumns(10);
    painelParametros.add(txfNumeroProcessadores);

    lblQtdNumeroProcessos = new JLabel("(1, 64)");
    lblQtdNumeroProcessos.setBounds(173, 99, 47, 14);
    painelParametros.add(lblQtdNumeroProcessos);

    lblQuantum = new JLabel("Quantum");
    lblQuantum.setBounds(10, 160, 58, 17);
    lblQuantum.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(lblQuantum);

    txfQuantum = new JTextField();
    txfQuantum.setBounds(216, 156, 58, 25);
    txfQuantum.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfQuantum.setEditable(false);
    txfQuantum.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfQuantum.setColumns(10);
    painelParametros.add(txfQuantum);

    lblQtdQuantum = new JLabel("(2, 20)");
    lblQtdQuantum.setBounds(72, 163, 58, 14);
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
    btnIniciar.setBounds(10, 228, 90, 25);
    btnIniciar.addActionListener(new ActionIniciar());
    btnIniciar.setEnabled(false);
    btnIniciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(btnIniciar);

    btnAdicionarProcessos = new JButton("Adicionar Processos");
    btnAdicionarProcessos.setBounds(110, 228, 183, 25);
    btnAdicionarProcessos.addActionListener(new
        ActionAdicionarProcessos());
    btnAdicionarProcessos.setEnabled(false);
    btnAdicionarProcessos.setFont(new Font("Tahoma", Font.PLAIN, 14));
    painelParametros.add(btnAdicionarProcessos);

    btnParar = new JButton("Parar");
    btnParar.setBounds(303, 228, 89, 25);
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

    lblLimiar = new JLabel("Limiar");
    lblLimiar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblLimiar.setBounds(10, 192, 58, 17);
    painelParametros.add(lblLimiar);

    txfLimiar = new JTextField();
    txfLimiar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txfLimiar.setEditable(false);
    txfLimiar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    txfLimiar.setColumns(10);
    txfLimiar.setBounds(216, 188, 58, 25);
    painelParametros.add(txfLimiar);

    painelMemoria = new JPanel();
    JScrollPane scrollPane_2 = new JScrollPane(painelMemoria);
    scrollPane_2.setBounds(440, 65, 460, 209);
    painelCima.add(scrollPane_2);
    painelMemoria.setBorder(new TitledBorder(null, "Memoria",
        TitledBorder.LEADING, TitledBorder.TOP, null, null));

    painelListaMemoria = new JPanel();
    painelListaMemoria.setBorder(new TitledBorder(UIManager
        .getBorder("TitledBorder.border"), "Disco R\u00EDgido",
        TitledBorder.LEADING, TitledBorder.TOP, null,
        new Color(0, 0, 0)));
    JScrollPane scrollPane_3 = new JScrollPane(painelListaMemoria);
    scrollPane_3.setBounds(900, 65, 460, 209);
    painelCima.add(scrollPane_3);

    painelDadosMemoria = new JPanel();
    painelDadosMemoria.setBounds(440, 0, 920, 53);
    painelCima.add(painelDadosMemoria);
    painelDadosMemoria.setLayout(null);

    lblEspacoUsadoMemoria = new JLabel("Espaco Usado na Mem\u00F3ria: ");
    lblEspacoUsadoMemoria.setHorizontalAlignment(SwingConstants.CENTER);
    lblEspacoUsadoMemoria.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblEspacoUsadoMemoria.setBounds(10, 11, 446, 42);
    painelDadosMemoria.add(lblEspacoUsadoMemoria);

    lblEspacoUsadoHD = new JLabel("Espaco Usado no Disco R\u00EDgido: ");
    lblEspacoUsadoHD.setHorizontalAlignment(SwingConstants.CENTER);
    lblEspacoUsadoHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
    lblEspacoUsadoHD.setBounds(464, 11, 446, 42);
    painelDadosMemoria.add(lblEspacoUsadoHD);

    painelCentro = new JPanel();
    painelCentro.setBounds(0, 276, 1362, 172);
    getContentPane().add(painelCentro);
    painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.X_AXIS));

    painelExecucao = new JPanel();
    JScrollPane scrollPane = new JScrollPane(painelExecucao);
    painelCentro.add(scrollPane);
    painelExecucao.setBorder(new TitledBorder(UIManager
        .getBorder("TitledBorder.border"),
        "Processos em execu\u00E7\u00E3o", TitledBorder.LEADING,
        TitledBorder.TOP, null, null));

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
