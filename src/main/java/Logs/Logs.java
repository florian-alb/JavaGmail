package Logs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logs {
    private static final String logFile = new File("").getAbsolutePath() + "/src/Logs/logs.txt";


    public static void readLogs() throws IOException {
        final FileReader fileReader = new FileReader(logFile);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
    }

    public static void writeLog(Exception exception) {
        try {

            File fileOfLog = new File(logFile);

            if (!fileOfLog.exists()) {
                fileOfLog.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(fileOfLog, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            printWriter.print("[" + timestamp + "] ");
            exception.printStackTrace(printWriter);
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}