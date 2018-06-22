CREATE TABLE IF NOT EXISTS `ADVENTURE` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`code`	TEXT,
	`levelBand`	TEXT,
	`runtimeHours`	TEXT,
	`title`	TEXT,
	`notes`	TEXT,
	`seasonId`	INTEGER,
	`description`	TEXT DEFAULT '<html><body></body></html>'
);

CREATE INDEX idx_adventure on ADVENTURE(`code`, `title`, `seasonId`, `levelBand`);

CREATE TABLE `SEASON` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`title`	TEXT,
	`notes` TEXT,
	`description`	TEXT
);
CREATE INDEX idx_season on SEASON(`notes`, `title`);


ALTER TABLE ENTITY ADD COLUMN 'ADVENTURE' REFERENCES 'ADVENTURE'('ID') ON DELETE CASCADE;
ALTER TABLE ENTITY ADD COLUMN 'SEASON' REFERENCES 'SEASON'('ID') ON DELETE CASCADE;

PRAGMA user_version = 15;