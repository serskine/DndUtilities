package com.soupthatisthick.encounterbuilder.model;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum DieSize {
    D4(4, R.mipmap.dice_d4_opaque),
    D6(6, R.mipmap.dice_d6_opaque),
    D8(8, R.mipmap.dice_d8_opaque),
    D10(10, R.mipmap.dice_d10_opaque),
    D12(12, R.mipmap.dice_d12_opaque),
    D20(20, R.mipmap.dice_d20_opaque);

    public final int imgResource;
    public final int numFaces;

    private DieSize(int numFaces, int imgResource)
    {
        this.numFaces = numFaces;
        this.imgResource = imgResource;
    }
};
