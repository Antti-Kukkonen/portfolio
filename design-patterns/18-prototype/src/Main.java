import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create some books and recommendations
        List<Book> fantasy_books = new ArrayList<>();
        List<Book> childrens_books = new ArrayList<>();
        fantasy_books.add(new Book("J.K. Rowling", "Harry Potter and the Philosopher's Stone", "Fantasy", 1997));
        fantasy_books.add(new Book("J.K. Rowling", "Harry Potter and the Chamber of Secrets", "Fantasy", 1998));
        childrens_books.add(new Book("Astrid Lindgren", "Pippi Longstocking", "Children's literature", 1945));
        childrens_books.add(new Book("Astrid Lindgren", "Ronia, the Robber's Daughter", "Children's literature", 1981));

        Recommendation fantasy_recommendation = new Recommendation("Fantasy fans", fantasy_books);
        Recommendation childrens_recommendation = new Recommendation("Children", childrens_books);
        List<Recommendation> recommendations = new ArrayList<>();
        recommendations.add(fantasy_recommendation);
        recommendations.add(childrens_recommendation);

        Scanner scanner = new Scanner(System.in);
        String input = "";
        int index;
        // Funny switch statement and epic code!! ^_^ (shoot me now)
        while (!Objects.equals(input, "exit")) {
            System.out.println("Enter a command (recommendations, clone, add, remove, target audience, exit):");
            input = scanner.nextLine();
            switch (input) {
                case "recommendations":
                    for (Recommendation recommendation : recommendations) {
                        recommendation.print();
                        System.out.println();
                    }
                    break;
                case "clone":
                    System.out.println("Enter the index of the recommendation you want to clone:");
                    for (int i = 0; i < recommendations.size(); i++) {
                        System.out.println(i + ": " + recommendations.get(i).getTargetAudience());
                    }
                    index = scanner.nextInt();
                    scanner.nextLine();
                    if (index >= 0 && index < recommendations.size()) {
                        Recommendation clone = recommendations.get(index).clone();
                        recommendations.add(clone);
                        System.out.println("Successfully cloned the recommendation for " + clone.getTargetAudience());
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "add":
                    System.out.println("Enter the index of the recommendation you want to add a book to:");
                    for (int i = 0; i < recommendations.size(); i++) {
                        System.out.println(i + ": " + recommendations.get(i).getTargetAudience());
                    }
                    index = scanner.nextInt();
                    scanner.nextLine();
                    if (index >= 0 && index < recommendations.size()) {
                        System.out.println("Enter the author of the book:");
                        String author = scanner.nextLine();
                        System.out.println("Enter the title of the book:");
                        String title = scanner.nextLine();
                        System.out.println("Enter the genre of the book:");
                        String genre = scanner.nextLine();
                        System.out.println("Enter the publication year of the book:");
                        int publicationYear = scanner.nextInt();
                        scanner.nextLine();
                        Book book = new Book(author, title, genre, publicationYear);
                        recommendations.get(index).addBook(book);
                        System.out.println("Successfully added " + book.getTitle() + " to the recommendation for " + recommendations.get(index).getTargetAudience());
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "remove":
                    System.out.println("Enter the index of the recommendation you want to remove a book from:");
                    for (int i = 0; i < recommendations.size(); i++) {
                        System.out.println(i + ": " + recommendations.get(i).getTargetAudience());
                    }
                    index = scanner.nextInt();
                    scanner.nextLine();
                    if (index >= 0 && index < recommendations.size()) {
                        System.out.println("Enter the index of the book you want to remove:");
                        Recommendation recommendation = recommendations.get(index);
                        List<Book> books = recommendation.getBooks();
                        for (int i = 0; i < books.size(); i++) {
                            System.out.println(i + ": " + books.get(i).getTitle());
                        }
                        int bookIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (bookIndex >= 0 && bookIndex < books.size()) {
                            recommendation.removeBook(books.get(bookIndex));
                            System.out.println("Successfully removed the book from the recommendation");
                        } else {
                            System.out.println("Invalid index");
                        }
                    } else {
                        System.out.println("Invalid index");
                    }
                    break;
                case "target audience":
                    System.out.println("Enter the index of the recommendation you want to change the target audience of:");
                    for (int i = 0; i < recommendations.size(); i++) {
                        System.out.println(i + ": " + recommendations.get(i).getTargetAudience());
                    }
                    index = scanner.nextInt();
                    scanner.nextLine();
                    if (index >= 0 && index < recommendations.size()) {
                        System.out.println("Enter the new target audience:");
                        String targetAudience = scanner.nextLine();
                        recommendations.get(index).setTargetAudience(targetAudience);
                        System.out.println("Successfully changed the target audience of the recommendation");
                    } else {
                        System.out.println("Invalid index");
                    }
                case "exit":
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
