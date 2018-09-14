package com.soupthatisthick.encounterbuilder.util.string;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;
import com.soupthatisthick.util.string.Table;

import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.text.MatchGroupCollection;
import kotlin.text.Regex;

import static junit.framework.Assert.assertEquals;

public class TableTest {
    final String HTML_INPUT = "\n<table>\n"
            + "  <tr><td>111</td><td>222222</td><td>3333333</td></tr>\n"
            + "  <tr><td>4444</td><td>55</td><td>666</td></tr>\n"
            + "  <tr><td>77777</td><td>8888</td><td>99</td></tr>\n"
            + "  <tr><td>aaaaa</td><td>00</td><td>bbbb</td></tr>\n"
            + "</table>\n";
    final String ONE_LINE_HTML_INPUT = HTML_INPUT.replace("\n", "");

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

    @Test
    public void testTable_buildFromHtml() {
        List<List<List<String>>> tables1 = Table.fromHtml(ONE_LINE_HTML_INPUT);
        List<List<List<String>>> tables2 = Table.fromHtml(HTML_INPUT);

        Logger.info(JsonUtil.toJson(tables1, true, true));
        Logger.info(JsonUtil.toJson(tables2, true, true));

        for(List<List<String>> tableInfo : tables1) {
            Table table = new Table(tableInfo);
            Logger.info(table.describe(true, true));
        }

        for(List<List<String>> tableInfo : tables2) {
            Table table = new Table(tableInfo);
            Logger.info(table.describe(true, true));
        }
    }



    @Test
    public void testTable_fromHtml() {

        List<List<List<String>>> tables = Table.fromHtml(ONE_LINE_HTML_INPUT);
        Logger.info(JsonUtil.toJson(tables, true, true));

        StringBuilder sb = new StringBuilder();

        for(int table=0; table<tables.size(); table++) {
            List<List<String>> theTable = tables.get(table);
            sb.append("\nTable[" + table + "]:");
            for(int row=0; row<theTable.size(); row++) {
                List<String> theRow = theTable.get(row);
                sb.append("\n  Row[" + row + "]: ");
                for(int cell=0; cell<theRow.size(); cell++) {
                    String theCell = theRow.get(cell);
                    sb.append("\n    Cell[" + theCell + "]");
                }
            }
        }
        sb.append("\n");

        Logger.info(sb.toString());
    }



}
