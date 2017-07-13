package com.soupthatisthick;

import com.soupthatisthick.util.Logger;
import com.soupthatisthick.util.dao.SqlUtil;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Owner on 5/28/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class SqlUtilTest {

    private String[] included, excluded, columns, order;

    private static final String COL = "COL";
    private static final String WORD = "WORD";

    private static final String[] SINGLE_COL = new String[] {"COL"};
    private static final String[] DOUBLE_COL = new String[] {"COL_1", "COL_2"};
    private static final String[] TRIPLE_COL = new String[] {"COL_1", "COL_2", "COL_3"};

    @Before
    public void onSetup() {
        included = new String[] {
                "%include%"
        };
        excluded = new String[] {
                "%exclude%"
        };
        columns = new String[] {
                "properties",
                "name",
                "type"
        };
        order = new String[] {
                "name"
        };

    }

    @Test
    public void testAnyIncludedWordAndNoExcludedWords()
    {

        SqlUtil.Query query = SqlUtil.anyIncludedWordAndNoExcludedWords(included, excluded, columns, order);
        Logger.debug(query.toOneLinestring());
    }

    @Test
    public void testIsNull()
    {
        String output = SqlUtil.isNull("COL");
        Logger.info(output);
    }

    @Test
    public void testLike()
    {
        String output;

        output = SqlUtil.likeAnyUppercase(WORD, SINGLE_COL);
        Logger.info(output);

        output = SqlUtil.likeAnyUppercase(WORD, DOUBLE_COL);
        Logger.info(output);

        output = SqlUtil.likeAnyUppercase(WORD, TRIPLE_COL);
        Logger.info(output);


    }

    @Test
    public void testNotLikeCol()
    {
        String output = SqlUtil.likeColumnUppercaseAndNotNull(COL);
        Logger.info(output);
    }

}
