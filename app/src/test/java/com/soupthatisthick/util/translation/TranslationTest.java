package com.soupthatisthick.util.translation;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TranslationTest {

    @Test
    public void displayLocaleCodes() {
        List<Locale> localeList = new ArrayList<>();
        localeList.add(Locale.ENGLISH);
        localeList.add(Locale.FRENCH);
        localeList.add(Locale.SIMPLIFIED_CHINESE);

        Logger.info("\n___ locales ___\n" + JsonUtil.toJson(localeList, true));
    }


}
