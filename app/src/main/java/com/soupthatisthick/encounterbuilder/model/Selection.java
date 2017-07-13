package com.soupthatisthick.encounterbuilder.model;

/**
 * Created by Owner on 5/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Selection<DataType> {
    private boolean isSelected;
    private DataType item;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public DataType getItem() {
        return this.item;
    }

    public void setItem(DataType data) {
        this.item = data;
    }

    @Override
    public String toString()
    {
        return "" + getItem() + " is " + (isSelected() ? "selected" : "not selected");
    }
}
