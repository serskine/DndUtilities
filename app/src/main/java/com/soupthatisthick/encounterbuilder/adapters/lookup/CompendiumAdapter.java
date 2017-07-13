package com.soupthatisthick.encounterbuilder.adapters.lookup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.Armor;
import com.soupthatisthick.encounterbuilder.model.lookup.Background;
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
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;
import com.soupthatisthick.encounterbuilder.util.adapter.CustomToggleAdapter;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.encounterbuilder.view.cell.CompendiumCell;
import com.soupthatisthick.encounterbuilder.view.cell.ReadCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.ArmorDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.BackgroundDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.ChallengeRatingDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.CompendiumDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.ConditionDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.EquipmentDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.FeatDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.GodDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.LevelDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.LifestyleDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.MagicItemDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.MountDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.NoteDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.StandardMonsterDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.WeaponDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.ArmorSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.BackgroundSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.ChallengeRatingSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.CompendiumSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.ConditionSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.CustomMonsterSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.DefaultCompendiumCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.CustomMonsterDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.detail.SpellDetailCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.EquipmentSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.FeatSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.GodSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.LevelSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.LifestyleSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.MagicItemSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.MountSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.NoteSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.SpellSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.StandardMonsterSummaryCell;
import com.soupthatisthick.encounterbuilder.view.cell.summary.WeaponSummaryCell;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CompendiumAdapter extends CustomToggleAdapter<Object> {
    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getCollapsedView(int position, View convertView, ViewGroup parent) {
        Object item = getItem(position);
        CompendiumSummaryCell cell = new CompendiumSummaryCell(inflater, convertView, parent);
        cell.updateUi(item);
        return cell.getView();
    }

    @Override
    public View getExpandedView(int position, View convertView, ViewGroup parent) {
        Object item = getItem(position);
        CompendiumDetailCell cell = new CompendiumDetailCell(inflater, convertView, parent);
        cell.updateUi(item);
        return cell.getView();
    }


    @Override
    protected int getViewType(int position, boolean isExpanded) {
        return 0;
    }

    public CompendiumAdapter(LayoutInflater inflater) {
        super(inflater);
    }

}
