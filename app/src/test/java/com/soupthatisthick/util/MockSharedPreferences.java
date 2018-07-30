package com.soupthatisthick.util;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MockSharedPreferences implements SharedPreferences {
    private Map<String, Object> allMap = new HashMap<>();
    private ArrayList<OnSharedPreferenceChangeListener> listeners = new ArrayList<>();

    @Override
    public Map<String, ?> getAll() {
        return allMap;
    }

    @Nullable
    @Override
    public String getString(String key, @Nullable String defValue) {
        return defValue;
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
        return defValues;
    }

    @Override
    public int getInt(String key, int defValue) {
        return defValue;
    }

    @Override
    public long getLong(String key, long defValue) {
        return defValue;
    }

    @Override
    public float getFloat(String key, float defValue) {
        return defValue;
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return defValue;
    }

    @Override
    public boolean contains(String key) {
        return false;
    }

    @Override
    public Editor edit() {
        return null;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {
        listeners.remove(listener);
    }
}
