package com.soupthatisthick.encounterbuilder.util;

/**
 * Created by Owner on 6/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class KeyValuePair<KeyType, ValueType> {
    private KeyType key;
    private ValueType value;

    public ValueType getValue() {
        return value;
    }

    public void setValue(ValueType value) {
        this.value = value;
    }

    public KeyType getKey() {
        return key;
    }

    public void setKey(KeyType key) {
        this.key = key;
    }
}
