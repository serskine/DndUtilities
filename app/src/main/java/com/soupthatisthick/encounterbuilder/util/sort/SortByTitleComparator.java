package com.soupthatisthick.encounterbuilder.util.sort;

import android.support.annotation.NonNull;

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
import com.soupthatisthick.encounterbuilder.model.lookup.Spell;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.Weapon;

import java.util.Comparator;

/**
 * Created by Owner on 5/27/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SortByTitleComparator implements Comparator<Object> {
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.<p>
     * <p>
     * The implementor must ensure that <tt>sgn(compare(x, y)) ==
     * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>compare(x, y)</tt> must throw an exception if and only
     * if <tt>compare(y, x)</tt> throws an exception.)<p>
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
     * <tt>compare(x, z)&gt;0</tt>.<p>
     * <p>
     * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
     * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
     * <tt>z</tt>.<p>
     * <p>
     * It is generally the case, but <i>not</i> strictly required that
     * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Object o1, Object o2) {
        return getTitle(o1).compareTo(getTitle(o2));
    }

    private static String getTitle(@NonNull Object obj)
    {
        Category category = Category.parse(obj);
        switch(category)
        {
            case CONDITION:
                return ((Condition) obj).getName();
            case CUSTOM_MONSTER:
                return ((CustomMonster) obj).getName();
            case STANDARD_MONSTER:
                return ((StandardMonster) obj).getName();
            case MAGIC_ITEM:
                return ((MagicItem) obj).getName();
            case SPELL:
                return ((Spell) obj).getName();
            case FEAT:
                return ((Feat) obj).getName();
            case BACKGROUND:
                return ((Background) obj).getName();
            case ARMOR:
                return ((Armor) obj).getName();
            case WEAPON:
                return ((Weapon) obj).getName();
            case EQUIPMENT:
                return ((Equipment) obj).getName();
            case NOTE:
                return ((Note) obj).getTitle();
            case CHALLENGE_RATING:
                return ((ChallengeRating) obj).getName();
            case LEVEL:
                return ((Level) obj).getName();
            case GOD:
                return ((God) obj).getName();
            case LIFESTYLE:
                return ((LifeStyle) obj).getName();
            case MOUNT:
                return ((Mount) obj).getName();
            default:
                return obj.toString();
        }
    }
}
