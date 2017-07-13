package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.Armor;
import com.soupthatisthick.encounterbuilder.model.lookup.Background;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.Condition;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Equipment;
import com.soupthatisthick.encounterbuilder.model.lookup.Feat;
import com.soupthatisthick.encounterbuilder.model.lookup.God;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.model.lookup.LifeStyle;
import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.encounterbuilder.model.lookup.Mount;
import com.soupthatisthick.encounterbuilder.model.lookup.Note;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.view.cell.CompendiumCell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;

/**
 * Created by Owner on 6/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CompendiumSummaryCell extends CompendiumCell
{

    public CompendiumSummaryCell(LayoutInflater inflater, View convertView, ViewGroup parent)
    {
        super(inflater, convertView, parent);
    }

    /**
     * This is used to create a detailed view for the object and update it with the details for the object
     *
     * @param category
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    public static final ReadCell<?> createSummaryCell(Category category, LayoutInflater inflater, View convertView, ViewGroup parent) {
        switch (category) {
            case CONDITION:
                ConditionSummaryCell conditionDetailedCell = new ConditionSummaryCell(inflater, convertView, parent);
                return conditionDetailedCell;
            case MAGIC_ITEM:
                MagicItemSummaryCell magicItemSummaryCell = new MagicItemSummaryCell(inflater, convertView, parent);
                return magicItemSummaryCell;
            case SPELL:
                SpellSummaryCell spellSummaryCell = new SpellSummaryCell(inflater, convertView, parent);
                return spellSummaryCell;
            case CUSTOM_MONSTER:
                CustomMonsterSummaryCell customMonsterSummaryCell = new CustomMonsterSummaryCell(inflater, convertView, parent);
                return customMonsterSummaryCell;
            case STANDARD_MONSTER:
                StandardMonsterSummaryCell standardMonsterSummaryCell = new StandardMonsterSummaryCell(inflater, convertView, parent);
                return standardMonsterSummaryCell;
            case FEAT:
                FeatSummaryCell featSummaryCell = new FeatSummaryCell(inflater, convertView, parent);
                return featSummaryCell;
            case BACKGROUND:
                BackgroundSummaryCell backgroundSummaryCell = new BackgroundSummaryCell(inflater, convertView, parent);
                return backgroundSummaryCell;
            case ARMOR:
                ArmorSummaryCell armorSummaryCell = new ArmorSummaryCell(inflater, convertView, parent);
                return armorSummaryCell;
            case WEAPON:
                WeaponSummaryCell weaponSummaryCell = new WeaponSummaryCell(inflater, convertView, parent);
                return weaponSummaryCell;
            case EQUIPMENT:
                EquipmentSummaryCell equipmentSummaryCell = new EquipmentSummaryCell(inflater, convertView, parent);
                return equipmentSummaryCell;
            case NOTE:
                NoteSummaryCell noteSummaryCell = new NoteSummaryCell(inflater, convertView, parent);
                return noteSummaryCell;
            case CHALLENGE_RATING:
                ChallengeRatingSummaryCell challengeRatingSummaryCell = new ChallengeRatingSummaryCell(inflater, convertView, parent);
                return challengeRatingSummaryCell;
            case LEVEL:
                LevelSummaryCell levelSummaryCell = new LevelSummaryCell(inflater, convertView, parent);
                return levelSummaryCell;
            case GOD:
                GodSummaryCell godSummaryCell = new GodSummaryCell(inflater, convertView, parent);
                return godSummaryCell;
            case LIFESTYLE:
                LifestyleSummaryCell lifestyleSummaryCell = new LifestyleSummaryCell(inflater, convertView, parent);
                return lifestyleSummaryCell;
            case MOUNT:
                MountSummaryCell mountSummaryCell = new MountSummaryCell(inflater, convertView, parent);
                return mountSummaryCell;
            default:
                DefaultCompendiumCell defaultCompendiumCell = new DefaultCompendiumCell(inflater, convertView, parent);
                return defaultCompendiumCell;
        }
    }


    /**
     * This method will create a new view for the specified category.
     *
     * @param category
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    protected ReadCell<?> createCell(@NonNull Category category, @NonNull LayoutInflater inflater, @Nullable View convertView, @Nullable ViewGroup parent)
    {
        return createSummaryCell(category, inflater, convertView, parent);
    }

    /**
     * When this method is called, the cell has initialized and the view created within the cell. However the cell will need to be updated
     *
     * @param category determines the type of cell that was created
     * @param cell     is the actual instance of the cell to be updated
     * @param item     is what we want to update the cell ui with
     */
    @Override
    protected void updateCellUi(@NonNull Category category, @NonNull ReadCell<?> cell, @Nullable Object item)
    {

        switch (category) {
            case CONDITION:
                ((ConditionSummaryCell) cell).updateUi((Condition) item);
                break;
            case MAGIC_ITEM:
                ((MagicItemSummaryCell) cell).updateUi((MagicItem) item);
                break;
            case SPELL:
                ((SpellSummaryCell) cell).updateUi((Spell) item);
                break;
            case CUSTOM_MONSTER:
                ((CustomMonsterSummaryCell) cell).updateUi((CustomMonster) item);
                break;
            case STANDARD_MONSTER:
                ((StandardMonsterSummaryCell) cell).updateUi((StandardMonster) item);
                break;
            case FEAT:
                ((FeatSummaryCell) cell).updateUi((Feat) item);
                break;
            case BACKGROUND:
                ((BackgroundSummaryCell) cell).updateUi((Background) item);
                break;
            case ARMOR:
                ((ArmorSummaryCell) cell).updateUi((Armor) item);
                break;
            case WEAPON:
                ((WeaponSummaryCell) cell).updateUi((Weapon) item);
                break;
            case EQUIPMENT:
                ((EquipmentSummaryCell) cell).updateUi((Equipment) item);
                break;
            case NOTE:
                ((NoteSummaryCell) cell).updateUi((Note) item);
                break;
            case CHALLENGE_RATING:
                ((ChallengeRatingSummaryCell) cell).updateUi((ChallengeRating) item);
                break;
            case LEVEL:
                ((LevelSummaryCell) cell).updateUi((Level) item);
                break;
            case GOD:
                ((GodSummaryCell) cell).updateUi((God) item);
                break;
            case LIFESTYLE:
                ((LifestyleSummaryCell) cell).updateUi((LifeStyle) item);
                break;
            case MOUNT:
                ((MountSummaryCell) cell).updateUi((Mount) item);
                break;
            default:
                ((DefaultCompendiumCell) cell).updateUi(item);
                break;
        }
    }
}
