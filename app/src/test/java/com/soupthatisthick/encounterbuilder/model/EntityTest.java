package com.soupthatisthick.encounterbuilder.model;

import com.soupthatisthick.encounterbuilder.model.lookup.Entity;
import com.soupthatisthick.encounterbuilder.util.sort.Category;
import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.soupthatisthick.encounterbuilder.util.sort.Category.BACKGROUND;
import static com.soupthatisthick.encounterbuilder.util.sort.Category.CHALLENGE_RATING;
import static com.soupthatisthick.encounterbuilder.util.sort.Category.DEFAULT;
import static junit.framework.Assert.assertEquals;

public class EntityTest {

    @Before
    public void setup() {}

    @After
    public void tearDown() {}

    @Test
    public void defaultEntity() {
        Entity entity = new Entity();
        Logger.debug("Entity: " + describeEntity(entity));
        assertEquals("Should match: ", DEFAULT, entity.getChildCategory());
    }

    @Test
    public void getCategoryAfterSetting() {
        final Long VALID_ID = new Long(1L);
        Set<Category> noColumnsAssigned = new HashSet<>();
        noColumnsAssigned.add(DEFAULT);
        noColumnsAssigned.add(BACKGROUND);
        noColumnsAssigned.add(CHALLENGE_RATING);

        for(Category expected : Category.values()) {
            Entity entity = new Entity();
            Logger.debug("Entity: " + describeEntity(entity));
            try {
                entity.setCategoryColumnId(expected, VALID_ID);
            } catch (Exception e) {
                Logger.error(e.getMessage(), e);
                assertEquals(e.getMessage() + "\nnoColumnsAssigned.contains(" + expected + ")", true, noColumnsAssigned.contains(expected));
                continue;
            }

            Category observed = entity.getChildCategory();
            assertEquals("Should match: ", expected, observed);
        }

    }

    String describeEntity(Entity entity) {
        if (entity==null) {
            return "null entity";
        } else {
            return JsonUtil.toJson(entity, true);
        }
    }

}
