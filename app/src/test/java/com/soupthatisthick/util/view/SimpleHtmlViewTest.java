package com.soupthatisthick.util.view;

import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class SimpleHtmlViewTest {

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
    public void parseHtmlUsingJsoup() {
        StringBuilder sb = new StringBuilder();
        sb = describeHtml(sb, SAMPLE_HTML, 0);
        System.out.println(sb.toString());
    }

    StringBuilder describeHtml(StringBuilder sb, String html, int depth) {
        Document document = Jsoup.parse(html);
        return describeElements(sb, document.getAllElements(), depth);
    }

    StringBuilder describeElements(StringBuilder sb, Elements elements, int depth) {
        final String indent = Text.padString("  ", depth);
        for(Element element : elements) {
            if (element.isBlock()) {
                sb.append("\n").append(indent);
            }
            if (element.hasText()) {
                sb.append("[").append(element.text()).append("]");
            }
        }
        return sb;
    }
}
