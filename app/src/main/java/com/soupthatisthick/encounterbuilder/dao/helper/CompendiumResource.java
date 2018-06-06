package com.soupthatisthick.encounterbuilder.dao.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.dao.lookup.ArmorDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ConditionDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
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
import com.soupthatisthick.encounterbuilder.exception.DaoModelException;
import com.soupthatisthick.encounterbuilder.model.Selection;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.util.sort.SortByTitleComparator;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.Dao;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CompendiumResource {


    /**
     * These variables track the usable dao's and the columns we are allowed to search
     */
    private Map<String, ReadDao<? extends Object>> usableDaos;
    private Set<String> selectedFilters = new HashSet<>();

    private final Object DB_LOCK = new Object();
    private DndMaster dndMaster;
    private EncounterMaster encounterMaster;
    private LogsheetMaster logsheetMaster;

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
    private EntityDao entityDao;

    private EntityListDao entityListDao;
    private ItemDao itemDao;

    protected final Context context;

    /**
     * This will create a central resource to access all of the data in a compendium
     * @param context
     */
    public CompendiumResource(
        Context context
    )
    {
        this.context = context;
    }

    /**
     * This will attempt to load all the data into the compendium for searching
     */
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
                        dndMaster = new DndMaster(context);
                        Logger.info("Opened the dndMaster on database " + dndMaster.getDatabaseName() + ".");

                        encounterMaster = new EncounterMaster(context);
                        Logger.info("Opened the encounterMaster on database " + encounterMaster.getDatabaseName() + ".");

                        logsheetMaster = new LogsheetMaster(context);
                        Logger.info("Opened the logsheet master on database " + logsheetMaster.getDatabaseName() + ".");

                        // Create instances of the dao's

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


                        // These might be excluded from the compendium...
                        entityListDao = new EntityListDao(logsheetMaster);
                        initDao(R.string.vc_title_item_list, entityListDao);

                        itemDao = new ItemDao(logsheetMaster);
                        initDao(R.string.vc_title_items, itemDao);

                        entityDao = new EntityDao(dndMaster);
                        initDao(R.string.vc_title_entity, entityDao);

                        Logger.info("Completed opening all the dao's!");

                        return Boolean.TRUE;
                    } catch (Exception e) {
                        Logger.error("Failed to open all the dao's! " + e.getMessage(), e);
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
                }
            }
        };
        loadTask.execute(null, null, null);

    }

    private void initDao(final int titleResourceId, final ReadDao<? extends Object> dao)
    {
        AsyncTask<Void,Void,Void> loadDaoTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String name = context.getResources().getString(titleResourceId);
                Logger.info("Opened dao on table " + dao.getTable() + ".");
                dao.logSchema();
                usableDaos.put(name, dao);

                return null;
            }
        };
        loadDaoTask.execute();
    }

    public final List<Object> searchForResults(String searchText) throws Exception {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            for(String key : selectedFilters)
            {
                Logger.debug("Including " + key);
                ReadDao<? extends Object> readDao = usableDaos.get(key);
                addRecordsLike(searchText, readDao, objects);
            }

            sortObjects(objects);

            return objects;
        }

    }


    public final List<Object> getAllRecords() throws Exception {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
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
            if (dao == entityDao) {
                List<Entity> theEntities = entityDao.getRecordsLike(searchText);
                for(Entity theEntity : theEntities) {
                    Object child = getEntityChild(theEntity);
                    if (child!=null) {
                        results.add(child);
                    }
                }
            } else {
                results.addAll(dao.getRecordsLike(searchText));
            }
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

    /**
     * This is calleed when we attempt to access a dao session table but fail.
     * @param dao
     */
    private void onDaoSessionFail(Dao<?> dao, Exception e)
    {
        Logger.error(context.getString(R.string.dao_session_fail, dao.getTable()), e);
    }


    /**
     * This is the method that wil determine how the objects will be displayed in the adapter
     * @param theList
     */
    private static final void sortObjects(@NonNull List<Object> theList)
    {
        Collections.sort(theList, new SortByTitleComparator());
    }


    public WriteDao<? extends Object> getDaoForCategory(Category category) {
        if (category==null) return null;
        switch(category) {
            case CONDITION:
                return conditionDao;
            case CUSTOM_MONSTER:
                return customMonsterDao;
            case STANDARD_MONSTER:
                return standardMonsterDao;
            case MAGIC_ITEM:
                return magicItemDao;
            case SPELL:
                return spellDao;
            case FEAT:
                return featDao;
            case BACKGROUND:
                return backgroundDao;
            case ARMOR:
                return armorDao;
            case WEAPON:
                return weaponDao;
            case EQUIPMENT:
                return equipmentDao;
            case NOTE:
                return notesDao;
            case CHALLENGE_RATING:
                return challengeRatingDao;
            case LEVEL:
                return levelDao;
            case GOD:
                return godsDao;
            case LIFESTYLE:
                return lifeStyleDao;
            case MOUNT:
                return mountDao;
            case ENTITY:
                return entityDao;
            case DEFAULT:
                return null;
        }
        throw new RuntimeException("Failed to determine a dao for category " + category + ".");
    }

    /**
     * This is used to get the child entity from of the specified Entity model
     * @param entity is the pointer {@link Entity}
     * @return null if the child entity does not exist
     */
    public Object getEntityChild(Entity entity) throws DaoModelException {
        Category category = entity.getChildCategory();
        WriteDao<? extends Object> writeDao = getDaoForCategory(category);
        if (writeDao == null) {
            return null;
        } else {
            Long theId = entity.getCategoryColumnId(category);
            if (theId != null) {
                synchronized (DB_LOCK) {
                    return writeDao.load(theId);
                }
            } else {
                return null;
            }
        }
    }

}
