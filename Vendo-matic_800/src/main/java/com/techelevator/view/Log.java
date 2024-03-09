package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Log {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void logWriter(String transactionType, double money,double balance){
        String date = LocalDate.now() + " " + LocalTime.now(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("h:mm:ss a"));
        String auditPath = "./log.txt";
        File logFile = new File(auditPath);
        String message = date + " " + transactionType + " $" + df.format(money) + " $" + df.format(balance);

        try (PrintWriter log = new PrintWriter(new FileOutputStream(logFile, true))){
            log.println(message);
        } catch (FileNotFoundException e){
            System.out.println("*** Unable to open log file: " + logFile.getAbsolutePath());
        }
    }
}
