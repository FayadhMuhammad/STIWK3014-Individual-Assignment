import java.util.*;
import java.util.stream.Collectors;

public class ParallelStream {
    public static void main(String[] args) {
        System.out.println("--- Parallel Stream ---");

        Map<String, String> studentMap = new HashMap<>();
        String[] mockCourses = {"STIW3014", "STID3014", "STIK2014", "STIA1113"};
        for (int i = 1; i <= 55; i++) {
            studentMap.put("ID" + String.format("%03d", i), mockCourses[i % mockCourses.length]);
        }

        System.out.println("Grouping data and printing worker threads names:");

        Map<String, Long> courseCounts = studentMap.entrySet().parallelStream()
                .peek(entry -> {
                    System.out.println("Thread [" + Thread.currentThread().getName()
                            + "] is processing: " + entry.getKey());
                })
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));

        System.out.println("\nFinal Course Distribution Counts:");
        courseCounts.forEach((course, count) ->
                System.out.println("Course " + course + " total: " + count)
        );

        Optional<Map.Entry<String, Long>> popular = courseCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        if (popular.isPresent()) {
            System.out.println("\nMost Popular Course: " + popular.get().getKey()
                    + " (" + popular.get().getValue() + " students)");
        }
    }
}