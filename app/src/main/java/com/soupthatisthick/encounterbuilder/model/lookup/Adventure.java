package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

public class Adventure extends DaoModel {

    private Long id;
    private String code;
    private String levelBand;
    private String runtimeHours;
    private String title;
    private String notes;
    private Long seasonId;
    private String description;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevelBand() {
        return levelBand;
    }

    public void setLevelBand(String levelBand) {
        this.levelBand = levelBand;
    }

    public String getRuntimeHours() {
        return runtimeHours;
    }

    public void setRuntimeHours(String runtimeHours) {
        this.runtimeHours = runtimeHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
