package com.soupthatisthick.util.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.encounterbuilder.model.FieldSchema;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.encounterbuilder.util.Text;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Owner on 2/2/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SqlUtil {

    public static final String TBL_SQLITE_MASTER = "sqlite_master";
    private static final String COL_TYPE = "type";
    private static final String COL_NAME = "name";
    private static final String COL_TABLE_NAME = "tbl_name";
    private static final String COL_ROOTPAGE = "rootpage";
    private static final String COL_SQL = "sql";

    private static final String TYPE_TABLE = "table";
    private static final String TYPE_INDEX = "index";


    public static final String COL_ROW_ID = "OID";

    public static class Query
    {
        public final String sql;
        public final String[] args;
        public final String groupBy;
        public final String having;
        public final String orderBy;

        public Query(String sql, String[] args, String orderBy)
        {
            this(sql, args, null, null, orderBy);
        }

        public Query(String sql, String[] args, String groupBy, String having, String orderBy)
        {
            this.sql = sql;
            this.args = args;
            this.groupBy = groupBy;
            this.having = having;
            this.orderBy = orderBy;
        }

        @Override
        public String toString()
        {
            String output = "";
            output += sql + " ";
            output += (having==null || having.length()<1) ? "" : "HAVING " + having + " ";
            output += (orderBy==null || orderBy.length()<1) ? "" : "ORDER BY " + orderBy;

            output = indent(output);
            output = insertArgs(output, args);

            return output;
        }

        public String toOneLinestring()
        {
            String output = "";
            output += sql + " ";
            output += (having==null || having.length()<1) ? "" : "HAVING " + having + " ";
            output += (orderBy==null || orderBy.length()<1) ? "" : "ORDER BY " + orderBy;

            output = insertArgs(output, args);

            return output;
        }
    }

    private static final String insertArgs(String sql, String[] args)
    {
        for(int i=0; i<args.length; i++)
        {
            sql = sql.replaceFirst("\\?", args[i]);
        }
        return sql;
    }

    private static final String indent(String sql)
    {
        String newSql = "";
        int numTabs = 0;
        String tab = "  ";

        for(int i=0; i<sql.length(); i++)
        {
            char c = sql.charAt(i);
            switch(c)
            {
                case '(':
                    newSql += '\n';
                    newSql += Text.padString(tab, numTabs);
                    numTabs++;
                    break;
                case ')':
                    newSql += '\n';
                    numTabs--;
                    newSql += Text.padString(tab, numTabs);
                    break;
            }
            newSql += c;
            switch(c)
            {
                case '(':
                    newSql += '\n';
                    newSql += Text.padString(tab, numTabs);
                    break;
                case ')':
                    newSql += '\n';
                    newSql += Text.padString(tab, numTabs);
                    break;
            }
        }
        return newSql;
    }

    public static final String any(@NonNull String... subExpressions)
    {
        return expression("OR", subExpressions);
    }

    public static final String all(@NonNull String... subExpressions)
    {
        return expression("AND", subExpressions);
    }

    public static final String none(@NonNull String... subExpressions)
    {
        return not(any(subExpressions));
    }

    public static final String not(@NonNull String conditition)
    {
        return "(NOT (" + conditition + "))";
    }

    /**
     * Returns a printable line of all the columns matched with their column index for a cursor
     * @param cursor
     * @return
     */
    public static String headerLine(Cursor cursor) {
        String line = "";
        for(int i=0; i<cursor.getColumnCount(); i++)
        {
            line += " " + cursor.getColumnName(i);
        }
        return line;
    }

    /**
     * This is used to concatenate arrays of strings together in order
     * into one array of strings
     * @param args
     * @return
     */
    public static String[] concatenate(String[]... args)
    {
        int sum = 0;
        for(String[] array : args)
        {
            sum += array.length;
        }

        String[] newArgs = new String[sum];
        int i = 0;
        for(String[] array : args)
        {
            for(int j=0; j<array.length; j++)
            {
                newArgs[i+j] = array[j];
            }
            i += array.length;
        }
        return newArgs;
    }

    /**
     * Creates an expression where the columnName in uppercase is like the parameter
     * @param columnName
     * @return
     */
    public static final String likeColumnUppercase(String columnName)
    {
        return "(UPPER(" + columnName + ") LIKE ?)";
    }

    /**
     * This clause will return all records where the specified column is not null and the
     * specified column in uppercase i
     * @param columnName
     * @return
     */
    public static final String likeColumnUppercaseAndNotNull(String columnName)
    {
        return all(not(isNull(columnName)), likeColumnUppercase(columnName));
    }

    public static final String notLikeColumnUppercaseOrIsNull(String columnName)
    {
        return any(isNull(columnName), notLikeColumnUppercase(columnName));
    }

    public static final String isNull(String columnName)
    {
        return "(" + columnName + " IS NULL)";
    }

    /**
     * Creates an expression where the columnName in uppercase is NOT like the parameter
     * @param columnName
     * @return
     */
    public static final String notLikeColumnUppercase(String columnName)
    {
        return "(UPPER(" + columnName + ") NOT LIKE ?)";
    }

    /**
     * Creates an expression where the tableName.columName in uppercase is like the parameter
     * @param tableName
     * @param columnName
     * @return
     */
    public static final String likeColumnUppercase(String tableName, String columnName)
    {
        return likeColumnUppercase(tableName + "." + columnName);
    }

    /**
     * Returns an expression where the paramater is like the specified columnName in lower case
     * @param columnName
     * @return
     */
    public static final String likeColumnLowercase(String columnName)
    {
        return "(LOWER(" + columnName + ") LIKE ?)";
    }

    /**
     * This will return an expression where we want the condition where the paramater is like the lowercase
     * version of the tableName.columnName
     * @param tableName
     * @param columnName
     * @return
     */
    public static final String likeColumnLowercase(String tableName, String columnName)
    {
        return likeColumnLowercase(tableName + "." + columnName);
    }

    /**
     * This will return an expression with any number of sub expressions all seperated by the specified operator
     * @param operator
     * @param subExpression
     * @return
     */
    public static final String expression(@NonNull String operator, @NonNull String[] subExpression)
    {
        if (subExpression.length>0)
        {
            String sql = subExpression[0];
            for(int i=1; i<subExpression.length; i++)
            {
                sql += " " + operator + " " + subExpression[i];
            }
            return "(" + sql + ")";
        } else {
            throw new RuntimeException("subExpression must contains at least one expression");
        }
    }

    /**
     * Builds the query expression for having the word like any of the columns in uppercase
     * @param word
     * @param cols
     * @return
     */
    public static final String likeAnyUppercase(@NonNull String word, @NonNull String[] cols)
    {
        String sql = "";
        sql += likeColumnUppercase(cols[0]);
        for(int i=0; i<cols.length; i++) {
            sql += " OR " + likeColumnUppercase(cols[i]);
        }

        return sql;
    }
    public static final Query anyWordsLikeAnyColumn(String[] words, String[] columns)
    {
        return anyWordsLikeAnyColumn(words, columns, null);
    }


    public static final Query anyWordsLikeAnyColumn(String[] words, String[] columns, String[] columnOrder)
    {

        String orderBy = Text.concat(", ", columnOrder);
        String args[] = new String[words.length * columns.length];

        int argIdx = 0;

        String[] subExpForWord = new String[words.length];

        for(int wordIdx=0; wordIdx<words.length; wordIdx++) {
            String word = words[wordIdx];
            String[] subExpr = new String[columns.length];

            for(int i=0; i<columns.length; i++)
            {
                subExpr[i] = likeColumnUppercase(columns[i]);
                args[argIdx] = word;
                argIdx++;
            }
            subExpForWord[wordIdx] = any(subExpr);
        }

        return new Query(any(subExpForWord),args, orderBy);
    }

    public static final Query allWordsNotLikeAnyColumn(String[] excludedWords, String[] columns, String[] order)
    {
        String orderBy = (order==null) ? "" : Text.concat(", ", order);
        String args[] = new String[excludedWords.length * columns.length];
        int argIdx = 0;

        String[] excludedSubExpForWord = new String[excludedWords.length];

        // Determine the excluded words expression (All excluded words must not match anything except null)
        for(int i=0; i<excludedWords.length; i++) {
            String word = excludedWords[i];
            String[] subExpr = new String[columns.length];
            for(int j=0; j<columns.length; j++)
            {
                String column = columns[j];
                subExpr[j] = notLikeColumnUppercaseOrIsNull(column);
                args[argIdx] = word;
                argIdx++;
            }
            excludedSubExpForWord[i] = all(subExpr);
        }
        String excludeSql = all(excludedSubExpForWord);


        return new Query(excludeSql, args, orderBy);
    }

    public static final Query allWordsLikeAnyColumn(String[] words, String[] columns, String[] order)
    {
        String orderBy = (order==null) ? "" : Text.concat(", ", order);
        String args[] = new String[words.length * columns.length];
        int argIdx = 0;

        String[] subExpForWord = new String[words.length];

        for(int wordIdx=0; wordIdx<words.length; wordIdx++) {
            String word = words[wordIdx];
            String[] subExpr = new String[columns.length];

            for(int i=0; i<columns.length; i++)
            {
                subExpr[i] = likeColumnUppercase(columns[i]);
                args[argIdx] = word;
                argIdx++;
            }
            subExpForWord[wordIdx] = any(subExpr);
        }

        return new Query(all(subExpForWord),args, orderBy);
    }

    private static final String arrayString(String[] items)
    {
        return Text.concat(",", items);
    }

    // TODO: WORKING HERE
    public static final Query anyIncludedWordAndNoExcludedWords(
        @NonNull String[] includedWords,
        @NonNull String[] excludedWords,
        String[] columns,
        String[] order
    ) {
        if (includedWords==null || includedWords.length<1)
        {
            return allWordsNotLikeAnyColumn(excludedWords, columns, order);
        }
        if (excludedWords==null || excludedWords.length<1)
        {
            return allWordsLikeAnyColumn(includedWords, columns, order);
        }

        // Prep the arguments array
        String orderBy = (order==null) ? "" : Text.concat(", ", order);
        int numWords = includedWords.length * columns.length + excludedWords.length * columns.length;
        String args[] = new String[numWords];
        int argIdx = 0;

        Logger.debug("--- The words ---");
        Logger.debug(" - INCLUDED (" + includedWords.length + ") => " + arrayString(includedWords));
        Logger.debug(" - EXCLUDED (" + excludedWords.length + ") => " + arrayString(excludedWords));
        Logger.debug(" - COLUMNS  (" + columns.length + ") => " + arrayString(columns));
        Logger.debug(" - ORDER    (" + order.length + ") => " + arrayString(order));

        // We have a list of included and excluded words to consider
        String[] includedSubExpForWord = new String[includedWords.length];
        String[] excludedSubExpForWord = new String[excludedWords.length];

        // Determine the included words expression (All included words must match anything)
        for(int i=0; i<includedWords.length; i++) {
            String word = includedWords[i];
            String[] subExpr = new String[columns.length];
            for(int j=0; j<columns.length; j++)
            {
                String column = columns[j];
                subExpr[j] = likeColumnUppercase(column);
                args[argIdx] = word;
                argIdx++;
            }
            includedSubExpForWord[i] = any(subExpr);
        }
        String includeSql = all(includedSubExpForWord);

        // Determine the excluded words expression (All excluded words must not match anything except null)
        for(int i=0; i<excludedWords.length; i++) {
            String word = excludedWords[i];
            String[] subExpr = new String[columns.length];
            for(int j=0; j<columns.length; j++)
            {
                String column = columns[j];
                subExpr[j] = notLikeColumnUppercaseOrIsNull(column);
                args[argIdx] = word;
                argIdx++;
            }
            excludedSubExpForWord[i] = all(subExpr);
        }
        String excludeSql = all(excludedSubExpForWord);

        String testSql = SqlUtil.all("includeSql","excludeSql");
        Logger.debug(testSql);
        Logger.debug(" include = " + includeSql);
        Logger.debug(" exclude = " + excludeSql);
        String allSql = SqlUtil.all(includeSql,excludeSql);

        Logger.debug(" total   = " + allSql);

        return new Query(allSql, args, orderBy);
    }

    public static final Cursor getLastInsertedRow(SQLiteDatabase db, String table, String primaryKeyColumn)
    {
        return db.query(table, new String[] { "MAX(" + primaryKeyColumn + ")" }, null, null, null, null, null, null);
    }

    public static final String cursorString(Cursor cursor)
    {
        if (cursor==null)
        {
            return "null";
        }

        String output = "";

        for(int col=0; col<cursor.getColumnCount(); col++)
        {
            String colName = cursor.getColumnName(col);
            output += "[" + Text.toString(colName) + "=" + Text.quote(cursor.getString(cursor.getColumnIndex(colName))) + "]";
        }
        return output;
    }

    public static final String cursorHeadersString(Cursor cursor)
    {
        if (cursor==null)
        {
            return "There are no headers. Cursor is null";
        }

        String output = "";
        for(int col=0; col<cursor.getColumnCount(); col++)
        {
            String colName = cursor.getColumnName(col);
            output += "[index " + col + "=" + Text.toString(colName) + "]";

        }
        return output;
    }

    /**
     * Use this function to retrieve a specific row in a database table
     * @param db
     * @param table
     * @param row
     * @return a cursor for the desired row
     */
    public static final Cursor loadRow(@NonNull SQLiteDatabase db, @NonNull String table, @NonNull Long row)
    {
        if (row < 1)
        {
            throw new IllegalArgumentException("row must be >= 1 but was " + row);
        }

        return db.query(
            table,                                  // the table
            null,                                   // desired columns
            "(" + COL_ROW_ID + "=?)",               // selection where clause
            new String[] {Long.toString(row)},      // selection args (desired row)
            null,                                   // group by
            null,                                   // having
            null,                                   // order by
            "1"                                     // limit (1 because it should be unique)
        );
    }

    /**
     * This will close the cursor if it exists. It is valid to take a null argument.
     * @param cursor to be closed
     */
    public static final void closeCursor(@Nullable Cursor cursor)
    {
        if (cursor!=null && !cursor.isClosed())
        {
            cursor.close();
        }
    }

    /**
     * This will return a list of all tables in the database
     * @param db we want to know information from
     * @return a {@link Cursor} that gives all the table properties
     */
    public static final Cursor getTablesCursor(SQLiteDatabase db)
    {
        return db.query(
                TBL_SQLITE_MASTER,                      // the table
                null,                                   // desired columns
                "(" + COL_TYPE + "=?)",                 // selection where clause
                new String[] {TYPE_TABLE},              // selection args (desired row)
                null,                                   // group by
                null,                                   // having
                COL_NAME                                // orderby
        );
    }

    /**
     * This will return a list of all indicies on the database
     * @param db is the {@link SQLiteDatabase} we want to know informaiton from
     * @return a {@link Cursor} that provides all the indicies table information
     */
    public static final Cursor getIndicies(SQLiteDatabase db)
    {
        return db.query(
                TBL_SQLITE_MASTER,                      // the table
                null,                                   // desired columns
                "(" + COL_TYPE + "=?)",                 // selection where clause
                new String[] {TYPE_INDEX},              // selection args (desired row)
                null,                                   // group by
                null,                                   // having
                COL_NAME                                // orderby
        );
    }

    public static final void logCursorInfo(Cursor cursor)
    {
        logCursorInfo(cursor, 1);
    }

    /**
     * This will iterate over everything the cursor provides and display to the user
     * @param cursor
     */
    private static final void logCursorInfo(Cursor cursor, int depth)
    {
        Logger.info(cursorHeadersString(cursor), depth+1);
        if (cursor.moveToFirst())
        {
            int row = 0;
            while(!cursor.isAfterLast())
            {
                @SuppressLint("DefaultLocale") String lineHead = String.format(" - row(%5d) = ", row);
                Logger.info(lineHead + cursorString(cursor), depth+1);
                row++;
                cursor.moveToNext();
            }
        }
    }

    /**
     * This will return a printable output string for the cursor information
     * @param cursor
     * @return String
     */
    private static final String cursorInfo(Cursor cursor)
    {
        String output = "";
        output += cursorHeadersString(cursor);
        if (cursor.moveToFirst())
        {
            int row = 0;
            while(!cursor.isAfterLast())
            {
                @SuppressLint("DefaultLocale") String lineHead = String.format(" - row(%5d) = ", row);
                output += lineHead + cursorString(cursor) + "\n";
                row++;
                cursor.moveToNext();
            }
        }
        return output;
    }

    public static final String tableContents(SQLiteDatabase db, String tableName)
    {
        String output = "";
        Cursor rowCursor = null;
        String header = "===== TABLE " + tableName + "=====";
        output += header + "\n";
        rowCursor = db.query(tableName, null, null, null, null, null, null);
        output += SqlUtil.cursorInfo(rowCursor) + "\n";
        String footer = Text.padString("=", header.length());
        output += footer + "\n";
        return output;
    }

    public static final void logSchema(SQLiteDatabase db)
    {
        logContents(db, 1);
    }

    /**
     * This will log all possible information about the schema from the given database.
     * @param db
     */
    public static final void logSchema(SQLiteDatabase db, int depth)
    {
        Cursor tableCursor = null;
        Cursor indexCursor = null;

        try {
            tableCursor = getTablesCursor(db);
            Logger.info("###### TABLES ######", depth+1);
            logCursorInfo(tableCursor, depth+1);

            indexCursor = getIndicies(db);
            Logger.info("##### INDICIES #####", depth+1);


            if (tableCursor.moveToFirst())
            {
                while(!tableCursor.isAfterLast()) {
                    String tableName = tableCursor.getString(tableCursor.getColumnIndex(COL_NAME));
                    logSchema(db, tableName, depth+1);
                    tableCursor.moveToNext();
                }
            }

        } catch (Exception e) {
            Logger.error(e.getMessage(), depth+1, e);
        } finally {
            closeCursor(tableCursor);
            closeCursor(indexCursor);
        }

    }


    public static final void logSchema(SQLiteDatabase db, String tableName)
    {
        logSchema(db, tableName, 1);
    }

    public static final void logSchema(SQLiteDatabase db, String tableName, int depth)
    {
        String schemaInfo = tableContents(db, tableName);
        Logger.info("===== TABLE " + tableName + " Schema =====");
        Logger.info(schemaInfo);
    }

    /**
     * This will log the schema for a specific table
     * @param db
     * @param tableName
     */
    public static final void logContents(SQLiteDatabase db, String tableName)
    {
        logContents(db, tableName, 1);
    }

    public static final void logContents(SQLiteDatabase db, String tableName, int depth)
    {
        Cursor rowCursor = null;
        String header = "===== TABLE " + tableName + "=====";
        Logger.info(header, depth+1);
        try {
            rowCursor = db.query(tableName, null, null, null, null, null, null);
            SqlUtil.logCursorInfo(rowCursor, depth+1);
            String footer = Text.padString("=", header.length());
            Logger.info(footer, depth+1);
        } catch (Exception e) {
            Logger.error(e.getMessage(), depth+1, e);
        } finally {
            closeCursor(rowCursor);
        }
    }

    public static final void logContents(SQLiteDatabase db)
    {
        logContents(db, 1);
    }

    /**
     * This will log all possible information about the schema from the given database.
     * @param db
     */
    public static final void logContents(SQLiteDatabase db, int depth)
    {
        Cursor tableCursor = null;
        Cursor indexCursor = null;

        try {
            tableCursor = getTablesCursor(db);
            Logger.info("###### TABLES ######", depth+1);
            logCursorInfo(tableCursor, depth+1);

            indexCursor = getIndicies(db);
            Logger.info("##### INDICIES #####", depth+1);


            if (tableCursor.moveToFirst())
            {
                while(!tableCursor.isAfterLast()) {
                    String tableName = tableCursor.getString(tableCursor.getColumnIndex(COL_NAME));
                    logContents(db, tableName, depth+1);
                    tableCursor.moveToNext();
                }
            }

        } catch (Exception e) {
            Logger.error(e.getMessage(), depth+1, e);
        } finally {
            closeCursor(tableCursor);
            closeCursor(indexCursor);
        }

    }

    /**
     * This will return a list of all columns for the specified table ordered by their
     * column index. If there is an error accessing the table in the database null will be returned.
     * @param db
     * @param table
     * @return null if there is an error else it returns an array of columns.
     */
    public static final String[] getTableColumns(SQLiteDatabase db, String table)
    {
        String[] columns;
        Cursor cursor = null;

        try {
            cursor = db.query(table, null, null, null, null, null, null, "1");
            int numColumns = cursor.getColumnCount();
            columns = new String[numColumns];
            for(int i=0; i<numColumns; i++)
            {
                columns[i] = cursor.getColumnName(i);
            }
        } catch (Exception e) {
            columns = null;
        } finally {
            closeCursor(cursor);
        }
        return columns;
    }

    public static final Set<String> getTableColumnsSet(SQLiteDatabase db, String table)
    {
        String[] columns = getTableColumns(db, table);
        Set<String> columnSet = new TreeSet<>();
        for(String col : columns)
        {
            columnSet.add(col);
        }
        return columnSet;
    }

    /**
     * This will return a set of all tables contained in the database
     * @param db
     * @return
     */
    public static final Set<String> getTables(SQLiteDatabase db)
    {
        Set<String> tables = new HashSet<>();
        Cursor tableCursor = null;
        Cursor indexCursor = null;

        tables.add(TBL_SQLITE_MASTER);  // ALL sqlite databases have this readonly table that defines all the other tables.

        try {
            tableCursor = getTablesCursor(db);

            if (tableCursor.moveToFirst())
            {
                while(!tableCursor.isAfterLast()) {
                    String tableName = tableCursor.getString(tableCursor.getColumnIndex(COL_NAME));
                    tables.add(tableName);
                    tableCursor.moveToNext();
                }
            }

        } catch (Exception e) {
            Logger.error(e.getMessage(), 1, e);
            Logger.error("Returning an empty set of table names", 1, e);
            tables = new HashSet<String>();
        } finally {
            closeCursor(tableCursor);
            closeCursor(indexCursor);
        }
        return tables;
    }

    public static final String fieldAsIntegerPrimaryKeyAutoIncrementSql(String name)
    {
        return String.format("\t'%s' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE\n", name);
    }
    public static final String fieldAsSql(String name, String type)
    {
        return String.format("\t'%s %s'\n", name, type);
    }

    /**
     * This is used to create the SQL we will use to create a table if it doesn't already exist
     * @param tableName     the name of the table
     * @param primaryIdKey  the name of the auto imcremented primary key column
     * @param otherTypes    all of the other columns with mapped to their types
     * @return SQL statement to execute on the database
     */
    public static final String createTableIfNotExistsSql(String tableName, String primaryIdKey, Map<String, String> otherTypes)
    {
        String start = "CREATE TABLE IF NOT EXISTS '%s' (\n";
        String primaryKey = fieldAsIntegerPrimaryKeyAutoIncrementSql(primaryIdKey);
        String fields = "";
        for(String field : otherTypes.keySet())
        {
            String type = otherTypes.get(field);
            fields += fieldAsSql(field, type);
        }
        String end = ");\n";
        return start + primaryIdKey + fields + end;
    }

    public static final FieldSchema getFieldSchema(SQLiteDatabase db, Cursor cursor, int index)
    {
        int typeCode = cursor.getType(index);
        FieldSchema.Type fieldType = getType(typeCode);

        FieldSchema schema = null;

        return null;
    }

    /**
     * This will return the sql used to create a table
     * @param db
     * @param tableName
     * @return
     */
    public static final String getTableSql(SQLiteDatabase db, String tableName)
    {
        Cursor cursor = null;
        String tableSql = null;

        try {
            cursor = db.query(
                    TBL_SQLITE_MASTER,
                    new String[] {COL_SQL},
                    COL_NAME + "=?",
                    new String[] {COL_NAME},
                    null,
                    null,
                    null
            );
            tableSql = cursor.getString(cursor.getColumnIndex(COL_SQL));
        } catch (Exception e) {
            tableSql = null;
        } finally {
            closeCursor(cursor);
        }
        return tableSql;
    }

    private static final FieldSchema.Type getType(int cursorTypeIndex)
    {
        switch(cursorTypeIndex)
        {
            case Cursor.FIELD_TYPE_INTEGER:
                return FieldSchema.Type.INTEGER;
            case Cursor.FIELD_TYPE_FLOAT:
                return FieldSchema.Type.REAL;
            case Cursor.FIELD_TYPE_BLOB:
                return FieldSchema.Type.BLOB;
            case Cursor.FIELD_TYPE_NULL:
            case Cursor.FIELD_TYPE_STRING:
            default:
                return FieldSchema.Type.TEXT;
        }
    }

    /**
     * This will get all columns data and put it into a {@link ContentValues}
     * @param cursor
     * @return
     */
    public static final ContentValues readContentValues(Cursor cursor)
    {
        ContentValues contentValues = new ContentValues();
        for(int col=0; col<cursor.getColumnCount(); col++)
        {
            contentValues.put(
                cursor.getColumnName(col),
                cursor.getBlob(col)
            );
        }
        return contentValues;
    }
}



