package presentation;

import domain.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SquareGUI extends JFrame implements ActionListener{

    private Square square;
    int size;
    //Panel Principal
    private JPanel boardPanel;
    private JButton[][] boardButtons;
    private HashMap<JButton, JButton> joinFillerHole;
    private ArrayList<Color> usedColors;

    //MenuBar
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem guardar;
    private JMenuItem salirM;

    private JButton refreshButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;

    public SquareGUI() {
        setTitle("Square");
        square = new Square();
        this.size = square.getSize();
        usedColors = new ArrayList<>();
        prepareElements();
        prepareActions();
        photoSquare();
    }

    private void prepareElements() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Manejo de dimensiones de la pantallam, 1/4 de la actual.
        ArrayList<Integer> dimensions = this.getScreeenDimensions();
        double width = dimensions.get(0);
        double height = dimensions.get(1);
        this.setSize((int)width/2, (int)height/2);
        // Manejo posicional de la pantalla.
        this.setLocationRelativeTo(null);

        prepareElementsMenu();
        prepareElementsBoard();
    }

    private void prepareElementsMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        guardar = new JMenuItem("Salvar");
        salirM = new JMenuItem("Salir");
        setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(guardar);
        menu.add(salirM);
    }

    private void prepareElementsBoard() {

        JPanel controlPanel = new JPanel(new BorderLayout());
        refreshButton = new JButton("Refresh");
        upButton = new JButton("Up");
        downButton = new JButton("Down");
        leftButton = new JButton("Left");
        rightButton = new JButton("Right");

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

        // Tamaño del tablero, debería coincidir con el GameBoard
        boardPanel = new JPanel(new GridLayout(size, size, 5, 5));
        boardButtons = new JButton[size][size];
        joinFillerHole = new HashMap<>();
        getRandomColors(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                button.setBorder(new LineBorder(Color.BLACK));
                boardButtons[i][j] = button;
                boardPanel.add(button);
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void prepareActionsBoard(){
        refreshButton.addActionListener(this);
        downButton.addActionListener(this);
        upButton.addActionListener(this);
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
    }

    private void photoSquare(){

        for (int i = 0; i < boardButtons.length; i++) {
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            button.setBorder(new LineBorder(Color.BLACK));
        }

        ArrayList<ArrayList<Integer>> fillerList = square.getFillerPositionsAletoryPositions();
        ArrayList<ArrayList<Integer>> holeList = square.getHoleositionsAletoryPositions();
        int centinela = 0;

        for(ArrayList<Integer> position: fillerList){
            JButton button = boardButtons[position.get(0)][position.get(1)];
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openColorPanel(position.get(0), position.get(1));
                }
            });
            button.setBackground(usedColors.get(centinela));
            button.setBorder(new LineBorder(Color.BLACK));
            JButton holeButton = boardButtons[holeList.get(centinela).get(0)][holeList.get(centinela).get(1)];
            holeButton.setBackground(Color.WHITE);
            holeButton.setBorder(new LineBorder(usedColors.get(centinela), 5));
            centinela += 1;
            joinFillerHole.put(button,holeButton);
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void getRandomColors(int size) {
        Random random = new Random();
        int centinela = 0;
        while(centinela < size-1) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            Color color = new Color(red,green,blue);
            if (!usedColors.contains(color)) {
                usedColors.add(color);
                centinela += 1;
            }
        }
    }

    private void prepareActions() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(SquareGUI.this, "¿Estas seguro de que deseas salir?", "Confirmacion de salida", JOptionPane.YES_NO_OPTION);
                exit(confirm);
            }
        });
        prepareActionsMenu();
    }

    private void prepareActionsMenu() {
        nuevo.addActionListener(this);
        abrir.addActionListener(this);
        guardar.addActionListener(this);
        salirM.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(salirM)){
            int confirm = JOptionPane.showConfirmDialog(
                    SquareGUI.this, "¿Estas seguro que deseas salir?", "Confirmacion de salida", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        if (e.getSource().equals(nuevo)){
            JOptionPane.showMessageDialog(SquareGUI.this, "En construccion");
        }
        if (e.getSource().equals(abrir)){
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(SquareGUI.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(SquareGUI.this, "En construccion");
            }
        }
        if (e.getSource().equals(guardar)){
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(SquareGUI.this);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(SquareGUI.this, "En construccion");
            }
        }
    }

    private void openColorPanel(int row, int col) {
        JFrame colorFrame = new JFrame("Choose Color");
        JColorChooser colorChooser = new JColorChooser();
        Color selectedColor = JColorChooser.showDialog(colorFrame, "Choose Color", colorChooser.getColor());

        if (selectedColor != null) {
            if (!usedColors.contains(selectedColor) && selectedColor != Color.WHITE) {
                usedColors.remove(boardButtons[row][col].getBackground());
                boardButtons[row][col].setBackground(selectedColor);
                joinFillerHole.get(boardButtons[row][col]).setBorder(new LineBorder(selectedColor, 5));
                usedColors.add(selectedColor);
                colorFrame.dispose();
            } else if (selectedColor == Color.WHITE) {
                JOptionPane.showMessageDialog(colorFrame, "Undisposed color.");
            } else {
                JOptionPane.showMessageDialog(colorFrame, "This color is already in use.");
            }
        } else {
            JOptionPane.showMessageDialog(colorFrame, "no color was selected.");;
        }
    }

    private void exit(int confirm){
        if (confirm == JOptionPane.YES_OPTION) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }else{
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    private ArrayList<Integer> getScreeenDimensions(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        ArrayList<Integer> dimensions = new ArrayList<Integer>();
        dimensions.add((int) screenSize.getWidth());
        dimensions.add((int) screenSize.getHeight());
        return dimensions;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SquareGUI().setVisible(true));
    }
}
