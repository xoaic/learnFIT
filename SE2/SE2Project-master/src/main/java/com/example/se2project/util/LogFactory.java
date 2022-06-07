package com.example.se2project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogFactory {
    public static Logger getLogger() {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
        return logger;
    }
}