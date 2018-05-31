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
CREATE TABLE IF NOT EXISTS `ENTITY` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`metadata`	TEXT,
	`ENTITY`	INTEGER,
	`ARMOR`	INTEGER,
	`CHARACTER_ADVANCEMENT`	INTEGER,
	`CONDITIONS`	INTEGER,
	`CONTAINERS`	INTEGER,
	`CUSTOM_MONSTERS`	INTEGER,
	`DAO_SEARCHABLE`	INTEGER,
	`EDITABLE_SPELLS`	INTEGER,
	`EQUIPMENT`	INTEGER,
	`FEATS`	INTEGER,
	`GODS`	INTEGER,
	`HEIGHT_WEIGHT`	INTEGER,
	`LANGUAGES`	INTEGER,
	`LIFESTYLE`	INTEGER,
	`LISTS`	INTEGER,
	`LIST_ITEMS`	INTEGER,
	`MAGIC_ITEMS`	INTEGER,
	`MC`	INTEGER,
	`MONSTERS`	INTEGER,
	`MOUNTS`	INTEGER,
	`MS`	INTEGER,
	`MULTICLASSING`	INTEGER,
	`NOTES`	INTEGER,
	`ROLL_TABLE`	INTEGER,
	`ROLL_TABLE_ENTRY`	INTEGER,
	`SERVICE`	INTEGER,
	`SPELL_SLOTS_MULTICLASS`	INTEGER,
	`STANDARD_MONSTERS`	INTEGER,
	`TRADE_GOODS`	INTEGER,
	`WATERBORNE_VECHICLES`	INTEGER,
	`WEAPONS`	INTEGER,
	FOREIGN KEY(`WATERBORNE_VECHICLES`) REFERENCES `WATERBORNE_VECHICLES`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`LANGUAGES`) REFERENCES `LANGUAGES`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`ARMOR`) REFERENCES `ARMOR`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`MONSTERS`) REFERENCES `MONSTERS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`CONTAINERS`) REFERENCES `CONTAINERS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`NOTES`) REFERENCES `NOTES`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`SPELL_SLOTS_MULTICLASS`) REFERENCES `SPELL_SLOTS_MULTICLASS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`GODS`) REFERENCES `GODS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`ENTITY`) REFERENCES `ENTITY`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`WEAPONS`) REFERENCES `WEAPONS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`LIST_ITEMS`) REFERENCES `LIST_ITEMS`(`itemId`) ON DELETE CASCADE,
	FOREIGN KEY(`CHARACTER_ADVANCEMENT`) REFERENCES `CHARACTER_ADVANCEMENT`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`MOUNTS`) REFERENCES `MOUNTS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`MULTICLASSING`) REFERENCES `MULTICLASSING`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`ROLL_TABLE`) REFERENCES `ROLL_TABLE`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`EDITABLE_SPELLS`) REFERENCES `EDITABLE_SPELLS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`STANDARD_MONSTERS`) REFERENCES `STANDARD_MONSTERS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`HEIGHT_WEIGHT`) REFERENCES `HEIGHT_WEIGHT`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`MAGIC_ITEMS`) REFERENCES `MAGIC_ITEMS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`LISTS`) REFERENCES `LISTS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`LIFESTYLE`) REFERENCES `LIFESTYLE`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`CONDITIONS`) REFERENCES `CONDITIONS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`DAO_SEARCHABLE`) REFERENCES `DAO_SEARCHABLE`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`ROLL_TABLE_ENTRY`) REFERENCES `ROLL_TABLE_ENTRY`(`entryId`) ON DELETE CASCADE,
	FOREIGN KEY(`EQUIPMENT`) REFERENCES `EQUIPMENT`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`TRADE_GOODS`) REFERENCES `TRADE_GOODS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`CUSTOM_MONSTERS`) REFERENCES `CUSTOM_MONSTERS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`MC`) REFERENCES `MC`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`MS`) REFERENCES `MS`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`SERVICE`) REFERENCES `SERVICE`(`id`) ON DELETE CASCADE,
	FOREIGN KEY(`FEATS`) REFERENCES `FEATS`(`id`) ON DELETE CASCADE
);

--
-- Apply indicies an the key fields
--
CREATE INDEX idx_armor on ARMOR(name, stealth, type);
CREATE INDEX idx_background on BACKGROUND(name, skills, languages, tools);
CREATE INDEX idx_conditions on CONDITIONS(name);
CREATE INDEX idx_custom_monsters on CUSTOM_MONSTERS(
  name,
  type,
  alignment,
  ac,
  acType,
  saves,
  skills,
  dmgResistance,
  dmgImmunity,
  conditionImmunity,
  senses,
  languages,
  other,
  source
);
CREATE INDEX idx_equipment on EQUIPMENT(name,type);
CREATE INDEX idx_feats on FEATS(name, prerequisite);
CREATE INDEX idx_gods on GODS(name, alignment, domains, source, notes);
CREATE INDEX idx_lists on LISTS(name);
CREATE INDEX idx_list_items on LIST_ITEMS(metaData);
CREATE INDEX idx_lifestyle on LIFESTYLE(name);
CREATE INDEX idx_magic_items on MAGIC_ITEMS(
  name,
  type,
  rarity,
  attunement,
  location
);
CREATE INDEX idx_mounts on MOUNTS(name);
CREATE INDEX idx_notes on NOTES(title);
CREATE INDEX idx_roll_table on ROLL_TABLE(name);
CREATE INDEX idx_roll_table_entry on ROLL_TABLE_ENTRY(result);
CREATE INDEX idx_dao_searchable on DAO_SEARCHABLE("table", "column");
CREATE INDEX idx_editable_spells on EDITABLE_SPELLS(
  level,
  name,
  type,
  castingTime,
  range,
  components,
  duration,
  class,
  materials
);
CREATE INDEX idx_standard_monsters on STANDARD_MONSTERS(
  name,
  size,
  type,
  subtype,
  alignment,
  speed,
  damage_vulnerabilities,
  damage_resistances,
  damage_immunities,
  condition_immunities,
  senses,
  languages
);
CREATE INDEX idx_weapons on WEAPONS(name, properties, type);

PRAGMA user_version = 13;
