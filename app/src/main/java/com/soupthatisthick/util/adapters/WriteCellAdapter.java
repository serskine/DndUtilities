package com.soupthatisthick.util.adapters;

import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.WriteCell;

import java.util.EventListener;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class WriteCellAdapter<Model> extends ReadCellAdapter<Model> {

    public interface Listener extends EventListener
    {
        void positionUpdated(
            WriteCellAdapter<? extends Object> source,
            Object model,
            int position);
    }

    public final Announcer<WriteCellAdapter.Listener> listeners = Announcer.to(WriteCellAdapter.Listener.class);

    public WriteCellAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WriteCell<Model> writeCell = createWriteCell(inflater, convertView, parent);
        writeCell.updateUi(getCastedItem(position));
        writeCell.listeners.addListener(new PositionListener(this, position));
        return writeCell.getView();
    }

    @Override
    protected final ReadCell<Model> createReadCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        return createWriteCell(inflater, convertView, parent);
    }

    protected abstract WriteCell<Model> createWriteCell(LayoutInflater inflater, View convertView, ViewGroup parent);


    private class PositionListener implements WriteCell.Listener
    {
        public final int position;
        public final WriteCellAdapter<Model> adapter;

        private PositionListener(WriteCellAdapter<Model> adapter, int position)
        {
            this.position = position;
            this.adapter = adapter;
        }

        @Override
        public void modelUpdated(WriteCell<? extends Object> cell) {
            Object model = cell.getModel();
            listeners.announce().positionUpdated(
                adapter,
                model,
                position)
            ;
        }
    }
}
