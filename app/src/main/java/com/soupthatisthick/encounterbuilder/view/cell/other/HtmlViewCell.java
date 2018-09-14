package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.os.PatternMatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.soupthatisthick.encounterbuilder.util.Tables;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.TextCell;
import com.soupthatisthick.util.string.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import soupthatisthick.encounterapp.R;

public class HtmlViewCell extends ReadCell<String> {
    LinearLayout theRootLayout;
    public HtmlViewCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    @Override
    public void updateUi(String html) {
        List<Section> sections = getSections(html);
        theRootLayout.removeAllViews();
        for(Section section : sections) {
            if (section.isTable) {
                List<Table> tables = Table.getTables(section.text);
                for(Table table : tables) {
                    TableViewCell tableViewCell = new TableViewCell(inflater, null, theRootLayout);
                    tableViewCell.updateUi(table);
                    theRootLayout.addView(tableViewCell.getView());
                }
            } else {
                TextCell textCell = new TextCell(inflater, null, theRootLayout);
                textCell.updateUi(htmlString(section.text));
                theRootLayout.addView(textCell.getView());
            }
        }
    }

    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_html, parent);
        theRootLayout = view.findViewById(R.id.theRootLayout);
        return view;
    }

    public static class Section {
        public final int index;
        public final boolean isTable;
        public final String text;

        public Section(int index, boolean isTable, String text) {
            this.index = index;
            this.isTable = isTable;
            this.text = text;
        }
    }

    public static final List<Section> getSections(String html) {
        final List<Section> sectionList = new ArrayList<>();
        if (html==null) html = "";

        // We don't support sub tables. So every odd token contains non-table content
        String[] tokens = html.split(Table.REGEX_TAG_TABLE);

        final String tableTag = "<table>";
        boolean isTableFirst = (html.length() >= tableTag.length())
                && html.substring(0, tableTag.length()).equalsIgnoreCase(tableTag);

        for(int i=0; i<tokens.length; i++) {
            boolean isTable = (isTableFirst) ? (i%2==0) : (i%2==1);
            Section section = new Section(i, isTable, tokens[i]);
            sectionList.add(section);
        }
        return sectionList;
    }
}
