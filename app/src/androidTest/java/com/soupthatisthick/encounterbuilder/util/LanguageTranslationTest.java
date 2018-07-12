package com.soupthatisthick.encounterbuilder.util;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.translation.MyTranslator;

import org.junit.Test;

import java.util.Locale;

import static junit.framework.Assert.assertEquals;

public class LanguageTranslationTest extends InstrumentationTest {


    @Override
    protected void onSetup() {

    }

    @Override
    protected void onTeardown() {

    }


    @Test
    public void english_to_english() {

        final String[] input = new String[] {
                "Hello"
        };
        final String[] expected = new String[] {
                "Hello"
        };

        for(int i=0; i<input.length; i++) {
            final String observed = MyTranslator.getInstance(context).translate(
                    input[i],
                    Locale.ENGLISH,
                    Locale.ENGLISH
            );
            Logger.info("[" + i + "]: " + input[i] + " => " + observed);
            assertEquals("input[" + i + "]: ", expected[i], observed);
        }
    }

    @Test
    public void translations() {

        final Locale[] supported = new Locale[] {
            Locale.ENGLISH,
            Locale.FRENCH,
            Locale.SIMPLIFIED_CHINESE
        };

        final String[] input = new String[] {
            "Hello"
        };
        final String[] expected = new String[] {
            "Hello"
        };

        final Locale source = Locale.ENGLISH;
        for(Locale target : supported) {
            for (int i = 0; i < input.length; i++) {
                final String observed = MyTranslator.getInstance(context).translate(
                    input[i],
                    source,
                    target
                );
                Logger.info("[" + i + "]: ("
                    + source.getDisplayName()
                    + " to "
                    + target.getLanguage()
                    + ") - "
                    + input[i]
                    + " => " +
                    observed
                );
            }
        }
    }
}
