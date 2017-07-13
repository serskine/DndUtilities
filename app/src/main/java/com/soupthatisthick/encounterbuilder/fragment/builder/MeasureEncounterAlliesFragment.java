package com.soupthatisthick.encounterbuilder.fragment.builder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.soupthatisthick.encounterbuilder.adapters.lookup.CompendiumAdapter;
import com.soupthatisthick.encounterbuilder.adapters.lookup.TextAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.dao.master.EncounterMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.fragment.AppFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MeasureEncounterAlliesFragment extends AppFragment
{

    private DaoMaster daoMaster;
    private LevelDao levelDao;

    public interface Listener
    {
        void addAlly(Object level);
        void deleteAlly(Object level);
        void clearAllies();
    }

    private ListView theListView;
    private TextAdapter theListAdapter;
    private Spinner theAlliesSpinner;

    private Object selectedAlly;

    private View addView, deleteView, clearView;

    private Listener fragmentListener = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_encounter_allies, container, false);

        theListView = (ListView) view.findViewById(R.id.ml_list_view);
        theListAdapter =new TextAdapter(inflater);
        theListView.setAdapter(theListAdapter);

        theAlliesSpinner = (Spinner) view.findViewById(R.id.theAlliesSpinner);
        theAlliesSpinner.setAdapter(null);

        theAlliesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAlly = parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedAlly = null;
            }
        });

        // Set the add view on click listener
        addView = view.findViewById(R.id.ml_add_button);
        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Logger.debug("TODO: Implement selecting an ally button!");
                    if (fragmentListener!=null && selectedAlly!=null) {
                        fragmentListener.addAlly(selectedAlly);
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
                    Logger.debug("TODO: Implement deleting an ally button!");
                    if (fragmentListener!=null && selectedAlly!=null) {
                        fragmentListener.deleteAlly(selectedAlly);
                    }
                } catch (Exception e) {
                    Logger.error("Failed to delete an ally", e);
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
                    fragmentListener.clearAllies();
                }
            }
        });


        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        try {
            daoMaster = new EncounterMaster(getContext());
            levelDao = new LevelDao(daoMaster);

            CompendiumAdapter adapter = new CompendiumAdapter(layoutInflater);
            List<Object> allies = new ArrayList<>();
            allies.addAll(levelDao.getAllRecords());
            adapter.setData(allies);
            theAlliesSpinner.setAdapter(adapter);

            Logger.debug("The allies adapter has " + allies.size() + " entries.");
        } catch (Exception e) {
            Logger.error("Failed to resume the " + getClass().getSimpleName() + ".", e);
        }
    }

    public void updateUi(List<Object> allies)
    {
        try {
            if (isAdded()) {
                List<String> descriptions = new ArrayList<>();
                for (Object ally : allies) {
                    String text = getDescription(ally);
                    descriptions.add(text);
                }
                Collections.sort(descriptions);
                theListAdapter.setData(descriptions);
            }
        } catch (Exception e) {
            Logger.error("Failed to update the MeasureEncounterAlliesFragment ui", e);
        }
    }


    private String getDescription(Object object)
    {
        return object.toString();
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof MeasureEncounterEnemiesFragment.Listener)
        {
            fragmentListener = (MeasureEncounterAlliesFragment.Listener) context;
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



}
