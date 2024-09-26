public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User("jeff");
        User admin = new User("admin");
        Document document = new Document("shrek 5 script");
        library.addDocument(document);
        try {
            DocumentInterface doc = library.getDocument(user, document.getId());
            System.out.println(doc.getContent());
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        }

        AccessControlService.getInstance().giveAccess(admin, document);

        try {
            DocumentInterface doc = library.getDocument(admin, document.getId());
            System.out.println(doc.getContent());
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        }
    }
}
