ALTER TABLE 'MAGIC_ITEMS' ADD COLUMN 'treasure_points' 'INTEGER' DEFAULT 0;

-- Select queries to get MOST of the items that are on tables {A,B,C}, {D,E,F}, {G,H}, {I}
-- select unit from ROLL_TABLE_ENTRY where tableId=20 or tableId=21 or tableId=22;
-- select unit from ROLL_TABLE_ENTRY where tableId=23 or tableId=24 or tableId=25;
-- select unit from ROLL_TABLE_ENTRY where tableId=26 or tableId=27;
-- select unit from ROLL_TABLE_ENTRY where tableId=28;

update MAGIC_ITEMS set treasure_points = 4 where name in (select unit from ROLL_TABLE_ENTRY where tableId=20 or tableId=21 or tableId=22);
update MAGIC_ITEMS set treasure_points = 8 where name in (select unit from ROLL_TABLE_ENTRY where tableId=23 or tableId=24 or tableId=25);
update MAGIC_ITEMS set treasure_points = 10 where name in (select unit from ROLL_TABLE_ENTRY where tableId=26 or tableId=27);
update MAGIC_ITEMS set treasure_points = 12 where name in (select unit from ROLL_TABLE_ENTRY where tableId=28);

select * from MAGIC_ITEMS where treasure_points = 0;

PRAGMA user_version = 16;
