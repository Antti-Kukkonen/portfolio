public class Book implements Prototype {
    private final String author;
    private final String title;
    private final String genre;
    private final int publicationYear;

    public Book(String author, String title, String genre, int publicationYear) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    @Override
    public Book clone() {
        return new Book(author, title, genre, publicationYear);
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void print() {
        System.out.println(title + " by " + author + " (" + publicationYear + ")");
    }
}
