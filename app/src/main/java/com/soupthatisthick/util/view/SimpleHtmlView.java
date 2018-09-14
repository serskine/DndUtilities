package com.soupthatisthick.util.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.soupthatisthick.encounterbuilder.view.cell.other.HtmlViewCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.XMLFormatter;

public class SimpleHtmlView extends LinearLayout {

    private String html;

    public SimpleHtmlView(Context context) {
        super(context);
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
        parseHtml(html);
    }

    private void parseHtml(String html) {
        this.removeAllViews();
        LayoutInflater layoutInflater;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            layoutInflater = getContext().getSystemService(LayoutInflater.class);
        } else {
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        HtmlViewCell htmlViewCell = new HtmlViewCell(layoutInflater, null, this);
        htmlViewCell.updateUi(html);
        this.addView(htmlViewCell.getView());
    }

}
