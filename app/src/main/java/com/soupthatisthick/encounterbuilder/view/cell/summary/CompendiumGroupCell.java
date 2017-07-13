package com.soupthatisthick.encounterbuilder.view.cell.summary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.MagicItem;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.view.cell.other.ScrollTextCell;

/**
 * Created by Owner on 4/12/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class CompendiumGroupCell extends ScrollTextCell {
    public CompendiumGroupCell(LayoutInflater inflater, View convertView, ViewGroup parent) {
        super(inflater, convertView, parent);
    }
    public CompendiumGroupCell(LayoutInflater inflater, View convertView, ViewGroup parent, String defaultText) {
        super(inflater, convertView, parent, defaultText);
    }

    public void updateUi(StandardMonster standardMonster)
    {
        super.updateUi(
                String.format(
                "%s (cr %s), %s %s",
                standardMonster.getName(),
                standardMonster.getChallengeRating(),
                standardMonster.getSize(),
                standardMonster.getType()
        ));
    }

    public void updateUi(CustomMonster customMonster)
    {
        super.updateUi(
                String.format(
                "%s (cr %s, %s xp), %s",
                customMonster.getName(),
                customMonster.getCr(),
                customMonster.getXp(),
                customMonster.getType()
        ));
    }

    public void updateUi(Spell spell)
    {
        super.updateUi(
                String.format(
                "%s, %s %s",
                spell.getName(),
                spell.getLevel(),
                spell.getType()
        ));
    }

    public void updateUi(MagicItem magicItem)
    {
        super.updateUi(
                String.format(
                        "%s, %s (%s)",
                        magicItem.getName(),
                        magicItem.getType(),
                        magicItem.getAttunement()
                )
        );
    }


}
