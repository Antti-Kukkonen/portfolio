public class Main {
    public static void main(String[] args) {
        APIFacade apiFacade = new APIFacade();
        try {
            String url = "https://api.fxratesapi.com/latest";
            String attribute = "date";
            String value = apiFacade.getAttributeValueFromJson(url, attribute);
            System.out.println(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            String url = "https://api.chucknorris.io/jokes/random";
            String attribute = "value";
            String value = apiFacade.getAttributeValueFromJson(url, attribute);
            System.out.println(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
