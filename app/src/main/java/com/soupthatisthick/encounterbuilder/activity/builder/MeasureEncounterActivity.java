package com.soupthatisthick.encounterbuilder.activity.builder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.soupthatisthick.encounterbuilder.adapters.builder.EditEncounterPageAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.fragment.builder.MeasureEncounterAlliesFragment;
import com.soupthatisthick.encounterbuilder.fragment.builder.MeasureEncounterEnemiesFragment;
import com.soupthatisthick.encounterbuilder.fragment.builder.MeasureEncounterStatsFragment;
import com.soupthatisthick.encounterbuilder.logic.Encounter;
import com.soupthatisthick.encounterbuilder.model.builder.EncounterStats;
import com.soupthatisthick.encounterbuilder.model.builder.XpThresholds;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.translater.ChallengeRatingTranslater;
import com.soupthatisthick.encounterbuilder.util.translater.LevelTranslater;
import com.soupthatisthick.util.Expression;
import com.soupthatisthick.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/8/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MeasureEncounterActivity extends AppCompatActivity
        implements
        MeasureEncounterAlliesFragment.Listener,
        MeasureEncounterEnemiesFragment.Listener,
        MeasureEncounterStatsFragment.Listener
{
    private DndMaster dndMaster;
    private EncounterMaster encounterMaster;

    private StandardMonsterDao standardMonsterDao;
    private CustomMonsterDao customMonsterDao;
    private ChallengeRatingDao crDao;
    private LevelDao levelDao;

    private Encounter mast;

    private List<Object> allies = new ArrayList<>();
    private List<Object> enemies = new ArrayList<>();

    // Controls on the activity
    ViewPager theViewPager;
    TabLayout theTabLayout;

    EditEncounterPageAdapter pageAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_encounter);
        pageAdapter = new EditEncounterPageAdapter(
                getSupportFragmentManager(),
                getBaseContext()
        );

        try {

            theViewPager = (ViewPager) findViewById(R.id.theViewPager);
            theViewPager.setAdapter(pageAdapter);

            theTabLayout = (TabLayout) findViewById(R.id.theTabLayout);
            theTabLayout.setupWithViewPager(theViewPager);

            dndMaster = new DndMaster(getBaseContext());
            encounterMaster = new EncounterMaster(getBaseContext());

            standardMonsterDao = new StandardMonsterDao(dndMaster);
            customMonsterDao = new CustomMonsterDao(dndMaster);
            crDao = new ChallengeRatingDao(encounterMaster);
            levelDao = new LevelDao(encounterMaster);

        } catch (Exception e) {
            Logger.error("Failed to start the MeasureEncounterActivity", e);
            finish();
        }

        logEncounterData();
        updateUi();
    }

    private void logEncounterData()
    {
        try {
            Logger.title("ENCOUNTER DATA");
            EncounterStats stats = getStats();
            Logger.info(stats.getDetailedString());
        } catch (Exception e) {
            Logger.error("Problem logging the encounter.", e);
        }
    }



    /**
     * This is the method that does all the work.
     * @return a {@link EncounterStats} object containing all the pertinent information
     */
    public EncounterStats getStats() throws IOException {

        int numEnemies = enemies.size();
        int numAllies = allies.size();
        XpThresholds xpThresholds = getPartyThresholds();
        int unmodifiedTotalEnemyXp = getEnemyTotalXp();

        return new EncounterStats(
            numAllies,
            xpThresholds,
            numEnemies,
            unmodifiedTotalEnemyXp
        );
    }

    /**
     * This will determine the thresholds of the entire party
     * @return
     * @throws IOException
     */
    private XpThresholds getPartyThresholds() throws IOException {
        XpThresholds xpThresholds = new XpThresholds();
        int easy = 0;
        int medium = 0;
        int hard = 0;
        int deadly = 0;

        for(Object ally : allies)
        {
            Level level = getLevelOfAlly(ally);
            easy += level.getEasy();
            medium += level.getNormal();
            hard += level.getHard();
            deadly += level.getDeadly();
        }
        return new XpThresholds(easy, medium, hard, deadly);
    }

    /**
     * This will simply sum up all the xp of all the enemies
     * @return
     * @throws IOException
     */
    private int getEnemyTotalXp() throws IOException {
        int totalXp = 0;
        for(Object enemy : enemies)
        {
            ChallengeRating cr = getChallengeOfEnemy(enemy);
            totalXp += cr.getXp();
        }
        return totalXp;
    }

    //
    // Methods used to add different types of allies and enemies to the encounter
    //

    @Override
    public void addEnemy(Object cr) {
        enemies.add(cr);
        updateUi();
    }

    @Override
    public void deleteEnemy(Object cr) {
        enemies.remove(cr);
        updateUi();
    }

    @Override
    public void clearEnemies()
    {
        enemies.clear();
        updateUi();
    }

    @Override
    public void addAlly(Object ally) {
        allies.add(ally);
        updateUi();
    }

    @Override
    public void deleteAlly(Object ally) {
        allies.remove(ally);
        updateUi();
    }

    @Override
    public void clearAllies()
    {
        allies.clear();
        updateUi();
    }

    @Override
    public void clearAll()
    {
        clearEnemies();
        clearAllies();
    }

    private Level getLevelOfAlly(@NonNull Object ally) throws IOException {
        LevelTranslater translater = new LevelTranslater(levelDao);
        return translater.getLevelOfAlly(ally);
    }

    private ChallengeRating getChallengeOfEnemy(@NonNull Object enemy) throws IOException {

        ChallengeRatingTranslater translater = new ChallengeRatingTranslater(crDao);
        return translater.getChallengeOfEnemy(enemy);
    }

    /**
     * Determines the challenge rating of the given standard monster
     * @param monster
     * @return
     */
    private ChallengeRating getStandardMonsterChallengeRating(StandardMonster monster) throws IOException {
        return getStringChallengeRating(monster.getChallengeRating());
    }

    private ChallengeRating getCustomMonsterChallengeRating(CustomMonster monster) throws IOException {
        return getStringChallengeRating(monster.getCr());
    }

    private ChallengeRating getStringChallengeRating(String str) throws IOException {
        Double crValue = Expression.eval(str);
        return getDoubleChallengeRating(crValue);
    }

    private ChallengeRating getDoubleChallengeRating(@NonNull Double value) throws IOException
    {
        return crDao.findWithValue(value);
    }

    private Level getStringLevel(@NonNull String str) throws IOException {
        Double value = Expression.eval(str);
        return getDoubleLevel(value);
    }

    private Level getDoubleLevel(@NonNull Double value) throws IOException {
        return levelDao.findWithValue(value);
    }


    public void updateUi()
    {
        try {
            MeasureEncounterStatsFragment statsFragment = pageAdapter.measureEncounterStatsFragment;
            EncounterStats stats = getStats();
            statsFragment.updateUi(stats);

            Logger.info(stats.getDetailedString());

            MeasureEncounterAlliesFragment alliesFragment = pageAdapter.measureEncounterAlliesFragment;
            alliesFragment.updateUi(allies);

            MeasureEncounterEnemiesFragment enemiesFragment = pageAdapter.measureEncounterEnemiesFragment;
            enemiesFragment.updateUi(enemies);

        } catch (Exception e) {
            Logger.error("Failed to update the ui", e);
        }

    }

    /**
     * This method is called when we want to clear all the encounter details
     * @param view
     */
    public void onClickClearEncounterButton(View view)
    {
        clearAll();
    }
}



