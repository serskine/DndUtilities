package com.soupthatisthick.encounterbuilder.activity.lookup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.soupthatisthick.encounterbuilder.dao.lookup.CustomMonsterDao;
import com.soupthatisthick.util.activity.DaoEditActivity;
import com.soupthatisthick.util.dao.DaoMaster;
import com.soupthatisthick.util.dao.WriteDao;
import com.soupthatisthick.encounterbuilder.dao.master.DndMaster;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.translater.ScoreTranslater;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.util.view.ViewUtil;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 3/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EditCustomMonsterActivity extends DaoEditActivity<CustomMonster> {

    private EditText theName, theTypeAndAlign, theSource, theAc, theAcType;
    private EditText theHp, theHd, theSaves, theSkills, theSpeed;
    private EditText theStr, theDex, theCon, theInt, theWis, theCha;
    private TextView theStrMod, theDexMod, theConMod, theIntMod, theWisMod, theChaMod;
    private EditText theDmgResistance, theDmgImmunity, theConditionImmunity;
    private EditText theSenses, theLanguages, theCr, theXp;
    private EditText theAbilities, theActions, theLegendary, theOther;

    @Override
    protected void initModelWithoutUi() {
        theName = (EditText) findViewById(R.id.theName);
        theTypeAndAlign = (EditText) findViewById(R.id.theTypeAndAlign);
        theSource = (EditText) findViewById(R.id.theSource);
        theAc = (EditText) findViewById(R.id.theAc);
        theAcType = (EditText) findViewById(R.id.theAcType);
        theHp = (EditText) findViewById(R.id.theHp);
        theHd = (EditText) findViewById(R.id.theHd);
        theSaves = (EditText) findViewById(R.id.theSaves);
        theSkills = (EditText) findViewById(R.id.theSkills);
        theSpeed = (EditText) findViewById(R.id.theSpeed);

        theStr = (EditText) findViewById(R.id.theStr);
        theDex = (EditText) findViewById(R.id.theDex);
        theCon = (EditText) findViewById(R.id.theCon);
        theInt = (EditText) findViewById(R.id.theInt);
        theWis = (EditText) findViewById(R.id.theWis);
        theCha = (EditText) findViewById(R.id.theCha);

        theStrMod = (TextView) findViewById(R.id.theStrMod);
        theDexMod = (TextView) findViewById(R.id.theDexMod);
        theConMod = (TextView) findViewById(R.id.theConMod);
        theIntMod = (TextView) findViewById(R.id.theIntMod);
        theWisMod = (TextView) findViewById(R.id.theWisMod);
        theChaMod = (TextView) findViewById(R.id.theChaMod);


        theDmgResistance = (EditText) findViewById(R.id.theDmgResistance);
        theDmgImmunity = (EditText) findViewById(R.id.theDmgImmunity);
        theConditionImmunity = (EditText) findViewById(R.id.theConditionImmunity);
        theSenses = (EditText) findViewById(R.id.theSenses);
        theLanguages = (EditText) findViewById(R.id.theLanguages);
        theCr = (EditText) findViewById(R.id.theCr);
        theXp = (EditText) findViewById(R.id.theXp);
        theAbilities = (EditText) findViewById(R.id.theAbilities);
        theActions = (EditText) findViewById(R.id.theActions);
        theLegendary = (EditText) findViewById(R.id.theLegendary);
        theOther = (EditText) findViewById(R.id.theOther);


    }

    @Override
    protected void listenToUi() {
        uiWatcher.listenTo(        theName);
        uiWatcher.listenTo(        theTypeAndAlign);
        uiWatcher.listenTo(        theSource);
        uiWatcher.listenTo(        theAc);
        uiWatcher.listenTo(        theHp);
        uiWatcher.listenTo(        theHd);
        uiWatcher.listenTo(        theSaves);
        uiWatcher.listenTo(        theSkills);
        uiWatcher.listenTo(        theSpeed);
        uiWatcher.listenTo(        theStr);
        uiWatcher.listenTo(        theDex);
        uiWatcher.listenTo(        theCon);
        uiWatcher.listenTo(        theInt);
        uiWatcher.listenTo(        theWis);
        uiWatcher.listenTo(        theCha);
        uiWatcher.listenTo(        theDmgResistance);
        uiWatcher.listenTo(        theDmgImmunity);
        uiWatcher.listenTo(        theConditionImmunity);
        uiWatcher.listenTo(        theSenses);
        uiWatcher.listenTo(        theLanguages);
        uiWatcher.listenTo(        theCr);
        uiWatcher.listenTo(        theXp);
        uiWatcher.listenTo(        theAbilities);
        uiWatcher.listenTo(        theActions);
        uiWatcher.listenTo(        theLegendary);
        uiWatcher.listenTo(        theOther);
    }

    @Override
    protected void ignoreUi() {
        uiWatcher.ignore(        theName);
        uiWatcher.ignore(        theTypeAndAlign);
        uiWatcher.ignore(        theSource);
        uiWatcher.ignore(        theAc);
        uiWatcher.ignore(        theHp);
        uiWatcher.ignore(        theHd);
        uiWatcher.ignore(        theSaves);
        uiWatcher.ignore(        theSkills);
        uiWatcher.ignore(        theSpeed);
        uiWatcher.ignore(        theStr);
        uiWatcher.ignore(        theDex);
        uiWatcher.ignore(        theCon);
        uiWatcher.ignore(        theInt);
        uiWatcher.ignore(        theWis);
        uiWatcher.ignore(        theCha);
        uiWatcher.ignore(        theDmgResistance);
        uiWatcher.ignore(        theDmgImmunity);
        uiWatcher.ignore(        theConditionImmunity);
        uiWatcher.ignore(        theSenses);
        uiWatcher.ignore(        theLanguages);
        uiWatcher.ignore(        theCr);
        uiWatcher.ignore(        theXp);
        uiWatcher.ignore(        theAbilities);
        uiWatcher.ignore(        theActions);
        uiWatcher.ignore(        theLegendary);
        uiWatcher.ignore(        theOther);
    }


    @Override
    protected void updateUiFromModel() {
        try {
            ViewUtil.setText(theName,model.getName());

            String typeAndAlign = model.getType() + ", " + model.getAlignment();
            ViewUtil.setText(theTypeAndAlign,typeAndAlign);

            ViewUtil.setText(theSource,model.getSource());

            ViewUtil.setText(theAc,Integer.toString(model.getAc()));
            ViewUtil.setText(theAcType,model.getAcType());

            ViewUtil.setText(theHp,model.getHp());
            ViewUtil.setText(theHd,model.getHd());
            ViewUtil.setText(theSaves,model.getSaves());
            ViewUtil.setText(theSkills,model.getSkills());
            ViewUtil.setText(theSpeed,model.getSpeed());

            ViewUtil.setText(theStr,Integer.toString(model.getStrength()));
            ViewUtil.setText(theDex,Integer.toString(model.getDexterity()));
            ViewUtil.setText(theCon,Integer.toString(model.getConstitution()));
            ViewUtil.setText(theInt,Integer.toString(model.getIntelligence()));
            ViewUtil.setText(theWis,Integer.toString(model.getWisdom()));
            ViewUtil.setText(theCha,Integer.toString(model.getCharisma()));

            // Make certain the modifier view matcher the model
            ViewUtil.setText(theStrMod,"(" + ScoreTranslater.INSTANCE.getMod(model.getStrength()) + ")");
            ViewUtil.setText(theDexMod,"(" + ScoreTranslater.INSTANCE.getMod(model.getDexterity()) + ")");
            ViewUtil.setText(theConMod,"(" + ScoreTranslater.INSTANCE.getMod(model.getConstitution()) + ")");
            ViewUtil.setText(theIntMod,"(" + ScoreTranslater.INSTANCE.getMod(model.getIntelligence()) + ")");
            ViewUtil.setText(theWisMod,"(" + ScoreTranslater.INSTANCE.getMod(model.getWisdom()) + ")");
            ViewUtil.setText(theChaMod,"(" + ScoreTranslater.INSTANCE.getMod(model.getCharisma()) + ")");

            ViewUtil.setText(theDmgResistance,model.getDmgResistance());
            ViewUtil.setText(theDmgImmunity,model.getDmgImmunity());
            ViewUtil.setText(theConditionImmunity,model.getConditionImmunity());
            ViewUtil.setText(theSenses,model.getSenses());
            ViewUtil.setText(theLanguages,model.getLanguages());
            ViewUtil.setText(theCr,model.getCr());
            ViewUtil.setText(theXp, Integer.toString(model.getXp()));

            ViewUtil.setText(theAbilities,model.getAbilities());
            ViewUtil.setText(theActions,model.getActions());
            ViewUtil.setText(theLegendary,model.getLegendaryActions());

            ViewUtil.setText(theOther,model.getOther());
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }


    }

    @Override
    protected void updateModelFromUi() {
        try {
            model.setName(theName.getText().toString());
            String theTypeValue = null;
            String theAlignmentValue = null;
            model.setType(theTypeValue);

            model.setAlignment(theAlignmentValue);

            model.setSource(theSource.getText().toString());

            int ac = getIntValue(theAc.getText().toString(), model.getAc());

            model.setAc(ac);
            model.setAcType(theAcType.getText().toString());

            model.setHp(theHp.getText().toString());
            model.setHd(theHd.getText().toString());
            model.setSaves(theSaves.getText().toString());
            model.setSkills(theSkills.getText().toString());
            model.setSpeed(theSpeed.getText().toString());

            int strength = getIntValue(theStr.getText().toString(), model.getStrength());
            int dexterity = getIntValue(theDex.getText().toString(), model.getDexterity());
            int constitution = getIntValue(theCon.getText().toString(), model.getConstitution());
            int intelligence = getIntValue(theInt.getText().toString(), model.getIntelligence());
            int wisdom = getIntValue(theWis.getText().toString(), model.getWisdom());
            int charisma = getIntValue(theCha.getText().toString(), model.getCharisma());

            model.setStrength(strength);
            model.setDexterity(dexterity);
            model.setConstitution(constitution);
            model.setIntelligence(intelligence);
            model.setWisdom(wisdom);
            model.setCharisma(charisma);

            // Make certain the modifier view matcher the model
            theStrMod.setText("(" + ScoreTranslater.INSTANCE.getMod(model.getStrength()) + ")");
            theDexMod.setText("(" + ScoreTranslater.INSTANCE.getMod(model.getDexterity()) + ")");
            theConMod.setText("(" + ScoreTranslater.INSTANCE.getMod(model.getConstitution()) + ")");
            theIntMod.setText("(" + ScoreTranslater.INSTANCE.getMod(model.getIntelligence()) + ")");
            theWisMod.setText("(" + ScoreTranslater.INSTANCE.getMod(model.getWisdom()) + ")");
            theChaMod.setText("(" + ScoreTranslater.INSTANCE.getMod(model.getCharisma()) + ")");

            model.setDmgResistance(theDmgResistance.getText().toString());
            model.setDmgImmunity(theDmgImmunity.getText().toString());
            model.setConditionImmunity(theConditionImmunity.getText().toString());
            model.setSenses(theSenses.getText().toString());
            model.setLanguages(theLanguages.getText().toString());
            model.setCr(theCr.getText().toString());

            int xp = getIntValue(theXp.getText().toString(), model.getXp());
            model.setXp(xp);

            model.setAbilities(theAbilities.getText().toString());
            model.setActions(theActions.getText().toString());
            model.setLegendaryActions(theLegendary.getText().toString());

            model.setOther(theOther.getText().toString());
        } catch (Exception e) {
            Logger.error(e.getMessage(), e);
        }


    }

    private static final int getIntValue(@Nullable String text, int prevValue)
    {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            Logger.warning("Failed to parse ability score value " + Text.quote(text));
            return prevValue;
        }
    }

    @Override
    protected View findSaveButton() {
        return findViewById(R.id.em_save_button);
    }

    @Override
    protected View findDeleteButton() {
        return findViewById(R.id.em_delete_button);
    }

    @Override
    protected View findCancelButton() {
        return findViewById(R.id.em_cancel_button);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_monster;
    }

    @Override
    protected int getDeleteTitleStringId() {
        return R.string.em_delete_title;
    }

    @Override
    protected int getDeleteMessageStringId() {
        return R.string.em_delete_message;
    }

    @Override
    protected DaoMaster createDaoMaster(Context context) throws Exception {
        return new DndMaster(context);
    }

    @Override
    protected WriteDao createWriteDao(DaoMaster daoMaster) throws Exception {
        return new CustomMonsterDao(daoMaster);
    }

}
