package com.soupthatisthick.encounterbuilder.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.dao.lookup.AdventureDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ArmorDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.BackgroundDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ConditionDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EntityListDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.EquipmentDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.FeatDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.GodsDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.ItemDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LifeStyleDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.MagicItemDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.MountDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.NotesDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SeasonDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.SpellDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.WeaponDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.dao.master.LogsheetMaster;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.ReadDao;
import com.soupthatisthick.util.dao.WriteDao;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import soupthatisthick.encounterapp.R;

public class CompendiumService extends IntentService {

    private final Map<Integer, WriteDao<?>> theDaoMap = new ConcurrentHashMap<>();

    private DndMaster dndMaster;
    private EncounterMaster encounterMaster;
    private LogsheetMaster logsheetMaster;

//    private SpellDao spellDao;
//    private CustomMonsterDao customMonsterDao;
//    private StandardMonsterDao standardMonsterDao;
//    private MagicItemDao magicItemDao;
//    private NotesDao notesDao;
//    private FeatDao featDao;
//    private BackgroundDao backgroundDao;
//    private ConditionDao conditionDao;
//    private LevelDao levelDao;
//    private ChallengeRatingDao challengeRatingDao;
//    private EquipmentDao equipmentDao;
//    private GodsDao godsDao;
//    private ArmorDao armorDao;
//    private LifeStyleDao lifeStyleDao;
//    private MountDao mountDao;
//    private WeaponDao weaponDao;
//    private SeasonDao seasonDao;
//    private AdventureDao adventureDao;
//    private EntityDao entityDao;
//
//    private EntityListDao entityListDao;
//    private ItemDao itemDao;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *     */
    public CompendiumService() {
        super(CompendiumService.class.getSimpleName());
    }

    public enum Header {
        ACTION
    }
    
    public enum Action {
        LOAD,
        SEARCH,
        ;
    }

    /**
     * This is used to start the service when it receives an intent
     * @param intent tells us what we intend to do.
     */
    @SuppressWarnings("JavacQuirks")
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent==null) {
            Logger.warning("Ignoring a null intent received in the Compendium Service.");
        }
        Action action = Objects.requireNonNull(intent).getParcelableExtra(Header.ACTION.name());
        switch(action) {
            case LOAD:
                loadAll();
                break;
            default:
                throw new RuntimeException("Action " + Text.objectString(action) + " is not supported in the CompendiumService.");
        }
        
    }

    /**
     * This will first attempt to load all the masters and then load all the daos
     */
    private void loadAll() {
        loadMasters();
        loadDaos();
    }

    /**
     * This method is used to retrieve a specific dao from the service. If the dao has already
     * been loaded this will return it's reference.
     * @param titleId is the resource string id of the title of the dao
     * @return a {@link WriteDao<>}
     * @throws IOException if we failed to load a dao or that title is unsupported.
     */
    private WriteDao<?> getDaoWithTitle(int titleId) throws IOException {
        final String title = getResources().getString(titleId);
        WriteDao<?> theDao = theDaoMap.get(titleId);
        if (theDao==null) {
            theDao = loadDaoWithTitle(titleId);
            theDaoMap.put(titleId, theDao);
        }
        return theDao;
    }

    /**
     * This will attempt to load up a new instance of the dao. This should only be called
     * internally if the dao has not already been loaded.
     * @param titleId will identify the dao we want to load.
     * @return a {@link WriteDao<>} of the appropriate type.
     * @throws IOException if we failed to load the dao.
     */
    private WriteDao<?> loadDaoWithTitle(int titleId) throws IOException {
        switch(titleId) {
            case R.string.vc_title_item_list:
                return new EntityListDao(dndMaster);
            case R.string.vc_title_items:
                return new ItemDao(dndMaster);
            case R.string.vc_title_entity:
                return new EntityDao(dndMaster);
            case R.string.vc_title_spell:
                return new SpellDao(dndMaster);
            case R.string.vc_title_magic_items:
                return new MagicItemDao(dndMaster);
            case R.string.vc_title_custom_monsters:
                return new CustomMonsterDao(dndMaster);
            case R.string.vc_title_standard_monsters:
                return new StandardMonsterDao(dndMaster);
            case R.string.vc_title_notes:
                return new NotesDao(dndMaster);
            case R.string.vc_title_feats:
                return new FeatDao(dndMaster);
            case R.string.vc_title_backgrounds:
                return new BackgroundDao(dndMaster);
            case R.string.vc_title_conditions:
                return new ConditionDao(dndMaster);
            case R.string.vc_title_equipment:
                return new EquipmentDao(dndMaster);
            case R.string.vc_title_gods:
                return new GodsDao(dndMaster);
            case R.string.vc_title_armor:
                return new ArmorDao(dndMaster);
            case R.string.vc_title_lifestyles:
                return new LifeStyleDao(dndMaster);
            case R.string.vc_title_mounts:
                return new MountDao(dndMaster);
            case R.string.vc_title_weapons:
                return new WeaponDao(dndMaster);
            case R.string.vc_title_season:
                return new SeasonDao(dndMaster);
            case R.string.vc_title_adventure:
                return new AdventureDao(dndMaster);

            case R.string.vc_title_levels:
                return new LevelDao(encounterMaster);
            case R.string.vc_title_challenge_ratings:
                return new ChallengeRatingDao(encounterMaster);
            default:
                throw new IOException("Failed to load dao for " + getApplicationContext().getResources().getString(titleId) + ".");
        }
    }

    /**
     * This will go through all the known categories we wish to have dao's for and attempt to load them.
     * If we fail to load a dao an error will be logged. If we replace an existing dao then a warning will be
     * provided in the logs indicating we reloaded the dao. This method will not fail.
     */
    private void loadDaos() {
        for(Category category : Category.values()) {
            try {
                final String title = getResources().getString(category.resourceId);
                final WriteDao<?> theDao = loadDaoWithTitle(category.resourceId);
                final WriteDao<?> thePrevDao = theDaoMap.put(category.resourceId, theDao);
                if (thePrevDao!=null) {
                    Logger.warning("Reloaded dao with title " + Text.quote(title) + ".");
                }
            } catch (IOException e) {
                Logger.error(e.getMessage(), e);
            }
        }
    }

    public WriteDao<?> getDaoForCategory(Category category) throws Exception {
        if (category==null) {
            throw new Exception("We can't determine a writeDao for a null category!");
        }

        WriteDao<?> theDao;
        theDao = getDaoWithTitle(category.resourceId);
        if (theDao==null) {
            throw new Exception("The dao for category " + category + " has not yet been loaded!");
        }
        return theDao;
    }

    private void loadMasters() {
        loadDndMaster();
        loadEncounterMaster();
        loadLogsheetMaster();
    }
    
    private void loadLogsheetMaster() {
        try {
            this.logsheetMaster = new LogsheetMaster(this.getApplicationContext());
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        }
    }
    
    private void loadEncounterMaster() {
        try {
            this.encounterMaster = new EncounterMaster(this.getApplicationContext());
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        }
    }
    
    private void loadDndMaster() {
        try {
            this.dndMaster = new DndMaster(this.getApplicationContext());
        } catch (IOException e) {
            Logger.error(e.getMessage(), e);
        }
    } 
}
