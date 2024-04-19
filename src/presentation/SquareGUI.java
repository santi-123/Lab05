package presentation;

import javax.swing.JFrame;

public class SquareGUI extends JFrame {

  public SquareGUI() {
    setTitle("Square");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 300);
    setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
    SquareGUI squareGui = new SquareGUI();
    squareGui.setVisible(true);
  }
}