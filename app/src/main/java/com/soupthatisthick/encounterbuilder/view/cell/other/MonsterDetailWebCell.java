package com.soupthatisthick.encounterbuilder.view.cell.other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.translater.ScoreTranslater;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import java.util.Locale;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 1/26/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MonsterDetailWebCell extends ReadCell<CustomMonster> {
    public WebView theName;
    public WebView theTypeAndAlign;
    public WebView theAc;
    public WebView theAcType;
    public WebView theHp;
    public WebView theHd;
    public WebView theSpeed;
    public WebView theStr;
    public WebView theDex;
    public WebView theCon;
    public WebView theInt;
    public WebView theWis;
    public WebView theCha;
    public WebView theSaves;
    public WebView theSkills;
    public WebView theDmgVulnerability;
    public WebView theDmgResistance;
    public WebView theDmgImmunity;
    public WebView theConditionImmunity;
    public WebView theSenses;
    public WebView theLanguages;
    public WebView theCr;
    public WebView theXp;
    public WebView theAbilities;
    public WebView theActions;
    public WebView theReactions;
    public WebView theLegendary;
    public WebView theOther;
    public WebView theSource;

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

    protected static final String MIME_TYPE = "text/html";
    protected static final String BASE_URL = null;
    protected static final String ENCODING = "utf8";
    protected static final String HISTORY_URL = null;

    public MonsterDetailWebCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public void updateUi(StandardMonster monster)
    {
        this.theName.loadDataWithBaseURL(BASE_URL, monster.getName(), BASE_URL, HISTORY_URL, HISTORY_URL);

        theTypeAndAlign.loadDataWithBaseURL(BASE_URL, monster.getType() + ", " + monster.getAlignment(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theCr.loadDataWithBaseURL(BASE_URL, titleString("Challenge: ", monster.getChallengeRating()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theXp.loadDataWithBaseURL(BASE_URL, UNKNOWN, BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theAc.loadDataWithBaseURL(BASE_URL, titleString("Armor Class: ", String.format(Locale.getDefault(), "%s", monster.getArmorClass())).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theAcType.loadDataWithBaseURL(BASE_URL, UNKNOWN, BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theHp.loadDataWithBaseURL(BASE_URL, titleString("Hit Points: ", monster.getHitPoints()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theHd.loadDataWithBaseURL(BASE_URL, "(" + monster.getHitDice() + ")", BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theStr.loadDataWithBaseURL(BASE_URL, scoreString(monster.getStrength()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDex.loadDataWithBaseURL(BASE_URL, scoreString(monster.getDexterity()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theCon.loadDataWithBaseURL(BASE_URL, scoreString(monster.getConstitution()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theInt.loadDataWithBaseURL(BASE_URL, scoreString(monster.getIntelligence()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theWis.loadDataWithBaseURL(BASE_URL, scoreString(monster.getWisdom()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theCha.loadDataWithBaseURL(BASE_URL, scoreString(monster.getCharisma()), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theSpeed.loadDataWithBaseURL(BASE_URL, titleString("Movement: ", monster.getSpeed()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        updateUiSaves(monster);
        updateUiSkills(monster);

        this.theSenses.loadDataWithBaseURL(BASE_URL, titleString("Senses: ", monster.getSenses()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theLanguages.loadDataWithBaseURL(BASE_URL, titleString("Languages: ", monster.getLanguages()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theConditionImmunity.loadDataWithBaseURL(BASE_URL, titleString("Condition Immunities: ", monster.getConditionImmunities()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDmgVulnerability.loadDataWithBaseURL(BASE_URL, titleString("Damage Vulnerabilities: ", monster.getDamageVulnerabilities()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDmgImmunity.loadDataWithBaseURL(BASE_URL, titleString("Damage Immunities: ", monster.getDamageImmunities()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDmgResistance.loadDataWithBaseURL(BASE_URL, titleString("Damage Resistances: ", monster.getDamageResistances()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        // TODO: Add reactions
        this.theAbilities.loadDataWithBaseURL(BASE_URL, htmlString(monster.getSpecialAbilities()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theActions.loadDataWithBaseURL(BASE_URL, htmlString(monster.getActions()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theLegendary.loadDataWithBaseURL(BASE_URL, htmlString(monster.getLegendaryActions()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theOther.loadDataWithBaseURL(BASE_URL, htmlString(UNKNOWN).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theSource.loadDataWithBaseURL(BASE_URL, htmlString(monster.getSource()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        checkVisibilities();

    }

    private void updateUiSaves(StandardMonster standardMonster)
    {
        String savesStr = UNKNOWN;
        this.theSaves.loadDataWithBaseURL(BASE_URL, titleString("Saves: ", savesStr).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
    }

    private void updateUiSkills(StandardMonster standardMonster)
    {
        String skillsStr = UNKNOWN;
        this.theSkills.loadDataWithBaseURL(BASE_URL, titleString("Skills: ", skillsStr).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
    }

    public void updateUi(CustomMonster customMonster)
    {
        this.theName.loadDataWithBaseURL(BASE_URL, customMonster.getName(), BASE_URL, HISTORY_URL, HISTORY_URL);

        theTypeAndAlign.loadDataWithBaseURL(BASE_URL, customMonster.getType() + ", " + customMonster.getAlignment(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theCr.loadDataWithBaseURL(BASE_URL, titleString("Challenge: ", customMonster.getCr()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theXp.loadDataWithBaseURL(BASE_URL, String.format(Locale.getDefault(), "(%d xp)", customMonster.getXp()), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theAc.loadDataWithBaseURL(BASE_URL, titleString("Armor Class: ", String.format(Locale.getDefault(), "%d", customMonster.getAc())).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theAcType.loadDataWithBaseURL(BASE_URL, customMonster.getAcType(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theHp.loadDataWithBaseURL(BASE_URL, titleString("Hit Points: ", customMonster.getHp()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theHd.loadDataWithBaseURL(BASE_URL, "(" + customMonster.getHd() + ")", BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theStr.loadDataWithBaseURL(BASE_URL, scoreString(customMonster.getStrength()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDex.loadDataWithBaseURL(BASE_URL, scoreString(customMonster.getDexterity()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theCon.loadDataWithBaseURL(BASE_URL, scoreString(customMonster.getConstitution()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theInt.loadDataWithBaseURL(BASE_URL, scoreString(customMonster.getIntelligence()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theWis.loadDataWithBaseURL(BASE_URL, scoreString(customMonster.getWisdom()), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theCha.loadDataWithBaseURL(BASE_URL, scoreString(customMonster.getCharisma()), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theSpeed.loadDataWithBaseURL(BASE_URL, titleString("Movement: ", customMonster.getSpeed()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theSaves.loadDataWithBaseURL(BASE_URL, titleString("Saves: ", customMonster.getSaves()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theSkills.loadDataWithBaseURL(BASE_URL, titleString("Skills: ", customMonster.getSkills()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theSenses.loadDataWithBaseURL(BASE_URL, titleString("Senses: ", customMonster.getSenses()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theLanguages.loadDataWithBaseURL(BASE_URL, titleString("Languages: ", customMonster.getLanguages()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theConditionImmunity.loadDataWithBaseURL(BASE_URL, titleString("Condition Immunities: ", customMonster.getConditionImmunity()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDmgVulnerability.loadDataWithBaseURL(BASE_URL, titleString("Damage Vulnerabilities: ", UNKNOWN).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDmgImmunity.loadDataWithBaseURL(BASE_URL, titleString("Damage Immunities: ", customMonster.getDmgImmunity()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theDmgResistance.loadDataWithBaseURL(BASE_URL, titleString("Damage Resistances: ", customMonster.getDmgResistance()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theAbilities.loadDataWithBaseURL(BASE_URL, htmlString(customMonster.getAbilities()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theActions.loadDataWithBaseURL(BASE_URL, htmlString(customMonster.getActions()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theLegendary.loadDataWithBaseURL(BASE_URL, htmlString(customMonster.getLegendaryActions()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);
        this.theOther.loadDataWithBaseURL(BASE_URL, htmlString(customMonster.getOther()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        this.theSource.loadDataWithBaseURL(BASE_URL, htmlString(customMonster.getSource()).toString(), BASE_URL, HISTORY_URL, HISTORY_URL);

        checkVisibilities();


    }


    @Override
    public View createView(LayoutInflater inflater, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.cell_monster_detailed_web, parent);

        this.theName = (WebView) view.findViewById(R.id.theName);
        this.theTypeAndAlign = (WebView) view.findViewById(R.id.theTypeAndAlign);
        this.theAc = (WebView) view.findViewById(R.id.theAc);
        this.theAcType = (WebView) view.findViewById(R.id.theAcType);
        this.theHp = (WebView) view.findViewById(R.id.theHp);
        this.theHd = (WebView) view.findViewById(R.id.theHd);
        this.theSpeed = (WebView) view.findViewById(R.id.theSpeed);
        this.theStr = (WebView) view.findViewById(R.id.theStr);
        this.theDex = (WebView) view.findViewById(R.id.theDex);
        this.theCon = (WebView) view.findViewById(R.id.theCon);
        this.theInt = (WebView) view.findViewById(R.id.theInt);
        this.theWis = (WebView) view.findViewById(R.id.theWis);
        this.theCha = (WebView) view.findViewById(R.id.theCha);
        this.theSaves = (WebView) view.findViewById(R.id.theSaves);
        this.theSkills = (WebView) view.findViewById(R.id.theSkills);

        this.theDmgVulnerability = (WebView) view.findViewById(R.id.theDmgVulnerability);
        this.theDmgResistance = (WebView) view.findViewById(R.id.theDmgResistance);
        this.theDmgImmunity = (WebView) view.findViewById(R.id.theDmgImmunity);
        this.theConditionImmunity = (WebView) view.findViewById(R.id.theConditionImmunity);

        this.theSenses = (WebView) view.findViewById(R.id.theSenses);
        this.theLanguages = (WebView) view.findViewById(R.id.theLanguages);
        this.theCr = (WebView) view.findViewById(R.id.theCr);
        this.theXp = (WebView) view.findViewById(R.id.theXp);
        this.theAbilities = (WebView) view.findViewById(R.id.theAbilities);
        this.theActions = (WebView) view.findViewById(R.id.theActions);
        this.theReactions = (WebView) view.findViewById(R.id.theReactions);
        this.theLegendary = (WebView) view.findViewById(R.id.theLegendary);
        this.theOther = (WebView) view.findViewById(R.id.theOther);
        this.theSource = (WebView)view.findViewById(R.id.theSource);

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
        theAbilities.getUrl();
        theAbilities.setVisibility(isEmpty(theAbilities) ? View.GONE : View.VISIBLE);
        theActionsGroup.setVisibility(isEmpty(theActions) ? View.GONE : View.VISIBLE);
        theReactionsGroup.setVisibility(isEmpty(theReactions) ? View.GONE : View.VISIBLE);
        theLegendaryActionsGroup.setVisibility(isEmpty(theLegendary) ? View.GONE : View.VISIBLE);
        theOther.setVisibility(isEmpty(theOther) ? View.GONE : View.VISIBLE);

        theDmgVulnerabilityGroup.setVisibility(isEmpty(theDmgVulnerability) ? View.GONE : View.VISIBLE);
        theDmgResistanceGroup.setVisibility(isEmpty(theDmgResistance) ? View.GONE : View.VISIBLE);
        theDmgImmunityGroup.setVisibility(isEmpty(theDmgImmunity) ? View.GONE : View.VISIBLE);
        theCondImmunityGroup.setVisibility(isEmpty(theConditionImmunity) ? View.GONE : View.VISIBLE);

        theSavesGroup.setVisibility(isEmpty(theSaves) ? View.GONE : View.VISIBLE);
        theSkillsGroup.setVisibility(isEmpty(theSkills) ? View.GONE : View.VISIBLE);
        theSensesGroup.setVisibility(isEmpty(theSenses) ? View.GONE : View.VISIBLE);

        theSource.setVisibility(isEmpty(theSource) ? View.GONE : View.VISIBLE);
    }

    private static final String scoreString(int score)
    {
        return ScoreTranslater.INSTANCE.toText(score);
    }


}
