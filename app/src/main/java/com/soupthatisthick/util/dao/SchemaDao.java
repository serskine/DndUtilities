package com.soupthatisthick.util.dao;

import com.soupthatisthick.encounterbuilder.model.TableSchema;

/**
 * Created by Owner on 4/17/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public interface SchemaDao<Record> extends Dao<Record> {

    TableSchema getSchema();
}
