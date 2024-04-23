package presentation;

import domain.GameBoard;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SquareGUI extends JFrame {
  private JPanel boardPanel;
  private JButton[][] boardButtons;
  private GameBoard gameBoard;
  private Map<Color, String> colorNames;
  private ArrayList<Color> usedColors;

  public SquareGUI() {
    gameBoard = new GameBoard(4); // Tamaño del tablero
    colorNames = new HashMap<>();
    usedColors = new ArrayList<>();
    initializeColors();
    prepareActions();
    prepareElements();
    prepareMenu();
  }

  private void initializeColors() {
    colorNames.put(new Color(255, 0, 0), "Red");
    colorNames.put(new Color(0, 255, 0), "Green");
    colorNames.put(new Color(0, 0, 255), "Blue");
    colorNames.put(new Color(255, 255, 0), "Yellow");
    colorNames.put(new Color(255, 165, 0), "Orange");
    colorNames.put(new Color(128, 0, 128), "Purple");
    colorNames.put(new Color(0, 128, 128), "Teal");
    colorNames.put(new Color(128, 0, 0), "Maroon");
    colorNames.put(new Color(128, 128, 0), "Olive");
    colorNames.put(new Color(0, 128, 0), "Dark Green");
    colorNames.put(new Color(128, 128, 128), "Gray");
    colorNames.put(new Color(0, 255, 255), "Aqua");
    colorNames.put(new Color(0, 0, 128), "Navy");
    colorNames.put(new Color(255, 192, 203), "Pink");
    colorNames.put(new Color(255, 105, 180), "Hot Pink");
    colorNames.put(new Color(75, 0, 130), "Indigo");
    colorNames.put(new Color(240, 230, 140), "Khaki");
    colorNames.put(new Color(245, 222, 179), "Wheat");
    colorNames.put(new Color(255, 69, 0), "Red Orange");
    colorNames.put(new Color(47, 79, 79), "Dark Slate Gray");
  }

  private void prepareElements() {
    setTitle("Square Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 600);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    prepareBoard();
    prepareControlButtons();
  }

  private void prepareBoard() {
    int size = 4; // Tamaño del tablero, debería coincidir con el GameBoard
    boardPanel = new JPanel(new GridLayout(size, size, 5, 5));
    boardButtons = new JButton[size][size];
    boardPanel.setPreferredSize(new Dimension(400, 400));

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        JButton button = new JButton();
        button.setBackground(Color.WHITE);
        button.setBorder(new LineBorder(Color.BLACK));
        final int row = i;
        final int col = j;
        button.addActionListener(e -> openColorPanel(row, col));
        boardButtons[i][j] = button;
        boardPanel.add(button);
      }
    }
    add(boardPanel, BorderLayout.CENTER);
  }

  private void openColorPanel(int row, int col) {
    JFrame colorFrame = new JFrame("Choose Color");
    colorFrame.setSize(300, 200);
    colorFrame.setLayout(new GridLayout(5, 10)); // 5x10 grid of color choices
    colorFrame.setLocationRelativeTo(this);

    colorNames.forEach((color, name) -> {
      JButton colorButton = new JButton(name);
      colorButton.setBackground(color);
      colorButton.setForeground(Color.BLACK);
      colorButton.setOpaque(true);
      colorButton.setBorderPainted(false);
      colorButton.addActionListener(e -> {
        if (!usedColors.contains(color)) {
          boardButtons[row][col].setBackground(color);
          gameBoard.setColor(row, col, color); // Set color in domain
          usedColors.add(color);
          colorFrame.dispose();
        } else {
          JOptionPane.showMessageDialog(colorFrame, "This color is already in use.");
        }
      });
      colorFrame.add(colorButton);
    });

    colorFrame.setVisible(true);
  }

  private void prepareControlButtons() {
    JPanel controlPanel = new JPanel(new BorderLayout());
    JButton refreshButton = new JButton("Refresh");
    JButton upButton = new JButton("Up");
    JButton downButton = new JButton("Down");
    JButton leftButton = new JButton("Left");
    JButton rightButton = new JButton("Right");

    JPanel arrowsPanel = new JPanel(new GridLayout(3, 3));
    arrowsPanel.add(new JLabel());
    arrowsPanel.add(upButton);
    arrowsPanel.add(new JLabel());
    arrowsPanel.add(leftButton);
    arrowsPanel.add(refreshButton);
    arrowsPanel.add(rightButton);
    arrowsPanel.add(new JLabel());
    arrowsPanel.add(downButton);
    arrowsPanel.add(new JLabel());

    controlPanel.add(arrowsPanel, BorderLayout.SOUTH);
    add(controlPanel, BorderLayout.SOUTH);
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
    SwingUtilities.invokeLater(() -> new SquareGUI().setVisible(true));
  }
}
