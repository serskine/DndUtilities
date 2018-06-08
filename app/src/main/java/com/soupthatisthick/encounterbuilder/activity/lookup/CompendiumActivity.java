package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.soupthatisthick.encounterbuilder.DndUtilApp;
import com.soupthatisthick.encounterbuilder.activity.SearchFiltersActivity;
import com.soupthatisthick.encounterbuilder.adapters.lookup.CompendiumAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.ItemListSummaryAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.SelectionAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.TextSelectionAdapter;
import com.soupthatisthick.encounterbuilder.dao.helper.CompendiumResource;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.exception.DaoModelException;
import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.model.Selection;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.model.lookup.EntityList;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.progress.ProgressMonitor;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.util.sort.SortByTitleComparator;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.ViewToggleListActivity;
import com.soupthatisthick.util.adapters.WriteCellAdapter;
import com.soupthatisthick.util.dao.Dao;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

@SuppressWarnings({"unused", "unchecked", "SpellCheckingInspection"})
public class CompendiumActivity extends ViewToggleListActivity<Object> implements CompendiumResource.Listener {

    private final Object DB_LOCK = new Object();

    // This is the set that we will use to update the dataset on the adapter
    ArrayList<Selection> selectionsList = new ArrayList<>();

    private CompendiumResource compendiumResource;

    private ViewGroup theFilterGroup, theResultsGroup;
    private ToggleButton theFiltersButton, theSearchButton;
    private SelectionAdapter theSelectionAdapter;
    private ViewGroup theTabsGroup;

    private boolean isSelectingFilters = false;

    private final Set<Integer> selectedPositions = new HashSet<>();

    static AsyncTask<Object, Object, Boolean> LOAD_TASK;

    /**
     * This is called to toggle the selected state of a position from on to off or off to on
     * @param position indicates which item is selected
     */
    private void toggleSelection(int position)
    {
        if (selectedPositions.contains(position))
        {
            selectedPositions.remove(position);
        } else {
            selectedPositions.add(position);
        }
    }

    /**
     * This is called to clear all selections
     */
    private void clearSelections()
    {
        selectedPositions.clear();
    }

    /**
     * These variables track the usable dao's and the columns we are allowed to search
     */
    private Map<String, ReadDao<?>>  usableDaos;
    private final Set<String> selectedFilters = new HashSet<>();
    private class FilterSelectionListener implements WriteCellAdapter.Listener {

        @Override
        public void positionUpdated(
                WriteCellAdapter<?> source,
                Object model,
                int position
        ) {
            Selection item = (Selection) model;

            Logger.info("Recieved model update = " + model.toString());

            if (item.isSelected()) {
                Logger.debug(" - Selecting " + item.getItem());
                selectedFilters.add((String) item.getItem());
            } else {
                Logger.debug(" - Deselecting " + item.getItem());
                //noinspection SuspiciousMethodCalls
                selectedFilters.remove(item.getItem());
            }
            searchForText();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_compendium;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        theFilterGroup = findViewById(R.id.filterGroup);
        theResultsGroup = findViewById(R.id.resultsGroup);

        theFiltersButton = findViewById(R.id.theFiltersButton);
        theSearchButton = findViewById(R.id.theSearchButton);
        FilterSelectionListener filterSelectionListener = new FilterSelectionListener();

        ListView theFilterList = findViewById(R.id.theFilterList);
        theSelectionAdapter = new TextSelectionAdapter(getLayoutInflater());
        theSelectionAdapter.listeners.addListener(filterSelectionListener);

        theFilterList.setAdapter(theSelectionAdapter);

        theTabsGroup = findViewById(R.id.theTabsGroup);

        checkTabVisibility();
    }

    /**
     * This will determine if we want to display all the filter tabs
     */
    private void checkTabVisibility() {
        // Load the preference of the use filters
        boolean showTabs = globalPreferences.getBoolean(
                getString(R.string.pref_use_filters_key),
                true
        );

        if (showTabs)
        {
            theTabsGroup.setVisibility(View.VISIBLE);
        } else {
            theTabsGroup.setVisibility(View.GONE);
        }
        setSelectingFilters(false);

    }

    /**
     * This must override the super method. Because we are searching the entire database we want to have a more precise search query
     * @return the minimum required characters before we perform a search
     */
    @Override
    protected int getMinSearchTextLength() {
        return 2;
    }

    @Override
    public void onResume() {
        super.onResume();
        compendiumResource = DndUtilApp.getInstance().getCompendiumResource();
        compendiumResource.addListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        compendiumResource = DndUtilApp.getInstance().getCompendiumResource();
        compendiumResource.removeListener(this);
    }


    @Override
    protected EditText findSearchEditText() {
        return (EditText) findViewById(R.id.theSearchEdit);
    }

    @Override
    protected ExpandableListView findExpandableListView() {
        final ExpandableListView theExListView = findViewById(R.id.theExpandableListView);
        theExListView.setOnGroupClickListener(
                (parent, v, groupPosition, id) -> {
                    Logger.debug("CLICKED ON GROUP POSITION " + groupPosition);
                    toggleSelection(groupPosition);
                    return false;
                }
        );
        return theExListView;
    }

    @Override
    protected CustomToggleAdapter<Object> createToggleListAdapter(LayoutInflater layoutInflater) {
        return new CompendiumAdapter(layoutInflater);
    }

    /**
     * This is calleed when we attempt to access a dao session table but fail.
     * @param dao is the dao we failed to load
     */
    private void onDaoSessionFail(Dao<?> dao, Exception e)
    {
        Logger.error(getString(R.string.dao_session_fail, dao.getTable()), e);
    }

    @Override
    protected List<Object> searchForResults(String searchText) {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            clearSelections();
            for(String key : selectedFilters)
            {
                ReadDao<?> readDao = usableDaos.get(key);
                addRecordsLike(searchText, readDao, objects);
            }

            sortObjects(objects);

            return objects;
        }

    }

    @Override
    protected List<Object> getAllRecords() {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            clearSelections();
            for(String key : selectedFilters)
            {
                ReadDao<?> readDao = usableDaos.get(key);
                addAllRecords(readDao, objects);
            }

            sortObjects(objects);
            return objects;
        }
    }

    @Override
    protected void loadAllData() {
        // Do nothing. This is handled in the compendium resource.
    }

    private void addRecordsLike(@NonNull String searchText, ReadDao<?> dao, List<Object> results)
    {
        try {
            results.addAll(getDisplayObjects((List<? extends DaoModel>) dao.getRecordsLike(searchText)));
        } catch (Exception e) {
            onDaoSessionFail(dao, e);
        }
    }

    private void addAllRecords(ReadDao<?> dao, List<Object> results)
    {
        try {
            results.addAll(getDisplayObjects((List<? extends DaoModel>) dao.getAllRecords()));
        } catch (Exception e) {
            onDaoSessionFail(dao, e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // the only globalPreferences we need to concern ourselves with are the editable filters in the compendium
                Intent intent = new Intent(this, SearchFiltersActivity.class);
                startActivity(intent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * This is the method that wil determine how the objects will be displayed in the adapter
     * @param theList is the list of display objects we wish to sort
     */
    private static void sortObjects(@NonNull List<Object> theList)
    {
        Collections.sort(theList, new SortByTitleComparator());
    }

    /**
     * Change the view to be displayed
     * @param view is the view we clicked
     */
    public void onFiltersButtonClicked(View view)
    {
        setSelectingFilters(true);
    }

    public void onSearchButtonClicked(View view)
    {
        setSelectingFilters(false);
    }

    public boolean isSelectingFilters() {
        return isSelectingFilters;
    }

    /**
     * Determines which views are visible. I didn't want ti implement multiple fragments so
     * I simply show different views
     * @param selectingFilters will determine which view to display filter selection or the data list
     */
    private void setSelectingFilters(boolean selectingFilters) {
        isSelectingFilters = selectingFilters;
        theFilterGroup.setVisibility((selectingFilters) ? View.VISIBLE : View.GONE);
        theResultsGroup.setVisibility((selectingFilters) ? View.GONE : View.VISIBLE);

        if (isSelectingFilters)
        {
            hideSoftKeyboard();
            theSearchButton.setChecked(false);
            theFiltersButton.setChecked(true);
        } else {
            theSearchButton.setChecked(true);
            theFiltersButton.setChecked(false);
        }
    }

    /**
     * When clicked will select all filters
     * @param view is the view that was pressed
     */
    public void onSelectAllButtonClicked(View view)
    {
        theSelectionAdapter.selectAll();
        searchForText();
    }


    /**
     * When clicked will select all filters
     * @param view is the view that was pressed
     */
    public void onDeSelectAllButtonClicked(View view)
    {
        theSelectionAdapter.deselectAll();
        searchForText();
    }

    /**
     * This method is called when we want to add our selected item to a list.
     * @param view is the view that was pressed
     */
    public void onAddToListButtonClicked(View view)
    {

        try {
            final List<Object> items = getExpandedPositions();
            Logger.debug("We have selected " + items.size() + " to add to a list.");
            Logger.debug(JsonUtil.toJson(items, true));
            if (items.size()<1) {
                throw new RuntimeException("We did not select any items to add to an entity list.");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.vc_add_to_item_list_dialog_title);

            final ItemListSummaryAdapter itemListAdapter = new ItemListSummaryAdapter(getLayoutInflater());

            List<EntityList> listOptions;
            try {
                listOptions = (List<EntityList>) compendiumResource.getDaoForCategory(Category.ENTITY_LIST).getAllRecords();
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
                listOptions = new ArrayList<>();
            }

            Logger.debug("The number of lists to choose from = " + listOptions.size() + " lists.");
            Logger.debug(JsonUtil.toJson(listOptions, true));

            if (listOptions.size()<1) {
                throw new RuntimeException("There is no list we can add the selected items to.");
            }



            itemListAdapter.setData(listOptions);
            builder.setAdapter(
                    itemListAdapter,
                    (dialog, which) -> {
                        try {

                            EntityList entityList = itemListAdapter.getCastedItem(which);
                            for (Object item : items) {
                                try {
                                    if (item instanceof DaoModel) {
                                        final DaoModel daoModel = (DaoModel) item;
                                        final EntityDao entityDao = (EntityDao) compendiumResource.getDaoForCategory(Category.ENTITY);
                                        final Entity entity = entityDao.create();
                                        final Category category = Category.parse(item);
                                        final WriteDao writeDao = compendiumResource.getDaoForCategory(category);
                                        String metadata = writeDao.getDesirableMetadata(item);

                                        // append desirable metadata from the item list as well so we
                                        // can search for the item using information we know about the list.
                                        try {
                                            EntityListDao entityListDao = (EntityListDao) compendiumResource.getDaoForCategory(Category.ENTITY_LIST);
                                            metadata += ((metadata.length() > 0) ? " " : "") + (entityListDao.getDesirableMetadata(entityList));
                                        } catch (Exception e) {
                                            Logger.error(e.getMessage(), e);
                                        }

                                        entity.setParentId(entityList.getId());
                                        entity.setMetadata(metadata);
                                        entity.setCategoryColumnId(category, daoModel.getId());
                                        entityDao.update(entity);
                                    } else {
                                        Logger.warning("We have a selected item that is not a dao model in position.");
                                    }
                                } catch (Exception e) {
                                    Logger.warning("Failed to add selected item (" + item + ") to list " + entityList + ". \n" + e.getMessage());
                                }
                            }
                        } catch (Exception e) {
                            Logger.error(e.getMessage(), e);
                        }
                    }
            );

            builder.setNegativeButton(
                    R.string.dialog_cancel,
                    (dialog, which) -> Logger.info("CANCEL CLICKED")
            );
            builder.setPositiveButton(
                    R.string.dialog_confirm,
                    (dialog, which) -> Logger.info("OK CLICKED!")
            );
            builder.show();
        } catch (Exception e) {
            Logger.error("Problem occurred when we clicked the add to list button", e);
        }
    }

    private List<Object> getExpandedPositions()
    {
        List<Object> items = new ArrayList<>();
        for(Integer position : selectedPositions)
        {
            items.add(theListAdapter.getItem(position));
        }
        return items;
    }


    private List<?> getDisplayObjects(List<? extends DaoModel> foundItems) {
        List<DaoModel> displayItems = new ArrayList<>();
        for(DaoModel foundItem : foundItems) {
            try {
                DaoModel displayObject = (DaoModel) getDisplayObject(foundItem);
                displayItems.add(displayObject);
            } catch (Exception e) {
                Logger.error("Failed to add display object for found item " + foundItem + ". " + e.getMessage(), e);
            }
        }
        return displayItems;
    }

    /**
     * This is a recursive method used to return the object we wish to display in the compendium.
     * It it's an entity, it will recursively call itself until it finds a non-Entity object or null
     * @param item is the item we want a display item for
     * @return the object to be displayed
     */
    private Object getDisplayObject(DaoModel item) throws DaoModelException {

        if (item==null) {
            throw new RuntimeException("We can't get the display item of a null object!");
        }

        // Recursively call by it's child object
        if (item instanceof Entity) {
            Entity entity = (Entity) item;
            Category childCategory = entity.getChildCategory();
            WriteDao<?> writeDao;

            Logger.info("Processing display entity: " + entity.json());
            try {
                writeDao = compendiumResource.getDaoForCategory(childCategory);
            } catch (Exception e) {
                throw new RuntimeException("Failed to add display entity "  + entity.toString() + ".\n" + e.getMessage(), e);
            }

            Long theId;
            theId = entity.getCategoryColumnId(childCategory);

            if (theId != null) {
                synchronized (DB_LOCK) {
                    DaoModel child = (DaoModel) writeDao.load(theId);
                    if (child==null) {
                        throw new RuntimeException("Entity(" + entity.getId() + ") refers to table " + writeDao.getTable() + "(" + theId + ") but the record does not exist!");
                    } else {
                        return getDisplayObject(child);
                    }
                }
            } else {
                throw new RuntimeException("Entity(" + entity.getId() + ") is isolated and points to nothing!");
            }
        } else {
            return item;
        }
    }


    @Override
    public void update(ProgressMonitor monitor, int numSteps, int numFailedSteps, int numSuccessSteps, int numPendingSteps) {
        Logger.info("Initializing the filter list");
        ArrayList<Selection> selectionsList = new ArrayList<>();
        selectedFilters.clear();

        // Add the option for all filters
        Selection selection;
        usableDaos = compendiumResource.getUsableDaos();

        for (String table : usableDaos.keySet()) {
            selection = new Selection();
            selection.setSelected(true);
            selection.setItem(table);
            selectionsList.add(selection);
            if (selection.isSelected()) {
                selectedFilters.add(table);
            }
            Logger.info("Adding a selection");
            Logger.info(" - text:       " + selection.getItem());
            Logger.info(" - isSelected: " + selection.isSelected());
        }

        theSelectionAdapter.setData(selectionsList);
    }

    @Override
    public void loadDaoMasterSuccess(String daoMasterKey, DaoMaster daoMaster) {
        // Do nothing. We dont care at this time
    }

    @Override
    public void loadDaoMasterFailure(String daoMasterKey) {
        // Do nothing. We dont care at this time
    }

    @Override
    public void loadDaoSuccess(String daoKey, ReadDao readDao) {
    }

    @Override
    public void loadDaoFailure(String daoKey) {
        Logger.warning("Failed to load dao for " + daoKey + ".");
    }



}

