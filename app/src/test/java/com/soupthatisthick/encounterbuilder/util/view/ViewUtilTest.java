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
    public void preprocessHtml_beholderText() {
        final String inputHtml = "<p><i><b>Bite. </b>Melee Weapon Attack:</i> +5 to hit, reach 5ft., one target. <i>Hit: </i> 14 (4d6) piercing damage.</p>\n" +
                "<p><i><b>Eye Rays. </b></i> The beholder shoots three of the following magical eye rays at random (reroll duplicates), choosing one to three targets it can see within 120 feet of it:<br>\n" +
                "<ul>\n" +
                "<li><i>1. Charm Ray: </i>The targeted creature must succeed on a DC 16 Wisdom Saving throw or be charmed by the beholder for 1 hour, or until the beholder harms the creature.</li>\n" +
                "<li><i>2. Paralyzing Ray. </i>The targeted creature must succeed on a DC 16 Constitution saving throw or be paralyzed for 1 minute. The target can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success.</li>\n" +
                "<li><i>3. Fear Ray. </i>The targeted creature must succeed on a DC 16 Wisdom saving throw or be frightened for 1 minute. The target can repeat the saving throw at the end of each of it's turns, ending the effect on itself on a success.</li>\n" +
                "<li><i>4. Slowing Ray. </i>The targeted creature must succeed on a DC 16 Dexterity saving throw. On a failed save, the target's speed is halved for 1 minute. In addition, the creature can't take reactions, and it can either take an action or a bonus action on it's turn, not both. The creature can repeat the saving throw at the end of each of its turns, ending the effect on itself on a success.</li>\n" +
                "<li><i>5. Enervation Ray. </i>The targeted creature must make a DC 16 Constitution saving throw, taking 36 (8d8) necrotic damage on a failed save, or half as much damage on a successful one.</li>\n" +
                "<li><i>6. Telekenetic Ray. </i> If the target is a creature, it must succeed on a DC 16 Strength saving throw or the beholder moves it up to 30 feet in any direction. It is restrained by the ray's telekenetic grip until the start of the beholder's next turn or until the beholder is incapacitated.<br>\n" +
                "If the target is an object weighing 300 pounds or less that isn't being worn or carried, it is moved up to 30 feet in any direction. The beholder can also exert fine control on objects with this ray, such as manipulating a simple tool or opening a door or container.</li>\n" +
                "<li><i>7. Sleep Ray </i>The targeted creature must succeed on a DC 16 Wisdom saving throw or fall asleep and remain unconcious for 1 minute. The target awakens if it takes damage or another creature takes an action to wake it. This ray has no effect on constructs and undead.</li>\n" +
                "<li><i>8. Petrification Ray </i>The targeted creature must succeed on a DC 16 Dexterity saving throw. On a failed save, the creature begins to turn to stone and is restrained. It must repeat the saving throw at the end of its next turn. On a success, the effect ends. On a failure, the creature is petrified until freed by the <i>greater restoration</i> spell or other magic.</li>\n" +
                "<li><i>9. Disintegration Ray </i>If the target is a creature, it must succeed on a DC 16 Dexterity saving throw or take 45 (10d8) force damage. If this damage reduces the creature to 0 hit points, its body becomes a pile of fine gray dust.<br>\n" +
                "If the target is a Large or smaller nonmagical object or creation of magical force, it is disintegrated without a saving throw. If the target is a Huge or larger object or creation of magical force, this ray disentegrates a 10-foot cube of it.</li>\n" +
                "<li><i>10. Death Ray </i>The targeted creature must succeed on a DC 16 Dexterity saving throw or take 55 (10d10) necrotic damage. The target dies if the ray reduces it to 0 hit points.</li>\n" +
                "</ul>\n" +
                "</p>\n" +
                "\n" +
                "\n" +
                "</p>";
        Logger.info("\n___ inputHtml ___\n" + inputHtml);
        String observed = ViewUtil.preprocessHtml(inputHtml);
        Logger.info("\n" + observed);
    }

    @Test
    public void preprocessHtml_testHtml() {
        final String inputHtml = "<ul>\n" +
                "                <li><p>The Quick brown fox jumped over the lazy dogs.</p></li>\n" +
                "                <li><p><i>The Quick brown fox jumped over the lazy dogs.</i></p></li>\n" +
                "                <li><p><h1>The Quick brown fox jumped over the lazy dogs.</h1></p></li>\n" +
                "                <li><p><h2>The Quick brown fox jumped over the lazy dogs.</h2></p></li>\n" +
                "                <li><p><h3>The Quick brown fox jumped over the lazy dogs.</h3></p></li>\n" +
                "                <li><p><h4>The Quick brown fox jumped over the lazy dogs.</h4></p></li>\n" +
                "                <li><p><h5>The Quick brown fox jumped over the lazy dogs.</h5></p></li>\n" +
                "                <li><p><h6>The Quick brown fox jumped over the lazy dogs.</h6></p></li>\n" +
                "                <li><p><em>The Quick brown fox jumped over the lazy dogs.</em></p></li>\n" +
                "                <li><p><b>The Quick brown fox jumped over the lazy dogs.</b></p></li>\n" +
                "                <li><p><u>The Quick brown fox jumped over the lazy dogs.</u></p></li>\n" +
                "            </ul>";

        String observed = ViewUtil.preprocessHtml(inputHtml);
        Logger.info("\n" + observed);
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
