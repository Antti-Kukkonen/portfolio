public class Main {
    public static void main(String[] args) {
        Directory root = new Directory("/");
        Directory etc = new Directory("etc");
        Directory home = new Directory("home");
        Directory user = new Directory("user");
        root.addChild(etc);
        root.addChild(home);
        home.addChild(user);

        File file1 = new File("abc", new byte[32]);
        etc.addChild(file1);
        File file2 = new File("abcde", new byte[64]);
        etc.addChild(file2);
        File file3 = new File("a", new byte[128]);
        user.addChild(file3);
        File file4 = new File("cd", new byte[32]);
        home.addChild(file4);

        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        sizeCalculator.printSize();

        SearchVisitor searchVisitor = new SearchVisitor("taxes");
        root.accept(searchVisitor);
        searchVisitor.printMatches();

        SearchVisitor searchVisitor2 = new SearchVisitor("b");
        root.accept(searchVisitor2);
        searchVisitor2.printMatches();
    }
}
