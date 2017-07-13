package com.soupthatisthick.encounterbuilder.fragment.builder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.CompendiumAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.TextAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.StandardMonsterDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.translater.ChallengeRatingTranslater;
import com.soupthatisthick.encounterbuilder.util.tasks.MultiDaoSearchTask;
import com.soupthatisthick.encounterbuilder.util.translater.FractionTranslater;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.fragment.AppFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MeasureEncounterEnemiesFragment extends AppFragment
{


    private DaoMaster encounterMaster;
    private DaoMaster dndMaster;

    private ChallengeRatingDao crDao;
    private StandardMonsterDao monsterDao;
    private CustomMonsterDao customMonsterDao;

    private ListView theListView;
    private TextAdapter theListAdapter;

    private Spinner theEnemiesSpinner;

    private View addView, deleteView, clearView;
    private ImageButton theSearchButton, theClearSearchButton;
    private EditText theSearchEdit;

    private CompendiumAdapter adapter = null;

    private Listener fragmentListener = null;

    public interface Listener extends EventListener
    {
        void addEnemy(Object cr);
        void deleteEnemy(Object cr);
        void clearEnemies();
    }

    private Object getSelectedEnemy()
    {
        if (theEnemiesSpinner==null) {
            return null;
        } else {
            return theEnemiesSpinner.getSelectedItem();
        }
    }

    /**
     * Initialises the list that we view the selected enemies
     * @param view
     */
    private void initListView(View view)
    {

        theListView = (ListView) view.findViewById(R.id.ml_list_view);
        theListAdapter = new TextAdapter(layoutInflater);
        theListView.setAdapter((ListAdapter) theListAdapter);

    }

    /**
     * Initializes the spinner we choose what to add next from
     * @param view
     */
    private void initEnemiesSpinner(View view)
    {

        theEnemiesSpinner = (Spinner) view.findViewById(R.id.theEnemiesSpinner);
        theEnemiesSpinner.setAdapter(null);
    }

    /**
     * Initializes the command buttons add, delete and clear
     * @param view
     */
    private void initCommandButtons(View view)
    {

        // Set the add view on click listener
        addView = view.findViewById(R.id.ml_add_button);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Object selectedEnemy = getSelectedEnemy();
                    if (fragmentListener!=null && selectedEnemy !=null) {
                        fragmentListener.addEnemy(selectedEnemy);
                    }
                } catch (Exception e) {
                    Logger.error("Failed to add an enemy", e);
                }
            }
        });

        // Set the delete view on click listener
        deleteView = view.findViewById(R.id.ml_delete_button);
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Object selectedEnemy = getSelectedEnemy();
                    if (fragmentListener!=null && selectedEnemy !=null) {
                        fragmentListener.deleteEnemy(selectedEnemy);
                    }
                } catch (Exception e) {
                    Logger.error("Failed to delete an enemy", e);
                }
            }
        });

        // Set the clear view on click listener
        clearView = view.findViewById(R.id.ml_clear_button);
        clearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentListener!=null)
                {
                    fragmentListener.clearEnemies();
                }
            }
        });


    }

    private void initFilterControls(View view)
    {

        theClearSearchButton = (ImageButton) view.findViewById(R.id.theClearSearchButton);
        theClearSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.debug("Clicked the clear search button text");
                theSearchEdit.setText("");
            }
        });

        theSearchButton = (ImageButton) view.findViewById(R.id.theSearchButton);
        theSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.debug("Clicked the search button");
                searchForText();
            }
        });


        theSearchEdit = (EditText) view.findViewById(R.id.theSearchEdit);
        theSearchEdit.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if  (   (actionId == EditorInfo.IME_ACTION_SEARCH)
                                ||  (   (event != null)
                                &&  (event.getKeyCode()==KeyEvent.KEYCODE_ENTER)
                        )
                                )
                        {
                            Logger.debug("Committed Search Edit text");
                            searchForText();
                            ViewUtil.hideInputWindow(MeasureEncounterEnemiesFragment.this.getActivity());
                        }
                        return true;
                    }
                }
        );


        theSearchEdit = (EditText) view.findViewById(R.id.theSearchEdit);
        theSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Logger.debug("Detected search edit text change");
                searchForText();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encounter_enemies, container, false);

        initListView(view);
        initEnemiesSpinner(view);
        initCommandButtons(view);
        initFilterControls(view);
        return view;
    }


    @Override
    public void onResume()
    {
        super.onResume();
        try {
            dndMaster = new DndMaster(getContext());
            encounterMaster = new EncounterMaster(getContext());
            crDao = new ChallengeRatingDao(encounterMaster);
            monsterDao = new StandardMonsterDao(dndMaster);
            customMonsterDao = new CustomMonsterDao(dndMaster);

            adapter = new CompendiumAdapter(layoutInflater);
            theEnemiesSpinner.setAdapter(adapter);

            searchForText();
        } catch (Exception e) {
            Logger.error("Failed to resume the " + getClass().getSimpleName() + ".", e);
        }
    }

    public void updateUi(List<Object> allies)
    {
        try {
            if (isAdded()) {
                ArrayList<String> descriptions = new ArrayList<>();
                for(Object ally : allies)
                {
                    descriptions.add(getDescription(ally));
                }
                Collections.sort(descriptions);
                theListAdapter.setData(descriptions);
            }
        } catch (Exception e) {
            Logger.error("Failed to update the MeasureEncounterEnemiesFragment ui", e);
        }
    }

    /**
     * This is used to get the descriptive string for an ally
     * @param object
     * @return
     */
    private String getDescription(Object object)
    {
        ChallengeRatingTranslater translater = new ChallengeRatingTranslater(crDao);
        try {
            ChallengeRating cr = translater.getChallengeOfEnemy(object);
            if (object instanceof ChallengeRating) {
                return object.toString();
            } else if (object instanceof StandardMonster) {
                StandardMonster standardMonster = (StandardMonster) object;
                return String.format(
                    "%s, cr %s (%d xp)",
                    standardMonster.getName(),
                    FractionTranslater.asFraction(cr.getValue()),
                    cr.getXp()
                );
            } else if (object instanceof CustomMonster) {
                CustomMonster customMonster = (CustomMonster) object;
                return String.format(
                        "%s, cr %s (%d xp)",
                        customMonster.getName(),
                        FractionTranslater.asFraction(cr.getValue()),
                        cr.getXp()
                );
            }
        } catch (Exception e) {
            Logger.error("Failed to determine the challenge rating of the enemy.", e);
            return object.toString();
        }

        try {
            return translater.getChallengeOfEnemy(object).toString();
        } catch (Exception e) {
            Logger.error("Failed to determine the challenge rating.", e);
            return object.toString();
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof Listener)
        {
            fragmentListener = (Listener) context;
        } else {
            fragmentListener = null;
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        fragmentListener = null;
    }

    /**
     * This method will trigger a search based on the text contained within the search field.
     */
    protected final void searchForText() {
        Logger.debug(" - search for text");
        final String searchText = theSearchEdit.getText().toString();

        MultiDaoSearchTask task = new MultiDaoSearchTask() {
            @Override
            protected void onSearchCompleted(List<Object> results) {
                adapter.setData(results);
            }

            @Override
            protected int getMinSearchTextLength() {
                return 0;
            }
        };
        task.readDaos.add(crDao);
        task.readDaos.add(monsterDao);
        task.readDaos.add(customMonsterDao);
        task.execute(searchText.toUpperCase());
    }

    /**
     * This will determine all the possible results
     * @param query
     * @return
     */
    private final List<Object> searchForResults(@Nullable String query)
    {
        ArrayList<Object> results = new ArrayList<>();
        try {
            results.addAll(crDao.getRecordsLike(query));
        } catch (Exception e) {
            Logger.error("Failed to search the cr dao for results.", e);
        }

        try {
            results.addAll(customMonsterDao.getRecordsLike(query));
        } catch (Exception e) {
            Logger.error("Failed to search the custom monster dao for results.", e);
        }

        try {
            results.addAll(monsterDao.getRecordsLike(query));
        } catch (Exception e) {
            Logger.error("Failed to search the standard monster dao for results.", e);
        }

        return results;
    }

}
