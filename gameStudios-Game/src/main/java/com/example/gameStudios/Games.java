package com.example.gameStudios;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.example.gameStudios.dto.GameDTO;
import com.example.gameStudios.entity.Game;
import com.example.gameStudios.entity.Studio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Games {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Games.class, args);

        ConsoleApplicationRunner consoleApplicationRunner = context.getBean(ConsoleApplicationRunner.class);
        consoleApplicationRunner.run(args);
        Set<Studio> studios = new HashSet<>();

        Studio CDProjectRed = Studio.builder()
                .name("CD Projekt Red")
                .yearOfFoundation(2002)
                .games(new HashSet<>())
                .build();
        Game witcher = Game.builder().name("Witcher").releaseYear(2007).studio(CDProjectRed).build();
        Game witcher3 = Game.builder().name("Witcher 3").releaseYear(2015).studio(CDProjectRed).build();
        Studio rockstar = Studio.builder()
                .name("Rockstar")
                .yearOfFoundation(1998)
                .games(new HashSet<>())
                .build();

        Game gta = Game.builder().name("Grand Theft Auto").releaseYear(1997).studio(rockstar).build();
        Game redDead = Game.builder().name("Red Dead Redemption").releaseYear(2010).studio(rockstar).build();

        Studio twoK = Studio.builder()
                .name("2K")
                .yearOfFoundation(1999)
                .games(new HashSet<>())
                .build();
        Game nba = Game.builder().name("NBA 2K").releaseYear(1999).studio(twoK).build();
        Game wwe = Game.builder().name("WWE 2K").releaseYear(2000).studio(twoK).build();


        studios.add(CDProjectRed);
        studios.add(rockstar);
        studios.add(twoK);

        // Print categories and elements
        studios.forEach(Studio -> {
            System.out.println("Studio: " + Studio.getName() + ", Year of Foundation: " + Studio.getYearOfFoundation());
            Studio.getGames().forEach(game -> System.out.println("  Game: " + game.getName() + ", Release Year: " + game.getReleaseYear()));
        });
        // 3
        System.out.println("3");
        Set<Game> allGames = studios.stream()
                .flatMap(studio -> studio.getGames().stream())
                .collect(Collectors.toSet());

        allGames.forEach(game -> {
            System.out.println("Game: " + game.getName() + ", Release Year: " + game.getReleaseYear());
        });
        // 4
        System.out.println("4");
        studios.stream()
                .flatMap(studio -> studio.getGames().stream())
                .filter(game -> game.getReleaseYear() >= 2007) // Filter based on the release year
                .sorted((game1, game2) -> game1.getName().compareTo(game2.getName())) // Sort alphabetically by game name
                .forEach(game -> {
                    System.out.println("Game: " + game.getName() + ", Release Year: " + game.getReleaseYear());
                });

        // 5
        System.out.println("5");
        List<GameDTO> sortedDTOs = studios.stream()
                .flatMap(studio -> studio.getGames().stream())
                .map(game -> new GameDTO(game.getName(), game.getReleaseYear()))
                .sorted()
                .toList();

        // Step 2: Print the sorted DTOs
        sortedDTOs.forEach(dto -> {
            System.out.println("Game: " + dto.getName() + ", Release Year: " + dto.getReleaseYear());
        });

        //6
        System.out.println("6");
        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/serialized_data.ser"))) {
            oos.writeObject(studios);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/serialized_data.ser"))){
            Set<Studio> deserializedStudios = (Set<Studio>) ois.readObject();

            // Print deserialized data
            deserializedStudios.forEach(studio -> {
                System.out.println("Studio: " + studio.getName() + ", Year of Foundation: " + studio.getYearOfFoundation());
                studio.getGames().forEach(game -> System.out.println("  Game: " + game.getName() + ", Foundation Year: " + game.getReleaseYear()));
            });
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 7
        System.out.println("7");
                // Create a custom ForkJoinPool with different pool sizes (e.g., 2 and 4)
        ForkJoinPool customThreadPool1 = new ForkJoinPool(2); // Adjust pool size as needed
        //ForkJoinPool customThreadPool2 = new ForkJoinPool(4); // Adjust pool size as needed

        // Simulate workload for each category using parallel streams and custom thread pools
        studios.parallelStream()
                .forEach(Studio -> {
                    ForkJoinTask<?> task1 = customThreadPool1.submit(() -> simulateWorkload(Studio));
                    //ForkJoinTask<?> task2 = customThreadPool2.submit(() -> simulateWorkload(Studio));

                    // Wait for the tasks to complete
                    try {
                        task1.get();
                        //task2.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        // Shutdown the custom thread pools
        customThreadPool1.shutdown();
        //customThreadPool2.shutdown();

        // Wait for the thread pools to terminate
        try {
            customThreadPool1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            //customThreadPool2.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
}

    }

    private static void simulateWorkload(Studio Studio) {
        try {
            for (Game game : Studio.getGames()) {
                System.out.println("Processing " + Studio.getName() + "'s " + game.getName() + "...");
                // Simulate a workload with a delay (e.g., 1 second)
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
