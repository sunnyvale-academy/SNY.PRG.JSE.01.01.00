package it.sunnyvale.academy.jsenewfeatures.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FlatMapExample
 */
public class FlatMapExample {

    public static void main(String[] args) {
        
        List<String> italianCities = Arrays.asList("Torino", "Milano", "Roma", "Firenze", "Bologna");
        List<String> englishCities = Arrays.asList("London", "Leeds", "Manchester", "Slough", "Birmingham");
        List<String> frenchCities = Arrays.asList("Paris", "Lyon", "Cannes", "Marseille", "Nice");

        List<List<String>> countries = new ArrayList<List<String>>();
        countries.add(italianCities);
        countries.add(englishCities);
        countries.add(frenchCities);

        System.out.println(String.format("Before flatMap: %s", countries));

        List<String> flatMapList = countries.stream()
            .flatMap(t -> t.stream())
            .collect(Collectors.toList()); 

        System.out.println(String.format("After flatMap: %s",flatMapList));

    }
}