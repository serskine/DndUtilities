package com.soupthatisthick.encounterbuilder.util.transform;

import com.soupthatisthick.encounterbuilder.model.lookup.CustomMonster;
import com.soupthatisthick.encounterbuilder.model.lookup.StandardMonster;
import com.soupthatisthick.util.Logger;

public class StandardToCustomMonsterAdapter implements ConversionAdapter<StandardMonster, CustomMonster> {
    @Override
    public void apply(final StandardMonster standardMonster, final CustomMonster customMonster) {
        updateArmorClassValues(standardMonster, customMonster);
        updateType(standardMonster, customMonster);
        updateNameSpeedAlignmentHealthSource(standardMonster, customMonster);
        updateAbilityScores(standardMonster, customMonster);
        updateSavingThrows(standardMonster, customMonster);
        updateSkills(standardMonster, customMonster);
        updateOtherProficiencies(standardMonster, customMonster);
        updateResistancesImmunitiesAndVulnerabilities(standardMonster, customMonster);
        updateExperienceInfo(standardMonster, customMonster);
        updateActionsAndAbilities(standardMonster, customMonster);
    }

    private void updateSavingThrows(final StandardMonster standardMonster, final CustomMonster customMonster) {
        final StringBuilder sb = new StringBuilder();
        final String saves[] = {
                profHtml("Strength", standardMonster.getStrengthSave(), standardMonster.getStrengthMod()),
                profHtml("Dexterity", standardMonster.getDexteritySave(), standardMonster.getDexterityMod()),
                profHtml("Constitution", standardMonster.getConstitutionSave(), standardMonster.getConstitutionMod()),
                profHtml("Intelligence", standardMonster.getIntelligenceSave(), standardMonster.getIntelligenceMod()),
                profHtml("Wisdom", standardMonster.getWisdomSave(), standardMonster.getWisdomMod()),
                profHtml("Charisma", standardMonster.getCharismaSave(), standardMonster.getCharismaMod()),
        };

        // Append only the skills that provide different bonuses than their ability modifier.
        // Those that don't will be empty strings of length 0 (not null)
        boolean isFirst = true;
        final String delim = ", ";

        for(String save : saves) {
            if (save.length()>0) {
                if (!isFirst) {
                    sb.append(delim);
                }
                sb.append(save);
                isFirst = false;
            }
        }

        customMonster.setSaves(sb.toString());

    }

    private void updateOtherProficiencies(final StandardMonster standardMonster, final CustomMonster customMonster) {
        customMonster.setLanguages(standardMonster.getLanguages());
    }

    private String profHtml(String title, int observedValue, int abilityMod) {
        if (abilityMod == observedValue) {
            return "";
        } else {
            return "<b>" + title + ":</b> " + intText(observedValue);
        }
    }

    private void updateSkills(final StandardMonster standardMonster, final CustomMonster customMonster) {
        final StringBuilder sb = new StringBuilder();
        final String skills[] = {
            profHtml("Acrobatics",      standardMonster.getAcrobatics(),    standardMonster.getDexterityMod()),
            profHtml("Arcana",          standardMonster.getArcana(),        standardMonster.getIntelligenceMod()),
            profHtml("Athletics",       standardMonster.getAthletics(),     standardMonster.getStrengthMod()),
            profHtml("Deception",       standardMonster.getDeception(),     standardMonster.getCharismaMod()),
            profHtml("History",         standardMonster.getHistory(),       standardMonster.getIntelligenceMod()),
            profHtml("Insight",         standardMonster.getInsight(),       standardMonster.getWisdomMod()),
            profHtml("Intimidation",    standardMonster.getIntimidation(),  standardMonster.getCharismaMod()),
            profHtml("Investigation",   standardMonster.getInvestigation(), standardMonster.getIntelligenceMod()),
            profHtml("Medicine",        standardMonster.getMedicine(),      standardMonster.getWisdomMod()),
            profHtml("Nature",          standardMonster.getNature(),        standardMonster.getIntelligenceMod()),
            profHtml("Performance",     standardMonster.getPerformance(),   standardMonster.getCharismaMod()),
            profHtml("Perception",      standardMonster.getPerception(),    standardMonster.getWisdomMod()),
            profHtml("Persuasion",      standardMonster.getPersuasion(),    standardMonster.getCharismaMod()),
            profHtml("Religion",        standardMonster.getReligion(),      standardMonster.getIntelligenceMod()),
            profHtml("Stealth",         standardMonster.getStealth(),       standardMonster.getDexterityMod()),
            profHtml("Survival",        standardMonster.getSurvival(),      standardMonster.getWisdomMod())
        };

        // Append only the skills that provide different bonuses than their ability modifier.
        // Those that don't will be empty strings of length 0 (not null)
        boolean isFirst = true;
        final String delim = ", ";

        for(String skill : skills) {
            if (skill.length()>0) {
                if (!isFirst) {
                    sb.append(delim);
                }
                sb.append(skill);
                isFirst = false;
            }
        }

        customMonster.setSkills(sb.toString());
    }

    private void updateActionsAndAbilities(final StandardMonster standardMonster, final CustomMonster customMonster) {
        customMonster.setActions(standardMonster.getActions());
        customMonster.setLegendaryActions(standardMonster.getLegendaryActions());
        customMonster.setAbilities(standardMonster.getSpecialAbilities());
    }

    private void updateExperienceInfo(final StandardMonster standardMonster, final CustomMonster customMonster) {
        final String cr = standardMonster.getChallengeRating();
        if (cr != null) {
            int openIdx = cr.indexOf("(");

            String crValue = (openIdx < 0) ? cr : cr.substring(0, openIdx);
            int xpValue;
            try {
                xpValue = challengeToXp(crValue);
            } catch (Exception e) {
                Logger.warning("Assuming xp=0. " + e.getMessage());
                xpValue = 0;
            }
            customMonster.setXp(xpValue);
            customMonster.setCr(crValue);
        } else {
            customMonster.setXp(0);
            customMonster.setCr(null);
        }
    }

    private int challengeToXp(String cr) {
        // TODO. Load from database instead of hard coding the values
        switch(cr.trim()) {
            case "0":
                return 10;
            case "1/8":
                return 25;
            case "1/4":
                return 50;
            case "1/2":
                return 100;
            case "1":
                return 200;
            case "2":
                return 450;
            case "3":
                return 700;
            case "4":
                return 1100;
            case "5":
                return 1800;
            case "6":
                return 2300;
            case "7":
                return 2900;
            case "8":
                return 3900;
            case "9":
                return 5000;
            case "10":
                return 5900;
            case "11":
                return 7200;
            case "12":
                return 8400;
            case "13":
                return 10000;
            case "14":
                return 11500;
            case "15":
                return 13000;
            case "16":
                return 15000;
            case "17":
                return 18000;
            case "18":
                return 20000;
            case "19":
                return 22000;
            case "20":
                return 25000;
            case "21":
                return 33000;
            case "22":
                return 41000;
            case "23":
                return 50000;
            case "24":
                return 62000;
            case "25":
                return 75000;
            case "26":
                return 90000;
            case "27":
                return 105000;
            case "28":
                return 120000;
            case "29":
                return 135000;
            case "30":
                return 155000;
            default:
                throw new RuntimeException("Could not determine the xp value of cr \"" + cr + "\".");
        }
    }

    private void updateResistancesImmunitiesAndVulnerabilities(final StandardMonster standardMonster, final CustomMonster customMonster) {
        customMonster.setDmgResistance(standardMonster.getDamageResistances());
        customMonster.setDmgImmunity(standardMonster.getDamageImmunities());
        customMonster.setConditionImmunity(standardMonster.getConditionImmunities());
        customMonster.setOther(standardMonster.getDamageVulnerabilities()); // TODO: Change to a different field
    }

    private void updateNameSpeedAlignmentHealthSource(final StandardMonster standardMonster, final CustomMonster customMonster) {
        customMonster.setSpeed(standardMonster.getSpeed());
        customMonster.setHd(standardMonster.getHitDice());
        customMonster.setHp(standardMonster.getHitPoints());
        customMonster.setName(standardMonster.getName());
        customMonster.setAlignment(standardMonster.getAlignment());
        customMonster.setSource(standardMonster.getSource());
    }

    private void updateAbilityScores(final StandardMonster standardMonster, final CustomMonster customMonster) {
        customMonster.setStrength(standardMonster.getStrength());
        customMonster.setDexterity(standardMonster.getDexterity());
        customMonster.setConstitution(standardMonster.getConstitution());
        customMonster.setIntelligence(standardMonster.getIntelligence());
        customMonster.setWisdom(standardMonster.getWisdom());
        customMonster.setCharisma(standardMonster.getCharisma());
    }

    private void updateType(final StandardMonster standardMonster, final CustomMonster customMonster) {
        String size = standardMonster.getSize();
        String type = standardMonster.getType();
        String subType = standardMonster.getSubType();

        String newType = size + " " + type + ((subType!=null) ? "" : (" " + subType));
        customMonster.setType(newType);
    }

    private void updateArmorClassValues(final StandardMonster standardMonster, final CustomMonster customMonster) {
        //
        // Determine the ac, acType from the armor_class field on the StandardMonster
        //
        final String armor_class = standardMonster.getArmorClass();
        if (armor_class != null) {
            int openIdx = armor_class.indexOf("(");
            String acType;
            int ac;
            if (openIdx < 0) {
                acType = null;
                ac = Integer.parseInt(armor_class.trim());
            } else {
                int closeIdx = armor_class.indexOf(")");
                ac = Integer.parseInt(armor_class.substring(0, openIdx).trim());
                acType = armor_class.substring(openIdx + 1, closeIdx);
            }

            customMonster.setAc(ac);
            customMonster.setAcType(acType);
        } else {
            customMonster.setAc(10);
            customMonster.setAcType(null);
        }
    }

    private String intText(int value) {
        return ((value<0) ? "" : "+") + Integer.toString(value);
    }
}
