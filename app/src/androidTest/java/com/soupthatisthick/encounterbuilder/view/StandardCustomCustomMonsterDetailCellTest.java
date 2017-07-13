package com.soupthatisthick.encounterbuilder.view;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.soupthatisthick.encounterbuilder.view.cell.detail.StandardMonsterDetailCell.myHtmlString;
import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 3/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@RunWith(AndroidJUnit4.class)
public class StandardCustomCustomMonsterDetailCellTest {

    private Context context;

    @Before
    public void setUp()
    {
        // Context of the app under test.
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testMyHtmlString() throws Exception
    {
        String[] inputs = new String[] {
            "<html><br><li><br><name><text><b>Bite</text></name><br><desc><text><i>Melee Weapon Attack: +5 to hit, reach 5 ft., one target. Hit: 10 (2d6 + 3) piercing damage plus 7 (2d6) poison damage.</text></desc><br><attack_bonus>5</attack_bonus><br><damage_dice><text>2d6 + 2d6</text></damage_dice><br><damage_bonus>3</damage_bonus><br></li><br></html>"
        };
        String[] expected = new String[] {
            "\n" +
            "\n" +
            "Bite\n" +
            "Melee Weapon Attack: +5 to hit, reach 5 ft., one target. Hit: 10 (2d6 + 3) piercing damage plus 7 (2d6) poison damage.\n" +
            "5\n" +
            "2d6 + 2d6\n" +
            "3\n" +
            "\n"
        };
        String[] observed = new String[inputs.length];

        for(int i=0; i<inputs.length; i++)
        {
            observed[i] = myHtmlString(inputs[i]);
        }

        assertMatch(expected, observed);

    }

    /**
     * Iterates over the array of expected results and expected outputs
     * @param expected
     * @param observed
     */
    public void assertMatch(String[] expected, String[] observed)
    {
        assertEquals("Array lengths should be the same.", expected.length, observed.length);
        for(int i=0; i<expected.length; i++)
        {
            assertEquals("expected[" + i + "] should match observed[" + i + "]", expected[i], observed[i]);
        }
    }
}
