package com.soupthatisthick.encounterbuilder.ifaces;

import android.support.annotation.NonNull;

/**
 * Created by Owner on 2/10/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public interface Crud<Key, Value> {

    /**
     * This will return a unique key of type {@link Key} for a record within the back-end store
     * @return the {@link Key} identifying the model in the back end store
     */
    Value create();

    /**
     * This will load the model values from the back end store.
     * @param key is a {@link Key} that identifies which model value to load
     * @return a {@link Value} that represents the data associated with the {@link Key} used to identify it.
     */
    Value load(@NonNull Key key);

    /**
     * This is used to change the values associated with the specified key
     * @param key is a {@link Key} used to identify the model to update
     * @param value is the {@link Value} that is the new data
     * @return true if there was a record to update. Else it returns false
     */
    boolean update(@NonNull Key key, @NonNull Value value);

    /**
     * This will delete the values associated with the specified key
     * @param key is a {@link Key} used to identify the model in the backend store
     * @return true if the {@link Value} model associated with the specified {@link Key} identifier has been deleted.
     */
    boolean delete(@NonNull Key key);

    /**
     * This will clear all records from the backend store.
     */
    void clear();

}


