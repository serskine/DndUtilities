package com.soupthatisthick.encounterbuilder.util;

import com.soupthatisthick.encounterbuilder.InstrumentationTest;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.Logger;

import org.junit.Test;

import soupthatisthick.encounterapp.R;

public class ViewUtilTest extends InstrumentationTest {

    @Override
    protected void onSetup() {

    }

    @Override
    protected void onTeardown() {

    }

    @Test
    public void preprocessHtml_testHtml() {
        final String inputHtml = context.getResources().getString(R.string.test_html);
        Logger.debug("\n___ inputHtml ___\n" + inputHtml);
        final String observedHtml = ViewUtil.preprocessHtml(inputHtml);
        Logger.debug("\n___ observedHtml ___\n" + observedHtml);
    }

}
