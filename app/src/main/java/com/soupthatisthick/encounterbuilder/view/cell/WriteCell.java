package com.soupthatisthick.encounterbuilder.view.cell;

import android.app.usage.UsageEvents;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;
import com.soupthatisthick.encounterbuilder.util.listeners.UiWatcher;
import com.soupthatisthick.encounterbuilder.util.view.FieldWatcher;
import com.soupthatisthick.util.Logger;

import java.util.EventListener;

/**
 * Created by Owner on 2/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class WriteCell<Model> extends ReadCell<Model> {

    private Model model = null;

    public final Announcer<Listener> listeners = Announcer.to(Listener.class);
    private final UiWatcher uiWatcher;

    public interface Listener extends EventListener
    {
        void modelUpdated(WriteCell<? extends Object> source);
    }

    public WriteCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);

        uiWatcher = new UiWatcher() {
            @Override
            protected void onUiUpdate() {
                Logger.debug("Detected a ui update");
                ignoreUi(uiWatcher);
                updateModelFromUi(model);
                listeners.announce().modelUpdated(WriteCell.this);
                listenToUi(uiWatcher);
            }
        };

        listenToUi(uiWatcher);
    }

    /**
     * This method will remove all the fields from the ui watcher so that updates do not trigger when their fields are changed
     * @param watcher
     */
    protected abstract void ignoreUi(final UiWatcher watcher);

    /**
     * This method will add all the fields to the ui watcher so that updates will trigger when their fields are changed
     * @param watcher
     */
    protected abstract void listenToUi(final UiWatcher watcher);

    /**
     * This will update the model from the fields on the ui. When this is called we will not be listening to the ui so we don't
     * have to worry about resursive changes
     */
    protected abstract void updateModelFromUi(@Nullable Model model);

    /**
     * Returns a reference to the model
     * @return
     */
    public synchronized Model getModel()
    {
        return this.model;
    }

    @Override
    public final void updateUi(@Nullable Model model)
    {
        this.model = model;
        updateUiFromModel(model);
    }

    public abstract void updateUiFromModel(@Nullable Model model);

}
