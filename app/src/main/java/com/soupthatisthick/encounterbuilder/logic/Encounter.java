package com.soupthatisthick.encounterbuilder.logic;

import com.soupthatisthick.encounterbuilder.logic.enums.MonsterEnum;
import com.soupthatisthick.encounterbuilder.util.announcer.Announcer;

import java.util.ArrayList;
import java.util.EventListener;

public class Encounter {
	private final ArrayList<Challenge>	challenges	= new ArrayList<Challenge>();
	private final ArrayList<Level>		party		= new ArrayList<Level>();
	
	protected final Announcer<Listener> listeners = Announcer.to(Listener.class);
	
	public static interface Listener extends EventListener {
		public void Changed(Encounter source);
	}
	
	public final void addMonster(MonsterEnum monster) {
		addChallenge(monster.cr);
	}
	
	public final void addChallenge(Challenge challenge) {
		challenges.add(challenge);
	}
	
	public final void removeChallenge(Challenge challenge) {
		challenges.remove(challenges.indexOf(challenge));
	}
	
	public final void addPlayer(Level level) {
		party.add(level);
	}
	
	public final void removePlayer(Level level) {
		party.remove(party.indexOf(level));
	}
	
	public final void addListener(Listener listener) {
		listeners.addListener(listener);
	}
	
	public final void removeListener(Listener listener) {
		listeners.removeListener(listener);
	}
	
	/**
	 * Returns a copy of all the players in the encounter
	 * @return
	 */
	public final ArrayList<Level>	getPlayers() {
		return new ArrayList<Level>(party);
	}
	
	/**
	 * Returns a copy of all the challenges in the encounter. 
	 * @return 
	 */
	public final ArrayList<Challenge> getChallenges() {
		return new ArrayList<Challenge>(challenges);
	}
	
	public final int easyPartyThreshold() {
		int sum = 0;
		for(Level level : party) {
			sum += level.easy;
		}
		return sum;
	}
	
	public final int mediumPartyThreshold() {
		int sum = 0;
		for(Level level : party) {
			sum += level.medium;
		}
		return sum;
	}
	
	public final int hardPartyThreshold() {
		int sum = 0;
		for(Level level : party) {
			sum += level.hard;
		}
		return sum;
	}
	
	public final int deadlyPartyThreshold() {
		int sum = 0;
		for(Level level : party) {
			sum += level.deadly;
		}
		return sum;
	}
	
	public final int monstersXp() {
		int sum = 0;
		for(Challenge cr : challenges) {
			sum += cr.xp;
		}
		return sum;
	}
	
	public final float monstersMultiplier() {
		int numMonsters = challenges.size();
		int numPlayers = party.size();
		
		if (numPlayers <=3) {
			if (numMonsters <= 1) {
				return 1.5f;
			} else if (numMonsters <=2) {
				return 2f;
			} else if (numMonsters <= 6) {
				return 2.5f;
			} else if (numMonsters <= 10) {
				return 3f;
			} else if (numMonsters <= 14) {
				return 4f;
			} else {
				return 5f;
			}
		} else if (numPlayers <= 5) {
			if (numMonsters <= 1) {
				return 1f;
			} else if (numMonsters <=2) {
				return 1.5f;
			} else if (numMonsters <= 6) {
				return 2f;
			} else if (numMonsters <= 10) {
				return 2.5f;
			} else if (numMonsters <= 14) {
				return 3f;
			} else {
				return 4f;
			}
		} else {
			if (numMonsters <= 1) {
				return 0.5f;
			} else if (numMonsters <=2) {
				return 1f;
			} else if (numMonsters <= 6) {
				return 1.5f;
			} else if (numMonsters <= 10) {
				return 2f;
			} else if (numMonsters <= 14) {
				return 2.5f;
			} else {
				return 3f;
			}
			
		}
	}
	
	public final float monstersThreat() {
		
		return monstersXp() * monstersMultiplier();
	}
	
	public final Threat	getEncounterThreatLevel() {
		Threat threat = Threat.EASY;
		
		float threatScore = monstersThreat(); 
		float med = mediumPartyThreshold();
		float hard = hardPartyThreshold();
		float deadly = deadlyPartyThreshold();
		
		if (med <= threatScore) {
			threat = Threat.MEDIUM;
		}
		if (hard <= threatScore) {
			threat = Threat.HARD;
		}
		if (deadly <= threatScore) {
			threat = Threat.DEADLY;
		}
		
		return threat;
	}

}