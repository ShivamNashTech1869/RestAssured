package Files;

public class Payload {

    public static String addPlace() {
        return "{\n" +
                "    \"name\": \"Shivam Singh\",\n" +
                "    \"job\": \"Automation\"\n" +
                "}";
    }
    public static String addThisPlace(String userName , String userJob){
        return "{\n" +
                "    \"name\": \""+userName+"\",\n" +
                "    \"job\": \""+userJob+"\"\n" +
                "}";
    }
}
