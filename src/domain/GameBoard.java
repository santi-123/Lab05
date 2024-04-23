package domain;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
  private Tile[][] tiles;
  private int size;

  public GameBoard(int size) {
    this.size = size;
    tiles = new Tile[size][size];
    initializeTiles();
  }

  private void initializeTiles() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        tiles[i][j] = new Tile();
      }
    }
  }

  public void setColor(int row, int col, Color color) {
    if (row >= 0 && row < size && col >= 0 && col < size) {
      tiles[row][col].setColor(color);
    }
  }

  public Color getColor(int row, int col) {
    if (row >= 0 && row < size && col >= 0 && col < size) {
      return tiles[row][col].getColor();
    }
    return null;
  }

  // Métodos adicionales según sea necesario
}

class Tile {
  private Color color;

  public Tile() {
    this.color = Color.WHITE; // Color inicial
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }
}
