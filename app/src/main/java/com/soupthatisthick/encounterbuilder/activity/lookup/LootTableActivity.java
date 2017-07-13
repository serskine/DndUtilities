package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.adapters.lookup.RollTableAdapter;
import com.soupthatisthick.encounterbuilder.dao.lookup.RollTableDao;
import com.soupthatisthick.encounterbuilder.dao.lookup.RollTableEntryDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.RollTable;
import com.soupthatisthick.encounterbuilder.model.lookup.RollTableEntry;
import com.soupthatisthick.encounterbuilder.view.cell.other.RollTableEntryCell;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.activity.AppActivity;
import com.soupthatisthick.util.model.Histogram;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LootTableActivity extends AppActivity {

    private static final Random RANDOM = new Random();
    private TextView theListOfRollsView;
    private Spinner theTableSpinner;
    private RollTableAdapter tableAdapter;
    private Button toggleViewButton;

    private RollTableDao        tableDao;
    private RollTableEntryDao   entryDao;

    private RollTable selectedTable = null;
    private TableLayout theResultsTable;
    private Histogram<Long> theResultsList = new Histogram<>();
    private boolean viewingResults = false;

    private List<Integer> listOfRolls = new ArrayList<>();
    private TextView theTotalGpValue;


    /**
     * This will perform a dice roll. It is equvelent to rolling a numFaces sided die qty times and them multiplying the sum by multiplier
     * @param qty is the number of dice we wish to roll
     * @param size is the number of sides on each die
     * @param multiplier is the amount we multiply the sum by when we are done
     * @return the total value of the roll
     */
    private static final int roll(int qty, int size, int multiplier)
    {
        int sum = 0;
        for(int i=0; i<qty; i++)
        {
            sum += RANDOM.nextInt(size) + 1;
        }
        int value = sum * multiplier;
        Logger.debug(" - rolled a " + value);
        return value;
    }

    /**
     * Returns a roll to look up an entry on a table
     * @param table
     * @return
     */
    protected int getTableRoll(@NonNull RollTable table)
    {
        return roll(table.getDieCount(), table.getDieSize(), 1);
    }

    /**
     * Returns a roll represent the quantity of an item for an entry
     * @param entry
     * @return
     */
    protected int getEntryQtyRoll(@NonNull RollTableEntry entry)
    {
        return roll(entry.getDieQty(), entry.getDieSize(), entry.getRollMul());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loot_table);

        theTotalGpValue = (TextView) findViewById(R.id.theTotalGpValue);
        toggleViewButton = (Button) findViewById(R.id.theToggleViewButton);

        theTableSpinner = (Spinner) findViewById(R.id.theTableSpinner);

        theListOfRollsView = (TextView) findViewById(R.id.theRollsList);
        tableAdapter = new RollTableAdapter(getLayoutInflater());
        theTableSpinner.setAdapter(tableAdapter);

        theTableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTable = (RollTable) theTableSpinner.getItemAtPosition(position);
                setViewingResults(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedTable = null;
                setViewingResults(false);
            }
        });

        theResultsTable = (TableLayout) findViewById(R.id.theResultsTable);
    }

    private void updateUiList(@NonNull List<RollTableEntry> entries)
    {
        theTotalGpValue.setVisibility(View.GONE);
        theResultsTable.removeAllViews();

        RollTableEntryCell header = new RollTableEntryCell(getLayoutInflater(), null, null);
        header.updateUiAsHeader();
        theResultsTable.addView(header.getView());

        for(RollTableEntry entry : entries)
        {
            RollTableEntryCell cell = new RollTableEntryCell(getLayoutInflater(), null, null);
            cell.updateUi(entry);

            theResultsTable.addView(cell.getView());
        }
    }

    /**
     * This will change the ui to match the provided list
     * @param entries is a histogram of RollTableEntry id's mapped to the quantity of that entry we desire
     */
    private void updateUiList(@NonNull Histogram<Long> entries)
    {
        theTotalGpValue.setVisibility(View.VISIBLE);
        theResultsTable.removeAllViews();

        double totalGpValue = 0d;
        RollTableEntryCell header = new RollTableEntryCell(getLayoutInflater(), null, null);
        header.updateUiAsHeader();
        theResultsTable.addView(header.getView());

        for(Long entryId : entries.keySet())
        {
            RollTableEntryCell cell = new RollTableEntryCell(getLayoutInflater(), null, null);
            RollTableEntry entry = entryDao.load(entryId);
            Long qty = entries.get(entryId);
            double entryValue = qty * entry.getUnitGpValue();
            totalGpValue += entryValue;

            cell.updateUi(entry, qty);

            theResultsTable.addView(cell.getView());
        }

        theTotalGpValue.setText(getString(R.string.totalGpValue, totalGpValue));
    }

    private void showResultEntries()
    {
        updateUiList(theResultsList);
        toggleViewButton.setText(getString(R.string.lt_toggle_viewing_results));
    }

    private void showTableEntries()
    {
        updateListForTableViewing(selectedTable);
        toggleViewButton.setText(getString(R.string.lt_toggle_viewing_table));
    }

    /**
     * This will update the results list to be all entries contained within the specified table
     * @param table
     */
    private void updateListForTableViewing(@Nullable RollTable table)
    {
        clearRolls();
        if (table==null)
        {
            Logger.debug("results table is null");
            updateUiList(new ArrayList<RollTableEntry>());
        } else {
            Long tableId = table.getId();
            Logger.debug("results table id is " + tableId);
            try {
                updateUiList(entryDao.getAllEntriesForTable(tableId));
            } catch (Exception e) {
                Logger.error("Error occurred while getting table results! Assuming an empty list.", e);
                updateUiList(new ArrayList<RollTableEntry>());
            }
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        loadAllData();
    }

    /**
     * This is called before we set the initial search text, triggering a search for data.
     */
    protected void loadAllData() {
        try {

            theResultsList = new Histogram<>();

            DndMaster dndMaster = new DndMaster(getBaseContext());

            tableDao = new RollTableDao(dndMaster);
            entryDao = new RollTableEntryDao(dndMaster);

            try {
                List<RollTable> tables = tableDao.getAllRecords();
                tableAdapter.setData(tables);
            } catch (Exception e) {
                Logger.error("Failed to update the table list.", e);
            }

            selectedTable = (RollTable) theTableSpinner.getSelectedItem();

            setViewingResults(false);

        } catch (Exception e) {
            Logger.error("Failed to load te dao's properly!", e);
            finish();
        }
    }

    /**
     * This will roll recursively on the immediate table and all the reroll tables after wards
     * @param view
     */
    public void onClickToggleView(View view)
    {

        Logger.info("onClickToggleView()");
        setViewingResults(!isViewingResults());

    }

    /**
     * Called when we click on the button that says "Add Roll". We will add
     * any results from this roll onto any previous results and then display the new results combined.
     * @param view
     */
    public void onClickAddRoll(View view)
    {
        Logger.info("onClickAddRollOnTable()");
        clearRolls();
        Histogram<Long> localResults = new Histogram<>();

        if (selectedTable != null) {
            try {
                rollRecursiveResults(false, selectedTable, listOfRolls, localResults);
                theResultsList.incrementAll(localResults);
                updateUiList(theResultsList);   // Update the list of data
            } catch (Exception e) {
                Logger.error("Failed to roll recursively for loot!", e);
            }
        } else {
            Logger.toast(getApplication(), getString(R.string.lt_select_table));
        }
        setViewingResults(true);
    }

    /**
     * Called when we click on the button that says "Replace Roll". we will replace
     * any previous results with the results from this roll
     * @param view
     */
    public void onClickClearButton(View view)
    {
        Logger.info("onClickClearButton()");
        clearRolls();
        theResultsList.clear();
        setViewingResults(true);
    }


    /**
     * This is used to recursively get results from the specified table with the specified dice roll
     * @param useAverageQty
     * @param table
     * @param results
     * @throws Exception
     */
    protected void rollRecursiveResults(
            boolean useAverageQty,
            @Nullable RollTable table,
            @NonNull List<Integer> rolls,
            @NonNull Histogram<Long> results
    ) throws Exception
    {
        if (table==null)
        {
            throw new RuntimeException("We can't roll on a null table.");
        }

        int roll = getTableRoll(table);
        rolls.add(roll);
        List<RollTableEntry> localResults = entryDao.lookupDiceRoll(table.getId(), roll);

        for(RollTableEntry entry : localResults) {

            Long reRollId = entry.getReRollTableId();
            RollTable rerollTable = tableDao.load(reRollId);
            int qty = (useAverageQty) ? entry.getRollAvg() : getEntryQtyRoll(entry);
            if (rerollTable == null) {
                results.increment(entry.getId(), qty);
            } else {
                for (int i = 0; i < qty; i++) {
                    rollRecursiveResults(
                        useAverageQty,
                        rerollTable,
                        rolls,
                        results
                    );
                }
            }
        }
    }


    /**
     * This should be called AFTER the search text is updated
     * @param roll
     */
    private void addRoll(int roll)
    {
        listOfRolls.add(roll);
        updateRollsListUi();
    }

    /**
     * This is called after the search text is cleared.
     */
    private void clearRolls()
    {
        listOfRolls.clear();
        updateRollsListUi();
    }

    /**
     * This is called after we have added or cleared rolls
     */
    private void updateRollsListUi()
    {
        String output = "";
        for(int i = 0; i< listOfRolls.size(); i++)
        {
            if (i!=0)
            {
                output += ", ";
            }
            output += listOfRolls.get(i);
        }

        theListOfRollsView.setText(output);
    }

    public boolean isViewingResults() {
        return viewingResults;
    }

    public void setViewingResults(boolean viewingResults) {
        this.viewingResults = viewingResults;
        if (viewingResults)
        {
            showResultEntries();
        } else {
            showTableEntries();
        }
    }

}
