package com.soupthatisthick.encounterbuilder.exception;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

public class DaoModelException extends Exception {
    DaoModel model;
    public DaoModelException(DaoModel model, String message) {
        super(message + "\n" + ((model==null) ? ("model is null") : model.json()));
        this.model = model;
    }
}
