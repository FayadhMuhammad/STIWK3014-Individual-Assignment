import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class UniversityRegistrationSystem {

    // global map for task 3 and task 5
    private static final ConcurrentHashMap<String, String> studentMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("=== University Registration System ===");
        System.out.println("Processing all tasks...\n");

        runTask1();
        runTask2();
        runTask3();
        runTask4();
        runTask5();

        System.out.println("=====================================");
    }

    // task 1: concurrent list testing
    private static void runTask1() {
        System.out.println("--- Task 1: List ---");
        CopyOnWriteArrayList<String> registrationList = new CopyOnWriteArrayList<>();

        // loop for adding 50+ records
        for (int i = 1; i <= 52; i++) {
            registrationList.add("REG-" + String.format("%03d", i) + " (STIWK3014)");
        }

        // printing all data
        for (String record : registrationList) {
            System.out.println(record);
        }

        // check total and some methods
        System.out.println("\nTotal requests: " + registrationList.size());

        String search = "REG-010 (STIWK3014)";
        System.out.println("Check existence: " + registrationList.contains(search));
        System.out.println("Record at index 10: " + registrationList.get(10));
        System.out.println();
    }

    // task 2: set implementation
    private static void runTask2() {
        System.out.println("--- Task 2: Set ---");
        Set<String> courseCodes = ConcurrentHashMap.newKeySet();

        // add some courses
        courseCodes.add("STIW3014");
        courseCodes.add("STIA1113");
        courseCodes.add("STID3014");
        courseCodes.add("STIK2014");

        // try duplicate here to see if its rejected
        System.out.println("Try adding duplicate code...");
        boolean isAdded = courseCodes.add("STIW3014");
        System.out.println("Success adding duplicate? " + isAdded);

        System.out.println("Unique codes: " + courseCodes);
        System.out.println("Check STID3014: " + courseCodes.contains("STID3014"));

        // remove one item
        courseCodes.remove("STIA1113");
        System.out.println("After remove: " + courseCodes);
        System.out.println();
    }

    // task 3: map with student records
    private static void runTask3() {
        System.out.println("--- Task 3: Map ---");

        // mock courses for loop populate data
        String[] mockCourses = {"STIW3014", "STID3014", "STIK2014", "STIA1113"};
        for (int i = 1; i <= 55; i++) {
            String studentId = "ID" + String.format("%03d", i);
            String course = mockCourses[i % mockCourses.length];
            studentMap.put(studentId, course);
        }

        // get and update testing
        String target = "ID015";
        System.out.println("Original course for ID015: " + studentMap.get(target));

        studentMap.replace(target, "STIW3014"); // update data
        System.out.println("Updated course for ID015: " + studentMap.get(target));

        // contains and remove method
        System.out.println("Check ID020: " + studentMap.containsKey("ID020"));
        studentMap.remove("ID001");
        System.out.println("Remaining size: " + studentMap.size());

        // print all mapping
        System.out.println("\nAll student courses:");
        studentMap.forEach((k, v) -> System.out.println(k + " -> " + v));
        System.out.println();
    }

    // task 4: queue processing
    private static void runTask4() {
        System.out.println("--- Task 4: Queue ---");
        ConcurrentLinkedQueue<String> registrationQueue = new ConcurrentLinkedQueue<>();

        // queueing requests
        registrationQueue.offer("Req-StudentA-Add");
        registrationQueue.offer("Req-StudentB-Drop");
        registrationQueue.offer("Req-StudentC-Add");

        System.out.println("Head of queue: " + registrationQueue.peek());

        // loop untill queue empty
        System.out.println("Processing queue elements:");
        while (!registrationQueue.isEmpty()) {
            System.out.println("Handled: " + registrationQueue.poll());
        }
        System.out.println();
    }

    // task 5: parallel stream analyze
    private static void runTask5() {
        System.out.println("--- Task 5: Parallel Stream ---");

        System.out.println("Grouping data and logging thread names:");
        Map<String, Long> courseCounts = studentMap.entrySet().parallelStream()
                .peek(entry -> {
                    // tracking active thread for logs
                    System.out.println("Thread [" + Thread.currentThread().getName()
                            + "] processing: " + entry.getKey());
                })
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));

        // print results
        System.out.println("\nCourse Distribution Counts:");
        courseCounts.forEach((course, count) ->
                System.out.println(course + " total: " + count)
        );

        // find most popular course using stream max
        Optional<Map.Entry<String, Long>> popular = courseCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (popular.isPresent()) {
            System.out.println("\nMost Popular Course: " + popular.get().getKey()
                    + " (" + popular.get().getValue() + " students)");
        }
    }
}