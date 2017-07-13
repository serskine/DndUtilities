ALTER TABLE 'ROLL_TABLE_ENTRY' ADD COLUMN 'unit' 'TEXT';
ALTER TABLE 'ROLL_TABLE_ENTRY' ADD COLUMN 'unitGpValue' 'REAL';

UPDATE ROLL_TABLE_ENTRY set unit=result;
UPDATE ROLL_TABLE_ENTRY set unit='cp' where ROLL_TABLE_ENTRY.result REGEXP '\d+d\d+(x\d+)?\s+(\(\d+\)\s+)?cp\s*';
UPDATE ROLL_TABLE_ENTRY set unit='sp' where ROLL_TABLE_ENTRY.result REGEXP '\d+d\d+(x\d+)?\s+(\(\d+\)\s+)?sp\s*';
UPDATE ROLL_TABLE_ENTRY set unit='ep' where ROLL_TABLE_ENTRY.result REGEXP '\d+d\d+(x\d+)?\s+(\(\d+\)\s+)?ep\s*';
UPDATE ROLL_TABLE_ENTRY set unit='gp' where ROLL_TABLE_ENTRY.result REGEXP '\d+d\d+(x\d+)?\s+(\(\d+\)\s+)?gp\s*';
UPDATE ROLL_TABLE_ENTRY set unit='pp' where ROLL_TABLE_ENTRY.result REGEXP '\d+d\d+(x\d+)?\s+(\(\d+\)\s+)?pp\s*';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='0.00';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='0.01' where ROLL_TABLE_ENTRY.unit = 'cp';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='0.10' where ROLL_TABLE_ENTRY.unit = 'sp';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='0.50' where ROLL_TABLE_ENTRY.unit = 'ep';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='1.00' where ROLL_TABLE_ENTRY.unit = 'gp';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='10.00' where ROLL_TABLE_ENTRY.unit = 'pp';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='10.0' where tableId='1';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='50.0' where tableId='2';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='100.0' where tableId='3';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='500.0' where tableId='4';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='1000.0' where tableId='5';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='5000.0' where tableId='6';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='25.0' where tableId='7';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='250.0' where tableId='8';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='750.0' where tableId='9';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='2500.0' where tableId='10';
UPDATE ROLL_TABLE_ENTRY set unitGpValue='7500.0' where tableId='11';

PRAGMA user_version = 9;
