package com.soupthatisthick.encounterbuilder.util.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.SelectionAdapter;
import com.soupthatisthick.encounterbuilder.model.Selection;
import com.soupthatisthick.util.dialog.AppDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class ListSelectDialog extends AppDialog
{

    protected ListView theListView;
    private SelectionAdapter selectionAdapter;

    /**
     * Creates a dialog window that uses the default dialog theme.
     * <p>
     * The supplied {@code context} is used to obtain the window manager and
     * base theme used to present the dialog.
     *
     * @param context the context in which the dialog should run
     */
    public ListSelectDialog(@NonNull Context context) {
        super(context);
    }

    public final SelectionAdapter getSelectionAdapter() {
        return selectionAdapter;
    }

    public void setData(Map<Object, Boolean> selectionMap, boolean valueIfNull)
    {
        List<Selection> selectionList = new ArrayList<>();
        for(Object key : selectionMap.keySet())
        {
            Selection selection = new Selection();
            Boolean value = selectionMap.get(key);
            if (value==null)
            {

            }
        }
    }

    public void setData(List<Object> data, boolean defaultSelection)
    {
        List<Selection> selectionList = new ArrayList<>();
        for(int i=0; i<data.size(); i++)
        {
            Object value = data.get(i);
            Selection selection = new Selection();
            selection.setItem(getTextFor(value).toString());
            selection.setSelected(defaultSelection);
            selectionList.add(selection);
        }
        getSelectionAdapter().setData(selectionList);
    }

    protected String getTextFor(Object item)
    {
        throw new RuntimeException("TODO: Implement!");
    }

    public void setSelectionAdapter(SelectionAdapter selectionAdapter) {
        this.selectionAdapter = selectionAdapter;
    }
}