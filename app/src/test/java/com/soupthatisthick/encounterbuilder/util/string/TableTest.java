package com.soupthatisthick.encounterbuilder.util.string;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;
import com.soupthatisthick.util.string.Table;

import org.junit.Test;

public class TableTest {
    final String INPUT = "The Quick brown fox jumped over the lazy dogs\n"
            + "The Quick brown fox jumped over the lazy dogs\n"
            + "The Quick brown fox jumped over the lazy dogs\n"
            + "The Quick brown fox jumped over the lazy dogs\n";
    final String[][] INPUT_ARRAY = new String[][] {
            {"The","Quick","Brown","Fox","Jumped","Over","The","Lazy","Dogs"},
            {"The","Quick","Brown","Fox","Jumped","Over","The","Lazy","Dogs"},
            {"The","Quick","Brown","Fox","Jumped","Over","The","Lazy","Dogs"},
            {"The","Quick","Brown","Fox","Jumped","Over","The","Lazy","Dogs"}
    };
    final String[][] WEIRD_INPUT_ARRAY = new String[][] {
            {null, "I was here.\nHere I was.\nWas I here?\nGuess I was.", "Hello World"},
            {"I was here.\nHere I was.\nWas I here?\nGuess I was.", "Hello World", null},
            {"Hello World", null, "I was here.\nHere I was.\nWas I here?\nGuess I was."}
    };
    final Table.Cell INPUT_CELL = new Table.Cell(INPUT);
    final Table.Cell WAS_CELL = new Table.Cell("I was here.\nHere I was.\nWas I here?\nGuess I was.");

    @Test
    public void testCell_text() {
        Logger.info("____ INPUT_CELL ____\n" + JsonUtil.toJson(INPUT_CELL, true, true));

        final Table.Cell cell = new Table.Cell(INPUT);
        Logger.info("____ cell ____\n" + JsonUtil.toJson(cell, true, true));

    }

    @Test
    public void testTable_text() {
        Table table = new Table(INPUT);
        Logger.info("___ as Json ___\n" + JsonUtil.toJson(table, true));
        Logger.info("___ as Description ___\n" + table.describe(false, false));
    }

    @Test
    public void testTable_array() {
        Table table = new Table(INPUT_ARRAY);
        Logger.info(JsonUtil.toJson(table, true));
        Logger.info(table.describe(true, true));
    }

    @Test
    public void testTable_weird_array() {
        Table table = new Table(WEIRD_INPUT_ARRAY);
        Logger.info(JsonUtil.toJson(table, true));
        Logger.info(table.describe(true, true));
    }

    @Test
    public void testTable_cell() {
        Table table = new Table(INPUT_CELL);
        Logger.info(JsonUtil.toJson(table, true));
        Logger.info(table.describe(false, false));
    }

    @Test
    public void testTable_was_cell() {
        Table table = new Table(WAS_CELL);
        Logger.info(JsonUtil.toJson(table, true));
        Logger.info(table.describe(false, false));
    }
}
