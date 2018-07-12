package com.soupthatisthick.encounterbuilder.util.view;

import com.soupthatisthick.util.Logger;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ViewUtilTest {

    private static final String HEADER = "\nThis is a header line\n";
    private static final String FOOTER = "\nThis is a footer line\n";

    private static final String inputList(
        String listTag,
        String listElementTag
    ) {
        return ""
        + "<" + listTag + ">\n"
        + "    <" + listElementTag + ">line 1</" + listElementTag + ">\n"
        + "    <" + listElementTag + ">line 1</" + listElementTag + ">\n"
        + "    <" + listElementTag + ">line 1</" + listElementTag + ">\n"
        + "</" + listTag + ">";
    }

    private static final String expectedList() {
        return  "<p>\n"
            +   "     • line 1<br>\n"
            +   "     • line 1<br>\n"
            +   "     • line 1<br>\n"
            +   "</p>";
    }

    @Test
    public void preprocessHtml_lists() {
        String[] inputs = {
                inputList("ul", "li"),
                inputList("ol", "li"),
                inputList("dl", "li"),
                inputList("UL", "LI"),
                inputList("OL", "LI"),
                inputList("DL", "LI"),
                inputList("Ul", "Li"),
                inputList("Ol", "Li"),
                inputList("Dl", "Li"),
                inputList("uL", "lI"),
                inputList("oL", "lI"),
                inputList("dL", "lI")

        };
        String[] expected = {
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList(),
                expectedList()
        };

        assertEquals("Input length should be the same as the expected outputs length.", inputs.length, expected.length);

        for(int i=0; i<inputs.length; i++)
        {
            String observed = ViewUtil.preprocessHtml(inputs[i]);
            Logger.info("\n___ input[" + i + "] ___\n" + inputs[i]);
            Logger.info("\n___ observed[" + i + "] ___\n" + observed);
            assertEquals("Failed on index " + i + ".", expected[i], observed);
        }
    }
}
