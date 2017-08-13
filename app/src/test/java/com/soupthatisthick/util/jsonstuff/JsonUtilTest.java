package com.soupthatisthick.util.jsonstuff;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.json.JsonUtil;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 8/6/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class JsonUtilTest {



    @Test
    public void testJsonToObjectAndBack_TestObj() {
        TestObj obj = new TestObj();

        String json1 = JsonUtil.toJson(obj, true);
        Logger.info(json1);

        TestObj loaded = JsonUtil.toObject(json1, TestObj.class);

        String json2 = JsonUtil.toJson(loaded, true);

        assertEquals("Json match", json1, json2);
    }

    @Test
    public void testJsonToObjectAndBack_String() {
        String obj = "The quick brown fox jumped over the lazy dog";

        String json1 = JsonUtil.toJson(obj, true);
        Logger.info(json1);

        String loaded = JsonUtil.toObject(json1, String.class);

        String json2 = JsonUtil.toJson(loaded, true);

        assertEquals("Json match", json1, json2);

    }
}