public class DocumentProxy implements DocumentInterface {
    private Document document;

    public DocumentProxy(Document document) {
        this.document = document;
    }

    @Override
    public int getId() {
        return document.getId();
    }

    @Override
    public String getContent() {
        return document.getContent();
    }

    @Override
    public long getCreationDate() {
        return document.getCreationDate();
    }
}
