package com.soupthatisthick.encounterbuilder.util.adapter;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class MapExpandableListAdapter<Parent, Child> implements ExpandableListAdapter {

    protected final ArrayList<DataSetObserver> observers = new ArrayList<>();
    protected final List<Parent> parentList = new ArrayList<>();

    protected final HashSet<Parent> expandedSet = new HashSet<>();
    protected final HashMap<Parent, List<Child>> theMap = new HashMap<>();

    protected final LayoutInflater inflater;

    public MapExpandableListAdapter(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }


    private final List<Child> getChildrenOf(Parent parent)
    {
        List<Child> children = theMap.get(parent);
        if (children==null)
        {
            children = new ArrayList<>();
            theMap.put(parent, children);
        }
        return children;
    }

    public void setData(Map<Parent, List<Child>> data)
    {
        parentList.clear();
        expandedSet.clear();
        parentList.addAll(data.keySet());
        theMap.putAll(data);
        notifyObservers();
    }

    public final void notifyObservers()
    {
        for(DataSetObserver observer : observers)
        {
            observer.onChanged();
        }
    }

    /**
     * @param observer
     */
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        observers.add(observer);
    }

    /**
     * @param observer
     */
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        observers.remove(observer);
    }

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupPosition the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        Parent parent = parentList.get(groupPosition);
        return getChildrenOf(parent).size();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Parent parent = parentList.get(groupPosition);
        List<Child> children = getChildrenOf(parent);
        return children.get(childPosition);
    }

    /**
     * Gets the ID for the group at the given position. This group ID must be
     * unique across groups. The combined ID (see
     * {@link #getCombinedGroupId(long)}) must be unique across ALL items
     * (groups and all children).
     *
     * @param groupPosition the position of the group for which the ID is wanted
     * @return the ID associated with the group
     */
    @Override
    public long getGroupId(int groupPosition) {
        return ("group" + groupPosition).hashCode();
    }

    /**
     * Gets the ID for the given child within the given group. This ID must be
     * unique across all children within the group. The combined ID (see
     * {@link #getCombinedChildId(long, long)}) must be unique across ALL items
     * (groups and all children).
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group for which
     *                      the ID is wanted
     * @return the ID associated with the child
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return ("child" + groupPosition + "." + childPosition).hashCode();
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the
     * underlying data.
     *
     * @return whether or not the same ID always refers to the same object
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * Gets a View that displays the given group. This View is only for the
     * group--the Views for the group's children will be fetched using
     * {@link #getChildView(int, int, boolean, View, ViewGroup)}.
     *
     * @param groupPosition the position of the group for which the View is
     *                      returned
     * @param isExpanded    whether the group is expanded or collapsed
     * @param convertView   the old view to reuse, if possible. You should check
     *                      that this view is non-null and of an appropriate type before
     *                      using. If it is not possible to convert this view to display
     *                      the correct data, this method can create a new view. It is not
     *                      guaranteed that the convertView will have been previously
     *                      created by
     *                      {@link #getGroupView(int, boolean, View, ViewGroup)}.
     * @param parentViewGroup        the parent that this view will eventually be attached to
     * @return the View corresponding to the group at the specified position
     */
    @Override
    public final View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parentViewGroup)
    {
        Parent parent = parentList.get(groupPosition);
        return getViewForParent(parent, groupPosition, isExpanded, convertView, parentViewGroup);
    }

    /**
     * This method will return the view that represents the parent group
     * @param group
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parentViewGroup
     * @return
     */
    public abstract View getViewForParent(Parent group, int groupPosition, boolean isExpanded, View convertView, ViewGroup parentViewGroup);


    /**
     * Gets a View that displays the data for the given child within the given
     * group.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child (for which the View is
     *                      returned) within the group
     * @param isLastChild   Whether the child is the last child within the group
     * @param convertView   the old view to reuse, if possible. You should check
     *                      that this view is non-null and of an appropriate type before
     *                      using. If it is not possible to convert this view to display
     *                      the correct data, this method can create a new view. It is not
     *                      guaranteed that the convertView will have been previously
     *                      created by
     *                      {@link #getChildView(int, int, boolean, View, ViewGroup)}.
     * @param parentViewGroup        the parent that this view will eventually be attached to
     * @return the View corresponding to the child at the specified position
     */
    @Override
    public final View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parentViewGroup) {
        Parent group = parentList.get(groupPosition);
        List<Child> children = getChildrenOf(group);
        Child child = children.get(childPosition);
        return getChildViewForParent(
            groupPosition,
            group,
            childPosition,
            child,
            isLastChild,
            convertView,
            parentViewGroup
        );
    }

    /**
     * This is used to create views for the children
     * @param groupPosition
     * @param parent
     * @param childPosition
     * @param child
     * @param isLastChild
     * @param convertView
     * @param parentViewGroup
     * @return
     */
    public abstract View getChildViewForParent(
        int groupPosition,
        Parent parent,
        int childPosition,
        Child child,
        boolean isLastChild,
        View convertView,
        ViewGroup parentViewGroup
    );

    /**
     * Whether the child at the specified position is selectable.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group
     * @return whether the child is selectable.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return parentList.isEmpty();
    }

    /**
     * Called when a group is expanded.
     *
     * @param groupPosition The group being expanded.
     */
    @Override
    public void onGroupExpanded(int groupPosition) {
        expandedSet.add(parentList.get(groupPosition));
    }

    /**
     * Called when a group is collapsed.
     *
     * @param groupPosition The group being collapsed.
     */
    @Override
    public void onGroupCollapsed(int groupPosition) {
        expandedSet.remove(parentList.get(groupPosition));
    }

    /**
     * Gets an ID for a child that is unique across any item (either group or
     * child) that is in this list. Expandable lists require each item (group or
     * child) to have a unique ID among all children and groups in the list.
     * This method is responsible for returning that unique ID given a child's
     * ID and its group's ID. Furthermore, if {@link #hasStableIds()} is true, the
     * returned ID must be stable as well.
     *
     * @param groupId The ID of the group that contains this child.
     * @param childId The ID of the child.
     * @return The unique (and possibly stable) ID of the child across all
     * groups and children in this list.
     */
    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    /**
     * Gets an ID for a group that is unique across any item (either group or
     * child) that is in this list. Expandable lists require each item (group or
     * child) to have a unique ID among all children and groups in the list.
     * This method is responsible for returning that unique ID given a group's
     * ID. Furthermore, if {@link #hasStableIds()} is true, the returned ID must be
     * stable as well.
     *
     * @param groupId The ID of the group
     * @return The unique (and possibly stable) ID of the group across all
     * groups and children in this list.
     */
    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
