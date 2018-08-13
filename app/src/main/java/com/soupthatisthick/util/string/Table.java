package com.soupthatisthick.util.string;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import java.io.Serializable;

public class Table implements Serializable {

    private static final String NEWLINE = "\n";
    private static final String INTERSECTION = "-+-";
    private static final String COL_DELIM = " | ";
    private static final String ROW_DELIM = "-";

    public int getTableHeight() {
        return tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public static final class Cell implements Serializable {
        public final int width;
        public final int height;
        public final String text;
        public final String[] lines;

        public Cell(String input) {
            this.text = (input==null) ? "" : input;
            if (text!=null) {
                this.lines = text.split("\n");
            } else {
                this.lines = new String[0];
            }
            int max = 0;
            for(int line=0; line<lines.length; line++) {
                final String field = lines[line];
                max = Math.max(max, (field==null) ? 0 : field.length());
            }
            this.width = max;
            this.height = lines.length;
        }

        public String[][] toArray() {
            String[][] table = new String[lines.length][1];
            for(int line=0; line<lines.length; line++) {
                table[line][0] = lines[line];
            }
            return table;
        }
    }

    private Cell[][] cells;
    private int colWidths[];
    private int rowHeights[];
    private int tableHeight = 0;
    private int tableWidth = 0;

    public int numRows() {
        return rowHeights.length;
    }

    public int numCols() {
        return colWidths.length;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public Table(String[][] array) {
        build(array);
    }

    public Table(Cell cell) {
        build(cell);
    }

    public Table(String text) {
        build(text);
    }

    /**
     * This will determine all the key information we need
     * @param newCells
     */
    private void build(String[][] newCells) {
        int numRows = newCells.length;
        int numCols = 0;
        for(String[] row : newCells) {
            if (row!=null) {
                numCols = Math.max(row.length, numCols);
            }
        }
        Logger.info("Building Table from [" + numRows + "][" + numCols + "]");
        this.cells = new Cell[numRows][numCols];
        this.colWidths = new int[numCols];
        this.rowHeights = new int[numRows];

        for(int col=0; col<numCols; col++) {
            colWidths[col] = 0;
        }

        for(int row=0; row<numRows; row++) {
            rowHeights[row] = 0;
        }

        tableHeight = 0;

        for(int row=0; row<newCells.length; row++) {
            String[] theRow = newCells[row];

            for (int col = 0; col < theRow.length; col++) {
                final Cell cell = new Cell(theRow[col]);
                this.cells[row][col] = cell;
                this.rowHeights[row] = Math.max(rowHeights[row], cell.height);
                this.colWidths[col] = Math.max(colWidths[col], cell.width);
            }

            for(int col=theRow.length; col<numCols; col++) {
                Cell cell = new Cell(null);
                this.cells[row][col] = cell;
            }
            tableHeight += rowHeights[row];
        }

        tableWidth = 0;
        for(int col=0; col<colWidths.length; col++) {
            tableWidth += colWidths[col];
        }
    }

    private void build(Cell cell) {
        String[][] asArray = cell.toArray();
        Logger.debug("___ asArray ___\n" + JsonUtil.toJson(asArray, true, true));
        build(asArray);
    }

    private void build(String text) {
        Cell cell = new Cell(text);
        Logger.debug("\n___ build(text) ___\n" + JsonUtil.toJson(cell, true, true));
        build(cell);
    }

    public String describe(
        boolean drawRows, boolean drawCols
    ) {
        return describeCells(drawRows, drawCols, COL_DELIM, ROW_DELIM, INTERSECTION);
    }

    public String describeAll(boolean drawRows, boolean drawCols) {
        StringBuilder sb = new StringBuilder();
        sb.append(describeCells(drawRows, drawCols, COL_DELIM, ROW_DELIM, INTERSECTION));
        sb.append(describeRows());
        sb.append(describeCols());
        return sb.toString();
    }

    public String describeRows() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nRow Heights\n");
        for(int row=0; row<numRows(); row++) {
            sb.append(" - row[").append(row).append("] = ").append(rowHeights[row]).append(NEWLINE);
        }
        return sb.toString();
    }

    public String describeCols() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCol Widths\n");
        for(int col=0; col<numCols(); col++) {
            sb.append(" - col[").append(col).append("] = ").append(colWidths[col]).append(NEWLINE);
        }
        return sb.toString();
    }

    public String describeCells(
        boolean drawRows,
        boolean drawColumns,
        final String colDelim,
        final String rowDelim,
        final String intersection
    ) {

        Logger.debug("Building description for table [" + numRows() + " x " + numCols() + "]");

        final StringBuilder sb = new StringBuilder();


        sb.append("\n");

        for(int row=0; row<numRows(); row++) {
            for(int line=0; line<rowHeights[row]; line++) {

                // append the row deliminator
                if (drawRows && row > 0 && line == 0) {
                    for (int col = 0; col < numCols(); col++) {
                        if (drawColumns && col>0) {
                            sb.append(intersection);
                        }
                        sb.append(Text.padString(rowDelim, colWidths[col]));
                    }
                    sb.append(NEWLINE);
                }

                // append the actual row data
                for (int col = 0; col < numCols(); col++) {
                    final Cell cell = getCell(row, col);

                    if (drawColumns && col>0) {
                        sb.append(colDelim);
                    }

                    int fieldWidth = colWidths[col];
                    if (line < cell.lines.length) {
                        sb.append(Text.fString(cell.lines[line], fieldWidth));
                    } else {
                        sb.append(Text.fString("", fieldWidth));
                    }
                }

                sb.append(NEWLINE);
            }
        }
        return sb.toString();
    }
}
