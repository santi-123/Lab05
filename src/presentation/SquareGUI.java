package presentation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class SquareGUI extends JFrame {

  public SquareGUI() {
    prepareElements();
    prepareMenu();
    prepareActions();
  }

  private void prepareElements() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    setTitle("Square");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setSize((screenSize.width) / 2, (screenSize.height) / 2);
    setLocationRelativeTo(null);
  }

  private void prepareActions() {
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
            SquareGUI.this, "¿Estas seguro de que deseas salir?", "Confirmacion de salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
  }

  private void prepareMenu() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");

    JMenuItem nuevo = new JMenuItem("Nuevo");
    JMenuItem abrir = new JMenuItem("Abrir");
    JMenuItem guardar = new JMenuItem("Salvar");
    JMenuItem salirM = new JMenuItem("Salir");

    nuevo.addActionListener(e -> JOptionPane.showMessageDialog(SquareGUI.this, "En construccion"));

    abrir.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      int option = fileChooser.showOpenDialog(SquareGUI.this);
      if (option == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        JOptionPane.showMessageDialog(SquareGUI.this, "En construccion");
      }
    });

    guardar.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      int option = fileChooser.showSaveDialog(SquareGUI.this);
      if (option == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        JOptionPane.showMessageDialog(SquareGUI.this, "En construccion");
      }
    });

    salirM.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
            SquareGUI.this, "¿Estas seguro que deseas salir?", "Confirmacion de salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });

    menu.add(nuevo);
    menu.addSeparator();
    menu.add(abrir);
    menu.addSeparator();
    menu.add(guardar);
    menu.addSeparator();
    menu.add(salirM);
    menuBar.add(menu);

    setJMenuBar(menuBar);
  }

  public static void main(String[] args) {
    SquareGUI squareGui = new SquareGUI();
    squareGui.setVisible(true);
  }
}
