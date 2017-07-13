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

CREATE TABLE `CONDITIONS` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT,
	`description`	TEXT
);

PRAGMA user_version = 7;
