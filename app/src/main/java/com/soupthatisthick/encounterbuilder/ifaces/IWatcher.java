package com.soupthatisthick.encounterbuilder.ifaces;

/**
 * Created by Owner on 2/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public interface IWatcher<Control> {
    void listenTo(Control control);
    void ignore(Control control);
}
