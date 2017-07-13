package com.soupthatisthick.encounterbuilder.printing.model;

import android.support.annotation.NonNull;

import com.soupthatisthick.encounterbuilder.model.lookup.Pc;
import com.soupthatisthick.encounterbuilder.model.lookup.Session;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.Text;

/**
 * Created by Owner on 3/4/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogsheetPage {
    public static final int SESSIONS_PER_PAGE = 3;
    public final Pc character;
    public final Session[] sessions = new Session[SESSIONS_PER_PAGE];
    public final int sheetNumber;

    public LogsheetPage(Pc character, int sheetNumber, Session session1, Session session2, Session session3)
    {
        this.character = character;
        this.sheetNumber = sheetNumber;
        this.sessions[0] = session1;
        this.sessions[1] = session2;
        this.sessions[2] = session3;
    }

    @Override
    public String toString()
    {
        String output = character.toString();
        for(int i=0; i<sessions.length; i++)
        {
            output += "[" + Text.toString(sessions[i].toString()) + "]";
        }
        return output;
    }


    public static final void log(@NonNull LogsheetPage page)
    {
        Logger.title(page.character.getCharacterName());
        Logger.info(" Player Name = " + page.character.getPlayerName() + "(" + page.character.getPlayerDci() + ")");
        Logger.info(" Classes     = " + page.character.getClassAndLevels());
        Logger.info(" Faction     = " + page.character.getFaction());

        for(int i=0; i<page.sessions.length; i++) {
            Session session = page.sessions[i];
            Logger.info(" - Session " + i + 1);
            if (session != null) {
                Logger.info("   - " + session.getAdventure() + "(" + session.getDate() + ")");
                Logger.info("   - " + session.getDmName() + "(" + session.getDmDci() + ")");
                Logger.info("   - "
                        + session.getXp() + " xp, "
                        + session.getGold() + " gp, "
                        + session.getDowntime() + " days, "
                        + session.getRenown() + " renown, "
                        + session.getMagicItems() + " magic items");
                Logger.info("   - Notes: " + session.getNotes());
            } else {
                Logger.info("   - EMPTY");
            }
        }
    }

}
