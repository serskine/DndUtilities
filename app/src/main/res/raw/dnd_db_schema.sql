CREATE TABLE IF NOT EXISTS android_metadata (locale TEXT);
CREATE TABLE IF NOT EXISTS "STANDARD_MONSTERS" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`size`	TEXT,
	`type`	TEXT,
	`subtype`	TEXT,
	`alignment`	TEXT,
	`armor_class`	TEXT,
	`hit_points`	TEXT,
	`hit_dice`	TEXT,
	`speed`	TEXT,
	`strength`	TEXT,
	`dexterity`	TEXT,
	`constitution`	TEXT,
	`intelligence`	TEXT,
	`wisdom`	TEXT,
	`charisma`	TEXT,
	`strength_save`	TEXT,
	`dexterity_save`	TEXT,
	`constitution_save`	TEXT,
	`intelligence_save`	TEXT,
	`wisdom_save`	TEXT,
	`charisma_save`	TEXT,
	`acrobatics`	TEXT,
	`arcana`	TEXT,
	`athletics`	TEXT,
	`deception`	TEXT,
	`history`	TEXT,
	`insight`	TEXT,
	`intimidation`	TEXT,
	`investigation`	TEXT,
	`investigation`	TEXT,
	`medicine`	TEXT,
	`nature`	TEXT,
	`performance`	TEXT,
	`perception`	TEXT,
	`persuasion`	TEXT,
	`religion`	TEXT,
	`stealth`	TEXT,
	`survival`	TEXT,
	`languages`	TEXT,
	`senses`	TEXT,
	`challenge_rating`	TEXT,
	`damage_vulnerabilities`	TEXT,
	`damage_resistances`	TEXT,
	`damage_immunities`	TEXT,
	`condition_immunities`	TEXT,
	`special_abilities`	TEXT,
	`reactions`	TEXT,
	`actions`	TEXT,
	`legendary_actions`	TEXT,
	`source`	TEXT
);
CREATE TABLE IF NOT EXISTS `NOTES` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`title`	TEXT,
	`content`	TEXT
);
CREATE TABLE IF NOT EXISTS "MAGIC_ITEMS" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`type`	TEXT,
	`rarity`	TEXT,
	`attunement`	TEXT,
	`location`	TEXT,
	`description`	TEXT
);
CREATE TABLE IF NOT EXISTS "LIST_ITEMS" (
	`itemId`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`listId`	INTEGER,
	`metaData`	TEXT,
	`table`	TEXT,
	`primaryKey`	TEXT,
	`key`	INTEGER
);
CREATE TABLE IF NOT EXISTS "LISTS" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`metaData`	TEXT
);
CREATE TABLE IF NOT EXISTS "EDITABLE_SPELLS" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`level`	INTEGER,
	`name`	TEXT,
	`type`	TEXT,
	`castingTime`	TEXT,
	`range`	TEXT,
	`components`	TEXT,
	`duration`	TEXT,
	`description`	TEXT,
	`class`	TEXT,
	`materials`	TEXT
);
CREATE TABLE IF NOT EXISTS "CUSTOM_MONSTERS" (
    `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    `name` TEXT,
    `type` TEXT,
    `alignment` TEXT,
    `ac` REAL,
    `acType` TEXT,
    `hp` TEXT,
    `hd` TEXT,
    `speed` TEXT,
    `str` TEXT,
    `dex` TEXT,
    `con` TEXT,
    `int` TEXT,
    `wis` TEXT,
    `cha` TEXT,
    `saves` TEXT,
    `skills` TEXT,
    `dmgResistance` TEXT,
    `dmgImmunity` TEXT,
    `conditionImmunity` TEXT,
    `senses` TEXT,
    `languages` TEXT,
    `cr` TEXT,
    `xp` TEXT,
    `abilities` TEXT,
    `actions` TEXT,
    `legendary` TEXT,
    `other` TEXT,
    `source` TEXT
);
CREATE TABLE IF NOT EXISTS "FEATS" (
    `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    `name` TEXT,
    `prerequisite` TEXT,
    `description` TEXT
);
CREATE TABLE IF NOT EXISTS "BACKGROUND" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`skills`	TEXT,
	`tools`	TEXT,
	`languages`	TEXT,
	`equipment`	TEXT,
	`description`	TEXT,
	`features`	TEXT
);
CREATE TABLE IF NOT EXISTS `CONDITIONS` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`description`	TEXT
);
CREATE TABLE "ROLL_TABLE_ENTRY" (
    `entryId` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    `tableId` INTEGER,
    `minRoll` INTEGER,
    `maxRoll` INTEGER,
    `result` TEXT,
    `unit` TEXT,
    `rerollTableId` INTEGER,
    `dieQty` INTEGER DEFAULT 1,
    `dieSize` INTEGER DEFAULT 1,
    `rollMul` INTEGER DEFAULT 1,
    `rollAvg` INTEGER DEFAULT 1,
    `unitGpValue` REAL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS "RANDOM_TABLE" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`dieQty`	INTEGER,
	`dieSize`	INTEGER
);

PRAGMA user_version = 8;