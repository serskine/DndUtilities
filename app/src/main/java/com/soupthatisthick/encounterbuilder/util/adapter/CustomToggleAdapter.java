package com.soupthatisthick.encounterbuilder.util.adapter;

import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public abstract class CustomToggleAdapter<DataType> extends CustomListAdapter<DataType> implements ExpandableListAdapter
{
    private final HashSet<Integer> expanded = new HashSet<Integer>();


    public abstract View getCollapsedView(int position, View convertView, ViewGroup parent);
    public abstract View getExpandedView(int position, View convertView, ViewGroup parent);

    /**
     * By default there is ony one view type to be returned.
     * @param position
     * @param isExpanded
     * @return
     */
    protected int getViewType(int position, boolean isExpanded) {
        return 0;
    }

    @Override
    @CallSuper
    public void setData(List<DataType> data)
    {
        synchronized (expanded)
        {
            expanded.clear();
        }
        super.setData(data);
    }

    protected final boolean isExpanded(int position)
    {
        synchronized(expanded) {
            return expanded.contains(position);
        }
    }

    protected final void expandPosition(int position)
    {
        synchronized (expanded)
        {
            if (expanded.add(position))
            {
                notifyObservers();
            }
        }
    }

    protected final void collapsePosition(int position)
    {
        synchronized (expanded)
        {
            if (expanded.remove(position))
            {
                notifyObservers();
            }
        }
    }

    public CustomToggleAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    /**
     * Gets the number of groups. Each element is it's own group
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return getCount();
    }

    /**
     * Gets the number of children in a specified group.
     * There are no children for each group!
     *
     * @param groupPosition the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    @Override
    public Object getGroup(int groupPosition) {
        return getItem(groupPosition);
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
        return getItem(groupPosition);
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
        return getItemId(groupPosition);
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
        return getGroupId(groupPosition);
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
     * @param parent        the parent that this view will eventually be attached to
     * @return the View corresponding to the group at the specified position
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return  (isExpanded)
                ?   getExpandedView(groupPosition, convertView, parent)
                :   getCollapsedView(groupPosition, convertView, parent);
    }


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
     * @param parent        the parent that this view will eventually be attached to
     * @return the View corresponding to the child at the specified position
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getView(groupPosition, convertView, parent);
    }

    /**
     * Whether the child at the specified position is selectable.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group
     * @return whether the child is selectable.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**
     * Called when a group is expanded.
     *
     * @param groupPosition The group being expanded.
     */
    @Override
    public void onGroupExpanded(int groupPosition) {
        expandPosition(groupPosition);
    }

    /**
     * Called when a group is collapsed.
     *
     * @param groupPosition The group being collapsed.
     */
    @Override
    public void onGroupCollapsed(int groupPosition) {
        collapsePosition(groupPosition);
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
        return groupId;
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
        return groupId;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        return getGroupView(position, isExpanded(position), convertView, parent);
    }

    /**
     * Get the type of View that will be created by {@link #getView} for the specified item.
     *
     * @param position The position of the item within the adapter's data set whose view type we
     *                 want.
     * @return An integer representing the type of View. Two views should share the same type if one
     * can be converted to the other in {@link #getView}. Note: Integers must be in the
     * range 0 to {@link #getViewTypeCount} - 1. {@link #IGNORE_ITEM_VIEW_TYPE} can
     * also be returned.
     * @see #IGNORE_ITEM_VIEW_TYPE
     */
    @Override
    public int getItemViewType(int position)
    {
        return getViewType(position, isExpanded(position));
    }
}
