CREATE INDEX idx_pc on logsheets(
  playerName,
  playerDci,
  characterName,
  faction,
  classLevels
);
CREATE INDEX idx_game_sessions on gameSessions(
  adventure,
  date,
  dmDci,
  dmName,
  notes
);

PRAGMA user_version = 4;