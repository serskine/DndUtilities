package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/5/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
// @Entity(nameInDb = "GODS")
public class God extends DaoModel {

    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "alignment")
    private String alignment;

    // @Property(nameInDb = "domains")
    private String domains;

    // @Property(nameInDb = "symbol")
    private String symbol;

    // @Property(nameInDb = "source")
    private String source;

    // @Property(nameInDb = "notes")
    private String notes;

    // @Generated(hash = 866637231)
    public God(Long id, String name, String alignment, String domains,
            String symbol, String source, String notes) {
        this.id = id;
        this.name = name;
        this.alignment = alignment;
        this.domains = domains;
        this.symbol = symbol;
        this.source = source;
        this.notes = notes;
    }

    // @Generated(hash = 691480775)
    public God() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
