package com.soupthatisthick.encounterbuilder.model.lookup;

import soupthatisthick.encounterapp.R;

/**
 * Created by Owner on 2/7/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public enum Faction {
    NONE                    (R.drawable.no_faction_banner_trimmed, R.string.faction_none),
    ORDER_OF_THE_GAUNTLET   (R.drawable.order_of_gauntlet_banner_trimmed, R.string.faction_order_of_gauntlet),
    LORDS_ALLIANCE          (R.drawable.lords_alliance_banner_trimmed, R.string.faction_lords_alliance),
    EMERALD_ENCLAVE         (R.drawable.emerald_enclave_banner_trimmed, R.string.faction_emerald_enclave),
    HARPERS                 (R.drawable.harpers_banner_trimmed, R.string.faction_harpers),
    ZHENTARIM               (R.drawable.zhentarim_banner_trimmed, R.string.faction_zhentarim);

    /**
     * Resource id for the display drawable to use for this faction
     */
    public final int drawable;

    /**
     * Resource id for the display string to use for this faction
     */
    public final int displayString;


    private Faction(int drawable, int displayString)
    {
        this.drawable = drawable;
        this.displayString = displayString;
    }

}
