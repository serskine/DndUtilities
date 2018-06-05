package com.soupthatisthick.encounterbuilder.fragment.builder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MainThread;
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

import com.soupthatisthick.encounterbuilder.activity.SearchFiltersActivity;
import com.soupthatisthick.encounterbuilder.adapters.lookup.CompendiumAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.ItemListSummaryAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.SelectionAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.TextSelectionAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ArmorDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ConditionDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EquipmentDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.FeatDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.GodsDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ItemDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LifeStyleDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.MagicItemDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.MountDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.NotesDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.WeaponDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.BackgroundDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.model.Selection;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.sort.SortByTitleComparator;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.adapters.WriteCellAdapter;
import com.soupthatisthick.util.dao.Dao;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.fragment.ViewToggleListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 7/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CompendiumFragment extends ViewToggleListFragment<Object> {

    private final Object DB_LOCK = new Object();
    private DndMaster dndMaster;                // Contains all the spells etc.
    private EncounterMaster encounterMaster;    // Contains all the level information
    private LogsheetMaster logsheetMaster;      // Contains all the custom lists we might add items

    private SpellDao spellDao;
    private CustomMonsterDao customMonsterDao;
    private StandardMonsterDao standardMonsterDao;
    private MagicItemDao magicItemDao;
    private NotesDao notesDao;
    private FeatDao featDao;
    private BackgroundDao backgroundDao;
    private ConditionDao conditionDao;
    private LevelDao levelDao;
    private ChallengeRatingDao challengeRatingDao;
    private EquipmentDao equipmentDao;
    private GodsDao godsDao;
    private ArmorDao armorDao;
    private LifeStyleDao lifeStyleDao;
    private MountDao mountDao;
    private WeaponDao weaponDao;

    private EntityListDao entityListDao;
    private ItemDao itemDao;

    private ViewGroup theFilterGroup, theResultsGroup;
    private ToggleButton theFiltersButton, theSearchButton;
    private ListView theFilterList;
    private SelectionAdapter theSelectionAdapter;
    private ViewGroup theTabsGroup;

    private boolean isSelectingFilters = false;
    private CompendiumFragment.FilterSelectionListener filterSelectionListener;

    private Set<Integer> selectedPositions = new HashSet<>();

    /**
     * This is called to toggle the selected state of a position from on to off or off to on
     * @param position
     */
    private void toggleSelection(int position)
    {
        if (selectedPositions.contains(position))
        {
            selectedPositions.remove(position);
            Logger.debug("Deselected position " + position);
        } else {
            selectedPositions.add(position);
            Logger.debug("Selected position " + position);
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
    private Map<String, ReadDao<? extends Object>> usableDaos;
    private Set<String> selectedFilters = new HashSet<>();


    private class FilterSelectionListener implements WriteCellAdapter.Listener
    {

        @Override
        public void positionUpdated(
                WriteCellAdapter<? extends Object> source,
                Object model,
                int position
        ) {
            SelectionAdapter<Object> adapter = (SelectionAdapter) source;
            Selection item = (Selection) model;

            Logger.info("Recieved model update = " + model.toString());

            if (item.isSelected()) {
                Logger.debug(" - Selecting " + item.getItem());
                selectedFilters.add((String) item.getItem());
            } else {
                Logger.debug(" - Deselecting " + item.getItem());
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        theFilterGroup = (ViewGroup) view.findViewById(R.id.filterGroup);
        theResultsGroup = (ViewGroup) view.findViewById(R.id.resultsGroup);

        theFiltersButton = (ToggleButton) view.findViewById(R.id.theFiltersButton);
        theSearchButton = (ToggleButton) view.findViewById(R.id.theSearchButton);
        filterSelectionListener = new CompendiumFragment.FilterSelectionListener();

        theFilterList = (ListView) view.findViewById(R.id.theFilterList);
        theSelectionAdapter = new TextSelectionAdapter(inflater);
        theSelectionAdapter.listeners.addListener(filterSelectionListener);

        theFilterList.setAdapter(theSelectionAdapter);

        theTabsGroup = (ViewGroup) view.findViewById(R.id.theTabsGroup);

        checkTabVisibility();

        return view;
    }

    /**
     * This will determine if we want to display all the filter tabs
     */
    private void checkTabVisibility()
    {
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
     * @return
     */
    @Override
    protected int getMinSearchTextLength() {
        return 2;
    }

    @Override
    public void loadAllData()
    {
        final AsyncTask<Object, Object, Boolean> loadTask = new AsyncTask<Object, Object, Boolean>()
        {

            /**
             * Override this method to perform a computation on a background thread. The
             * specified parameters are the parameters passed to {@link #execute}
             * by the caller of this task.
             * <p>
             * This method can call {@link #publishProgress} to publish updates
             * on the UI thread.
             *
             * @param params The parameters of the task.
             * @return A result, defined by the subclass of this task.
             * @see #onPreExecute()
             * @see #onPostExecute
             * @see #publishProgress
             */
            @Override
            protected Boolean doInBackground(Object... params) {
                Logger.info(" - waiting for DB_LOCK [loadAllData().AsyncTask]");
                synchronized (DB_LOCK) {

                    Logger.info(" - initializing the usable daos map");
                    usableDaos = new TreeMap<>();

                    Logger.info(" - aquired DB_LOCK [loadAllData().AsyncTask]");
                    try {
                        dndMaster = new DndMaster(getActivity().getBaseContext());
                        Logger.info("Opened the dndMaster on database " + dndMaster.getDatabaseName() + ".");

                        encounterMaster = new EncounterMaster(getActivity().getBaseContext());
                        Logger.info("Opened the encounterMaster on database " + encounterMaster.getDatabaseName() + ".");

                        logsheetMaster = new LogsheetMaster(getActivity().getBaseContext());
                        Logger.info("Opened the logsheetMaster on database " + logsheetMaster.getDatabaseName() + ".");


                        // Open these dao's so we can add items to lists from the Compendium Activity
                        entityListDao = new EntityListDao(logsheetMaster);
                        itemDao = new ItemDao(logsheetMaster);

                        //
                        // Start opening all the dao's for looking up items.
                        //

                        spellDao = new SpellDao(dndMaster);
                        initDao(R.string.vc_title_spell, spellDao);

                        magicItemDao = new MagicItemDao(dndMaster);
                        initDao(R.string.vc_title_magic_items, magicItemDao);

                        customMonsterDao = new CustomMonsterDao(dndMaster);
                        initDao(R.string.vc_title_custom_monsters, customMonsterDao);

                        standardMonsterDao = new StandardMonsterDao(dndMaster);
                        initDao(R.string.vc_title_standard_monsters, standardMonsterDao);

                        notesDao = new NotesDao(dndMaster);
                        initDao(R.string.vc_title_notes, notesDao);

                        featDao = new FeatDao(dndMaster);
                        initDao(R.string.vc_title_feats, featDao);

                        backgroundDao = new BackgroundDao(dndMaster);
                        initDao(R.string.vc_title_backgrounds, backgroundDao);

                        conditionDao = new ConditionDao(dndMaster);
                        initDao(R.string.vc_title_conditions, conditionDao);

                        levelDao = new LevelDao(encounterMaster);
                        initDao(R.string.vc_title_levels, levelDao);

                        challengeRatingDao = new ChallengeRatingDao(encounterMaster);
                        initDao(R.string.vc_title_challenge_ratings, challengeRatingDao);

                        equipmentDao = new EquipmentDao(dndMaster);
                        initDao(R.string.vc_title_equipment, equipmentDao);

                        godsDao = new GodsDao(dndMaster);
                        initDao(R.string.vc_title_gods, godsDao);

                        armorDao = new ArmorDao(dndMaster);
                        initDao(R.string.vc_title_armor, armorDao);

                        lifeStyleDao = new LifeStyleDao(dndMaster);
                        initDao(R.string.vc_title_lifestyles, lifeStyleDao);

                        mountDao = new MountDao(dndMaster);
                        initDao(R.string.vc_title_mounts, mountDao);

                        weaponDao = new WeaponDao(dndMaster);
                        initDao(R.string.vc_title_weapons, weaponDao);

                        Logger.info("Completed opening all the dao's!");

                        return Boolean.TRUE;
                    } catch (Exception e) {
                        Logger.error("Failed to open all the dao's!", e);
                        getActivity().finish();
                        return Boolean.FALSE;
                    }
                }
            }

            /**
             * Executed after all the data has been loaded.
             * @param result
             */
            @Override
            @MainThread
            protected void onPostExecute(Boolean result) {
                synchronized (DB_LOCK) {
                    Logger.info("Initializing the filter list");
                    ArrayList<Selection> selectionsList = new ArrayList<>();
                    selectedFilters.clear();

                    // Add the option for all filters
                    Selection selection;

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
            }
        };
        loadTask.execute(null, null, null);

    }

    private void initDao(int titleResourceId, ReadDao<? extends Object> dao)
    {
        String name = getResources().getString(titleResourceId);
        Logger.info("Opened dao on table " + dao.getTable() + ".");
        dao.logSchema();
        usableDaos.put(name, dao);
    }
    @Override
    protected EditText findSearchEditText(final View view) {
        return (EditText) view.findViewById(R.id.theSearchEdit);
    }

    @Override
    protected ExpandableListView findExpandableListView(final View view) {
        final ExpandableListView theExListView = (ExpandableListView) view.findViewById(R.id.theExpandableListView);
        theExListView.setOnGroupClickListener(
                new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Logger.debug("CLICKED ON GROUP POSITION " + groupPosition);
                        toggleSelection(groupPosition);
                        return false;
                    }
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
     * @param dao
     */
    private void onDaoSessionFail(Dao<?> dao, Exception e)
    {
        Logger.error(getString(R.string.dao_session_fail, dao.getTable()), e);
    }

    @Override
    protected List<Object> searchForResults(String searchText) throws Exception {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            clearSelections();
            for(String key : selectedFilters)
            {
                ReadDao<? extends Object> readDao = usableDaos.get(key);
                addRecordsLike(searchText, readDao, objects);
            }

            sortObjects(objects);

            return objects;
        }

    }

    @Override
    protected List<Object> getAllRecords() throws Exception {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            clearSelections();
            for(String key : selectedFilters)
            {
                ReadDao<? extends Object> readDao = usableDaos.get(key);
                addAllRecords(readDao, objects);
            }

            sortObjects(objects);
            return objects;
        }
    }

    private void addRecordsLike(@NonNull String searchText, ReadDao<? extends Object> dao, List<Object> results)
    {
        try {
            results.addAll(dao.getRecordsLike(searchText));
        } catch (Exception e) {
            onDaoSessionFail(dao, e);
        }
    }

    private void addAllRecords(ReadDao<? extends Object> dao, List<Object> results)
    {
        try {
            results.addAll(dao.getAllRecords());
        } catch (Exception e) {
            onDaoSessionFail(dao, e);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // the only globalPreferences we need to concern ourselves with are the editable filters in the compendium
                Intent intent = new Intent(getActivity(), SearchFiltersActivity.class);
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
     * @param theList
     */
    private static final void sortObjects(@NonNull List<Object> theList)
    {
        Collections.sort(theList, new SortByTitleComparator());
    }

    /**
     * Change the view to be displayed
     * @param view
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
     * @param selectingFilters
     */
    public void setSelectingFilters(boolean selectingFilters) {
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
     * @param view
     */
    public void onSelectAllButtonClicked(View view)
    {
        theSelectionAdapter.selectAll();
        searchForText();
    }


    /**
     * When clicked will select all filters
     * @param view
     */
    public void onDeSelectAllButtonClicked(View view)
    {
        theSelectionAdapter.deselectAll();
        searchForText();
    }

    /**
     * This method is called when we want to add our selected item to a list.
     * @param view
     */
    public void onAddToListButtonClicked(View view)
    {
        try {
            final List<Object> items = getExpandedPositions();
            Logger.info("TODO: Implement! We want to add " + items.size() + " expanded items to a list.");
            for (Object item : items) {
                Logger.info(" - " + item.toString());
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle(R.string.vc_add_to_item_list_dialog_title);

            final ItemListSummaryAdapter itemListAdapter = new ItemListSummaryAdapter(getActivity().getLayoutInflater());
            itemListAdapter.setData(entityListDao.getAllRecords());
            builder.setAdapter(itemListAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Logger.debug("CLICKED ON POSITION " + which);
//                        ItemList list = itemListAdapter.getCastedItem(which);
//                        for (Object itemObject : items) {
//                            if (itemObject instanceof DaoModel) {
//                                DaoModel model = (DaoModel) itemObject;
//                                Item newItem = itemDao.create();
//                                newItem.init(model);
//                                itemDao.update(newItem);
//                                Logger.debug(" itemObject (" + itemObject.toString() + ") has been added to list " + list.toString());
//                            } else {
//                                Logger.warning(" itemObject (" + itemObject.toString() + ") is not an instance of a DaoModel.");
//                            }
//                        }

                }
            });

            builder.setNegativeButton(
                    R.string.dialog_cancel,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Logger.info("CANCEL CLICKED");
                        }
                    }
            );
            builder.setPositiveButton(
                    R.string.dialog_confirm,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Logger.info("OK CLICKED!");
                        }
                    }
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

}
