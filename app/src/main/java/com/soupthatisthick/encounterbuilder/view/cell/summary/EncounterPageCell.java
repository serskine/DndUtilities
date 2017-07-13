package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.printing.PageBounds;
import com.soupthatisthick.encounterbuilder.printing.bounds.EncounterPageBounds;
import com.soupthatisthick.encounterbuilder.printing.model.EncounterPage;
import com.soupthatisthick.encounterbuilder.util.Text;
import com.soupthatisthick.encounterbuilder.util.view.Draw;
import com.soupthatisthick.encounterbuilder.view.cell.PageCell;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 4/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class EncounterPageCell extends PageCell<EncounterPage> {

    /**
     * The default constructor for the PageCell
     *
     * @param inflater
     * @param convertView
     * @param parent
     */
    public EncounterPageCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This will paint the fields onto the provided bitmap.
     *
     * @param canvas is the canvas we use to perform drawing on the bitmap.
     * @param page
     * @param bounds will provide all the boundaries of the fields/
     */
    @Override
    protected void paintFields(Canvas canvas, EncounterPage page, PageBounds bounds) {
        if (bounds instanceof EncounterPageBounds)
        {
            EncounterPageBounds eBounds = (EncounterPageBounds) bounds;
            Paint paint = new Paint(Color.BLACK);

            for(EncounterPageBounds.Field field : EncounterPageBounds.Field.values())
            {
                switch(field)
                {

                    case PAGE:
                        break;
                    case ENCOUNTER_NAME:
                        Draw.write(canvas, field.bounds, paint, page.name);
                        break;
                    case DIFFICULTY:
                        Draw.write(canvas, field.bounds, paint, page.difficulty);
                        break;
                    case XP_VALUE:
                        Draw.write(canvas, field.bounds, paint, ""+page.xpValue);
                        break;
                    case LOCATION:
                        Draw.write(canvas, field.bounds, paint, page.location);
                        break;
                    case INIT_NEG_5:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(-5)));
                        break;
                    case INIT_NEG_4:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(-4)));
                        break;
                    case INIT_NEG_3:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(-3)));
                        break;
                    case INIT_NEG_2:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(-2)));
                        break;
                    case INIT_NEG_1:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(-1)));
                        break;
                    case INIT_ZERO:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(0)));
                        break;
                    case INIT_1:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(1)));
                        break;
                    case INIT_2:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(2)));
                        break;
                    case INIT_3:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(3)));
                        break;
                    case INIT_4:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(4)));
                        break;
                    case INIT_5:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(5)));
                        break;
                    case INIT_6:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(6)));
                        break;
                    case INIT_7:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(7)));
                        break;
                    case INIT_8:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(8)));
                        break;
                    case INIT_9:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(9)));
                        break;
                    case INIT_10:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(10)));
                        break;
                    case INIT_11:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(11)));
                        break;
                    case INIT_12:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(12)));
                        break;
                    case INIT_13:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(13)));
                        break;
                    case INIT_14:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(14)));
                        break;
                    case INIT_15:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(15)));
                        break;
                    case INIT_16:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(16)));
                        break;
                    case INIT_17:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(17)));
                        break;
                    case INIT_18:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(18)));
                        break;
                    case INIT_19:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(19)));
                        break;
                    case INIT_20:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(20)));
                        break;
                    case INIT_21:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(21)));
                        break;
                    case INIT_22:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(22)));
                        break;
                    case INIT_23:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(23)));
                        break;
                    case INIT_24:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(24)));
                        break;
                    case INIT_25:
                        Draw.write(canvas,field.bounds,paint,Text.concat(",", page.getMonstersAtInit(25)));
                        break;
                    case NOTES:
                        Draw.write(canvas,field.bounds,paint,page.notes);
                        break;

                    //
                    // MONSTER 1
                    //
                    case MONSTER_1_NAME:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getName()));
                        break;
                    case MONSTER_1_STR_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getStrength()));
                        break;
                    case MONSTER_1_DEX_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterity()));
                        break;
                    case MONSTER_1_CON_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getConstitution()));
                        break;
                    case MONSTER_1_INT_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getIntelligence()));
                        break;
                    case MONSTER_1_WIS_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getWisdom()));
                        break;
                    case MONSTER_1_CHA_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getCharisma()));
                        break;
                    case MONSTER_1_STR_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getStrengthMod()));
                        break;
                    case MONSTER_1_DEX_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterityMod()));
                        break;
                    case MONSTER_1_CON_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getConstitutionMod()));
                        break;
                    case MONSTER_1_INT_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getIntelligenceMod()));
                        break;
                    case MONSTER_1_WIS_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getWisdomMod()));
                        break;
                    case MONSTER_1_CHA_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getCharismaMod()));
                        break;
                    case MONSTER_1_INIT:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterityMod()));
                        break;
                    case MONSTER_1_SPEED:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getSpeed()));
                        break;
                    case MONSTER_1_AC:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getArmorClass()));
                        break;

                    // TODO: Allow for multiple copies of the same type of monster on a single page
//                    case MONSTER_1_HP_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_1_HP_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_1_HP_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_1_HP_4:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_1_HP_5:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_1_HP_6:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;


                    // TODO: Update the monster stat block with the most popular attacks for the monster
//                    case MONSTER_1_ATTACK_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_ATTACK_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_ATTACK_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_ATK_BONUS_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_ATK_BONUS_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_ATK_BONUS_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_DAMAGE_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_DAMAGE_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_1_DAMAGE_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;

                    case MONSTER_1_ATK_AND_SPELL:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" :
                                Text.concat("\n",
                                        "" + page.monsters[0].getReactions(),
                                        "" + page.monsters[0].getActions(),
                                        "" + page.monsters[0].getLegendaryActions(),
                                        ""
                                )
                        ));
                        break;
                    case MONSTER_1_FEATURES_AND_SKILLS:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" :
                            Text.concat("\n",
                                    "" + page.monsters[0].getConditionImmunities(),
                                    "" + page.monsters[0].getDamageImmunities(),
                                    "" + page.monsters[0].getDamageResistances(),
                                    "" + page.monsters[0].getDamageVulnerabilities(),
                                    ""
                            )
                        ));
                        break;



                    //
                    // MONSTER 2
                    //
                    case MONSTER_2_NAME:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getName()));
                        break;
                    case MONSTER_2_STR_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getStrength()));
                        break;
                    case MONSTER_2_DEX_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterity()));
                        break;
                    case MONSTER_2_CON_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getConstitution()));
                        break;
                    case MONSTER_2_INT_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getIntelligence()));
                        break;
                    case MONSTER_2_WIS_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getWisdom()));
                        break;
                    case MONSTER_2_CHA_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getCharisma()));
                        break;
                    case MONSTER_2_STR_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getStrengthMod()));
                        break;
                    case MONSTER_2_DEX_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterityMod()));
                        break;
                    case MONSTER_2_CON_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getConstitutionMod()));
                        break;
                    case MONSTER_2_INT_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getIntelligenceMod()));
                        break;
                    case MONSTER_2_WIS_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getWisdomMod()));
                        break;
                    case MONSTER_2_CHA_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getCharismaMod()));
                        break;
                    case MONSTER_2_INIT:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterityMod()));
                        break;
                    case MONSTER_2_SPEED:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getSpeed()));
                        break;
                    case MONSTER_2_AC:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getArmorClass()));
                        break;

                    // TODO: Allow for multiple copies of the same type of monster on a single page
//                    case MONSTER_2_HP_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_2_HP_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_2_HP_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_2_HP_4:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_2_HP_5:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_2_HP_6:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;


                    // TODO: Update the monster stat block with the most popular attacks for the monster
//                    case MONSTER_2_ATTACK_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_ATTACK_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_ATTACK_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_ATK_BONUS_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_ATK_BONUS_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_ATK_BONUS_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_DAMAGE_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_DAMAGE_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_2_DAMAGE_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;

                    case MONSTER_2_ATK_AND_SPELL:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" :
                                Text.concat("\n",
                                        "" + page.monsters[0].getReactions(),
                                        "" + page.monsters[0].getActions(),
                                        "" + page.monsters[0].getLegendaryActions(),
                                        ""
                                )
                        ));
                        break;
                    case MONSTER_2_FEATURES_AND_SKILLS:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" :
                                Text.concat("\n",
                                        "" + page.monsters[0].getConditionImmunities(),
                                        "" + page.monsters[0].getDamageImmunities(),
                                        "" + page.monsters[0].getDamageResistances(),
                                        "" + page.monsters[0].getDamageVulnerabilities(),
                                        ""
                                )
                        ));
                        break;


                    //
                    // MONSTER 3
                    //
                    case MONSTER_3_NAME:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getName()));
                        break;
                    case MONSTER_3_STR_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getStrength()));
                        break;
                    case MONSTER_3_DEX_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterity()));
                        break;
                    case MONSTER_3_CON_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getConstitution()));
                        break;
                    case MONSTER_3_INT_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getIntelligence()));
                        break;
                    case MONSTER_3_WIS_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getWisdom()));
                        break;
                    case MONSTER_3_CHA_SCORE:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getCharisma()));
                        break;
                    case MONSTER_3_STR_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getStrengthMod()));
                        break;
                    case MONSTER_3_DEX_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterityMod()));
                        break;
                    case MONSTER_3_CON_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getConstitutionMod()));
                        break;
                    case MONSTER_3_INT_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getIntelligenceMod()));
                        break;
                    case MONSTER_3_WIS_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getWisdomMod()));
                        break;
                    case MONSTER_3_CHA_MOD:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getCharismaMod()));
                        break;
                    case MONSTER_3_INIT:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getDexterityMod()));
                        break;
                    case MONSTER_3_SPEED:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getSpeed()));
                        break;
                    case MONSTER_3_AC:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getArmorClass()));
                        break;

                    // TODO: Allow for multiple copies of the same type of monster on a single page
//                    case MONSTER_3_HP_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_3_HP_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_3_HP_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_3_HP_4:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_3_HP_5:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;
//                    case MONSTER_3_HP_6:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getHitPoints()));
//                        break;


                    // TODO: Update the monster stat block with the most popular attacks for the monster
//                    case MONSTER_3_ATTACK_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_ATTACK_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_ATTACK_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_ATK_BONUS_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_ATK_BONUS_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_ATK_BONUS_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_DAMAGE_1:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_DAMAGE_2:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;
//                    case MONSTER_3_DAMAGE_3:
//                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" : "" + page.monsters[0].getClassName()));
//                        break;

                    case MONSTER_3_ATK_AND_SPELL:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" :
                                Text.concat("\n",
                                        "" + page.monsters[0].getReactions(),
                                        "" + page.monsters[0].getActions(),
                                        "" + page.monsters[0].getLegendaryActions(),
                                        ""
                                )
                        ));
                        break;
                    case MONSTER_3_FEATURES_AND_SKILLS:
                        Draw.write(canvas,field.bounds,paint,((page.monsters[0]==null) ? "" :
                                Text.concat("\n",
                                        "" + page.monsters[0].getConditionImmunities(),
                                        "" + page.monsters[0].getDamageImmunities(),
                                        "" + page.monsters[0].getDamageResistances(),
                                        "" + page.monsters[0].getDamageVulnerabilities(),
                                        ""
                                )
                        ));
                        break;


                }
            }

        }
    }

    /**
     * @return the integer resource id of the background image we want to display before painting the field values.
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.encounter_sheet;
    }

    /**
     * @return a new {@link PageBounds} object that will provide all the fields, and boundaries for those fields on the page
     */
    @Override
    public PageBounds createPageBounds() {
        return new EncounterPageBounds();
    }
}
