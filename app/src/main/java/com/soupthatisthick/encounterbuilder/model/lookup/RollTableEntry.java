package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

// import org.greenrobot.greendao.annotation.Entity;
// import org.greenrobot.greendao.annotation.Id;
// import org.greenrobot.greendao.annotation.Property;
// import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
// @Entity(nameInDb = "ROLL_TABLE_ENTRY")
public class RollTableEntry extends DaoModel {

    // @Id
    // @Property(nameInDb = "entryId")
    private Long id;

    // @Property(nameInDb = "tableId")
    private Long tableId;

    // @Property(nameInDb = "rerollTableId")
    private Long reRollTableId;

    // @Property(nameInDb = "minRoll")
    private Integer minRoll;

    // @Property(nameInDb = "maxRoll")
    private Integer maxRoll;

    // @Property(nameInDb = "dieQty")
    private Integer dieQty;

    // @Property(nameInDb = "dieSize")
    private Integer dieSize;

    // @Property(nameInDb = "rollMul")
    private Integer rollMul;

    // @Property(nameInDb = "rollAvg")
    private Integer rollAvg;

    // @Property(nameInDb = "result")
    private String result;

    // @Property(nameInDb = "unit")
    private String unit;

    // @Property(nameInDb = "unitGpValue")
    private Double unitGpValue;

    // @Generated(hash = 132354382)
    public RollTableEntry(Long id, Long tableId, Long reRollTableId, Integer minRoll, Integer maxRoll,
            Integer dieQty, Integer dieSize, Integer rollMul, Integer rollAvg, String result, String unit,
            Double unitGpValue) {
        this.id = id;
        this.tableId = tableId;
        this.reRollTableId = reRollTableId;
        this.minRoll = minRoll;
        this.maxRoll = maxRoll;
        this.dieQty = dieQty;
        this.dieSize = dieSize;
        this.rollMul = rollMul;
        this.rollAvg = rollAvg;
        this.result = result;
        this.unit = unit;
        this.unitGpValue = unitGpValue;
    }

    // @Generated(hash = 466988778)
    public RollTableEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getReRollTableId() {
        return reRollTableId;
    }

    public void setReRollTableId(Long reRollTableId) {
        this.reRollTableId = reRollTableId;
    }

    public Integer getMinRoll() {
        return minRoll;
    }

    public void setMinRoll(Integer minRoll) {
        this.minRoll = minRoll;
    }

    public Integer getMaxRoll() {
        return maxRoll;
    }

    public void setMaxRoll(Integer maxRoll) {
        this.maxRoll = maxRoll;
    }

    public Integer getDieQty() {
        return dieQty;
    }

    public void setDieQty(Integer dieQty) {
        this.dieQty = dieQty;
    }

    public Integer getDieSize() {
        return dieSize;
    }

    public void setDieSize(Integer dieSize) {
        this.dieSize = dieSize;
    }

    public Integer getRollMul() {
        return rollMul;
    }

    public void setRollMul(Integer rollMul) {
        this.rollMul = rollMul;
    }

    public Integer getRollAvg() {
        return rollAvg;
    }

    public void setRollAvg(Integer rollAvg) {
        this.rollAvg = rollAvg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // @Override
    public String toString()
    {
        String rollText =   (getMinRoll()==getMaxRoll())
                            ?   ("" + getMinRoll())
                            :   ("" + getMinRoll() + "-" + getMaxRoll());
        String rerollText = (getReRollTableId()==null) ? "" : " => Table(" + getReRollTableId() + ")";
        String qtyRoll = getDieQty() + "d" + getDieSize() + "x" + getRollMul() + "(" + getRollAvg() + ")";


        return String.format("Entry (%s), table(%s) [%s] %s %s%s",
            ("" + getId()),
            ("" + getTableId()),
            rollText,
            qtyRoll,
            result,
            rerollText
        );
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitGpValue() {
        return unitGpValue;
    }

    public void setUnitGpValue(Double unitGpValue) {
        this.unitGpValue = unitGpValue;
    }
}
