import java.util.ArrayList;
import java.util.List;

public class Recommendation implements Prototype {
    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience, List<Book> books) {
        this.targetAudience = targetAudience;
        this.books = books;
    }

    @Override
    public Recommendation clone() {
        Recommendation clone = new Recommendation(this.targetAudience, this.books);
        List<Book> clonedBooks = new ArrayList<>();
        for (Book book : this.books) {
            clonedBooks.add(book.clone());
        }
        clone.setBooks(clonedBooks);
        return clone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public void print() {
        System.out.println("Recommendation for " + targetAudience + ":");
        for (Book book : books) {
            book.print();
        }
    }
}
