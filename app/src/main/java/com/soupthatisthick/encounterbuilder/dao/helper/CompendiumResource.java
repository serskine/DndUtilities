package com.soupthatisthick.encounterbuilder.dao.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.dao.lookup.AdventureDao;
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
import com.soupthatisthick.encounterbuilder.dao.lookup.SeasonDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.WeaponDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.BackgroundDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.exception.DaoModelException;
import com.soupthatisthick.encounterbuilder.model.DaoModel;
import com.soupthatisthick.encounterbuilder.model.lookup.Condition;
import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;
import com.soupthatisthick.encounterbuilder.util.progress.ProgressMonitor;
import com.soupthatisthick.encounterbuilder.util.progress.TaskManager;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.util.sort.SortByTitleComparator;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.Dao;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.WriteDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

@SuppressWarnings({"unchecked", "SpellCheckingInspection", "MismatchedQueryAndUpdateOfCollection"})
public class CompendiumResource {

    public interface Listener extends ProgressMonitor.Listener {
        void loadDaoMasterSuccess(String daoMasterKey, DaoMaster daoMaster);
        void loadDaoMasterFailure(String daoMasterKey);

        void loadDaoSuccess(String daoKey, ReadDao readDao);
        void loadDaoFailure(String daoKey);
    }

    private final Announcer<CompendiumResource.Listener> listeners = Announcer.to(CompendiumResource.Listener.class);

    /**
     * These variables track the usable dao's and the columns we are allowed to search
     */

    private final ProgressMonitor progressMonitor;

    private Map<String, ReadDao<?>> usableDaos;
    private final Set<String> selectedFilters = new HashSet<>();

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
    private SeasonDao seasonDao;
    private AdventureDao adventureDao;
    private EntityDao entityDao;

    private EntityListDao entityListDao;
    private ItemDao itemDao;

    private final Context context;

    private static AsyncTask<Object, Object, Boolean> LOAD_TASK;

    /**
     * This will create a central resource to access all of the data in a compendium
     * @param context is where the resource is contains. It should be the application context
     */
    public CompendiumResource(
        Context context
    )
    {
        this.context = context;
        this.progressMonitor = new ProgressMonitor(0);
    }

    /**
     * This will attempt to load all the data into the compendium for searching
     */
    @SuppressLint("StaticFieldLeak")
    public synchronized void loadAllData()
    {
        Logger.info("Loading all compendium Resource.");
        if (LOAD_TASK!=null && !LOAD_TASK.isCancelled()) {
            LOAD_TASK.cancel(true);
        }

        LOAD_TASK = new AsyncTask<Object, Object, Boolean>()
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

                progressMonitor.init(21);

                synchronized (DB_LOCK) {

                    Logger.info(" - initializing the usable daos map");

                    // This must be thread safe but also allow sorting by the key.
                    usableDaos = Collections.synchronizedMap(new TreeMap<>());

                    Logger.info(" - aquired DB_LOCK [loadAllData().AsyncTask]");
                    try {

                        final TaskManager<Void, Void, Void> daoMasterTaskManager = new TaskManager<>();

                        dndMaster = new DndMaster(context);
                        daoMasterTaskManager.add(initDaoMasterTask(R.string.vc_title_master_dnd, dndMaster));

                        encounterMaster = new EncounterMaster(context);
                        daoMasterTaskManager.add(initDaoMasterTask(R.string.vc_title_master_encounter, encounterMaster));

                        logsheetMaster = new LogsheetMaster(context);
                        daoMasterTaskManager.add(initDaoMasterTask(R.string.vc_title_master_logsheets, logsheetMaster));

                        daoMasterTaskManager.startAllPendingTasks();
//                        daoMasterTaskManager.waitForAllTasksToFinish();
//                        daoMasterTaskManager.clear(false);

                        Logger.info("Finished loading the dao masters!");

                        // Create instances of the dao's

                        final TaskManager<Void, Void, Void> daoTaskManager = new TaskManager();

                        entityListDao = new EntityListDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_item_list, entityListDao));

                        itemDao = new ItemDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_items, itemDao));

                        entityDao = new EntityDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_entity, entityDao));


                        spellDao = new SpellDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_spell, spellDao));

                        magicItemDao = new MagicItemDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_magic_items, magicItemDao));

                        customMonsterDao = new CustomMonsterDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_custom_monsters, customMonsterDao));

                        standardMonsterDao = new StandardMonsterDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_standard_monsters, standardMonsterDao));

                        notesDao = new NotesDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_notes, notesDao));

                        featDao = new FeatDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_feats, featDao));

                        backgroundDao = new BackgroundDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_backgrounds, backgroundDao));

                        conditionDao = new ConditionDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_conditions, conditionDao));

                        levelDao = new LevelDao(encounterMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_levels, levelDao));

                        challengeRatingDao = new ChallengeRatingDao(encounterMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_challenge_ratings, challengeRatingDao));

                        equipmentDao = new EquipmentDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_equipment, equipmentDao));

                        godsDao = new GodsDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_gods, godsDao));

                        armorDao = new ArmorDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_armor, armorDao));

                        lifeStyleDao = new LifeStyleDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_lifestyles, lifeStyleDao));

                        mountDao = new MountDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_mounts, mountDao));

                        weaponDao = new WeaponDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_weapons, weaponDao));

                        seasonDao = new SeasonDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_season, seasonDao));

                        adventureDao = new AdventureDao(dndMaster);
                        daoTaskManager.add(initDaoTask(R.string.vc_title_adventure, adventureDao));

                        daoTaskManager.startAllPendingTasks();
//                        daoTaskManager.waitForAllTasksToFinish();
//                        daoTaskManager.clear(true);

                        Logger.info("Completed opening all the dao's!");
                        progressMonitor.recordRemainderAsSuccess();


                        return Boolean.TRUE;
                    } catch (Exception e) {
                        Logger.error("Failed to open all the dao's! " + e.getMessage(), e);
                        progressMonitor.recordRemainderAsFailure();

                        return Boolean.FALSE;
                    }
                }
            }

            /**
             * Executed after all the data has been loaded.
             * @param result indicates if there are results
             */
            @Override
            @MainThread
            protected void onPostExecute(Boolean result) {
                // Do nothing. Listeners will respond when the progress Monitor fires
            }
        };


        LOAD_TASK.execute(null, null, null);

    }
    


    private AsyncTask<Void,Void,Void> initDaoMasterTask(final int titleResourceId, final DaoMaster daoMaster) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void,Void,Void> loadDaoMasterTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String name = context.getResources().getString(titleResourceId);
                try {
                    Logger.info("Opened dao on table " + daoMaster.getDatabaseName() + ".");
                    daoMaster.logSchema();
                    // Add to usable dao master map???
                    listeners.announce().loadDaoMasterSuccess(name, daoMaster);
                    progressMonitor.recordSuccess();
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                    listeners.announce().loadDaoMasterFailure(name);
                    progressMonitor.recordFailure();
                }
                return null;
            }
        };
        return loadDaoMasterTask;
    }

    private AsyncTask<Void,Void,Void> initDaoTask(final int titleResourceId, final ReadDao<?> dao)
    {
        AsyncTask<Void,Void,Void> loadDaoTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String name = context.getResources().getString(titleResourceId);
                try {
                    Logger.info("Opened dao on table " + dao.getTable() + ".");
                    dao.logSchema();
                    usableDaos.put(name, dao);
                    listeners.announce().loadDaoSuccess(name, dao);
                    progressMonitor.recordSuccess();
                } catch (Exception e) {
                    Logger.error(e.getMessage(), e);
                    listeners.announce().loadDaoFailure(name);
                    progressMonitor.recordFailure();
                }
                return null;
            }
        };
        return loadDaoTask;
    }

    public synchronized final List<Object> searchForResults(String searchText) {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            for(String key : selectedFilters)
            {
                Logger.debug("Including " + key);
                ReadDao<?> readDao = usableDaos.get(key);
                addRecordsLike(searchText, readDao, objects);
            }

            sortObjects(objects);

            return objects;
        }

    }


    public synchronized final List<Object> getAllRecords() {

        final List<Object> objects = new ArrayList<>();

        synchronized (DB_LOCK)
        {
            for(String key : selectedFilters)
            {
                ReadDao<?> readDao = usableDaos.get(key);
                addAllRecords(readDao, objects);
            }

            sortObjects(objects);
            return objects;
        }
    }

    private void addRecordsLike(@NonNull String searchText, ReadDao<?> dao, List<Object> results)
    {
        try {
            if (dao == entityDao) {
                List<Entity> theEntities = entityDao.getRecordsLike(searchText);
                for(Entity theEntity : theEntities) {
                    Object child = getDisplayObject(theEntity);
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

    private void addAllRecords(ReadDao<?> dao, List<Object> results)
    {
        try {
            results.addAll(dao.getAllRecords());
        } catch (Exception e) {
            onDaoSessionFail(dao, e);
        }
    }

    /**
     * This is calleed when we attempt to access a dao session table but fail.
     * @param dao indicates which dao the session failed on
     */
    private void onDaoSessionFail(Dao<?> dao, Exception e)
    {
        Logger.error(context.getString(R.string.dao_session_fail, dao.getTable()), e);
    }


    /**
     * This is the method that wil determine how the objects will be displayed in the adapter
     * @param theList is a list of display objects we wish to sort
     */
    private static void sortObjects(@NonNull List<Object> theList)
    {
        Collections.sort(theList, new SortByTitleComparator());
    }

    public synchronized <T> WriteDao<T> getDaoForClass(Class<T> classType) throws Exception {
        Category category = Category.findForClass(classType);
        return (WriteDao<T>) getDaoForCategory(category);
    }

    public synchronized WriteDao<?> getDaoForCategory(Category category) throws Exception {
        if (category==null) {
            throw new Exception("We can't determine a writeDao for a null category!");
        }

        WriteDao<?> theDao;
        switch(category) {
            case CONDITION:
                theDao = conditionDao;
				break;
            case CUSTOM_MONSTER:
                theDao = customMonsterDao;
				break;
            case STANDARD_MONSTER:
                theDao = standardMonsterDao;
				break;
            case MAGIC_ITEM:
                theDao = magicItemDao;
				break;
            case SPELL:
                theDao = spellDao;
				break;
            case FEAT:
                theDao = featDao;
				break;
            case BACKGROUND:
                theDao = backgroundDao;
				break;
            case ARMOR:
                theDao = armorDao;
				break;
            case WEAPON:
                theDao = weaponDao;
				break;
            case EQUIPMENT:
                theDao = equipmentDao;
				break;
            case NOTE:
                theDao = notesDao;
				break;
            case CHALLENGE_RATING:
                theDao = challengeRatingDao;
				break;
            case LEVEL:
                theDao = levelDao;
				break;
            case GOD:
                theDao = godsDao;
				break;
            case LIFESTYLE:
                theDao = lifeStyleDao;
				break;
            case MOUNT:
                theDao = mountDao;
				break;
            case ENTITY:
                theDao = entityDao;
				break;
            case ENTITY_LIST:
                theDao = entityListDao;
				break;
            case SEASON:
                theDao = seasonDao;
                break;
            case ADVENTURE:
                theDao = adventureDao;
                break;
            case DEFAULT:
            default:
                throw new Exception("Failed to determine a dao for category " + category + ".");
        }
        if (theDao==null) {
            throw new Exception("The dao for category " + category + " has not yet been loaded!");
        }
        return theDao;
    }



    /**
     * This is a recursive method used to return the object we wish to display in the compendium.
     * It it's an entity, it will recursively call itself until it finds a non-Entity object or null
     * @param item is the item we want a display item for
     * @return the object to be displayed
     */
    private synchronized Object getDisplayObject(DaoModel item) throws DaoModelException {

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
                writeDao = getDaoForCategory(childCategory);
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

    public synchronized Map<String, ReadDao<?>> getUsableDaos() {
        return this.usableDaos;
    }

    public Set<String> getSelectedFilters() {
        return this.selectedFilters;
    }

    public void addListener(CompendiumResource.Listener listener) {
        progressMonitor.addListener(listener);
        listeners.addListener(listener);
    }

    public void removeListener(CompendiumResource.Listener listener) {
        progressMonitor.removeListener(listener);
        listeners.removeListener(listener);
    }
}


