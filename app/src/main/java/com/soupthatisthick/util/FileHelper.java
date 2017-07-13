package com.soupthatisthick.util;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.channels.FileChannel;

/**
 * @author Danny Remington - MacroSolve
 *
 *         Helper class for common tasks using files.
 *
 */
public class FileHelper {
    /**
     * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
     * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then
     * it will be replaced with a copy of <i><b>fromFile</b></i>. The name and
     * path of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
     * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
     * operation.
     *
     * @param fromFile
     *            - InputStream for the file to copy from.
     * @param toFile
     *            - InputStream for the file to copy to.
     */
    public static void copyFile(InputStream fromFile, OutputStream toFile) throws IOException {
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;

        try {
            while ((length = fromFile.read(buffer)) > 0) {
                toFile.write(buffer, 0, length);
            }
        }
        // Close the streams
        finally {
            try {
                if (toFile != null) {
                    try {
                        toFile.flush();
                    } finally {
                        toFile.close();
                    }
                }
            } finally {
                if (fromFile != null) {
                    fromFile.close();
                }
            }
        }
    }

    /**
     * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
     * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then
     * it will be replaced with a copy of <i><b>fromFile</b></i>. The name and
     * path of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
     * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
     * operation.
     *
     * @param fromFile
     *            - String specifying the path of the file to copy from.
     * @param toFile
     *            - String specifying the path of the file to copy to.
     */
    public static void copyFile(String fromFile, String toFile) throws IOException {
        copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
    }

    /**
     * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
     * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then
     * it will be replaced with a copy of <i><b>fromFile</b></i>. The name and
     * path of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
     * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
     * operation.
     *
     * @param fromFile
     *            - File for the file to copy from.
     * @param toFile
     *            - File for the file to copy to.
     */
    public static void copyFile(File fromFile, File toFile) throws IOException {
        copyFile(new FileInputStream(fromFile), new FileOutputStream(toFile));
    }

    /**
     * Creates the specified <i><b>toFile</b></i> that is a byte for byte a copy
     * of <i><b>fromFile</b></i>. If <i><b>toFile</b></i> already existed, then
     * it will be replaced with a copy of <i><b>fromFile</b></i>. The name and
     * path of <i><b>toFile</b></i> will be that of <i><b>toFile</b></i>. Both
     * <i><b>fromFile</b></i> and <i><b>toFile</b></i> will be closed by this
     * operation.
     *
     * @param fromFile
     *            - FileInputStream for the file to copy from.
     * @param toFile
     *            - FileInputStream for the file to copy to.
     */
    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = fromFile.getChannel();
        FileChannel toChannel = toFile.getChannel();

        try {
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

    /**
     * Parses a file containing sql statements into a String array that contains
     * only the sql statements. Comments and white spaces in the file are not
     * parsed into the String array. Note the file must not contained malformed
     * comments and all sql statements must end with a semi-colon ";" in order
     * for the file to be parsed correctly. The sql statements in the String
     * array will not end with a semi-colon ";".
     *
     * @param sqlFile
     *            - String containing the path for the file that contains sql
     *            statements.
     *
     * @return String array containing the sql statements.
     */
    public static String[] parseSqlFile(String sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(new FileReader(sqlFile)));
    }

    /**
     * Parses a file containing sql statements into a String array that contains
     * only the sql statements. Comments and white spaces in the file are not
     * parsed into the String array. Note the file must not contained malformed
     * comments and all sql statements must end with a semi-colon ";" in order
     * for the file to be parsed correctly. The sql statements in the String
     * array will not end with a semi-colon ";".
     *
     * @param sqlFile
     *            - InputStream for the file that contains sql statements.
     *
     * @return String array containing the sql statements.
     */
    public static String[] parseSqlFile(InputStream sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(new InputStreamReader(sqlFile)));
    }

    /**
     * Parses a file containing sql statements into a String array that contains
     * only the sql statements. Comments and white spaces in the file are not
     * parsed into the String array. Note the file must not contained malformed
     * comments and all sql statements must end with a semi-colon ";" in order
     * for the file to be parsed correctly. The sql statements in the String
     * array will not end with a semi-colon ";".
     *
     * @param sqlFile
     *            - Reader for the file that contains sql statements.
     *
     * @return String array containing the sql statements.
     */
    public static String[] parseSqlFile(Reader sqlFile) throws IOException {
        return parseSqlFile(new BufferedReader(sqlFile));
    }

    /**
     * This will attempt to take a string representing a file and split it up into seperate sql statements.
     * @param statements
     * @return
     * @throws IOException
     */
    public static String[] parseSqlStatements(String statements) throws IOException {
        StringBuilder sql = new StringBuilder();
        String multiLineComment = null;

        String[] lines = statements.split("\\n");

        for(String line : lines)
        {
            line = line.trim();

            // Check for start of multi-line comment
            if (multiLineComment == null) {
                // Check for first multi-line comment type
                if (line.startsWith("/*")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "/*";
                    }
                    // Check for second multi-line comment type
                } else if (line.startsWith("{")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "{";
                    }
                    // Append line if line is not empty or a single line comment
                } else if (!line.startsWith("--") && !line.equals("")) {
                    sql.append(line);
                } // Check for matching end comment
            } else if (multiLineComment.equals("/*")) {
                if (line.endsWith("*/")) {
                    multiLineComment = null;
                }
                // Check for matching end comment
            } else if (multiLineComment.equals("{")) {
                if (line.endsWith("}")) {
                    multiLineComment = null;
                }
            }

        }
        String regex = "\\s*;\\s*(?=([^']*'[^']*')*[^']*$)";
//        String regex = ";";
        return sql.toString().split(regex);
    }

    /**
     * Parses a file containing sql statements into a String array that contains
     * only the sql statements. Comments and white spaces in the file are not
     * parsed into the String array. Note the file must not contained malformed
     * comments and all sql statements must end with a semi-colon ";" in order
     * for the file to be parsed correctly. The sql statements in the String
     * array will not end with a semi-colon ";".
     *
     * @param sqlFile
     *            - BufferedReader for the file that contains sql statements.
     *
     * @return String array containing the sql statements.
     */
    public static String[] parseSqlFile(BufferedReader sqlFile) throws IOException {
        String line;
        StringBuilder sql = new StringBuilder();
        String multiLineComment = null;

        while ((line = sqlFile.readLine()) != null) {
            line = line.trim();

            // Check for start of multi-line comment
            if (multiLineComment == null) {
                // Check for first multi-line comment type
                if (line.startsWith("/*")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "/*";
                    }
                    // Check for second multi-line comment type
                } else if (line.startsWith("{")) {
                    if (!line.endsWith("}")) {
                        multiLineComment = "{";
                    }
                    // Append line if line is not empty or a single line comment
                } else if (!line.startsWith("--") && !line.equals("")) {
                    sql.append(line);
                } // Check for matching end comment
            } else if (multiLineComment.equals("/*")) {
                if (line.endsWith("*/")) {
                    multiLineComment = null;
                }
                // Check for matching end comment
            } else if (multiLineComment.equals("{")) {
                if (line.endsWith("}")) {
                    multiLineComment = null;
                }
            }

        }

        sqlFile.close();

        String regex = "^([^\"]|\"[^\"]*\")*?(;)";
        String regex2 = "\"\\\\s*;\\\\s*(?=([^']*'[^']*')*[^']*$)\"";
        String regex3 = unquotesSemicolonRegex();

        // TODO: Determine which semicolons are actually end of line terminators and unquotedSemicolonSplit only on those.
        return sql.toString().split(regex3);
    }

    private static final String unquotesSemicolonRegex()
    {
        return "(" + unquotedDelimRegex(";", '\'') + "|" + unquotedDelimRegex(";", '\"') + ")";

    }

    private static final String unquotedDelimRegex(String delim, char quote)
    {
        return "\"\\\\s*" + delim + "\\\\s*(?=([^" + quote + "]*" + quote + "[^" + quote + "]*" + quote + ")*[^" + quote + "]*$)\"";
    }

    /**
     * This will unquotedSemicolonSplit a string on all unquoted semicolons
     * @param line
     * @return an array of {@link String}
     */
    private static final String[] unquotedSemicolonSplit(String line)
    {
        String otherThanQuote = " [^\"\'] ";
        String quotedString = String.format(" \" %s* \" ", otherThanQuote);
        String regex = String.format("(?x) "+ // enable comments, ignore white spaces
                        ";                         "+ // match a semicolon
                        "(?=                       "+ // start positive look ahead
                        "  (?:                     "+ //   start non-capturing group 1
                        "    %s*                   "+ //     match 'otherThanQuote' zero or more times
                        "    %s                    "+ //     match 'quotedString'
                        "  )*                      "+ //   end group 1 and repeat it zero or more times
                        "  %s*                     "+ //   match 'otherThanQuote'
                        "  $                       "+ // match the end of the string
                        ")                         ", // stop positive look ahead
                otherThanQuote, quotedString, otherThanQuote);

        String[] tokens = line.split(regex, -1);
        return tokens;
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        // http://www.java2s.com/Code/Java/File-Input-Output/ConvertInputStreamtoString.htm
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        Boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if(firstLine){
                sb.append(line);
                firstLine = false;
            } else {
                sb.append("\n").append(line);
            }
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile (String filePath) throws IOException {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }

    /**
     * This will attempt to copy the specified resource id information into
     * the specified external file.
     * @param context
     * @param resourceId
     * @param filename
     * @return
     * @throws IOException
     */
    public static final File copyResourceToExternalStoragePrivateFile(
        @NonNull Context context,
        int resourceId,
        @NonNull String filename
    ) throws IOException {
        // Create a path where we will place our private file on external
        // storage.
        File file = new File(context.getExternalFilesDir(null), filename);

        // Very simple code to copy a picture from the application's
        // resource into the external file.  Note that this code does
        // no error checking, and assumes the picture is small (does not
        // try to copy it in chunks).  Note that if external storage is
        // not currently mounted this will silently fail.
        InputStream is = context.getResources().openRawResource(resourceId);
        OutputStream os = new FileOutputStream(file);
        byte[] data = new byte[is.available()];
        is.read(data);
        os.write(data);
        is.close();
        os.close();

        return file;
    }

    /**
     * This is used to get the specified file from the app's external storage directy
     * @param context
     * @param filename
     * @return a {@link File} pointing to the file in the external storage
     */
    public static final File getExternalStoragePrivateFile(
        @NonNull Context context,
        @NonNull String filename
    ) {
        // Create a path where we will place our private file on external
        // storage.
        return new File(context.getExternalFilesDir(null), filename);
    }

    /**
     * This will delete the specified file from the external storage private directory if it exists.
     * @param context
     * @param filename
     */
    void deleteExternalStoragePrivateFile(@NonNull Context context, @NonNull String filename) {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(context.getExternalFilesDir(null), filename);
        if (file != null) {
            file.delete();
        }
    }

    /**
     * This will determine if there is a file with the specified filename located in the private external storage directory
     * @return true if the file exists else it will return false.
     */
    boolean hasExternalStoragePrivateFile(@NonNull Context context, @NonNull String filename) {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(context.getExternalFilesDir(null), filename);
        if (file != null) {
            return file.exists();
        }
        return false;
    }

    /**
     * Call this method to delete any cache created by app
     * @param context context for your application
     */
    public static final void clearApplicationData(Context context) {
        Logger.info( "Clearing app cache");

        File cache = context.getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                File f = new File(appDir, s);
                if(deleteDir(f)) {
                    Logger.info(String.format("*** DELETED -> (%s) ***", f.getAbsolutePath()));
                }
            }
        }
    }

    /**
     * This is used to recursively delete a specified directory
     * @param dir
     * @return true if the dirctory was successfully deleted
     */
    public static final boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}