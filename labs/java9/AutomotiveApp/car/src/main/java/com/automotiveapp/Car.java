package com.automotiveapp;

import org.apache.log4j.Logger;

/**
 * Car
 */
public class Car {

    private Engine engine;
    Logger logger = Logger.getLogger(Engine.class);

    public Car(){
        engine = new Engine();
    }

    public void move(){
        logger.info("Car is moving...");
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.start();
    }

    public void start(){
        System.out.println("Engine starting...");
        engine.start();
        this.move();
    }
}