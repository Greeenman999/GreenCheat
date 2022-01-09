package de.greenman999.greencheat.pluginsystem;

import de.greenman999.greencheat.pluginsystem.interfaces.MessageLogger;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ConsoleMessageLogger implements MessageLogger {

    private Logger logger;
    private FileHandler fileHandler;

    public ConsoleMessageLogger() {

    }

    @Override
    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    @Override
    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    @Override
    public void info2LogFile(String message) {

    }
}
