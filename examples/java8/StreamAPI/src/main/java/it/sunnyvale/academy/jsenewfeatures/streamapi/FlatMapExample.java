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
        
        List<String> italianCities = Arrays.asList("Torino", "Milano", "Roma", "Firenze", "Bologna", "Alessandria");
        List<String> englishCities = Arrays.asList("London", "Leeds", "Manchester", "Slough", "Birmingham");
        List<String> frenchCities = Arrays.asList("Paris", "Lyon", "Cannes", "Marseille", "Nice");
        List<String> egytpianCities = Arrays.asList("Cairo", "Alessandria");

        List<List<String>> countries = new ArrayList<List<String>>();
        countries.add(italianCities);
        countries.add(englishCities);
        countries.add(frenchCities);
        countries.add(egytpianCities);

        System.out.println(String.format("Before flatMap: %s", countries));

        List<String> flatMapList = countries.stream()
            .flatMap(t -> t.stream())
            // Remove duplicated cities (Alessandria)
            .distinct()
            //.peek(System.out::println)
            .collect(Collectors.toList()); 

        System.out.println(String.format("After flatMap: %s",flatMapList));

    }
}