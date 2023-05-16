package Logs;

import mailBox.Email;
import mailBox.MailBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logs {
    private static final String logFile = new File("").getAbsolutePath() + "/src/Logs/logs.txt";

    public static void writeLog(Exception exception) {
        try {
            PrintWriter printWriter = printLogFormat();
            exception.printStackTrace(printWriter);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeLog(String message, Email email, MailBox mailBox){
        try {

            PrintWriter printWriter = printLogFormat();
            printWriter.println(message + email + mailBox);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static PrintWriter printLogFormat() throws IOException {
        File fileOfLog = new File(logFile);

        if (!fileOfLog.exists()) {
            fileOfLog.getParentFile().mkdirs();
            fileOfLog.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(fileOfLog, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        printWriter.print("[" + timestamp + "] ");

        return printWriter;
    }

}