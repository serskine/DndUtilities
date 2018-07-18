package com.soupthatisthick.encounterbuilder.model.podam;

import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Annotation;
import java.util.List;

import uk.co.jemos.podam.common.AttributeStrategy;

public class HtmlTextStrategy implements AttributeStrategy<String> {


    private String[] HTML = new String[] {
        "<p>The quick brown fox jumped over the lazy dogs.</p>",
        "<p> This is a list </p>\n" +
                "    <ul>\n" +
                "        <li> list element 1</li>\n" +
                "        <li> list element 2</li>\n" +
                "        <li> list element 3</li>\n" +
                "    </ul>",
        "<p>Started several mistake joy say painful removed reached end. State burst think end are its. Arrived off she elderly beloved him affixed noisier yet. An course regard to up he hardly. View four has said does men saw find dear shy. Talent men wicket add garden. </p>",
        "<p>Am of mr friendly by strongly peculiar juvenile. Unpleasant it sufficient simplicity am by friendship no inhabiting. Goodness doubtful material has denoting suitable she two. Dear mean she way and poor bred they come. He otherwise me incommode explained so in remaining. Polite barton in it warmly do county length an.</p>",
        "<p>Started several mistake joy say painful removed reached end. State burst think end are its. Arrived off she elderly beloved him affixed noisier yet. An course regard to up he hardly. View four has said does men saw find dear shy. Talent men wicket add garden. </p>"
    };

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        int idx = RandomUtils.nextInt(0, HTML.length);
        return HTML[idx];
    }
}
