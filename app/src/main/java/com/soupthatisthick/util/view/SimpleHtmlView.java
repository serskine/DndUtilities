package com.soupthatisthick.util.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    private Elements elements = new Elements();

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

    public void setDocument(Document document) {

    }

    private void parseHtml(String html) {
        this.removeAllViews();
        Document document = Jsoup.parse(html);
        this.html = html;
        this.elements = document.getAllElements();
        parseElements();
    }

    private void setElements(Elements elements) {
        this.elements = elements;
        parseElements();
    }

    private void parseElements() {
        this.removeAllViews();
        if (this.elements==null) {
            return;
        }
    }

}
