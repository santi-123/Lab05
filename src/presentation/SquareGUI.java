package presentation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SquareGUI extends JFrame {

  public SquareGUI() {
    prepareElements();
    prepareExitListener();
  }

  private void prepareElements() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    setTitle("Square");
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setSize((screenSize.width) / 2, (screenSize.height) / 2);
    setLocationRelativeTo(null);
  }

  private void prepareExitListener() {
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
            SquareGUI.this,
            "Are you sure you want to close this window?",
            "Exit Confirmation",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
  }

  public static void main(String[] args) {
    SquareGUI squareGui = new SquareGUI();
    squareGui.setVisible(true);
  }
}
