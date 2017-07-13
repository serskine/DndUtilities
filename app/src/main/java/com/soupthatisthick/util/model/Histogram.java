package com.soupthatisthick.util.model;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Owner on 5/16/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Histogram<Key>
{
    private final HashMap<Key, Long> map;

    private long sum = 0;

    public Histogram()
    {
        sum = 0;
        map = new HashMap<>();
    }

    public final long getSum()
    {
        return sum;
    }

    /**
     * All keys are possible within a histogram. If the key is not present in the keyset then it's value is 0
     * @param key
     * @return
     */
    public long get(Key key)
    {
        Long value = map.get(key);
        return (value==null) ? 0 : value;
    }

    public void set(Key key, long value)
    {
        if (value<0)
        {
            throw new IndexOutOfBoundsException(String.format("You may only insert positive values into a histogram. [%s,%d]", key.toString(), value));
        } else {
            if (value==0)
            {
                Long prev = map.remove(key);
                sum -= prev;
            } else {
                Long prev = map.put(key, value);
                sum += value;
                sum -= (prev!=null) ? prev : 0;
            }
        }
    }

    /**
     * This will return all keys that do not have a value of zero
     * @return
     */
    public Set<Key> keySet()
    {
        return map.keySet();
    }

    public final void increment(Key key)
    {
        increment(key, 1);
    }

    /**
     * this will increment the specified key by value. If value is negative an exception will be thrown.
     * @param key
     * @param value
     */
    public final void increment(Key key, long value)
    {
        if (value<0)
        {
            throw new IndexOutOfBoundsException(String.format("You can't increment a value by a nagative amount. [%s,%d]", key, value));
        }
        set(key, get(key)+value);
    }

    /**
     * This will increment every entry in this histogram by it's corresponding value in the other histogram
     * @param other
     */
    public final void incrementAll(Histogram<Key> other)
    {
        for(Key key : other.keySet())
        {
            increment(key, other.get(key));
        }
    }

    /**
     * This will remove all entries in the histogram, effectively setting everything to zero
     */
    public final void clear()
    {
        map.clear();
    }

}
