package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.util.translater.ScoreTranslater;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import java.util.Locale;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CustomMonsterDetailCell extends ReadCell<CustomMonster> {
    public TextView theName;
    public TextView theTypeAndAlign;
    public TextView theAc;
    public TextView theAcType;
    public TextView theHp;
    public TextView theHd;
    public TextView theSpeed;
    public TextView theStr;
    public TextView theDex;
    public TextView theCon;
    public TextView theInt;
    public TextView theWis;
    public TextView theCha;
    public TextView theSaves;
    public TextView theSkills;
    public TextView theDmgVulnerability;
    public TextView theDmgResistance;
    public TextView theDmgImmunity;
    public TextView theConditionImmunity;
    public TextView theSenses;
    public TextView theLanguages;
    public TextView theCr;
    public TextView theXp;
    public TextView theAbilities;
    public TextView theActions;
    public TextView theReactions;
    public TextView theLegendary;
    public TextView theOther;
    public TextView theSource;

    public ViewGroup theLegendaryActionsGroup;
    public ViewGroup theActionsGroup;
    public ViewGroup theReactionsGroup;
    public ViewGroup theSensesGroup;
    public ViewGroup theDmgVulnerabilityGroup;
    public ViewGroup theDmgResistanceGroup;
    public ViewGroup theDmgImmunityGroup;
    public ViewGroup theCondImmunityGroup;
    public ViewGroup theSavesGroup;
    public ViewGroup theSkillsGroup;

    public CustomMonsterDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public void updateUi(CustomMonster customMonster)
    {
        this.theName.setText(customMonster.getName());

        theTypeAndAlign.setText(customMonster.getType() + ", " + customMonster.getAlignment());

        this.theCr.setText(titleString("Challenge: ", customMonster.getCr()));
        this.theXp.setText(String.format(Locale.getDefault(), "(%d xp)", customMonster.getXp()));

        this.theAc.setText(titleString("Armor Class: ", String.format(Locale.getDefault(), "%d", customMonster.getAc())));
        this.theAcType.setText(customMonster.getAcType());

        this.theHp.setText(titleString("Hit Points: ", customMonster.getHp()));
        this.theHd.setText("(" + customMonster.getHd() + ")");

        this.theStr.setText(scoreString(customMonster.getStrength()));
        this.theDex.setText(scoreString(customMonster.getDexterity()));
        this.theCon.setText(scoreString(customMonster.getConstitution()));
        this.theInt.setText(scoreString(customMonster.getIntelligence()));
        this.theWis.setText(scoreString(customMonster.getWisdom()));
        this.theCha.setText(scoreString(customMonster.getCharisma()));

        this.theSpeed.setText(titleString("Movement: ", customMonster.getSpeed()));
        this.theSaves.setText(titleString("Saves: ", customMonster.getSaves()));
        this.theSkills.setText(titleString("Skills: ", customMonster.getSkills()));

        this.theSenses.setText(titleString("Senses: ", customMonster.getSenses()));
        this.theLanguages.setText(titleString("Languages: ", customMonster.getLanguages()));

        this.theConditionImmunity.setText(titleString("Condition Immunities: ", customMonster.getConditionImmunity()));
        this.theDmgVulnerability.setText(titleString("Damage Vulnerabilities: ", UNKNOWN));
        this.theDmgImmunity.setText(titleString("Damage Immunities: ", customMonster.getDmgImmunity()));
        this.theDmgResistance.setText(titleString("Damage Resistances: ", customMonster.getDmgResistance()));

        this.theAbilities.setText(htmlString(customMonster.getAbilities()));
        this.theActions.setText(htmlString(customMonster.getActions()));
        this.theLegendary.setText(htmlString(customMonster.getLegendaryActions()));
        this.theOther.setText(htmlString(customMonster.getOther()));

        this.theSource.setText(htmlString(customMonster.getSource()));

        checkVisibilities();


    }


    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_standard_monster_detailed, parent);

        this.theName = (TextView) view.findViewById(R.id.theName);
        this.theTypeAndAlign = (TextView) view.findViewById(R.id.theTypeAndAlign);
        this.theAc = (TextView) view.findViewById(R.id.theAc);
        this.theAcType = (TextView) view.findViewById(R.id.theAcType);
        this.theHp = (TextView) view.findViewById(R.id.theHp);
        this.theHd = (TextView) view.findViewById(R.id.theHd);
        this.theSpeed = (TextView) view.findViewById(R.id.theSpeed);
        this.theStr = (TextView) view.findViewById(R.id.theStr);
        this.theDex = (TextView) view.findViewById(R.id.theDex);
        this.theCon = (TextView) view.findViewById(R.id.theCon);
        this.theInt = (TextView) view.findViewById(R.id.theInt);
        this.theWis = (TextView) view.findViewById(R.id.theWis);
        this.theCha = (TextView) view.findViewById(R.id.theCha);
        this.theSaves = (TextView) view.findViewById(R.id.theSaves);
        this.theSkills = (TextView) view.findViewById(R.id.theSkills);

        this.theDmgVulnerability = (TextView) view.findViewById(R.id.theDmgVulnerability);
        this.theDmgResistance = (TextView) view.findViewById(R.id.theDmgResistance);
        this.theDmgImmunity = (TextView) view.findViewById(R.id.theDmgImmunity);
        this.theConditionImmunity = (TextView) view.findViewById(R.id.theConditionImmunity);

        this.theSenses = (TextView) view.findViewById(R.id.theSenses);
        this.theLanguages = (TextView) view.findViewById(R.id.theLanguages);
        this.theCr = (TextView) view.findViewById(R.id.theCr);
        this.theXp = (TextView) view.findViewById(R.id.theXp);
        this.theAbilities = (TextView) view.findViewById(R.id.theAbilities);
        this.theActions = (TextView) view.findViewById(R.id.theActions);
        this.theReactions = (TextView) view.findViewById(R.id.theReactions);
        this.theLegendary = (TextView) view.findViewById(R.id.theLegendary);
        this.theOther = (TextView) view.findViewById(R.id.theOther);
        this.theSource = (TextView)view.findViewById(R.id.theSource);

        this.theLegendaryActionsGroup = (ViewGroup) view.findViewById(R.id.theLegendaryActionsGroup);
        this.theActionsGroup = (ViewGroup) view.findViewById(R.id.theActionsGroup);
        this.theReactionsGroup = (ViewGroup) view.findViewById(R.id.theReactionsGroup);
        this.theSensesGroup = (ViewGroup) view.findViewById(R.id.theSensesGroup);
        this.theDmgVulnerabilityGroup = (ViewGroup) view.findViewById(R.id.theDmgVulnerabilityGroup);
        this.theDmgResistanceGroup = (ViewGroup) view.findViewById(R.id.theDmgResistanceGroup);
        this.theDmgImmunityGroup = (ViewGroup) view.findViewById(R.id.theDmgImmunityGroup);
        this.theCondImmunityGroup = (ViewGroup) view.findViewById(R.id.theConditionImmunityGroup);
        this.theSavesGroup = (ViewGroup) view.findViewById(R.id.theSavesGroup);
        this.theSkillsGroup = (ViewGroup) view.findViewById(R.id.theSkillsGroup);

        checkVisibilities();

        return view;
    }


    private void checkVisibilities()
    {

        theAbilities.setVisibility(isEmpty(theAbilities.getText()) ? View.GONE : View.VISIBLE);
        theActionsGroup.setVisibility(isEmpty(theActions.getText()) ? View.GONE : View.VISIBLE);
        theReactionsGroup.setVisibility(isEmpty(theReactions.getText()) ? View.GONE : View.VISIBLE);
        theLegendaryActionsGroup.setVisibility(isEmpty(theLegendary.getText()) ? View.GONE : View.VISIBLE);
        theOther.setVisibility(isEmpty(theOther.getText()) ? View.GONE : View.VISIBLE);

        theDmgVulnerabilityGroup.setVisibility(isEmpty(theDmgVulnerability.getText()) ? View.GONE : View.VISIBLE);
        theDmgResistanceGroup.setVisibility(isEmpty(theDmgResistance.getText()) ? View.GONE : View.VISIBLE);
        theDmgImmunityGroup.setVisibility(isEmpty(theDmgImmunity.getText()) ? View.GONE : View.VISIBLE);
        theCondImmunityGroup.setVisibility(isEmpty(theConditionImmunity.getText()) ? View.GONE : View.VISIBLE);

        theSavesGroup.setVisibility(isEmpty(theSaves.getText()) ? View.GONE : View.VISIBLE);
        theSkillsGroup.setVisibility(isEmpty(theSkills.getText()) ? View.GONE : View.VISIBLE);
        theSensesGroup.setVisibility(isEmpty(theSenses.getText()) ? View.GONE : View.VISIBLE);

        theSource.setVisibility(isEmpty(theSource.getText()) ? View.GONE : View.VISIBLE);
    }

    private static final String scoreString(int score)
    {
        return ScoreTranslater.INSTANCE.toText(score);
    }


}
