package com.soupthatisthick.util.model;

import android.net.ParseException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.soupthatisthick.util.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Owner on 5/11/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class LogEntry {

    public String text;
    public String month, day, hour, min, sec, millisec, processId, threadId, packageName, logLevel, tag, message;

    private static final int GROUP_MONTH = 1;
    private static final int GROUP_DAY = 2;
    private static final int GROUP_HOUR = 3;
    private static final int GROUP_MINUTE = 4;
    private static final int GROUP_SECOND = 5;
    private static final int GROUP_MILLI_SECOND = 6;
    private static final int GROUP_PROCESS_ID = 7;
    private static final int GROUP_THREAD_ID = 8;
    private static final int GROUP_PACKAGE_NAME = 9;
    private static final int GROUP_LOG_LEVEL = 10;
    private static final int GROUP_TAG = 11;
    private static final int GROUP_MESSAGE = 12;

    // 1- month
    // 2- day
    // 3- hour
    // 4- min
    // 5- sec
    // 6- millisec
    // 7- process id
    // 8- thread id
    // 9- package name
    // 10- log level
    // 11- tag
    // 12- message

    private static final String REGEX = "(\\d+)-(\\d+) (\\d+):(\\d+):(\\d+)\\.(\\d+) (\\d+)-(\\d+)/([\\w\\.]+) (.)/(.*): (.*)";

    private LogEntry()
    {
        // Private method. Access only through the parse method.
    }

    /**
     * Public method used to parse log text and return an appropriate object.
     * The regular expression used is {@value #REGEX}
     * @param line is a {@link String} that matches the regular expression {@value #REGEX}
     * @return a {@link LogEntry}
     * @throws Exception when it can't be parsed
     */
    public static final LogEntry parseText(@Nullable String line) throws Exception {

        LogEntry entry = new LogEntry();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches())
        {
//            throw new RuntimeException(String.format("Input does not match the expected regex!\n - regex = \"%s\"\n - input = \"%s\"", REGEX, line));
//            Logger.warning(String.format("Input does not match the expected regex!\n - regex = \"%s\"\n - input = \"%s\"", REGEX, line));
        }

        Logger.info(matcher.toString());

        entry.text = line;
        try {
            entry.month = matcher.group(GROUP_MONTH);
            entry.day = matcher.group(GROUP_DAY);
            entry.hour = matcher.group(GROUP_HOUR);
            entry.min = matcher.group(GROUP_MINUTE);
            entry.sec = matcher.group(GROUP_SECOND);
            entry.millisec = matcher.group(GROUP_MILLI_SECOND);
            entry.processId = matcher.group(GROUP_PROCESS_ID);
            entry.threadId = matcher.group(GROUP_THREAD_ID);
            entry.packageName = matcher.group(GROUP_PACKAGE_NAME);
            entry.logLevel = matcher.group(GROUP_LOG_LEVEL);
            entry.tag = matcher.group(GROUP_TAG);
            entry.message = matcher.group(GROUP_MESSAGE);
        } catch (Exception e) {
//            Logger.error("Parse Exception of Log entry.", e);
        }

        return entry;
    }

    public static final String logString(String time, String tag, String message)
    {
        String output = "\n*** LOG ENTRY ***\n";
        output += String.format(" - time    = %s\n", time);
        output += String.format(" - tag     = %s\n" + tag);
        output += String.format(" - message = %s\n" + message);
        return output;
    }

    public void setTime(
        @NonNull String month,
        @NonNull String day,
        @NonNull String hour,
        @NonNull String min,
        @NonNull String sec,
        @NonNull String millisec
    ) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.millisec = millisec;
    }

    public void setExecution(@NonNull String processId, @NonNull String threadId)
    {
        this.processId = processId;
        this.threadId = threadId;
    }

    public void setPackageName(@NonNull String packageName)
    {
        this.packageName = packageName;
    }

    public void setLogLevel(@NonNull String logLevel)
    {
        this.logLevel = logLevel;
    }

    public String getTime()
    {
        return "" + month + "-" + day + " " + hour + ":" + min + ":" + sec + "." + millisec;
    }

    public String getHeader()
    {
        return "" + packageName + " " + logLevel + "/" + tag;
    }

    public String getContent()
    {
        return "" + message;
    }

    @Override
    public String toString()
    {
//        return String.format(
//            "%s-%s %s:%s:%s.%s %s-%s/%s %s/%s: %s",
//            month,
//            day,
//            hour,
//            min,
//            sec,
//            millisec,
//            processId,
//            threadId,
//            packageName,
//            logLevel,
//            tag,
//            message
//        );
        return "" + month + "-" + day + " " + hour + ":" + min + ":" + sec + "." + millisec + "/" + packageName + " " + logLevel + "/" + tag + ":" + message;
    }

}
