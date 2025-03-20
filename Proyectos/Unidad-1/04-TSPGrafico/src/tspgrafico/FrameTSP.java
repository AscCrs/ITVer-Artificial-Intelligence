package tspgrafico;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPopupMenu.Separator;
import modelos.tsp.GrafoTSP;

/**
 * @author Rafael Rivera-López
 */
public class FrameTSP extends JFrame {
  private GrafoTSP grafo;
  private JMenuBar barraMenu;
  private JButton botonIniciar;
  private JLabel etiquetaAptitud;
  private JLabel etiquetaDatos;
  private JMenu menuArchivo;
  private JMenuItem opcionAbrir;
  private JMenuItem opcionSalir;
  private PanelTSP panelCentro;
  private JPanel panelDatos;
  private JSplitPane panelSur;
  private JPopupMenu.Separator separador;

  public FrameTSP(GrafoTSP grafo) {
    this.grafo = grafo;
    initComponents();
  }
  
  public void addEventos(ActionListener oyente){
    this.opcionAbrir.addActionListener(oyente);
    this.opcionSalir.addActionListener(oyente);
    this.botonIniciar.addActionListener(oyente);
    botonIniciar.setName("iniciar");
    opcionAbrir.setName("abrir"); 
    opcionSalir.setName("salir"); 
  }

  private void initComponents() {
    panelCentro = new PanelTSP(grafo);
    panelSur = new JSplitPane();
    panelDatos = new JPanel();
    etiquetaDatos = new JLabel();
    etiquetaAptitud = new JLabel();
    botonIniciar = new JButton();
    barraMenu = new JMenuBar();
    menuArchivo = new JMenu();
    opcionAbrir = new JMenuItem();
    separador = new Separator();
    opcionSalir = new JMenuItem();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Algoritmo Genético para resolver el TSP");

    panelCentro.setBorder(BorderFactory.createTitledBorder("Grafo del problema TSP"));
    getContentPane().add(panelCentro, "Center");

    panelSur.setDividerLocation(600);

    panelDatos.setLayout(new GridLayout(2, 1));
    panelDatos.add(etiquetaDatos);

    etiquetaAptitud.setHorizontalAlignment(SwingConstants.RIGHT);
    panelDatos.add(etiquetaAptitud);

    panelSur.setLeftComponent(panelDatos);

    botonIniciar.setText("Iniciar AG");
    botonIniciar.setEnabled(false);
    panelSur.setRightComponent(botonIniciar);

    getContentPane().add(panelSur, "South");

    menuArchivo.setText("Archivo");

    opcionAbrir.setText("Abrir problema");
    menuArchivo.add(opcionAbrir);
    menuArchivo.add(separador);

    opcionSalir.setText("Salir del programa");
    menuArchivo.add(opcionSalir);

    barraMenu.add(menuArchivo);

    setJMenuBar(barraMenu);
  }
  
  /**
   * @return the grafo
   */
  public GrafoTSP getGrafo() {
    return grafo;
  }

  /**
   * @param grafo the grafo to set
   */
  public void setGrafo(GrafoTSP grafo) {
    this.grafo = grafo;
  }

  /**
   * @return the etiquetaAptitud
   */
  public JLabel getEtiquetaAptitud() {
    return etiquetaAptitud;
  }

  /**
   * @return the etiquetaDatos
   */
  public JLabel getEtiquetaDatos() {
    return etiquetaDatos;
  }

  /**
   * @return the botonIniciar
   */
  public JButton getBotonIniciar() {
    return botonIniciar;
  }
}
