import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentListRegistration {
    public static void main(String[] args) {
        System.out.println("--- Course Registration List ---");
        CopyOnWriteArrayList<String> registrationList = new CopyOnWriteArrayList<>();

        // adding at least 50 records inside loop
        for (int i = 1; i <= 52; i++) {
            registrationList.add("REG-" + String.format("%03d", i) + " (STIWK3014)");
        }

        // printing all records
        for (String record : registrationList) {
            System.out.println(record);
        }

        // showing size and checking existence
        System.out.println("\nTotal requests size: " + registrationList.size());

        String search = "REG-010 (STIWK3014)";
        System.out.println("Is REG-010 exist? " + registrationList.contains(search));
        System.out.println("Record at index 10: " + registrationList.get(10));
    }
}