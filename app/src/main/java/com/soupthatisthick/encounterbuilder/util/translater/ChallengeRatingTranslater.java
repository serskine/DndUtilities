package com.soupthatisthick.encounterbuilder.util.translater;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.dao.lookup.ChallengeRatingDao;
import com.soupthatisthick.encounterbuilder.model.lookup.ChallengeRating;
import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Level;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Expression;

import java.io.IOException;

/**
 * Created by Owner on 6/22/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class ChallengeRatingTranslater {

    protected final ChallengeRatingDao crDao;

    public ChallengeRatingTranslater(@NonNull ChallengeRatingDao crDao)
    {
        this.crDao = crDao;
    }

    public final ChallengeRating getChallengeOfEnemy(@NonNull Object enemy) throws IOException {
        if (enemy instanceof ChallengeRating)
        {
            return (ChallengeRating) enemy;
        } else if (enemy instanceof StandardMonster) {
            return getStandardMonsterChallengeRating((StandardMonster) enemy);
        } else if (enemy instanceof CustomMonster) {
            return getCustomMonsterChallengeRating((CustomMonster) enemy);
        } else if (enemy instanceof String) {
            return getStringChallengeRating((String) enemy);
        } else if (enemy instanceof Double) {
            return getDoubleChallengeRating((Double) enemy);
        } else {
            throw new RuntimeException("Failed to determine ChallengeRating of enemy");
        }
    }

    /**
     * Determines the challenge rating of the given standard monster
     * @param monster
     * @return
     */
    public final  ChallengeRating getStandardMonsterChallengeRating(StandardMonster monster) throws IOException {
        return getStringChallengeRating(monster.getChallengeRating());
    }

    public final  ChallengeRating getCustomMonsterChallengeRating(CustomMonster monster) throws IOException {
        return getStringChallengeRating(monster.getCr());
    }

    public final  ChallengeRating getStringChallengeRating(String str) throws IOException {
        Double crValue = Expression.eval(str);
        return getDoubleChallengeRating(crValue);
    }

    public final  ChallengeRating getDoubleChallengeRating(@NonNull Double value) throws IOException
    {
        return crDao.findWithValue(value);
    }

}
