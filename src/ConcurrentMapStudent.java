import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapStudent {
    // keeping map public static so other processes can reuse if needed
    public static ConcurrentHashMap<String, String> studentMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("--- Student Registration Map ---");

        // loop for populating more than 50 mock data
        String[] mockCourses = {"STIW3014", "STID3014", "STIK2014", "STIA1113"};
        for (int i = 1; i <= 55; i++) {
            String studentId = "ID" + String.format("%03d", i);
            String course = mockCourses[i % mockCourses.length];
            studentMap.put(studentId, course);
        }

        // test get and replace method
        String target = "ID015";
        System.out.println("Original course for ID015: " + studentMap.get(target));

        studentMap.replace(target, "STIW3014"); // updating course data
        System.out.println("Updated course for ID015: " + studentMap.get(target));

        // testing contains and remove mapping
        System.out.println("Is ID020 inside map? " + studentMap.containsKey("ID020"));
        studentMap.remove("ID001");
        System.out.println("Remaining map size: " + studentMap.size());

        // displaying all registrations mapping
        System.out.println("\nAll active registrations:");
        studentMap.forEach((k, v) -> System.out.println("Student " + k + " -> " + v));
    }
}