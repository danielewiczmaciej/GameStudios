package com.example.rpggame;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class RPGGame {
    public static void main(String[] args) {
        Set<Hero> heroes = new HashSet<>();
        Hero hero1 = Hero.builder()
                .name("Warrior")
                .level(10)
                .skills(new HashSet<>())
                .build();
        hero1.getSkills().add(Skill.builder().name("Slash").damage(30).build());
        hero1.getSkills().add(Skill.builder().name("Block").damage(0).build());

        Hero hero2 = Hero.builder()
                .name("Mage")
                .level(12)
                .skills(new HashSet<>())
                .build();
        hero2.getSkills().add(Skill.builder().name("Fireball").damage(40).build());
        hero2.getSkills().add(Skill.builder().name("Teleport").damage(0).build());

        heroes.add(hero1);
        heroes.add(hero2);

        // Print categories and elements
        heroes.forEach(hero -> {
            System.out.println("Hero: " + hero.getName() + ", Level: " + hero.getLevel());
            hero.getSkills().forEach(skill -> System.out.println("  Skill: " + skill.getName() + ", Damage: " + skill.getDamage()));
        });


        // Create a custom ForkJoinPool with different pool sizes (e.g., 2 and 4)
        ForkJoinPool customThreadPool1 = new ForkJoinPool(2); // Adjust pool size as needed
        ForkJoinPool customThreadPool2 = new ForkJoinPool(4); // Adjust pool size as needed

        // Simulate workload for each category using parallel streams and custom thread pools
        heroes.parallelStream()
                .forEach(hero -> {
                    ForkJoinTask<?> task1 = customThreadPool1.submit(() -> simulateWorkload(hero));
                    ForkJoinTask<?> task2 = customThreadPool2.submit(() -> simulateWorkload(hero));

                    // Wait for the tasks to complete
                    try {
                        task1.get();
                        task2.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        // Shutdown the custom thread pools
        customThreadPool1.shutdown();
        customThreadPool2.shutdown();

        // Wait for the thread pools to terminate
        try {
            customThreadPool1.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            customThreadPool2.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
}

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/serialized_data.ser"))) {
            oos.writeObject(heroes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/serialized_data.ser"))){
            Set<Hero> deserializedHeroes = (Set<Hero>) ois.readObject();

            // Print deserialized data
            deserializedHeroes.forEach(hero -> {
                System.out.println("Hero: " + hero.getName() + ", Level: " + hero.getLevel());
                hero.getSkills().forEach(skill -> System.out.println("  Skill: " + skill.getName() + ", Damage: " + skill.getDamage()));
            });
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void simulateWorkload(Hero hero) {
        try {
            for (Skill skill : hero.getSkills()) {
                System.out.println("Processing " + hero.getName() + "'s " + skill.getName() + "...");
                // Simulate a workload with a delay (e.g., 1 second)
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
