package gg.acai.acava.commons.structure;

/**
 * A cell in a matrix with a row and column.
 *
 * @author Clouke
 * @since 21.04.2023 08:37
 * © Acava - All Rights Reserved
 */
public class Cell {

  /**
   * The row of this cell.
   */
  private final int row;
  /**
   * The column of this cell.
   */
  private final int col;

  /**
   * Constructs a new cell with the given row and column.
   *
   * @param row The row of this cell.
   * @param col The column of this cell.
   */
  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Gets the row of this cell.
   *
   * @return Returns the row of this cell.
   */
  public int row() {
    return row;
  }

  /**
   * Gets the column of this cell.
   *
   * @return Returns the column of this cell.
   */
  public int col() {
    return col;
  }

}
