package com.soupthatisthick.util.set;

import com.soupthatisthick.util.differences.Differences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Owner on 6/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 *
 * WARNING! These methods are not efficient! TODO: Make more efficient
 */
public class SetUtil {

    /**
     * This will contain all the items in either set, but without duplicates
     * @param items1
     * @param items2
     * @return
     */
    public static final Set<Object> or(Set<?> items1, Set<?> items2)
    {
        HashSet<Object> theSet = new HashSet<>();
        theSet.addAll(items1);
        theSet.addAll(items2);
        return theSet;
    }

    /**
     * This will return all items that are contained within both sets
     * @param items1
     * @param items2
     * @return
     */
    public static final Set<Object> and(Set<?> items1, Set<?> items2)
    {
        HashSet<Object> theSet = new HashSet<>();
        Set<?> smaller, larger;
        if (items1.size() < items2.size())
        {
            smaller = items1;
            larger = items2;
        } else {
            smaller = items2;
            larger = items1;
        }

        for(Object item : smaller)
        {
            if (larger.contains(item))
            {
                theSet.add(item);
            }
        }
        return theSet;
    }

    /**
     * This will return all items that are in the first set (items1) but not the second. It is the same as a left outer join
     * @param items1
     * @param items2
     * @return
     */
    public static final Set<Object> diff(Set<?> items1, Set<?> items2)
    {
        HashSet<Object> theSet = new HashSet<>();
        for(Object item : items1)
        {
            if (!items2.contains(item))
            {
                theSet.add(item);
            }
        }
        return theSet;
    }

    /**
     * This will return the exclusive or of two sets of items. Items that or only in one set but not the other
     * @param items1
     * @param items2
     * @return
     */
    public static final Set<Object> xor(Set<?> items1, Set<?> items2)
    {
        return diff(or(items1, items2), and(items1, items2));
    }

    /**
     * This will return a set of objects with no duplicates contained within
     * @param items
     */
    public static final Set<Object> removeDupes(Collection<?> items)
    {
        HashSet<Object> theSet = new HashSet<>();
        theSet.addAll(items);
        return theSet;
    }

    /**
     * This will return all the items that are in the first list and the second list. If an item is in either
     * list multiple times, it will be in the new list the minimum amount of times in the new list
     * @param items1
     * @param items2
     * @return
     */
    public static final List<Object> intersect(Collection<?> items1, Collection<?> items2)
    {
        Differences diff = new Differences(items1, items2);
        return diff.expectedAndObserved;
    }

    /**
     * This will return all the items in the the first list that would not be contained in the second list
     * if it we paired up will all instances. That is to say, if A is in items1 3 times and A is in items2 twice, then
     * the return list will contain A only once (3-2=1) If items2 contains an equals or greater quantity of the same
     * item, then it will not be present in the return list
     * @param items1
     * @param items2
     * @return
     */
    public static final List<Object> leftJoin(Collection<?> items1, Collection<?> items2)
    {
        Differences diff = new Differences(items1, items2);
        return diff.expectedButNotObserved;
    }

    /**
     * This is the same as leftJoin(items2, items1). It will return all items in the second list
     * that could not be paired off with items from the first list
     * @param items1
     * @param items2
     * @return
     */
    public static final List<Object> rightJoin(Collection<?> items1, Collection<?> items2)
    {
        Differences diff = new Differences(items1, items2);
        return diff.observedButNotExpected;
    }

    /**
     * This will return all the items that can't be paired off. That is to say all items that are not in
     * the intersection of the two collections
     * @param items1
     * @param items2
     * @return
     */
    public static final List<Object> exclusion(Collection<?> items1, Collection<?> items2)
    {
        Differences diff = new Differences(items1, items2);
        return union(diff.observedButNotExpected, diff.expectedButNotObserved);
    }

    /**
     * This will return the union of two collections of items. If an item is in both list, then it
     * will be added to the new list for each instance. That is to say if A is in items1 2 times and
     * a is in items2 3 times then the new list will contain A 5 times (2+3=5)
     * @param items1
     * @param items2
     * @return
     */
    public static final List<Object> union(Collection<?> items1, Collection<?> items2)
    {
        List<Object> theItems = new LinkedList<>();
        theItems.addAll(items1);
        theItems.addAll(items2);
        return theItems;
    }
}



