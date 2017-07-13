package com.soupthatisthick.util;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.soupthatisthick.encounterbuilder.util.Text;

/**
 * Created by Owner on 1/29/2017.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */

public class Logger {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getSimpleName());

    public static class CodeSpot
    {
        public final StackTraceElement stackTraceElement;
        public final int height;

        public CodeSpot(StackTraceElement stackTraceElement, int height)
        {
            this.stackTraceElement = stackTraceElement;
            this.height = height;
        }

        @Override
        public String toString()
        {
            return "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") ";
        }

    }

    private static final String tabString(int height)
    {
        return Text.padString(".", height);
    }

    public static final CodeSpot getCodeSpot(int depth)
    {
        try {
            throw new Exception();
        } catch (Exception e)
        {
            StackTraceElement topElement = e.getStackTrace()[depth+1];
            return new CodeSpot(topElement, e.getStackTrace().length - (depth+1));
        }
    }

    private static final String getTag(CodeSpot element)
    {
        return element.toString();
    }

    public static final void debug(String text)
    {
        debug(text, 1);
    }


    public static final void debug(String text, int depth)
    {
        CodeSpot src = getCodeSpot(depth+1);
        String TAG = getTag(src);
        try {
            Log.d(TAG, text);
        } catch (Exception e)
        {
            logger.info(TAG + text);
        }
    }

    public static final void error(String text, @Nullable Exception e)
    {
        error(text, 1, e);
    }

    public static final void error(String text, int depth, @Nullable Exception e)
    {
        CodeSpot src = getCodeSpot(depth+1);
        String TAG = getTag(src);
        try {
            if (e!=null) {
                Log.e(TAG, text, e);
            } else {
                Log.e(TAG, text);
            }
        } catch (Exception ex)
        {
            logger.severe(TAG + text);
        }

    }

    public static final void info(String text)
    {
        info(text, 1);
    }

    public static final void info(String text, int depth)
    {
        CodeSpot src = getCodeSpot(depth+1);
        String TAG = getTag(src);
        try {
            Log.i(TAG, text);
        } catch (Exception e)
        {
            logger.info(TAG + text);
        }

    }

    public static final void warning(String text)
    {
        warning(text, 1);
    }

    public static final void warning(String text, int depth)
    {
        CodeSpot src = getCodeSpot(depth+1);
        String TAG = getTag(src);
        try {
            Log.w(TAG, text);
        } catch (Exception e)
        {
            logger.warning(TAG + text);
        }
    }

    public static final void toast(Application applicationContext, String text)
    {
        toast(applicationContext, text, 1);
    }

    public static final void toast(Application applicationContext, int stringResourceId, Object... args)
    {
        String text = applicationContext.getString(stringResourceId, args);
        toast(applicationContext, text, 1);
    }

    public static final void toast(Application applicationContext, String text, int depth)
    {
        CodeSpot src = getCodeSpot(depth+1);
        String TAG = getTag(src);
        try {
            Log.i(TAG, text);
        } catch (Exception e)
        {
            logger.info(TAG + text);
            Toast.makeText(applicationContext, text, Toast.LENGTH_LONG);
        }

    }

    /**
     * Excluding the depth of this function it will log the two code spots previous to it on the stack track.
     * IE. The previous scope to the scope containing the call of this method will be displayed in the logs.
     * This is useful for determining where a particular method was called from.
     */
    public static final CodeSpot getPreviousCodeSpot()
    {
        return getCodeSpot(2);
    }

    public static final void logCodeTransition()
    {
        Logger.debug(getCodeSpot(2) + " => " + getCodeSpot(1));
    }

    private static final String stackTraceString(int depth)
    {
        String output = "";
        try {
            throw new RuntimeException("");
        } catch (Exception e) {
            StackTraceElement[] elements = e.getStackTrace();

            int sourceWidth = 0, methodWidth = 0;

            for(int i=depth+1; i<elements.length; i++) {
                CodeSpot codeSpot = new CodeSpot(elements[i], i);
                sourceWidth = Math.max(sourceWidth, codeSpot.toString().length());
                methodWidth = Math.max(sourceWidth, (codeSpot.stackTraceElement.getMethodName() + "()").length());
            }
            sourceWidth++;
            methodWidth++;

            // print the data
            for(int i=depth+1; i<elements.length; i++)
            {
                CodeSpot codeSpot = new CodeSpot(elements[i], i);
                output +=
                        Text.fString(codeSpot.toString(), sourceWidth) + ": " +
                        Text.fString(codeSpot.stackTraceElement.getMethodName() + "()", methodWidth) + "\n";
            }
        }
        return output;
    }

    /**
     * This will log the stack trace up to the stack level value below
     * the calling of this method. By calling this method with a depth of 0 you will
     * include everything up to but excluding the call to this method.
     * @param depth
     */
    public static final void logStackTrace(int depth)
    {
        Logger.info(stackTraceString(depth+1), depth+1);
    }

    /**
     * This will log the stack trace up to the point of calling this method but not including this method.
     */
    public static final void logStackTrace()
    {
        logStackTrace(1);
    }


    public static final void title(String text)
    {
        title(text, 1);
    }
    public static final void title(String text, int depth)
    {
        title(text, 80, depth+1);
    }
    public static final void title(String text, int rowLength, int depth)
    {
        Logger.info(Text.titleString(text,'*', '=', '|', ' ', rowLength), depth+1);
    }
}
