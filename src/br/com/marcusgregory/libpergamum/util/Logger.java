package br.com.marcusgregory.libpergamum.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gregory
 */
public class Logger {

    // private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    // private static PrintStream out = new PrintStream(baos);;
    private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private final static Date DATE = new Date();
    private static File file;

    public static void log(String log) {
        if (!log.isEmpty()) {
            DATE.setTime(System.currentTimeMillis());
            String message = String.format("%s [%s] LOG: %s() - %s",
                    DATE_FORMAT.format(DATE),
                    StackTraceInfo.getInvokingClassName(), StackTraceInfo.getInvokingMethodName(), log);
            //out.println(message);
            System.out.println(message);
            writeToFile(message);
        }

    }

    public static void logError(Throwable throwable) {
        DATE.setTime(System.currentTimeMillis());
        String message = String.format("%s [%s] LOG/ERR: %s() - %s",
                DATE_FORMAT.format(DATE),
                StackTraceInfo.getInvokingClassName(), StackTraceInfo.getInvokingMethodName(), StackTraceUtil.getStackTrace(throwable));
        //out.println(message);
        System.err.println(message);
        writeToFile(message);
    }

    public static void setFile(File file) {
        Logger.file = file;
    }

    private static void writeToFile(String message) {
        if (!(file == null)) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                bw.write(message);
                bw.newLine();
                bw.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    /* public static byte[] getLog() {
        return baos.toByteArray();
    }
     */
}
