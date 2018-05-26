package com.soupthatisthick.encounterbuilder.model.builder;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Owner on 6/20/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

// @Entity
public class EncounterEnemy {

    // @Id
    // @Property(nameInDb = "id")
    private Long id;

    // @Property(nameInDb = "encounterId")
    private Long encounterId;

    // @Property(nameInDb = "challengeId")
    private Long challengeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(Long encounterId) {
        this.encounterId = encounterId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }
}
