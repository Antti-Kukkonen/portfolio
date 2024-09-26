public class Main {
    public static void main(String[] args) {
        CalendarToNewDateAdapter calendar = new CalendarToNewDateAdapter();
        System.out.println("Initial date:");
        System.out.println(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());
        calendar.advanceDays(10);
        System.out.println("After advancing 10 days:");
        System.out.println(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay());
    }
}
