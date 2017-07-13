package com.soupthatisthick.encounterbuilder.util;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Owner on 1/13/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Picker<Type> implements Map<Type, Double> {

    ////////////////////////////////////////////////////////////
    //
    // Map interface requirements
    //
    ////////////////////////////////////////////////////////////

    private Map<Type, Double> values = new HashMap<>();
    private TreeMap<Double, Set<Type>> items = new TreeMap<>();
    private double totalWeight = 0D;

    @Override
    public int size() {
        return values.keySet().size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return items.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return items.containsKey(value);
    }

    @Override
    public Double get(Object key) {
        Double weight = values.get(key);
        return (weight==null) ? 0d : weight;
    }

    @Override
    public Double put(Type key, Double value) {

        Double weight = values.get(key);
        if (weight==null)
        {
            if (value != null && value > 0d)
            {
                values.put(key, value);
                totalWeight += value;
            }
        } else {
            totalWeight -= value;
            if (value != null && value > 0d)
            {
                values.put(key, value);
                totalWeight += value;
            }
        }
        return weight;
    }

    @Override
    public Double remove(Object key) {
        return values.remove(key);
    }

    @Override
    public void putAll(Map<? extends Type, ? extends Double> m) {

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public Set<Type> keySet() {
        return null;
    }

    @NonNull
    @Override
    public Collection<Double> values() {
        return null;
    }

    @NonNull
    @Override
    public Set<Entry<Type, Double>> entrySet() {
        return null;
    }
}
