package com.soupthatisthick.encounterbuilder.util.monitor;

import java.util.Date;

/**
 * Created by Owner on 2/16/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class MyLock {

    private Key key = null;
    private final long sleepTime;

    public static final class Key
    {
        private final Date timeLocked;

        public Key()
        {
            timeLocked = new Date();
        }

        @Override
        public boolean equals(Object token)
        {
            if (token instanceof Key)
            {
                return ((Key) token).timeLocked.equals(this.timeLocked);
            } else {
                return false;
            }
        }

    }

    public MyLock()
    {
        this(1000); // Retried enough to wait 1 second
    }
    public MyLock(long sleepTime)
    {
        this.sleepTime = sleepTime;
    }

    public Key waitFor()
    {
        Key key;
        Date started = new Date();
        Date current;

        do {
            current = new Date();
            key = request();
        } while (   (key==null)
                &&  (
                        (sleepTime<1)
                    ||  ((current.getTime() - started.getTime()) < sleepTime)
                    )
                );
        if (key==null)
        {
            throw new RuntimeException("Time out waiting for lock");
        }
        return key;
    }

    public synchronized Key request()
    {
        if (key !=null) {
            return null;
        } else {
            key = new Key();
            return key;
        }
    }

    public synchronized void release(Key key)
    {
        if (key !=null && this.key.equals(key))
        {
            this.key = null;
        }
    }

}
