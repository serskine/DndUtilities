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

PRAGMA user_version = 11;
