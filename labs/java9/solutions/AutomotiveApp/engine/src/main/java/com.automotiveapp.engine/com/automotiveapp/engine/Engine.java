package com.automotiveapp.engine;

import org.apache.log4j.Logger;

/**
 * Engine
 */
public class Engine {

    Logger logger = Logger.getLogger(Engine.class);


    public Engine() {
        logger.info("A new engine has been created");
    }

    public void start() {
        logger.info("Engine is starting");
    }
}