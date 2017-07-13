package com.soupthatisthick.encounterbuilder.util.translater;

import android.support.test.runner.AndroidJUnit4;

import com.soupthatisthick.encounterbuilder.util.translater.DateTranslater;
import com.soupthatisthick.util.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class DateTranslaterTest {

    @Test
    public void testDateTranslations()
    {
        assertTranslationOfDate(new Date(0));
        assertTranslationOfDate(new Date());
    }

    @Test
    public void testTextTranslations()
    {
        assertTranslationOfText("1969/04/30");
        assertTranslationOfText("2000/09/25");
        assertTranslationOfText("2017/01/01");
        assertTranslationOfText("0001/01/01");
        assertTranslationOfText("0010/01/01");
        assertTranslationOfText("0011/01/01");
        assertTranslationOfText("0100/01/01");
        assertTranslationOfText("0101/01/01");
        assertTranslationOfText("0110/01/01");
        assertTranslationOfText("0111/01/01");
        assertTranslationOfText("1000/01/01");
        assertTranslationOfText("1001/01/01");
        assertTranslationOfText("1010/01/01");
        assertTranslationOfText("1011/01/01");
        assertTranslationOfText("1100/01/01");
        assertTranslationOfText("1101/01/01");
        assertTranslationOfText("1110/01/01");
        assertTranslationOfText("1111/01/01");

    }

    public void assertTranslationOfText(String expectedText)
    {
        Date observedDate = DateTranslater.translate(expectedText);
        String observedText = DateTranslater.translate(observedDate);

        Logger.info("Expected Text = " + expectedText);
        Logger.info("Observed Date = " + observedDate.toString());
        Logger.info("Observed Text = " + observedText);

        assertEquals("Texts should be the same", expectedText, observedText);

        assertTrue(expectedText.matches(DateTranslater.REGEX));
        assertTrue(observedText.matches(DateTranslater.REGEX));

    }

    public void assertTranslationOfDate(Date expectedDate)
    {
        String observedText = DateTranslater.translate(expectedDate);
        Date observedDate = DateTranslater.translate(observedText);


        Logger.info("Expected Date = " + expectedDate.toString());
        Logger.info("Observed Text = " + observedText);
        Logger.info("Observed Date = " + observedDate);

        assertEquals("Dates should be the same", expectedDate.getDate(), observedDate.getDate());

        assertTrue(observedText.matches(DateTranslater.REGEX));

    }



}
