public class Document implements DocumentInterface {
    static int idCounter = 0;
    private final int id;
    private final long date;
    private final String content;

    Document(String content) {
        this.id = idCounter++;
        this.date = System.currentTimeMillis();
        this.content = content;
    }
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public long getCreationDate() {
        return this.date;
    }
}
