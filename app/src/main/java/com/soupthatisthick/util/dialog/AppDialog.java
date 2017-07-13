package com.soupthatisthick.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class AppDialog extends Dialog
{
    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     * @see android.R.styleable#Theme_dialogTheme
     */
    public AppDialog(@NonNull Context context) {
        super(context);
    }
}
