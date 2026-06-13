import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueueRequest {
    public static void main(String[] args) {
        System.out.println("--- Registration Request Queue ---");
        ConcurrentLinkedQueue<String> registrationQueue = new ConcurrentLinkedQueue<>();

        // adding requests to queue line
        registrationQueue.offer("Req-StudentA-Add");
        registrationQueue.offer("Req-StudentB-Drop");
        registrationQueue.offer("Req-StudentC-Add");

        System.out.println("First request waiting (peek): " + registrationQueue.peek());

        // processing untill queue is fully empty
        System.out.println("Processing queue elements sequentially:");
        while (!registrationQueue.isEmpty()) {
            System.out.println("Handled and removed: " + registrationQueue.poll());
        }
    }
}