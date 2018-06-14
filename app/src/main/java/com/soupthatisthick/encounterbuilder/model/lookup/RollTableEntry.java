package com.soupthatisthick.encounterbuilder.model.lookup;

import com.soupthatisthick.encounterbuilder.model.DaoModel;

import java.util.Objects;


/**
 * Created by Owner on 5/9/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
@SuppressWarnings("SpellCheckingInspection")
public class RollTableEntry extends DaoModel {

    private Long id;
    private Long tableId;
    private Long reRollTableId;
    private Integer minRoll;
    private Integer maxRoll;
    private Integer dieQty;
    private Integer dieSize;
    private Integer rollMul;
    private Integer rollAvg;
    private String result;
    private String unit;
    private Double unitGpValue;

    public RollTableEntry(Long id, Long tableId, Long reRollTableId, Integer minRoll, Integer maxRoll,
        Integer dieQty, Integer dieSize, Integer rollMul, Integer rollAvg, String result, String unit,
        Double unitGpValue
    ) {
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

    public RollTableEntry() {
        // Do nothing
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

    public String toString() {
        String rollText =   (getMinRoll().equals(getMaxRoll()))
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
