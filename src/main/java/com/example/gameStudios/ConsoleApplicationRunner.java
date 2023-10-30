package com.example.gameStudios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApplicationRunner implements CommandLineRunner {

    private final GameService gameService; // Inject GameService
    private final StudioService studioService; // Inject StudioService
    private final CategoryService categoryService;

    @Autowired
    public ConsoleApplicationRunner(GameService gameService, StudioService studioService, CategoryService categoryService) {
        this.gameService = gameService;
        this.studioService = studioService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Available Commands:");
            System.out.println("1. List Categories");
            System.out.println("2. List Elements");
            System.out.println("3. Add New Studio");
            System.out.println("4. Add New Game");
            System.out.println("5. Delete Element");
            System.out.println("6. Exit");

            System.out.print("Enter a command (1-5): ");
            int command = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (command) {
                case 1:
                    listCategories();
                    break;
                case 2:
                    listElements();
                    break;
                case 3:
                    addStudio();
                    break;
                case 4:
                    addGame();
                    break;
                case 5:
                    deleteElement();
                    break;
                case 6:
                    System.out.println("Exiting application.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid command. Please enter a valid command (1-5).");
            }
        }
    }

    private void listCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            System.out.println("Categories:");
            categories.forEach(category -> System.out.println(category.getName()));
        }
    }

    private void listElements() {
        System.out.println("Game Studios and Games:");
        List<Studio> studios = studioService.getAllStudios();
        if (studios.isEmpty()) {
            System.out.println("No studios found.");
        } else {
            studios.forEach(studio -> {
                System.out.println("Studio: " + studio.getName());
                studio.getGames().forEach(game -> {
                    System.out.println("  - Game: " + game.getName() + ", Release Year: " + game.getReleaseYear());
                    if (game.getCategory() != null) {
                        System.out.println("    - Category: " + game.getCategory().getName());
                    }
                });
            });
        }
    }

    private void addStudio() {
        System.out.print("Enter the name of the studio: ");
        Scanner scanner = new Scanner(System.in);
        String studioName = scanner.nextLine();
        System.out.print("Enter the year of Foundation: ");
        int yearOfFoundation = Integer.parseInt(scanner.nextLine());

        Studio studio = studioService.createStudio(studioName, yearOfFoundation);

        if (studio != null) {
            System.out.println("Studio '" + studioName + "' created.");
        } else {
            System.out.println("Failed to create the studio.");
        }
    }

    private void addGame() {
        System.out.print("Enter the name of the game: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Enter the year of release: ");
        String releaseYear = scanner.nextLine();
        System.out.print("Enter the name of Studio: ");
        String studioName = scanner.nextLine();
        System.out.print("Enter the category: ");
        String category = scanner.nextLine();


        Game game = gameService.createGame(name, Integer.parseInt(releaseYear), studioService.getStudioIdByName(studioName), categoryService.getCategoryIdByName(category));

        if (game != null) {
            System.out.println("Game '" + studioName + "' created.");
        } else {
            System.out.println("Failed to create the game.");
        }
    }

    private void deleteElement() {
        System.out.print("Enter the name of the studio to delete: ");
        Scanner scanner = new Scanner(System.in);
        String studioName = scanner.nextLine();

        boolean deleted = studioService.deleteStudio(studioName);

        if (deleted) {
            System.out.println("Studio '" + studioName + "' deleted.");
        } else {
            System.out.println("Studio '" + studioName + "' not found or could not be deleted.");
        }
    }
}
