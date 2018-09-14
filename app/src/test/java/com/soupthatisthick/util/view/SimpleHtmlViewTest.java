package com.soupthatisthick.util.view;

import android.text.Spanned;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;
import com.soupthatisthick.util.string.Table;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.List;

public class SimpleHtmlViewTest {

    private static final String TABLE_HTML = "<table>\n"
            + "  <tr><th>h 1</th><th>h 2</th><th>h 3</th></tr>\n"
            + "  <tr><td>cell 1.1</td><td>cell 1.2</td><td>cell 1.3</td></tr>\n"
            + "  <tr><td>cell 2.1</td><td>cell 2.2</td><td>cell 2.3</td></tr>\n"
            + "  <tr><td>cell 3.1</td><td>cell 3.2</td><td>cell 3.3</td></tr>\n"
            + "</table>\n";

    private static final String INLINE_HTML = "<p>The <b>quick</b> brown <i>fox</i> jumped <b>over <i>the lazy</i> dogs</b>.</p><p>Stuff happened.</p>";
    private static final String SAMPLE_HTML = "\"<p>You pull wisps of shadow material from the Shadowfell to create a nonliving object of vegetable matter within range - soft goods, rope, wood, or something similar. You can also use this spell to create mineral objects such as stone, crystal, or metal. The object created must be no larger than a 5-foot cube, and the object must be of a form and material that you have seen before.</p>\n" +
            "<p>The duration depends on the object's material. If the object is composed of multiple materials, use the shortest duration.</p>\n" +
            "<!--\n" +
            "<ul>\n" +
            "<li><b>Material - Duration:</b></li>\n" +
            "<li><b>Vegetable matter</b> - 1 day </li>\n" +
            "<li><b>Stone/crystal</b> - 12 hours </li>\n" +
            "<li><b>Precious metals</b> - 1 hour </li>\n" +
            "<li><b>Gems</b> - 10 minutes </li>\n" +
            "<li><b>Adamantine/Mithral</b> - 1 minute </li>\n" +
            "</ul>\n" +
            "-->\n" +
            "<table>\n" +
            "<tr><th><b>Material</b></th><th>Duration</th></tr>\n" +
            "<tr><td>Vegetable matter</td><td>1 day</td></tr>\n" +
            "<tr><td>Stone/crystal</td><td>12 hours</td></tr>\n" +
            "<tr><td>Precious metals</td><td>1 hour</td></tr>\n" +
            "<tr><td>Gems</td><td>10 minutes </td></tr>\n" +
            "<tr><td>Adamantine/Mithral</td><td> 1 minute</td></tr>\n" +
            "</table>\n" +
            "<p>Using any material created by this spell as another spell's material component causes that spell to fail.</p>\n" +
            "<p><b>At Higher Levels:</b> When you cast this spell using a spell slot of 6th level or higher, the cube increases by 5 feet for each slot level above 5th.</p>\"";




    @Test
    public void parseHtmlUsingJsoup_inlineHtml() {
        StringBuilder sb = new StringBuilder();
        Document doc = Jsoup.parse(INLINE_HTML);
        System.out.println(parse(sb, doc.body(), 0).toString());
    }

    @Test
    public void parseTableHtml() {
        Logger.info("\n" + TABLE_HTML);

        List<Table> tables = Table.getTables(Table.fromHtml(TABLE_HTML));
        for(Table table: tables) {
            Logger.info(table.describe(false, false));
        }
        Spanned spanned = HtmlView.htmlString(TABLE_HTML);
        Logger.info("\n" + spanned);
    }

    private StringBuilder parse(StringBuilder sb, Node root, int depth) {
        if (root==null) {
            return sb;
        }
        final String indent = Text.padString("  ", depth);
        boolean isElement = root instanceof Element;
        if (isElement) {
            Element rootElement = (Element) root;
            if (rootElement.isBlock()) {
                sb.append("\n").append(indent);
            }
            Tag tag = rootElement.tag();
            if (tag.isKnownTag()) {
                sb.append("<" + tag.getName() + "," + tag.isBlock() + ">");
            }
        } else {
            sb.append("[" + depth + "," + root.toString().trim() + "]");
        }

        if (root.childNodeSize()!=0) {
            final Node child = root.childNode(0);
            parse(sb, child, depth+1);
        }

        if (isElement) {
            Element rootElement = (Element) root;
            Tag tag = rootElement.tag();
            if (tag.isKnownTag()) {
                sb.append("</" + tag.getName() + ">");
            }
        }

        parse(sb, root.nextSibling(), depth);
        return sb;
    }

}
