package com.soupthatisthick.encounterbuilder.view.cell.detail;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.util.translater.ScoreTranslater;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

import java.util.Locale;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/30/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class StandardMonsterDetailCell extends ReadCell<StandardMonster> {
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
    
    private static final String SPACE = " ";
    private static final String DELIM = "," + SPACE;
    private static final String EMPTY = "";
    

    public StandardMonsterDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    public void updateUi(StandardMonster monster)
    {
        this.theName.setText(monster.getName());

        theTypeAndAlign.setText(monster.getType() + DELIM + monster.getAlignment());

        this.theCr.setText(titleString("Challenge: ", monster.getChallengeRating()));
        this.theXp.setText(UNKNOWN);

        this.theAc.setText(titleString("Armor Class: ", String.format(Locale.getDefault(), "%s", monster.getArmorClass())));
        this.theAcType.setText(UNKNOWN);

        this.theHp.setText(titleString("Hit Points: ", monster.getHitPoints()));
        this.theHd.setText("(" + monster.getHitDice() + ")");

        this.theStr.setText(scoreString(monster.getStrength()));
        this.theDex.setText(scoreString(monster.getDexterity()));
        this.theCon.setText(scoreString(monster.getConstitution()));
        this.theInt.setText(scoreString(monster.getIntelligence()));
        this.theWis.setText(scoreString(monster.getWisdom()));
        this.theCha.setText(scoreString(monster.getCharisma()));

        this.theSpeed.setText(titleString("Movement: ", monster.getSpeed()));

        updateUiSaves(monster);
        updateUiSkills(monster);

        this.theSenses.setText(titleString("Senses: ", monster.getSenses()));
        this.theLanguages.setText(titleString("Languages: ", monster.getLanguages()));

        this.theConditionImmunity.setText(titleString("Condition Immunities: ", monster.getConditionImmunities()));
        this.theDmgVulnerability.setText(titleString("Damage Vulnerabilities: ", monster.getDamageVulnerabilities()));
        this.theDmgImmunity.setText(titleString("Damage Immunities: ", monster.getDamageImmunities()));
        this.theDmgResistance.setText(titleString("Damage Resistances: ", monster.getDamageResistances()));


        updateUiPowers(monster);

        this.theSource.setText(myHtmlString(monster.getSource()));

        checkVisibilities();

    }

    private String abilityString(int resString, int skillOrSave, int abilityMod) {
        final Resources res = getView().getResources();
        String text = res.getString(resString);
        if (skillOrSave != abilityMod) {
            return text + SPACE + bonusString(skillOrSave);
        } else {
            return EMPTY;
        }
    }

    private void updateUiSaves(StandardMonster monster)
    {
        String savesStr[] = {EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY};



        savesStr[0] = abilityString(R.string.abil_str_abv, monster.getStrengthSave(), monster.getStrengthMod());       //(monster.getStrengthSave() > monster.getStrengthMod()) ? res.getString(R.string.abil_str_abv) + SPACE + bonusString(monster.getStrengthSave()) : EMPTY;
        savesStr[1] = abilityString(R.string.abil_dex_abv, monster.getDexteritySave(), monster.getDexterityMod());      //(monster.getDexteritySave() > monster.getDexterityMod()) ? res.getString(R.string.abil_dex_abv) + SPACE + bonusString(monster.getDexteritySave()) : EMPTY;
        savesStr[2] = abilityString(R.string.abil_con_abv, monster.getConstitutionSave(), monster.getConstitutionMod());   //(monster.getConstitutionSave() > monster.getConstitutionMod()) ? res.getString(R.string.abil_con_abv) + SPACE + bonusString(monster.getConstitutionSave()) : EMPTY;
        savesStr[3] = abilityString(R.string.abil_int_abv, monster.getIntelligenceSave(), monster.getIntelligenceMod());   //(monster.getIntelligenceSave() > monster.getIntelligenceMod()) ? res.getString(R.string.abil_int_abv) + SPACE + bonusString(monster.getIntelligenceSave()) : EMPTY;
        savesStr[4] = abilityString(R.string.abil_wis_abv, monster.getWisdomSave(), monster.getWisdomMod());         //(monster.getWisdomSave() > monster.getWisdomMod()) ? res.getString(R.string.abil_wis_abv) + SPACE + bonusString(monster.getWisdomSave()) : EMPTY;
        savesStr[5] = abilityString(R.string.abil_cha_abv, monster.getCharismaSave(), monster.getCharismaMod());       //(monster.getCharismaSave() > monster.getCharismaMod()) ? res.getString(R.string.abil_cha_abv) + SPACE + bonusString(monster.getCharismaSave()) : EMPTY;

        String savesText = Text.concatNoEmpty(DELIM, savesStr);
        this.theSaves.setText(titleString("Saves: ", savesText));
    }

    private void updateUiSkills(StandardMonster monster)
    {
        String skillsStr[] = {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
        Resources res = getView().getResources();

        skillsStr[0] = abilityString(R.string.skill_acrobatics, monster.getAcrobatics(), monster.getDexterityMod());
        skillsStr[1] = abilityString(R.string.skill_arcana, monster.getArcana(), monster.getIntelligenceMod());
        skillsStr[2] = abilityString(R.string.skill_athletics, monster.getAthletics(), monster.getStrengthMod());
        skillsStr[4] = abilityString(R.string.skill_history, monster.getHistory(), monster.getIntelligenceMod());
        skillsStr[5] = abilityString(R.string.skill_insight, monster.getInsight(), monster.getWisdomMod());
        skillsStr[6] = abilityString(R.string.skill_intimidation, monster.getIntimidation(), monster.getCharismaMod());
        skillsStr[7] = abilityString(R.string.skill_investigation, monster.getInvestigation(), monster.getIntelligenceMod());
        skillsStr[8] = abilityString(R.string.skill_medicine, monster.getMedicine(), monster.getWisdomMod());
        skillsStr[9] = abilityString(R.string.skill_nature, monster.getNature(), monster.getIntelligenceMod());
        skillsStr[10] = abilityString(R.string.skill_perception, monster.getPerception(), monster.getWisdomMod());
        skillsStr[11] = abilityString(R.string.skill_performance, monster.getPerformance(), monster.getCharismaMod());
        skillsStr[12] = abilityString(R.string.skill_persuasion, monster.getPersuasion(), monster.getCharismaMod());
        skillsStr[13] = abilityString(R.string.skill_religion, monster.getReligion(), monster.getIntelligenceMod());
        skillsStr[14] = abilityString(R.string.skill_stealth, monster.getStealth(), monster.getDexterityMod());
        skillsStr[15] = abilityString(R.string.skill_survival, monster.getSurvival(), monster.getWisdomMod());

        String skillsText = Text.concatNoEmpty(DELIM, skillsStr);

        this.theSkills.setText(titleString("Skills: ", skillsText));
    }

    private void updateUiPowers(StandardMonster monster)
    {
        this.theReactions.setText(myHtmlString(monster.getReactions()));
        this.theAbilities.setText(myHtmlString(monster.getSpecialAbilities()));
        this.theActions.setText(myHtmlString(monster.getActions()));
        this.theLegendary.setText(myHtmlString(monster.getLegendaryActions()));
        this.theOther.setText(myHtmlString(UNKNOWN));

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

    private static final String bonusString(int bonus)
    {
        return (bonus>=0) ? "+" + bonus : EMPTY + bonus;
    }

    private static final String bold(String text)
    {
        return "<b>" + text + "</b>";
    }

    public static final String myHtmlString(String html)
    {
        String parsed = htmlString(html).toString();
//        String removeLeading = "\\s*<html>(\\s*<br>)*\\s*";
//        String removeTrailing = "\\s*<br>*\\s*</html>\\s*";
//        String removeListElements = "(<li>)|(</li>)";
//        String removeMultiLineBreaks = "<br>(\\s*<br>)*";
//        parsed = parsed.replace(removeLeading,EMPTY);
//        parsed = parsed.replace(removeTrailing, EMPTY);
//        parsed = parsed.replace(removeListElements, EMPTY);
//        parsed = parsed.replace(removeMultiLineBreaks, "<br>");

        return parsed;
    }
    
}
