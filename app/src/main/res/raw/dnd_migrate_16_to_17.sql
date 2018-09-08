-- SELECT MI.name, MI.treasure_points, MI.treasure_table FROM MAGIC_ITEMS MI where treasure_points = 0 and treasure_table is not null;
-- 8 points
-- SELECT MI.name, MI.treasure_points, MI.treasure_table FROM MAGIC_ITEMS MI
-- where (treasure_table like '%A%' or treasure_table like '%B%' or treasure_table like '%C%');
-- 16 points
-- SELECT MI.name, MI.treasure_points, MI.treasure_table FROM MAGIC_ITEMS MI
-- where (treasure_table like '%D%' or treasure_table like '%E%' or treasure_table like '%F%');
-- 20 points
-- SELECT MI.name, MI.treasure_points, MI.treasure_table FROM MAGIC_ITEMS MI
-- where (treasure_table like '%G%' or treasure_table like '%H%');
-- 24 points
-- SELECT MI.name, MI.treasure_points, MI.treasure_table FROM MAGIC_ITEMS MI
-- where (treasure_table like '%I%');
ALTER TABLE 'MAGIC_ITEMS' ADD COLUMN 'treasure_table';

-- convert the old table to the new table
update MAGIC_ITEMS SET treasure_points = (treasure_points * 2);
-- update the treasure points to use the treasure table column to set the treasure points if not already
-- the player really should update their database from the assets directory instead.
update MAGIC_ITEMS SET treasure_points = 8 where treasure_points = 0 and (treasure_table like '%A%' or treasure_table like '%B%' or treasure_table like '%C%');
update MAGIC_ITEMS SET treasure_points = 16 where treasure_points = 0 and (treasure_table like '%D%' or treasure_table like '%E%' or treasure_table like '%F%');
update MAGIC_ITEMS SET treasure_points = 20 where treasure_points = 0 and (treasure_table like '%G%' or treasure_table like '%H%');
update MAGIC_ITEMS SET treasure_points = 24 where treasure_points = 0 and (treasure_table like '%I%');

PRAGMA user_version = 17;