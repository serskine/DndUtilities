package com.soupthatisthick.encounterbuilder.view.cell.detail;

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
import com.soupthatisthick.encounterbuilder.view.cell.summary.DefaultCompendiumCell;

/**
 * Created by Owner on 6/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CompendiumDetailCell extends CompendiumCell {

    public CompendiumDetailCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }

    /**
     * This is used to create a detailed view for the object and update it with the details for the object
     * @param category
     * @param inflater
     * @param convertView
     * @param parent
     * @return
     */
    public static final ReadCell<?> createDetailCell(Category category, LayoutInflater inflater, View convertView, ViewGroup parent)
    {
        switch (category)
        {
            case CONDITION:
                ConditionDetailCell conditionDetailedCell = new ConditionDetailCell(inflater, convertView, parent);
                return conditionDetailedCell;
            case MAGIC_ITEM:
                MagicItemDetailCell magicItemDetailCell = new MagicItemDetailCell(inflater, convertView, parent);
                return magicItemDetailCell;
            case SPELL:
                SpellDetailCell spellDetailCell = new SpellDetailCell(inflater, convertView, parent);
                return spellDetailCell;
            case CUSTOM_MONSTER:
                CustomMonsterDetailCell customMonsterDetailCell = new CustomMonsterDetailCell(inflater, convertView, parent);
                return customMonsterDetailCell;
            case STANDARD_MONSTER:
                StandardMonsterDetailCell standardMonsterDetailCell = new StandardMonsterDetailCell(inflater, convertView, parent);
                return standardMonsterDetailCell;
            case FEAT:
                FeatDetailCell featDetailCell = new FeatDetailCell(inflater, convertView, parent);
                return featDetailCell;
            case BACKGROUND:
                BackgroundDetailCell backgroundDetailCell = new BackgroundDetailCell(inflater, convertView, parent);
                return backgroundDetailCell;
            case ARMOR:
                ArmorDetailCell armorDetailCell = new ArmorDetailCell(inflater, convertView, parent);
                return armorDetailCell;
            case WEAPON:
                WeaponDetailCell weaponDetailCell = new WeaponDetailCell(inflater, convertView, parent);
                return weaponDetailCell;
            case EQUIPMENT:
                EquipmentDetailCell equipmentDetailCell = new EquipmentDetailCell(inflater, convertView, parent);
                return equipmentDetailCell;
            case NOTE:
                NoteDetailCell noteDetailCell = new NoteDetailCell(inflater, convertView, parent);
                return noteDetailCell;
            case CHALLENGE_RATING:
                ChallengeRatingDetailCell challengeRatingDetailCell = new ChallengeRatingDetailCell(inflater, convertView, parent);
                return challengeRatingDetailCell;
            case LEVEL:
                LevelDetailCell levelDetailCell = new LevelDetailCell(inflater, convertView, parent);
                return levelDetailCell;
            case GOD:
                GodDetailCell godDetailCell = new GodDetailCell(inflater, convertView, parent);
                return godDetailCell;
            case LIFESTYLE:
                LifestyleDetailCell lifestyleDetailCell = new LifestyleDetailCell(inflater, convertView, parent);
                return lifestyleDetailCell;
            case MOUNT:
                MountDetailCell mountDetailCell = new MountDetailCell(inflater, convertView, parent);
                return mountDetailCell;
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
    protected ReadCell<?> createCell(@NonNull Category category, @NonNull LayoutInflater inflater, @Nullable View convertView, @Nullable ViewGroup parent) {
        return createDetailCell(category, inflater, convertView, parent);
    }

    /**
     * When this method is called, the cell has initialized and the view created within the cell. However the cell will need to be updated
     *
     * @param category determines the type of cell that was created
     * @param cell     is the actual instance of the cell to be updated
     * @param item     is what we want to update the cell ui with
     */
    @Override
    protected void updateCellUi(@NonNull Category category, @NonNull ReadCell<?> cell, @Nullable Object item) {
        switch (category) {
            case CONDITION:
                ((ConditionDetailCell) cell).updateUi((Condition) item);
                break;
            case MAGIC_ITEM:
                ((MagicItemDetailCell) cell).updateUi((MagicItem) item);
                break;
            case SPELL:
                ((SpellDetailCell) cell).updateUi((Spell) item);
                break;
            case CUSTOM_MONSTER:
                ((CustomMonsterDetailCell) cell).updateUi((CustomMonster) item);
                break;
            case STANDARD_MONSTER:
                ((StandardMonsterDetailCell) cell).updateUi((StandardMonster) item);
                break;
            case FEAT:
                ((FeatDetailCell) cell).updateUi((Feat) item);
                break;
            case BACKGROUND:
                ((BackgroundDetailCell) cell).updateUi((Background) item);
                break;
            case ARMOR:
                ((ArmorDetailCell) cell).updateUi((Armor) item);
                break;
            case WEAPON:
                ((WeaponDetailCell) cell).updateUi((Weapon) item);
                break;
            case EQUIPMENT:
                ((EquipmentDetailCell) cell).updateUi((Equipment) item);
                break;
            case NOTE:
                ((NoteDetailCell) cell).updateUi((Note) item);
                break;
            case CHALLENGE_RATING:
                ((ChallengeRatingDetailCell) cell).updateUi((ChallengeRating) item);
                break;
            case LEVEL:
                ((LevelDetailCell) cell).updateUi((Level) item);
                break;
            case GOD:
                ((GodDetailCell) cell).updateUi((God) item);
                break;
            case LIFESTYLE:
                ((LifestyleDetailCell) cell).updateUi((LifeStyle) item);
                break;
            case MOUNT:
                ((MountDetailCell) cell).updateUi((Mount) item);
                break;
            default:
                ((DefaultCompendiumCell) cell).updateUi(item);
                break;
        }
    }
}
