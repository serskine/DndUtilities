--
-- If the previous monsters table existed, delete it so we can start fresh
--
drop table MONSTERS;

--
-- Create the new table that will contain all monster data
--
CREATE TABLE IF NOT EXISTS `MONSTERS` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`type`	TEXT,
	`alignment`	TEXT,
	`ac`	REAL,
	`acType`	TEXT,
	`hp`	TEXT,
	`hd`	TEXT,
	`speed`	TEXT,
	`str`	TEXT,
	`dex`	TEXT,
	`con`	TEXT,
	`int`	TEXT,
	`wis`	TEXT,
	`cha`	TEXT,
	`saves`	TEXT,
	`skills`	TEXT,
	`dmgResistance`	TEXT,
	`dmgImmunity`	TEXT,
	`conditionImmunity`	TEXT,
	`senses`	TEXT,
	`languages`	TEXT,
	`cr`	TEXT,
	`xp`	TEXT,
	`abilities`	TEXT,
	`actions`	TEXT,
	`legendary`	TEXT,
	`other`	TEXT,
	`source`	TEXT
);

--
-- Copy data from the CUSTOM_MONSTERS table directly into this table
--
INSERT INTO MONSTERS SELECT * FROM CUSTOM_MONSTERS;

INSERT INTO MONSTERS VALUES(
	null, -- `id`	
	null, -- `name`	
	null, -- `type`	
	null, -- `alignment`	
	null, -- `ac`	
	null, -- `acType`	
	null, -- `hp`	
	null, -- `hd`	
	null, -- `speed`	
	null, -- `str`	
	null, -- `dex`	
	null, -- `con`	
	null, -- `int`	
	null, -- `wis`	
	null, -- `cha`	
	null, -- `saves`	
	null, -- `skills`	
	null, -- `dmgResistance`	
	null, -- `dmgImmunity`	
	null, -- `conditionImmunity`	
	null, -- `senses`	
	null, -- `languages`	
	null, -- `cr`	
	null, -- `xp`	
	null, -- `abilities`	
	null, -- `actions`	
	null, -- `legendary`	
	null, -- `other`	
	null -- `source`
);

SELECT
	-- `id`
	STANDARD_MONSTERS.name, -- `name`
	STANDARD_MONSTERS.type, -- `type`
	STANDARD_MONSTERS.alignment, -- `alignment`
	STANDARD_MONSTERS.armor_class, -- `ac`
	STANDARD_MONSTERS.armor_class, -- `acType`
	STANDARD_MONSTERS.hit_points, -- `hp`
	STANDARD_MONSTERS.hit_dice, -- `hd`
	STANDARD_MONSTERS.speed, -- `speed`
	STANDARD_MONSTERS.strength, -- `str`
	STANDARD_MONSTERS.dexterity, -- `dex`
	STANDARD_MONSTERS.constitution, -- `con`
	STANDARD_MONSTERS.intelligence, -- `int`
	STANDARD_MONSTERS.wisdom, -- `wis`
	STANDARD_MONSTERS.charisma, -- `cha`
	-- `saves`
	-- `skills`
	STANDARD_MONSTERS.damage_resistances, -- `dmgResistance`
	STANDARD_MONSTERS.damage_immunities, -- `dmgImmunity`
	STANDARD_MONSTERS.condition_immunities, -- `conditionImmunity`
	STANDARD_MONSTERS.senses, -- `senses`
	STANDARD_MONSTERS.languages, -- `languages`
	STANDARD_MONSTERS.challenge_rating, -- `cr`
	-- `xp`
	STANDARD_MONSTERS.special_abilities, -- `abilities`
	STANDARD_MONSTERS.actions, -- `actions`
	STANDARD_MONSTERS.legendary_actions, -- `legendary`
	STANDARD_MONSTERS.damage_vulnerabilities, -- `other`
	STANDARD_MONSTERS.source -- `source`
FROM STANDARD_MONSTERS;

--
-- This will copy over some of the values (NOT ALL) from STANDARD_MONSTERS into MONSTERS
--
INSERT INTO MONSTERS (
	-- id
	name,
	type,
	alignment,
	ac,
	-- acType
	hp,
	hd,
	speed,
	str,
	dex,
	con,
	int,
	wis,
	cha,
	-- saves
	-- skills
	dmgResistance,
	dmgImmunity,
	conditionImmunity,
	senses,
	languages,
	cr,
	-- xp
	abilities,
	actions,
	legendary,
	other,
	source
)
SELECT
	-- STANDARD_MONSTERS.id, -- 'id'
	STANDARD_MONSTERS.name, -- `name`
	STANDARD_MONSTERS.type, -- `type`
	STANDARD_MONSTERS.alignment, -- `alignment`
	STANDARD_MONSTERS.armor_class, -- `ac`
	-- `acType`
	STANDARD_MONSTERS.hit_points, -- `hp`
	STANDARD_MONSTERS.hit_dice, -- `hd`
	STANDARD_MONSTERS.speed, -- `speed`
	STANDARD_MONSTERS.strength, -- `str`
	STANDARD_MONSTERS.dexterity, -- `dex`
	STANDARD_MONSTERS.constitution, -- `con`
	STANDARD_MONSTERS.intelligence, -- `int`
	STANDARD_MONSTERS.wisdom, -- `wis`
	STANDARD_MONSTERS.charisma, -- `cha`
	-- `saves`
	-- `skills`
	STANDARD_MONSTERS.damage_resistances, -- `dmgResistance`
	STANDARD_MONSTERS.damage_immunities, -- `dmgImmunity`
	STANDARD_MONSTERS.condition_immunities, -- `conditionImmunity`
	STANDARD_MONSTERS.senses, -- `senses`
	STANDARD_MONSTERS.languages, -- `languages`
	STANDARD_MONSTERS.challenge_rating, -- `cr`
	-- `xp`
	STANDARD_MONSTERS.special_abilities, -- `abilities`
	STANDARD_MONSTERS.actions, -- `actions`
	STANDARD_MONSTERS.legendary_actions, -- `legendary`
	STANDARD_MONSTERS.damage_vulnerabilities, -- `other`
	STANDARD_MONSTERS.source -- `source`
FROM STANDARD_MONSTERS;
