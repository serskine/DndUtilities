package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/1/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

// @Entity(nameInDb = "CONTACTS")
public class Contact extends DaoModel {

    // @Property(nameInDb = "name")
    private String name;

    // @Property(nameInDb = "dci")
    private String dci;

    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Generated(hash = 290474664)
    public Contact(String name, String dci, Long id) {
        this.name = name;
        this.dci = dci;
        this.id = id;
    }

    // @Generated(hash = 672515148)
    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDci() {
        return dci;
    }

    public void setDci(String dci) {
        this.dci = dci;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
