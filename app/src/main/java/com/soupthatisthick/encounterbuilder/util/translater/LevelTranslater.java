package com.soupthatisthick.encounterbuilder.util.translater;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.soupthatisthick.encounterbuilder.dao.lookup.LevelDao;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.util.Expression;

import java.io.IOException;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class LevelTranslater {

    protected final LevelDao levelDao;

    public LevelTranslater(@NonNull LevelDao levelDao)
    {
        this.levelDao = levelDao;
    }

    public final  Level getLevelOfAlly(@NonNull Object ally) throws IOException {
        if (ally instanceof Level)
        {
            return (Level) ally;
        } else if (ally instanceof Double) {
            return getDoubleLevel((Double) ally);
        } else if (ally instanceof String) {
            return getStringLevel((String) ally);
        } else {
            throw new RuntimeException("Could not determine the level for ally");
        }
    }

    public final  Level getStringLevel(@NonNull String str) throws IOException {
        Double value = Expression.eval(str);
        return getDoubleLevel(value);
    }

    public final  Level getDoubleLevel(@NonNull Double value) throws IOException {
        return levelDao.findWithValue(value);
    }


}
