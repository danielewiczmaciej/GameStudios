package com.example.rpggame;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
}
