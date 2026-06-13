import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentSetCourse {
    public static void main(String[] args) {
        System.out.println("--- Unique Course Set ---");
        Set<String> courseCodes = ConcurrentHashMap.newKeySet();

        // adding courses codes
        courseCodes.add("STIW3014");
        courseCodes.add("STIA1113");
        courseCodes.add("STID3014");
        courseCodes.add("STIK2014");

        // testing duplicate input here
        System.out.println("Try add duplicate code...");
        boolean isAdded = courseCodes.add("STIW3014");
        System.out.println("Success adding duplicate item? " + isAdded);

        System.out.println("Unique set codes: " + courseCodes);
        System.out.println("Contains STID3014: " + courseCodes.contains("STID3014"));

        // removing one course code
        courseCodes.remove("STIA1113");
        System.out.println("After remove one code: " + courseCodes);
        System.out.println("Current set size: " + courseCodes.size());
    }
}